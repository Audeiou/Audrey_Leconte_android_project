package fr.ensicaen.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private Button exit;  // tout en anglais
    private Button number0;
    private Button number1;
    private Button number2;
    private Button number3;
    private Button number4;
    private Button number5;
    private Button number6;
    private Button number7;
    private Button number8;
    private Button number9;

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

        /*String operations = "";
        Expression expression = new ExpressionBuilder(operations).build();
        double result = expression.evaluate();

        number0 = findViewById(R.id.button0);
        number1 = findViewById(R.id.button1);
        number2 = findViewById(R.id.button2);
        number3 = findViewById(R.id.button3);
        number4 = findViewById(R.id.button4);
        number5 = findViewById(R.id.button5);
        number6 = findViewById(R.id.button6);
        number7 = findViewById(R.id.button7);
        number8 = findViewById(R.id.button8);
        number9 = findViewById(R.id.button9);*/

        exit = findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}