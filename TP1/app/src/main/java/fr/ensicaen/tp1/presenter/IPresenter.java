package fr.ensicaen.tp1.presenter;

public interface IPresenter {
    String getLeftOperandText();
    String getRightOperandText();
    String getOperationText();

    void setLeftOperand(String text);
    void setRightOperand(String text);
    void setOperationText(String text);

    void activateNumberButtons();
    void disableNumberButtons();

    void removeAllPictures();
    void displaySamePictureMultipleTimes(int drawableId, int times);
}
