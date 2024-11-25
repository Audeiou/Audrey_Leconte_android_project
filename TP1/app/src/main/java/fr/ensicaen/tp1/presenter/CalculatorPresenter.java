package fr.ensicaen.tp1.presenter;

import fr.ensicaen.tp1.R;
import fr.ensicaen.tp1.model.CalculatorModel;

public class CalculatorPresenter {

    private CalculatorModel _model;
    private IPresenter _interface;

    int numberClickCounterLeftOperand = 0;
    int numberClickCounterRightOperand = 0;
    int numberClickCounterOperation = 0;
    int[] candyPackages = {0, 0, 0, 0};

    public void setModel(CalculatorModel model) {
        _model = model;
    }

    public void setInterface(IPresenter interfacePresenter) {
        _interface = interfacePresenter;
    }

    public double getResult(String formula) {
        return _model.calculateResult(formula);
    }

    public void logicForSettingNumberButtons(String buttonText) {
        if (numberClickCounterLeftOperand != 2 && numberClickCounterOperation == 0 ) {
            updateLeftOperand(buttonText);
            numberClickCounterLeftOperand++;
        }
        else if (numberClickCounterRightOperand != 2 && numberClickCounterOperation != 0) {
            updateRightOperand(buttonText);
            numberClickCounterRightOperand++;
        }
        else {
            _interface.disableNumberButtons();
        }
    }

    public void logicForSettingOperationButton(String buttonText) {
        _interface.setOperationText(buttonText);
        numberClickCounterOperation++;
        if (numberClickCounterRightOperand != 2) {
            _interface.activateNumberButtons();
        }
    }

    private void updateLeftOperand(String buttonText) {
        if (!_interface.getLeftOperandText().equals("0")) {
            _interface.setLeftOperand(_interface.getLeftOperandText() + buttonText);
        } else {
            _interface.setLeftOperand(buttonText);
        }
    }

    private void updateRightOperand(String buttonText) {
        if (!_interface.getRightOperandText().equals("0")) {
            _interface.setRightOperand(_interface.getRightOperandText() + buttonText);
        } else {
            _interface.setRightOperand(buttonText);
        }
    }

    public void logicForSettingEqualButton() {
        if (!_interface.getOperationText().equals("..")) {
            String formula = _interface.getLeftOperandText() + _interface.getOperationText() + _interface.getRightOperandText();
            double result = getResult(formula);
            resultNumberCandyPackages(result);
            displayResultWithCandies();
        }
    }

    public void logicForSettingEraseButton() {
        _interface.setLeftOperand("0");
        _interface.setOperationText("..");
        _interface.setRightOperand("0");

        numberClickCounterLeftOperand = 0;
        numberClickCounterRightOperand = 0;
        numberClickCounterOperation = 0;
        candyPackages = new int[]{0, 0, 0, 0};

        _interface.activateNumberButtons();
        _interface.removeAllPictures();
    }

    private void resultNumberCandyPackages(double result) {
        int resultCandy = (int) result;

        candyPackages[3] = resultCandy / 50;
        resultCandy %= 50;

        candyPackages[2] = resultCandy / 10;
        resultCandy %= 10;

        candyPackages[1] = resultCandy / 5;
        resultCandy %= 5;

        candyPackages[0] = resultCandy;
    }

    private void displayResultWithCandies() {
        _interface.removeAllPictures();
        _interface.displaySamePictureMultipleTimes(R.drawable.candies50, candyPackages[3]);
        _interface.displaySamePictureMultipleTimes(R.drawable.candies10, candyPackages[2]);
        _interface.displaySamePictureMultipleTimes(R.drawable.candies5, candyPackages[1]);
        _interface.displaySamePictureMultipleTimes(R.drawable.candy1, candyPackages[0]);
    }

}
