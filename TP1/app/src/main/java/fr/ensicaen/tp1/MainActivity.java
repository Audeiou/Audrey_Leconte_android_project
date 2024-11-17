package fr.ensicaen.tp1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


//interroger une API avec thread + utiliser base de données
// pour API : Android ROOM
// fond d'écran ? qui change avec flèches
// langue avec localisation

public class MainActivity extends AppCompatActivity {

    int numberClickCounterLeftOperand = 0;
    int numberClickCounterRightOperand = 0;

    private Button number0, number1, number2, number3, number4, number5, number6, number7, number8, number9;
    private Button plus, minus, equal, erase, exit;
    private TextView resultText, leftOperandText, rightOperandText, operationText;

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

        initView();

        Button[] numbers = {number0, number1, number2, number3, number4, number5, number6, number7, number8, number9};
        Button[] operations = {plus, minus};

        setNumberButtons(numbers);
        setOperationButtons(operations);
        setEqualButton();
        setEraseButton(numbers);
        setExitButton();
    }

    private void initView() {
        number0 = findViewById(R.id.button0);
        number1 = findViewById(R.id.button1);
        number2 = findViewById(R.id.button2);
        number3 = findViewById(R.id.button3);
        number4 = findViewById(R.id.button4);
        number5 = findViewById(R.id.button5);
        number6 = findViewById(R.id.button6);
        number7 = findViewById(R.id.button7);
        number8 = findViewById(R.id.button8);
        number9 = findViewById(R.id.button9);

        plus = findViewById(R.id.plusButton);
        minus = findViewById(R.id.minusButton);

        equal = findViewById(R.id.equalButton);
        erase = findViewById(R.id.eraseButton);
        exit = findViewById(R.id.exitButton);

        resultText = findViewById(R.id.result);
        leftOperandText = findViewById(R.id.leftOperand);
        rightOperandText = findViewById(R.id.rightOperand);
        operationText = findViewById(R.id.operationText);
    }

    private void setNumberButtons(Button[] numbers) {
        for (Button button : numbers) {
            button.setOnClickListener(view -> {
                if (numberClickCounterLeftOperand != 2) {
                    if (!leftOperandText.getText().toString().equals("0")) {
                        leftOperandText.setText(leftOperandText.getText().toString() + button.getText().toString());
                    } else {
                        leftOperandText.setText(button.getText().toString());
                    }
                    numberClickCounterLeftOperand++;
                }
                else if (numberClickCounterRightOperand != 2) {
                    if (!rightOperandText.getText().toString().equals("0")) {
                        rightOperandText.setText(rightOperandText.getText().toString() + button.getText().toString());
                    } else {
                        rightOperandText.setText(button.getText().toString());
                    }
                    numberClickCounterRightOperand++;
                }
                else {
                    disableNumberButtons(numbers);
                }
            });
        }
    }

    private void setOperationButtons(Button[] operations) {
        for (Button button : operations) {
            button.setOnClickListener(view -> {
                operationText.setText(button.getText().toString());
            });
        }
    }

    private void setEqualButton() {
        equal.setOnClickListener( view -> {
            String formula = leftOperandText.getText().toString() + operationText.getText().toString() + rightOperandText.getText().toString();
            Expression expression = new ExpressionBuilder(formula).build();
            double result = expression.evaluate();
            String resultString = String.valueOf(result);
            resultText.setText(resultString);
        });
    }

    private void setEraseButton(Button[] numbers) {
        erase.setOnClickListener( view -> {
            leftOperandText.setText("0");
            operationText.setText("+");
            rightOperandText.setText("0");
            resultText.setText("0");

            numberClickCounterLeftOperand = 0;
            numberClickCounterRightOperand = 0;

            activateNumberButtons(numbers);
        });
    }

    private void setExitButton() {
        exit.setOnClickListener(view -> finish());  //finishAffinity();
    }

    private void disableNumberButtons(Button[] numbers) {
        for (Button button : numbers) {
            button.setEnabled(false);
        }
    }

    private void activateNumberButtons(Button[] numbers) {
        for (Button button : numbers) {
            button.setEnabled(true);
        }
    }


}