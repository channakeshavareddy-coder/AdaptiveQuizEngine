# 🧠 Adaptive Quiz Engine with Difficulty Adjustment (CAT-lite)
A Full Stack Java Web Application that implements a lightweight **Computerized Adaptive Testing (CAT)** algorithm. The quiz dynamically adjusts question difficulty based on the user's running score trajectory — delivering a personalized quiz experience.
## 📌 Project Requirement
> Implement a lightweight Computerized Adaptive Testing (CAT) algorithm in the Servlet layer — question difficulty adjusts based on running score trajectory.
> - **USP:** JSP + JavaScript (timer, live scoring, progress bar)
> - **Logic:** IRT-lite (Item Response Theory simplified) — 3 difficulty tiers, threshold switching
> - **Data:** MySQL (question bank with difficulty/discrimination index, session logs)
## 🚀 Features

- ✅ CAT Algorithm — difficulty adjusts after every answer
- ✅ IRT-lite scoring — 3 difficulty tiers with discrimination index
- ✅ 30-second countdown timer per question
- ✅ Live score tracking during quiz
- ✅ Progress bar showing quiz completion
- ✅ Session logging — every answer recorded in database
- ✅ Random question selection — different experience every attempt
- ✅ Result page with accuracy, score, and final difficulty level
- 
## 🛠️ Tech Stack
| Layer | Technology |
|---|---|
| Frontend | JSP, HTML5, CSS3, JavaScript |
| Backend | Java Servlets (Jakarta EE) |
| Algorithm | CAT-lite + IRT-lite |
| Database | MySQL |
| Server | Apache Tomcat 11 |
| Build Tool | Apache Maven |
| IDE | IntelliJ IDEA |
| JDK | Java 17 |
## 📁 Project Structure
```
AdaptiveQuizEngine/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/quiz/
│       │       ├── dao/
│       │       │   ├── DBConnection.java       ← JDBC MySQL connection
│       │       │   └── QuestionDAO.java        ← Database queries
│       │       ├── model/
│       │       │   ├── Question.java           ← Question model
│       │       │   └── QuizSession.java        ← CAT session logic
│       │       └── servlet/
│       │           ├── QuizServlet.java        ← Main CAT controller
│       │           └── ResultServlet.java      ← Result handler
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           ├── index.jsp                       ← Welcome page
│           ├── quiz.jsp                        ← Quiz UI (timer, progress)
│           └── result.jsp                      ← Result page
└── pom.xml
```
## 🧠 Algorithm — How CAT Works
```
Start → Easy (Difficulty = 1)
          ↓
    Answer Correct?
       ↙        ↘
     YES          NO
      ↓            ↓
 Difficulty UP   Difficulty DOWN
 Score += D×10   (min difficulty = 1)
      ↓
 Next Question
      ↓
 10 Questions Done → Show Result
```
### IRT-lite Scoring
| Difficulty | Points per Correct Answer | Discrimination Index |
|---|---|---|
| Easy (1) | 10 points | 0.50 – 0.65 |
| Medium (2) | 20 points | 1.00 – 1.20 |
| Hard (3) | 30 points | 1.70 – 1.90 |
---
## 🗄️ Database Schema
```sql
-- Question bank with difficulty & discrimination index
CREATE TABLE questions (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    question_text    TEXT NOT NULL,
    option_a         VARCHAR(255) NOT NULL,
    option_b         VARCHAR(255) NOT NULL,
    option_c         VARCHAR(255) NOT NULL,
    option_d         VARCHAR(255) NOT NULL,
    correct_answer   CHAR(1) NOT NULL,
    difficulty       INT NOT NULL,         -- 1=Easy, 2=Medium, 3=Hard
    discrimination   DECIMAL(3,2)          -- IRT discrimination index
);
-- Session logs — every answer tracked
CREATE TABLE session_logs (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    session_id        VARCHAR(100) NOT NULL,
    question_id       INT NOT NULL,
    selected_answer   CHAR(1),
    is_correct        BOOLEAN,
    difficulty_at_time INT,
    answered_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
---
## ⚙️ How to Run
### Prerequisites
- Java 17
- Apache Tomcat 11
- MySQL Server
- IntelliJ IDEA
- Maven
### Steps
1. **Clone the repository**
```bash
git clone https://github.com/keshavachanna045-hash/AdaptiveQuizEngine.git
```
2. **Set up the database**
   - Open MySQL Workbench
   - Run the SQL schema to create `quiz_db`, `questions`, and `session_logs` tables
   - Insert question data
3. **Configure DB connection**
   - Open `src/main/java/com/quiz/dao/DBConnection.java`
   - Update your MySQL password:
```java
private static final String PASSWORD = "your_password";
```
4. **Run in IntelliJ**
   - Open project in IntelliJ IDEA
   - Configure Tomcat 11 in Run → Edit Configurations
   - Set deployment artifact: `AdaptiveQuizEngine:war exploded`
   - Set context path: `/quiz`
   - Click ▶ Run
5. **Open in browser**
```
http://localhost:8080/quiz
```
## 📊 Design Patterns Used
| Pattern | Where Used |
|---|---|
| MVC (Model-View-Controller) | Full project architecture |
| DAO (Data Access Object) | QuestionDAO.java |
| Session Management | HttpSession for quiz state |
| Factory Pattern | DBConnection.getConnection() |
---
## 🎯 Result Scoring
| Score | Message |
|---|---|
| 200+ | Outstanding! You're a Java Expert! 🏆 |
| 150–199 | Great job! Keep it up! 💪 |
| 100–149 | Good effort! Practice more! 📚 |
| Below 100 | Keep learning! You'll get better! 🌱 |
---
## 👨‍💻 Developer
**Channakeshava Reddy**
- GitHub: [@keshavachanna045-hash](https://github.com/keshavachanna045-hash)
---
## 📄 License
This project is developed as part of an academic requirement for demonstrating Full Stack Java Web Development skills using Jakarta EE, Apache Tomcat, and MySQL.
