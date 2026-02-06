package com.example.dailyquiz2.presentation.Start_Quiz

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz2.data.data_repository.HistoryQuizRepository
import com.example.dailyquiz2.domain.use_case.LogicsUse
import com.example.dailyquiz2.presentation.History_Quiz.historyModels
import com.example.dailyquiz2.presentation.History_Quiz.resultHistoryModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

sealed class QuizScreenState {

    object Start : QuizScreenState()

    object Loading : QuizScreenState()
    data class Progress(val currentIndex: Int) : QuizScreenState()
    data class Result(val correctCount: Int) : QuizScreenState()

}

@HiltViewModel
class Start_quiz_ViewModel @Inject constructor(

    private val Logics_use: LogicsUse,
    private val historyRepository: HistoryQuizRepository


): ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiStateQuizProgress())
    val uiState = _uiState.asStateFlow()
    val historyID = UUID.randomUUID().toString()
  //  val uiState: StateFlow<QuizUiState_QuizProgress> = _uiState


    @RequiresApi(Build.VERSION_CODES.O)
    fun onQuizFinished(correct: Int, total: Int) {
        val now = ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        val month = now.month.getDisplayName(TextStyle.FULL, Locale("ru", "RU"))


        val history = historyModels(
            stars = correct,
            total = total,
            month = month,
            monthNumber = now.dayOfMonth,
            time = now.format(DateTimeFormatter.ofPattern("HH:mm")),
            id_history_model = historyID,

        )

        viewModelScope.launch {
            historyRepository.saveHistory(history)
        }

    }
    fun navigationQuizStart() {
       _uiState.update { state ->
           state.copy(
              // screenState = QuizScreenState.Start,
               currentIndex = 0,
                selectedAnswerIndex = null,
                answersHistory = emptyList()


            )

       }
    }
    fun navigation_start() {
        _uiState.update { state->
            state.copy(
                screenState = QuizScreenState.Start,
                currentIndex = 0,
                selectedAnswerIndex = null,
                answersHistory = emptyList()
            )
        }
    }

    fun onAnswerSelected(index: Int) {
        Log.d("QuizDebug", "Answer selected: $index")

        _uiState.update { current ->
            current.copy(selectedAnswerIndex = index,)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onNextQuestion() {
        _uiState.update { state ->

            val selectedIndex = state.selectedAnswerIndex

            if (selectedIndex == null) {
                Log.d("QuizDebug", "No answer selected, cannot proceed")
                return@update state
            }

            val newHistory = state.answersHistory.toMutableList()

            if (state.currentIndex < newHistory.size) {
                newHistory[state.currentIndex] = selectedIndex
            }

            val nextIndex = state.currentIndex + 1

            if (nextIndex < state.quiz.size) {
                Log.d(
                    "QuizDebug",
                    "Moving to next question: ${nextIndex + 1}/${state.quiz.size}"
                )

                state.copy(
                    currentIndex = nextIndex,
                    answersHistory = newHistory,
                    selectedAnswerIndex = null
                )
            } else {

                    //  fun result_datastore_history(newHistory: List<Int?>) {
                 //   Log.d("QuizDebug", "result_datastore_history called")

                Log.d("QuizDebug", "Generated historyId for all results: $historyID")

                state.quiz.indices.forEach { idx ->
                    val model = resultHistoryModels(
                        questionIndex = idx,
                        totalQuestions = state.quiz.size,
                        questionText = state.quiz[idx].question,
                        options = state.quiz[idx].answers,
                        selectedIndex = newHistory[idx] ?: -1,
                        correctIndex = state.quiz[idx].correctAnswerIndex,
                        id = UUID.randomUUID().toString(),
                        historyId = historyID
                    )

                    Log.d(
                        "QuizDebug",
                        "Result idx=$idx: id=${model.id}, historyId=${model.historyId}"
                    )

                    viewModelScope.launch {
                        historyRepository.save_result_history(model)
                    }
                }

                var correctCount = 0

                state.quiz.indices.forEach { idx ->
                    val userAnswer = newHistory.getOrNull(idx)
                    val correctAnswer = state.quiz[idx].correctAnswerIndex
                    val isCorrect = userAnswer == correctAnswer

                    Log.d(
                        "QuizDebug",
                        "Question $idx: user selected $userAnswer, correct is $correctAnswer, isCorrect = $isCorrect"
                    )

                    if (isCorrect) correctCount++
                }

                viewModelScope.launch {
                    onQuizFinished(correctCount, state.quiz.size)
                }

                    Log.d(
                        "QuizDebug",
                        "Quiz finished. Correct answers: $correctCount / ${state.quiz.size}"
                    )

                    state.copy(
                        answersHistory = newHistory,
                        selectedAnswerIndex = null,
                        screenState = QuizScreenState.Result(
                            correctCount = correctCount,

                        )
                    )

                }
            }
        }

    //init {
  //      loadQuiz()
  //  }

    fun onStartClicked() {

        Log.d("QuizDebug", "onStartClicked called")

        _uiState.update {

            it.copy(screenState = QuizScreenState.Loading)

        }


        viewModelScope.launch {
            try {


                Logics_use().first().let { quiz ->

                    val code = quiz.first().failedCode

                    if (code == 0) {

                        _uiState.update {

                            Log.d("QuizDebug", "Quiz loaded: ${quiz.size} questions")

                            it.copy(
                                quiz = quiz,
                                answersHistory = List(quiz.size) { null },
                                screenState = QuizScreenState.Progress(currentIndex = 0),
                                failedCodeUi = 0

                                )


                        }


                    } else {
                        Log.e("errr", "Ошибка загрузки. Код: $code")

                        if (quiz.isEmpty()) {


                            _uiState.update {
                                it.copy(
                                    screenState = QuizScreenState.Start,
                                    failedCodeUi = code
                                )
                            }
                        }

                    }


                }
            }catch (e: Exception){

                Log.e("error", "Ошибка при загрузке: ${e.message}", e)

                _uiState.update {
                    it.copy(
                        screenState = QuizScreenState.Start,
                        failedCodeUi = -1

                    )

                }

            }

        }

    }

  //  fun loadQuiz() {
  //      viewModelScope.launch {
   //         try {
   //             Logics_use().collect { quiz ->
   //                 _uiState.update { it.copy(quiz = quiz,) }
    //            }
    //        } catch (e: Exception) {
    //            _uiState.update { it.copy(screenState = QuizScreenState.Progress(e.message ?: "Unknown error")) }
    //        }
    //    }
  //  }

}