package by.bsuir.quiz.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import by.bsuir.quiz.models.Question

class QuizViewModel : ViewModel() {
    var questions: List<Question> = emptyList()

    private val _answersMap = mutableListOf<Boolean>()

    val answersMap: List<Boolean>
        get() = _answersMap

    private val _currentQuestionIndex = MutableLiveData<Int>()

    val headerText: LiveData<String> = Transformations.map(_currentQuestionIndex) {
        "Question ${it + 1} of ${questions.size}"
    }

    val questionText: LiveData<String> = Transformations.map(_currentQuestionIndex) {
        if (it < questions.size) questions[it].text.trim() else ""
    }

    val currentQuestionIndex: LiveData<Int>
        get() = _currentQuestionIndex

    init {
        _currentQuestionIndex.value = 0
    }

    fun onClickAnswer(isCorrect: Boolean) {
        _answersMap.add(isCorrect)
    }

    fun stepQuestionIndex(step: Int) {
        _currentQuestionIndex.value?.let {
            if (it < questions.size && it >= 0) {
                _currentQuestionIndex.value = it + step
            }
        }
    }
}
