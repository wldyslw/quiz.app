package by.bsuir.quiz.screens.quiz

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import by.bsuir.quiz.databinding.QuizFragmentBinding
import by.bsuir.quiz.util.AnswerClickListener
import by.bsuir.quiz.util.AnswerItemAdapter
import by.bsuir.quiz.util.LOG_KEY

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private lateinit var binding: QuizFragmentBinding

    private val viewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuizFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.questions = QuizFragmentArgs.fromBundle(arguments!!).questions.toList()

        val adapter = AnswerItemAdapter(AnswerClickListener { isCorrect ->
            viewModel.onClickAnswer(isCorrect)
            Log.d(LOG_KEY, "Answers map: ${viewModel.answersMap}")
        })
        viewModel.currentQuestionIndex.observe(this, Observer { newIndex ->
            if (newIndex >= viewModel.questions.size) {
                view?.findNavController()
                    ?.navigate(
                        QuizFragmentDirections.actionQuizToResults(
                            viewModel.answersMap.toBooleanArray()
                        )
                    )
            } else {
                viewModel.questions[newIndex].answers?.let {
                    adapter.answers = it
                }
            }
        })
        binding.answersList.adapter = adapter

        return binding.root
    }
}
