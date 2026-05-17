
package com.quiz.servlet;

import com.quiz.model.QuizSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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