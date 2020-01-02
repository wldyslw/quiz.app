package by.bsuir.quiz.screens.results

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import by.bsuir.quiz.R
import by.bsuir.quiz.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private lateinit var binding: ResultsFragmentBinding

    private lateinit var viewModel: ResultsViewModel
    private lateinit var viewModelFactory: ResultsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ResultsViewModelFactory(
            ResultsFragmentArgs.fromBundle(arguments!!).answers.toList()
        )
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ResultsViewModel::class.java)
        binding = ResultsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.backButton.setOnClickListener {
            view?.findNavController()?.navigate(ResultsFragmentDirections.actionResultsToStat())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.sendStatistics()

        binding.totalScoreText.text = getString(
            R.string.quiz_finished_info,
            viewModel.correctAnswersCount,
            viewModel.answers.size,
            viewModel.correctAnswersPercentage
        )
    }

}
