<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Result</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f0f4f8; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .result-container { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); width: 500px; text-align: center; }
        h1 { font-size: 28px; color: #333; margin-bottom: 10px; }
        .score-big { font-size: 64px; font-weight: bold; color: #4CAF50; margin: 20px 0; }
        .stats { display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin: 25px 0; }
        .stat-box { background: #f5f5f5; padding: 15px; border-radius: 8px; }
        .stat-box h3 { font-size: 13px; color: #888; margin-bottom: 5px; text-transform: uppercase; }
        .stat-box p { font-size: 24px; font-weight: bold; color: #333; }
        .difficulty-badge { display: inline-block; padding: 6px 16px; border-radius: 20px; font-weight: bold; margin: 10px 0 25px; }
        .easy { background: #e8f5e9; color: #2e7d32; }
        .medium { background: #fff3e0; color: #e65100; }
        .hard { background: #fce4ec; color: #c62828; }
        .message { font-size: 16px; color: #555; margin-bottom: 25px; }
        .retry-btn { display: inline-block; padding: 14px 40px; background: #4CAF50; color: white; text-decoration: none; border-radius: 8px; font-size: 16px; font-weight: bold; transition: background 0.2s; }
        .retry-btn:hover { background: #388e3c; }
    </style>
</head>
<body>
<div class="result-container">
    <h1>🎉 Quiz Complete!</h1>

    <div class="score-big">${score}</div>

    <%
        int finalDiff = (int) request.getAttribute("finalDifficulty");
        String diffLabel = finalDiff == 1 ? "Easy" : finalDiff == 2 ? "Medium" : "Hard";
        String diffClass = finalDiff == 1 ? "easy" : finalDiff == 2 ? "medium" : "hard";
    %>

    <div class="difficulty-badge <%= diffClass %>">
        Final Level: <%= diffLabel %>
    </div>

    <div class="stats">
        <div class="stat-box">
            <h3>Correct</h3>
            <p>${correct}</p>
        </div>
        <div class="stat-box">
            <h3>Total</h3>
            <p>${total}</p>
        </div>
        <div class="stat-box">
            <h3>Accuracy</h3>
            <p>${correct * 100 / total}%</p>
        </div>
        <div class="stat-box">
            <h3>Score</h3>
            <p>${score}</p>
        </div>
    </div>

    <%
        int score = (int) request.getAttribute("score");
        String message;
        if (score >= 200) message = "Outstanding! You're a Java Expert! 🏆";
        else if (score >= 150) message = "Great job! Keep it up! 💪";
        else if (score >= 100) message = "Good effort! Practice more! 📚";
        else message = "Keep learning! You'll get better! 🌱";
    %>
    <div class="message"><%= message %></div>

    <a href="quiz" class="retry-btn">Try Again 🔄</a>
</div>
</body>
</html>