package com.quiz.model;

public class QuizSession {

    private String sessionId;
    private int currentDifficulty;
    private int score;
    private int totalAnswered;
    private int correctAnswers;

    public QuizSession(String sessionId) {
        this.sessionId = sessionId;
        this.currentDifficulty = 1; // Start with Easy
        this.score = 0;
        this.totalAnswered = 0;
        this.correctAnswers = 0;
    }

    // CAT Logic — adjust difficulty based on performance
    public void updateDifficulty(boolean isCorrect) {
        if (isCorrect) {
            correctAnswers++;
            score += currentDifficulty * 10; // Hard = more points
            if (currentDifficulty < 3) currentDifficulty++;
        } else {
            if (currentDifficulty > 1) currentDifficulty--;
        }
        totalAnswered++;
    }

    // Getters and Setters
    public String getSessionId() { return sessionId; }

    public int getCurrentDifficulty() { return currentDifficulty; }
    public void setCurrentDifficulty(int currentDifficulty) { this.currentDifficulty = currentDifficulty; }

    public int getScore() { return score; }

    public int getTotalAnswered() { return totalAnswered; }

    public int getCorrectAnswers() { return correctAnswers; }
}