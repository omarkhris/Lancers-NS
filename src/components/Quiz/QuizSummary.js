import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { FaSeedling, FaClipboardList, FaCheckCircle, FaArrowLeft } from 'react-icons/fa';
import '../Quiz/QuizSummary.scss';
const QuizSummary = () => {
    const location = useLocation();
    const navigate = useNavigate();
    
    // Ensure playerStats always has valid data
    const playerStats = location.state || { totalQuestions: 0, answeredQuestions: 0, answers: 0 };

    return (
        <div className="quiz-summary-container">
            <Helmet><title>Quiz Summary</title></Helmet>

            <div className="quiz-summary-card">
                <h1 className="quiz-summary-title">
                    <FaSeedling className="icon" /> Quiz Summary
                </h1>

                {playerStats.answeredQuestions === 0 ? (
                    <h2 className="no-stats-message">No stats available. Please take a quiz.</h2>
                ) : (
                    <div className="stats-container">
                        <p className="stat">
                            <FaClipboardList className="icon" />
                            <strong>Total Questions:</strong> {playerStats.totalQuestions}
                        </p>
                        <p className="stat">
                            <FaCheckCircle className="icon" />
                            <strong>Answered:</strong> {playerStats.answeredQuestions}
                        </p>
                        <p className="score">
                            Score: {((playerStats.answeredQuestions / playerStats.totalQuestions) * 100).toFixed(2)}%
                        </p>
                    </div>
                )}

                <button className="back-button" onClick={() => navigate('/')}>
                    <FaArrowLeft className="icon" /> Back to Home
                </button>
            </div>
        </div>
    );
};

export default QuizSummary;
