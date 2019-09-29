package Practicas.Quiz;

import java.util.ArrayList;

public class Question {
    private String text;
    private int correctAnswer;
    private String[] answers;

    public Question(String text, int correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }
    public void setAnswers(String[] answer){
       /* for (int i=0;i<4;i++){
            answers[i]=answer[i];
        }*/
       answers=answer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }
}
