package com.quiz.model;

public class Question {

    private int id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctAnswer;
    private int difficulty;
    private double discrimination;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public char getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(char correctAnswer) { this.correctAnswer = correctAnswer; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public double getDiscrimination() { return discrimination; }
    public void setDiscrimination(double discrimination) { this.discrimination = discrimination; }
}