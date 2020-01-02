package by.bsuir.quiz.screens.results

import androidx.lifecycle.ViewModel
import by.bsuir.quiz.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ResultsViewModel(val answers: List<Boolean>) : ViewModel() {
    var correctAnswersCount: Int = 0
    var correctAnswersPercentage: String = ""

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    init {
        correctAnswersCount = answers.fold(0) { acc, el -> if (el) acc + 1 else acc }
        correctAnswersPercentage =
            "%.2f%%".format(correctAnswersCount / answers.size.toDouble() * 100)
    }

    fun sendStatistics() {
        auth.currentUser?.let {
            db.collection("users")
                .document(it.email!!)
                .set(User(correctAnswersPercentage))
        }
    }
}
