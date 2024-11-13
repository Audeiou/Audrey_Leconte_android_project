package fr.ensicaen.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//interroger une API avec thread + utiliser base de données
// pour API : Android ROOM
// fond d'écran ? qui change avec flèches

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button number0 = findViewById(R.id.button0);
        Button number1 = findViewById(R.id.button1);
        Button number2 = findViewById(R.id.button2);
        Button number3 = findViewById(R.id.button3);
        Button number4 = findViewById(R.id.button4);
        Button number5 = findViewById(R.id.button5);
        Button number6 = findViewById(R.id.button6);
        Button number7 = findViewById(R.id.button7);
        Button number8 = findViewById(R.id.button8);
        Button number9 = findViewById(R.id.button9);

        Button plus = findViewById(R.id.plusButton);
        Button minus = findViewById(R.id.minusButton);
        Button times = findViewById(R.id.timesButton);
        Button divide = findViewById(R.id.divideButton);

        Button equal = findViewById(R.id.equalButton);
        Button erase = findViewById(R.id.eraseButton);

        TextView resultText = findViewById(R.id.result);
        TextView formulaText = findViewById(R.id.formula);

        ArrayList<Button> numbers = new ArrayList<>();
        numbers.add(number0);
        numbers.add(number1);
        numbers.add(number2);
        numbers.add(number3);
        numbers.add(number4);
        numbers.add(number5);
        numbers.add(number6);
        numbers.add(number7);
        numbers.add(number8);
        numbers.add(number9);

        for (Button button : numbers) {
            button.setOnClickListener(view -> {
                if (!formulaText.getText().toString().equals("0")) {
                    formulaText.setText(formulaText.getText().toString() + button.getText().toString());
                } else {
                    formulaText.setText(button.getText().toString());
                }
            });
        }

        ArrayList<Button> operations = new ArrayList<>();
        operations.add(divide);
        operations.add(plus);
        operations.add(minus);
        operations.add(times);

        for (Button button : operations) {
            button.setOnClickListener(view -> {
                formulaText.setText(formulaText.getText().toString() + button.getText().toString());
            });
        }

        equal.setOnClickListener( view -> {
            String formula = formulaText.getText().toString();
            Expression expression = new ExpressionBuilder(formula).build();
            double result = expression.evaluate();
            String resultString = String.valueOf(result);
            resultText.setText(resultString);
        });

        erase.setOnClickListener( view -> {
            formulaText.setText("0");
            resultText.setText("0");
        });

        Button exit = findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}