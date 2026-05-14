<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adaptive Quiz</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f0f4f8; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .quiz-container { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); width: 650px; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .score { font-size: 18px; font-weight: bold; color: #4CAF50; }
        .difficulty { padding: 4px 12px; border-radius: 20px; font-size: 13px; font-weight: bold; }
        .easy { background: #e8f5e9; color: #2e7d32; }
        .medium { background: #fff3e0; color: #e65100; }
        .hard { background: #fce4ec; color: #c62828; }
        .progress-bar { background: #e0e0e0; border-radius: 10px; height: 10px; margin-bottom: 20px; }
        .progress-fill { background: #4CAF50; height: 10px; border-radius: 10px; transition: width 0.3s; }
        .timer { text-align: center; font-size: 22px; font-weight: bold; color: #e53935; margin-bottom: 15px; }
        .question { font-size: 18px; font-weight: bold; margin-bottom: 25px; color: #333; line-height: 1.5; }
        .options { display: grid; gap: 12px; }
        .option-label { display: flex; align-items: center; gap: 12px; padding: 14px 18px; border: 2px solid #e0e0e0; border-radius: 8px; cursor: pointer; transition: all 0.2s; font-size: 15px; }
        .option-label:hover { border-color: #4CAF50; background: #f1f8e9; }
        input[type="radio"] { width: 18px; height: 18px; accent-color: #4CAF50; }
        .submit-btn { width: 100%; padding: 14px; margin-top: 20px; background: #4CAF50; color: white; border: none; border-radius: 8px; font-size: 16px; font-weight: bold; cursor: pointer; transition: background 0.2s; }
        .submit-btn:hover { background: #388e3c; }
        .question-count { text-align: center; color: #888; margin-bottom: 10px; font-size: 14px; }
    </style>
</head>
<body>
<div class="quiz-container">
    <div class="header">
        <span class="score">Score: ${score}</span>
        <%
            int diff = ((com.quiz.model.Question)request.getAttribute("question")).getDifficulty();
            String diffLabel = diff == 1 ? "Easy" : diff == 2 ? "Medium" : "Hard";
            String diffClass = diff == 1 ? "easy" : diff == 2 ? "medium" : "hard";
        %>
        <span class="difficulty <%= diffClass %>"><%= diffLabel %></span>
    </div>

    <!-- Progress Bar -->
    <div class="progress-bar">
        <div class="progress-fill" style="width: ${questionNumber * 100 / maxQuestions}%"></div>
    </div>

    <!-- Timer -->
    <div class="timer" id="timer">⏱ 30s</div>

    <div class="question-count">Question ${questionNumber} of ${maxQuestions}</div>

    <!-- Question -->
    <div class="question">${question.questionText}</div>

    <!-- Options -->
    <form action="quiz" method="post" id="quizForm">
        <div class="options">
            <label class="option-label">
                <input type="radio" name="answer" value="A" required> A) ${question.optionA}
            </label>
            <label class="option-label">
                <input type="radio" name="answer" value="B"> B) ${question.optionB}
            </label>
            <label class="option-label">
                <input type="radio" name="answer" value="C"> C) ${question.optionC}
            </label>
            <label class="option-label">
                <input type="radio" name="answer" value="D"> D) ${question.optionD}
            </label>
        </div>
        <button type="submit" class="submit-btn">Next Question →</button>
    </form>
</div>

<script>
    let timeLeft = 30;
    const timerEl = document.getElementById('timer');
    const form = document.getElementById('quizForm');

    const countdown = setInterval(() => {
        timeLeft--;
        timerEl.textContent = '⏱ ' + timeLeft + 's';
        if (timeLeft <= 10) timerEl.style.color = '#e53935';
        if (timeLeft <= 0) {
            clearInterval(countdown);
            form.submit();
        }
    }, 1000);
</script>
</body>
</html>