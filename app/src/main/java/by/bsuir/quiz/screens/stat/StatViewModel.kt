package by.bsuir.quiz.screens.stat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.bsuir.quiz.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StatViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val _highScore = MutableLiveData<String>()

    val highScore: LiveData<String>
        get() = _highScore

    private val _email = MutableLiveData<String>()

    val email: LiveData<String>
        get() = _email

    init {
        _highScore.value = ""

        val username = auth.currentUser?.email?.split("@")?.getOrNull(0)
        _email.value = "Welcome, ${username ?: "user"}!"
    }

    fun getStatistics() {
        db.collection("users")
            .document(auth.currentUser?.email!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                _highScore.value = user?.highScore ?: ""
            }
    }
}
