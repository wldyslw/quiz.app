package by.bsuir.quiz.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultsViewModelFactory(private val answers: List<Boolean>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(answers) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}