package com.example.pr15

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.vector.PathNode
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class First : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton:Button
    private lateinit var textview:TextView
    data class Question(
        val questionText: String,
        val answer: Boolean
    )

    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0
    private var incorrectAnswersCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        textview = findViewById(R.id.question)
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        initializeQuestionsAndAnswers()
        setQuestion()

    }
    private lateinit var questionsAndAnswers: List<Question>
    private fun initializeQuestionsAndAnswers() {
        questionsAndAnswers = listOf(
            Question(getString(R.string.first_quest), true),
            Question(getString(R.string.second_quest), true),
            Question(getString(R.string.third_quest), false),
            Question(getString(R.string.fourth_quest), true),
            Question(getString(R.string.fifth_quest), true),
            Question(getString(R.string.sixth_quest), true),
            Question(getString(R.string.seventh_quest), true),
            Question(getString(R.string.eighth_quest), true),
            Question(getString(R.string.nineth_quest), false)
        )
    }
    private fun setQuestion() {
        val currentQuestion = questionsAndAnswers[currentQuestionIndex]
        textview.text = currentQuestion.questionText
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val currentQuestion = questionsAndAnswers[currentQuestionIndex]
        if (userAnswer == currentQuestion.answer) {
            correctAnswersCount++
            val toast = Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP,0,0)
            toast.show()
        } else {
            incorrectAnswersCount++
            val toast = Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP,0,0)
            toast.show()
        }
        currentQuestionIndex++
        if (currentQuestionIndex < questionsAndAnswers.size)
        {
            setQuestion()
        }
        else
        {
            showResultsDialog()
        }
    }

    fun nextquestion(view: View) {
        if (currentQuestionIndex < questionsAndAnswers.size - 1) {
            currentQuestionIndex++
            setQuestion()
        } else {
            Toast.makeText(this, getString(R.string.lastquest), Toast.LENGTH_SHORT).show()
        }
    }
    fun backquestion(view: View) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            setQuestion()
        } else {
            Toast.makeText(this, getString(R.string.backerror), Toast.LENGTH_SHORT).show()
        }
    }
    private fun showResultsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.finish))
        builder.setMessage("${getString(R.string.correctans)} $correctAnswersCount\n${getString(R.string.incorrectans)} $incorrectAnswersCount")
        builder.setPositiveButton(getString(R.string.OK)) { dialog, _ ->
            dialog.dismiss()
            application.onTerminate()


        }
        builder.show()
    }

    fun endquest(view: View) {
        val toast = Toast.makeText(this, getString(R.string.testend),Toast.LENGTH_SHORT)
        toast.show()
        System.exit(0)
    }

}