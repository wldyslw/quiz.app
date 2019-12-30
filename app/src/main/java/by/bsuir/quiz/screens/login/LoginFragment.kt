package by.bsuir.quiz.screens.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.bsuir.quiz.R
import by.bsuir.quiz.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import by.bsuir.quiz.util.LOG_KEY


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.vm = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.loginButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            activity?.let { a ->
                viewModel
                    .tryLogin(email, password)
                    .addOnCompleteListener(a) { task ->
                        if (task.isSuccessful) {
                            Log.d(LOG_KEY, "signInWithEmail:success")
                            Toast.makeText(
                                context, "Logged in successfully.",
                                Toast.LENGTH_SHORT
                            ).show()
                            view?.findNavController()?.navigate(
                                LoginFragmentDirections.actionLoginFragmentToStat()
                            )
                        } else {
                            Log.w(LOG_KEY, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, "Log in failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    private fun onLoginClicked() {
        // TODO: Refactor code above an move logic to separate methods
    }

}
