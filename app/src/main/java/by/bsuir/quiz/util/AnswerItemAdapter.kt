package by.bsuir.quiz.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quiz_fragment.view.*
import by.bsuir.quiz.R
import by.bsuir.quiz.models.Answer

class AnswerItemAdapter : RecyclerView.Adapter<AnswerItemViewHolder>() {
    var answers = listOf<Answer>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = answers.size

    override fun onBindViewHolder(holder: AnswerItemViewHolder, position: Int) {
        val item = answers[position]
        holder.answerItem.text = item.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.answer_item_view, parent, false) as RadioButton
        return AnswerItemViewHolder(view)
    }
}