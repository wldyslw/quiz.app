package by.bsuir.quiz.util

import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.quiz_fragment.view.*
import by.bsuir.quiz.R
import by.bsuir.quiz.models.Answer
import kotlinx.android.synthetic.main.answer_item_view.view.*
import by.bsuir.quiz.databinding.AnswerItemViewBinding

class AnswerItemAdapter : RecyclerView.Adapter<AnswerItemAdapter.AnswerItemViewHolder>() {
    private var selectedAnswer = -1

    var answers = listOf<Answer>()
        set(value) {
            field = value
            selectedAnswer = -1
            notifyDataSetChanged()
        }

    operator fun get(position: Int) = answers[position]

    override fun getItemCount() = answers.size

    override fun onBindViewHolder(holder: AnswerItemViewHolder, position: Int) {
        val item = answers[position]
        holder.answerItem.radioButton.text = item.text
        holder.answerItem.radioButton.id = position
        holder.answerItem.radioButton.isChecked = position == selectedAnswer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        inflate<AnswerItemViewBinding>(
            from(parent.context),
            R.layout.answer_item_view,
            parent,
            false
        ).run {
            AnswerItemViewHolder(this)
        }

    inner class AnswerItemViewHolder(val answerItem: AnswerItemViewBinding) :
        RecyclerView.ViewHolder(answerItem.root) {
        private val clickHandler: (View) -> Unit = {
            selectedAnswer = adapterPosition
            notifyDataSetChanged()
        }

        init {
            answerItem.apply {
                root.setOnClickListener(clickHandler)
                radioButton.setOnClickListener(clickHandler)
            }
        }
    }
}