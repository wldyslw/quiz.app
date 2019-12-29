package by.bsuir.quiz.screens.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    var emailText = ""
    var passwordText = ""

    fun tryLogin() {
        // TODO
    }

    fun trySignUp() {
        // TODO
    }
}
