package by.bsuir.quiz.screens.stat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class StatViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _email = MutableLiveData<String>()

    val email: LiveData<String>
        get() = _email

    init {
        val username = auth.currentUser?.email?.split("@")?.getOrNull(0)
        _email.value = "Welcome, ${username ?: "user"}!"
    }
}
