package by.bsuir.quiz.screens.results

import androidx.lifecycle.ViewModel

class ResultsViewModel(val answers: List<Boolean>) : ViewModel() {
    var correctAnswersCount: Int = 0
    var correctAnswersPercentage: String = ""

    init {
        correctAnswersCount = answers.fold(0) { acc, el -> if (el) acc + 1 else acc }
        correctAnswersPercentage =
            "%.2f%%".format(correctAnswersCount / answers.size.toDouble() * 100)
    }
}
