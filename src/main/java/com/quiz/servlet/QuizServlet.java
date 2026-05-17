package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.model.Question;
import com.quiz.model.QuizSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {

    private static final int MAX_QUESTIONS = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");
        List<Integer> askedIds = (List<Integer>) session.getAttribute("askedIds");

        // Start new quiz
        if (quizSession == null) {
            quizSession = new QuizSession(session.getId());
            askedIds = new ArrayList<>();
            session.setAttribute("quizSession", quizSession);
            session.setAttribute("askedIds", askedIds);
        }

        // Quiz finished
        if (quizSession.getTotalAnswered() >= MAX_QUESTIONS) {
            response.sendRedirect("result");
            return;
        }

        // Get next question based on current difficulty
        try {
            QuestionDAO dao = new QuestionDAO();
            Question question = dao.getQuestionByDifficulty(quizSession.getCurrentDifficulty(), askedIds);

            if (question == null) {
                response.sendRedirect("result");
                return;
            }

            session.setAttribute("currentQuestion", question);
            request.setAttribute("question", question);
            request.setAttribute("questionNumber", quizSession.getTotalAnswered() + 1);
            request.setAttribute("maxQuestions", MAX_QUESTIONS);
            request.setAttribute("score", quizSession.getScore());

            request.getRequestDispatcher("/quiz.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");
        Question currentQuestion = (Question) session.getAttribute("currentQuestion");
        List<Integer> askedIds = (List<Integer>) session.getAttribute("askedIds");

        String selectedAnswer = request.getParameter("answer");

        if (selectedAnswer != null && currentQuestion != null) {
            boolean isCorrect = selectedAnswer.charAt(0) == currentQuestion.getCorrectAnswer();
            quizSession.updateDifficulty(isCorrect);
            askedIds.add(currentQuestion.getId());

            try {
                QuestionDAO dao = new QuestionDAO();
                dao.logAnswer(session.getId(), currentQuestion.getId(),
                        selectedAnswer, isCorrect, currentQuestion.getDifficulty());
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        response.sendRedirect("quiz");
    }
}