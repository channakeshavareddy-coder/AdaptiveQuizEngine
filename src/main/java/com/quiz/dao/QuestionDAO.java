package com.quiz.dao;

import com.quiz.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDAO {

    // Get a random question by difficulty
    public Question getQuestionByDifficulty(int difficulty, List<Integer> askedIds) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String excludeIds = askedIds.isEmpty() ? "0" : askedIds.toString().replace("[", "").replace("]", "");

        String sql = "SELECT * FROM questions WHERE difficulty = ? AND id NOT IN (" + excludeIds + ") ORDER BY RAND() LIMIT 1";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, difficulty);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Question q = new Question();
            q.setId(rs.getInt("id"));
            q.setQuestionText(rs.getString("question_text"));
            q.setOptionA(rs.getString("option_a"));
            q.setOptionB(rs.getString("option_b"));
            q.setOptionC(rs.getString("option_c"));
            q.setOptionD(rs.getString("option_d"));
            q.setCorrectAnswer(rs.getString("correct_answer").charAt(0));
            q.setDifficulty(rs.getInt("difficulty"));
            q.setDiscrimination(rs.getDouble("discrimination"));
            conn.close();
            return q;
        }
        conn.close();
        return null;
    }

    // Log session answer to DB
    public void logAnswer(String sessionId, int questionId, String selectedAnswer, boolean isCorrect, int difficulty) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO session_logs (session_id, question_id, selected_answer, is_correct, difficulty_at_time) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, sessionId);
        ps.setInt(2, questionId);
        ps.setString(3, selectedAnswer);
        ps.setBoolean(4, isCorrect);
        ps.setInt(5, difficulty);
        ps.executeUpdate();
        conn.close();
    }
}