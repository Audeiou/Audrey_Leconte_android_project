package fr.ensicaen.tp1.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.ensicaen.tp1.R;
import fr.ensicaen.tp1.model.CalculatorModel;
import fr.ensicaen.tp1.presenter.CalculatorPresenter;
import fr.ensicaen.tp1.presenter.IPresenter;


public class MainActivity extends AppCompatActivity implements IPresenter {

    private Button number0, number1, number2, number3, number4, number5, number6, number7, number8, number9;
    private Button plus, equal, erase, exit;
    private TextView leftOperandText, rightOperandText, operationText;
    private GridLayout resultCandy;
    private Button[] numbers;

    private CalculatorPresenter _presenter;

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

        CalculatorPresenter presenter = new CalculatorPresenter();
        setPresenter(presenter);
        presenter.setInterface(this);
        CalculatorModel model = new CalculatorModel();
        presenter.setModel(model);

        initView();

        numbers = new Button[]{number0, number1, number2, number3, number4, number5, number6, number7, number8, number9};

        setNumberButtons();
        setOperationButton();
        setEqualButton();
        setEraseButton();
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

        equal = findViewById(R.id.equalButton);
        erase = findViewById(R.id.eraseButton);
        exit = findViewById(R.id.exitButton);

        resultCandy = findViewById(R.id.resultPictures);
        leftOperandText = findViewById(R.id.leftOperand);
        rightOperandText = findViewById(R.id.rightOperand);
        operationText = findViewById(R.id.operationText);
    }

    private void setNumberButtons() {
        for (Button button : numbers) {
            button.setOnClickListener(view -> _presenter.logicForSettingNumberButtons(button.getText().toString()));
        }
    }

    public void setLeftOperand(String text) {
        leftOperandText.setText(text);
    }

    public void setRightOperand(String text) {
        rightOperandText.setText(text);
    }

    public void setOperationText(String text) {
        operationText.setText(text);
    }

    private void setOperationButton() {
        plus.setOnClickListener(view -> _presenter.logicForSettingOperationButton(plus.getText().toString()));
    }

    private void setEqualButton() {
        equal.setOnClickListener(view -> _presenter.logicForSettingEqualButton());
    }

    private void setEraseButton() {
        erase.setOnClickListener(view -> _presenter.logicForSettingEraseButton());
    }

    private void setExitButton() {
        exit.setOnClickListener(view -> finish());
    }

    public void disableNumberButtons() {
        for (Button button : numbers) {
            button.setEnabled(false);
        }
    }

    public void activateNumberButtons() {
        for (Button button : numbers) {
            button.setEnabled(true);
        }
    }

    private void displayPicture(int drawableId) {
        ImageView imageview = new ImageView(this);
        imageview.setImageResource(drawableId);

        int cellWidth = resultCandy.getWidth() / resultCandy.getColumnCount();
        int cellHeight = resultCandy.getHeight() / resultCandy.getRowCount();

        imageview.setLayoutParams(new LinearLayout.LayoutParams(cellWidth, cellHeight));
        imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);

        resultCandy.addView(imageview);
    }

    public void removeAllPictures() {
        resultCandy.removeAllViews();
    }

    public void displaySamePictureMultipleTimes(int drawableId, int times) {
        for (int i = 0; i < times; i++) {
            displayPicture(drawableId);
        }
    }

    public void setPresenter(CalculatorPresenter presenter) {
        _presenter = presenter;
    }

    public String getLeftOperandText() {
        return leftOperandText.getText().toString();
    }

    public String getRightOperandText() {
        return rightOperandText.getText().toString();
    }

    public String getOperationText() {
        return operationText.getText().toString();
    }

}