package com.quiz.model;

public class QuizSession {

    private String sessionId;
    private int currentDifficulty;
    private int score;
    private int totalAnswered;
    private int correctAnswers;
    private int consecutiveCorrect;

    public QuizSession(String sessionId) {
        this.sessionId = sessionId;
        this.currentDifficulty = 1; // Start with Easy
        this.score = 0;
        this.totalAnswered = 0;
        this.correctAnswers = 0;
        this.consecutiveCorrect = 0;
    }

    // CAT Logic — adjust difficulty based on performance
    public void updateDifficulty(boolean isCorrect) {
        if (isCorrect) {
            correctAnswers++;
            score += currentDifficulty * 10;
            consecutiveCorrect++;
            if (consecutiveCorrect >= 3 && currentDifficulty < 3) {
                currentDifficulty++;
                consecutiveCorrect = 0;
            }
        } else {
            consecutiveCorrect = 0;
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