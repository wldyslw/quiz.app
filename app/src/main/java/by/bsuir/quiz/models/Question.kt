package by.bsuir.quiz.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Answer(
    var text: String = "",
    var isCorrect: Boolean = false
) : Serializable, Parcelable

@Parcelize
data class Question(
    var text: String = "",
    var answers: List<Answer>? = null
) : Serializable, Parcelable
