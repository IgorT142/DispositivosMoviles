package Practicas.Quiz;

public class CheckBoxQuestion extends Question {
    private boolean[] correctAnswers = {true,true,false,true};
    public CheckBoxQuestion(String text, int correctAnswer) {
        super(text, correctAnswer);
    }
}
