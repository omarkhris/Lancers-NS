import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Helmet } from "react-helmet";
import { FaSeedling, FaClipboardList, FaCheckCircle, FaArrowLeft } from "react-icons/fa";
import "../Quiz/QuizSummary.scss";

const QuizSummary = (props) => {
    const location = useLocation();
    const navigate = useNavigate();

    // Get data from props (if passed) or location.state (fallback)
    const quizData = props.quizData || location.state || {};
    const {
        totalQuestions = 0,
        answeredQuestions = 0,
        answers = [],
        geminiAnalysis = "No analysis available."
    } = quizData;

    // Function to process Gemini text, highlighting numbers.
    const processGeminiText = (text) => {
        const regex = /(\d+\..*?)(?=\*\*|$)/gs; // Regex to match numbers followed by text until next ** or end of string.
        let parts = [];
        let lastIndex = 0;
        let match;

        while ((match = regex.exec(text)) !== null) {
            if (match.index > lastIndex) {
                parts.push(text.slice(lastIndex, match.index));
            }
            parts.push(<span key={match.index} className="highlighted-word">{match[1]}</span>);
            lastIndex = regex.lastIndex;
        }

        if (lastIndex < text.length) {
            parts.push(text.slice(lastIndex));
        }

        return parts;
    };

    // Calculate score percentage
    const scorePercentage = totalQuestions ? ((answeredQuestions / totalQuestions) * 100).toFixed(2) : 0;

    // Parse answers for more user-friendly display
    const formattedAnswers = answers.map((answer, index) => (
        <p key={index}>
            <strong>Q{index + 1}:</strong> {answer?.optionText || "No answer selected"}
        </p>
    ));
    console.log("Quiz answers:", answers);


    return (
        <div className="quiz-summary-container">
            <Helmet>
                <title>Quiz Summary</title>
            </Helmet>

            <div className="quiz-summary-card">
                <h1 className="quiz-summary-title">
                    <FaSeedling className="icon" /> Quiz Summary
                </h1>

                {answeredQuestions === 0 ? (
                    <h2 className="no-stats-message">No stats available. Please take a quiz.</h2>
                ) : (
                    <div className="stats-container">
                        <p className="stat">
                            <FaClipboardList className="icon" />
                            <strong>Total Questions:</strong> {totalQuestions}
                        </p>
                        <p className="stat">
                            <FaCheckCircle className="icon" />
                            <strong>Answered:</strong> {answeredQuestions}
                        </p>
                        

                        <h3>LLM Analysis integrated with Gemini :</h3>
                        <div className="gemini-response">
                            {processGeminiText(geminiAnalysis).map((part, index) => {
                                if (typeof part === "string") {
                                    return <p key={index}>{part}</p>;
                                }
                                return <span key={index}>{part}</span>;
                            })}
                        </div>
                    </div>
                )}

                <button className="back-button" onClick={() => navigate("/")}>
                    <FaArrowLeft className="icon" /> Back to Home
                </button>
            </div>
        </div>
    );
};

export default QuizSummary;