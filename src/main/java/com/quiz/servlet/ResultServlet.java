package com.quiz.servlet;

import com.quiz.model.QuizSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");

        if (quizSession == null) {
            response.sendRedirect("quiz");
            return;
        }

        request.setAttribute("score", quizSession.getScore());
        request.setAttribute("correct", quizSession.getCorrectAnswers());
        request.setAttribute("total", quizSession.getTotalAnswered());
        request.setAttribute("finalDifficulty", quizSession.getCurrentDifficulty());

        // Clear session for next attempt
        session.removeAttribute("quizSession");
        session.removeAttribute("askedIds");
        session.removeAttribute("currentQuestion");

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}