package by.bsuir.quiz.screens.stat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import by.bsuir.quiz.R
import by.bsuir.quiz.databinding.StatFragmentBinding
import by.bsuir.quiz.models.Question
import by.bsuir.quiz.util.LOG_KEY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StatFragment : Fragment() {

    companion object {
        fun newInstance() = StatFragment()
    }

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: StatFragmentBinding
    private lateinit var questions: Array<Question>

    private val viewModel: StatViewModel by lazy {
        ViewModelProviders.of(this).get(StatViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StatFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        db = FirebaseFirestore.getInstance()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.startQuizButton.setOnClickListener {
            fetchQuestions()
        }

        viewModel.getStatistics()
    }

    private fun fetchQuestions() {
        activity?.let {
            binding.startQuizButton.isEnabled = false
            db.collection("quiz")
                .get()
                .addOnSuccessListener(it) { result ->
                    result.forEach { doc ->
                        Log.d(LOG_KEY, "${doc.id} -> ${doc.data}")
                    }
                    questions = result.toObjects(Question::class.java).toTypedArray()
                    view?.findNavController()
                        ?.navigate(StatFragmentDirections.actionStatToQuiz(questions))
                }
                .addOnFailureListener(it) { ex ->
                    Log.e(LOG_KEY, "Failed to get questions: ", ex)
                }
                .addOnCompleteListener {
                    binding.startQuizButton.isEnabled = true
                }
        }
    }

}
