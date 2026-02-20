package com.example.problem2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizGame()
        }
    }
}

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Result: $score/$total",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onPlayAgain) {
            Text("Play Again")
        }
    }
}




@Composable
fun QuestionScreen(
    question: Question,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.goblin),
            contentDescription = "Sample Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentScale = ContentScale.Fit,
            alpha = 0.9f
        )

        Text(
            text = question.text,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEachIndexed { index, option ->
            Button(
                onClick = { onOptionSelected(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(option)
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizGame(viewModel: QuizViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz Game", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        if (viewModel.showResult) {
            ResultScreen(
                score = viewModel.score,
                total = viewModel.totalQuestions,
                onPlayAgain = { viewModel.resetGame() },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            QuestionScreen(
                question = viewModel.currentQuestion,
                onOptionSelected = { index -> viewModel.selectOption(index) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}



