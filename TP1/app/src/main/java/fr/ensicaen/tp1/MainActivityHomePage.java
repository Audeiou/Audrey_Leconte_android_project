package fr.ensicaen.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import fr.ensicaen.tp1.view.MainActivityCalculator;

public class MainActivityHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page);

        Button calculator = findViewById(R.id.calculatorButton);
        Button quiz = findViewById(R.id.quizButton);

        calculator.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityHomePage.this, MainActivityCalculator.class);
            startActivity(intent);
        });

        quiz.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityHomePage.this, MainActivityQuiz.class);
            startActivity(intent);
        });
    }
}