package com.example.problem2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val questions = listOf(
        Question("What is the capital of Bangladesh?", listOf("Dhaka", "Chittagong", "Barisal", "Sylhet", "Tangail"), 0),
        Question("Who is my profession?", listOf("Google", "Android Developer", "Microsoft", "Oracle"), 1),
        Question("10 + 7 ?", listOf("3", "17", "5", "6"), 1)
    )

    var currentIndex by mutableStateOf(0)
        private set

    var score by mutableStateOf(0)
        private set

    var showResult by mutableStateOf(false)
        private set

    val currentQuestion: Question
        get() = questions[currentIndex]

    val totalQuestions: Int
        get() = questions.size

    fun selectOption(index: Int) {
        if (index == currentQuestion.correctAnswer) score++
        if (currentIndex < questions.size - 1) {
            currentIndex++
        } else {
            showResult = true
        }
    }

    fun resetGame() {
        currentIndex = 0
        score = 0
        showResult = false
    }
}
