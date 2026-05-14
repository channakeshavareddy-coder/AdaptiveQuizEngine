<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adaptive Quiz Engine</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f0f4f8; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .container { background: white; padding: 50px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); width: 500px; text-align: center; }
        h1 { font-size: 28px; color: #333; margin-bottom: 15px; }
        p { color: #666; font-size: 15px; margin-bottom: 30px; line-height: 1.6; }
        .features { text-align: left; background: #f5f5f5; padding: 20px; border-radius: 8px; margin-bottom: 30px; }
        .features li { list-style: none; padding: 6px 0; color: #555; font-size: 14px; }
        .features li::before { content: "✅ "; }
        .start-btn { display: inline-block; padding: 16px 50px; background: #4CAF50; color: white; text-decoration: none; border-radius: 8px; font-size: 18px; font-weight: bold; transition: background 0.2s; }
        .start-btn:hover { background: #388e3c; }
    </style>
</head>
<body>
<div class="container">
    <h1>🧠 Adaptive Quiz Engine</h1>
    <p>Test your Java & OOP knowledge with our smart quiz that adapts to your skill level!</p>

    <div class="features">
        <ul>
            <li>10 questions per session</li>
            <li>3 difficulty levels: Easy, Medium, Hard</li>
            <li>Difficulty adjusts based on your answers</li>
            <li>30 second timer per question</li>
            <li>Live score tracking</li>
        </ul>
    </div>

    <a href="quiz" class="start-btn">Start Quiz 🚀</a>
</div>
</body>
</html>