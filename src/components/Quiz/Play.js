import React, { useState, useEffect } from 'react';
import { Helmet } from 'react-helmet';
import { useNavigate } from 'react-router-dom';  // React Router v6
import questionsData from '../../questions.json';

const Play = () => {
    const [questions, setQuestions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [currentQuestion, setCurrentQuestion] = useState({});
    const [answers, setAnswers] = useState({});
    const [isQuizEnded, setIsQuizEnded] = useState(false);  
    const navigate = useNavigate();  // React Router navigation

    useEffect(() => {
        setQuestions(questionsData);
        if (questionsData.length > 0) {
            setCurrentQuestion(questionsData[0]);
        }
    }, []);

    const handleNextQuestion = () => {
        if (currentQuestionIndex < questions.length - 1) {
            const newIndex = currentQuestionIndex + 1;
            setCurrentQuestionIndex(newIndex);
            setCurrentQuestion(questions[newIndex]);
        }
    };

    const handlePreviousQuestion = () => {
        if (currentQuestionIndex > 0) {
            const newIndex = currentQuestionIndex - 1;
            setCurrentQuestionIndex(newIndex);
            setCurrentQuestion(questions[newIndex]);
        }
    };

    const handleOptionClick = (option) => {
        setAnswers((prevAnswers) => ({
            ...prevAnswers,
            [currentQuestionIndex]: option,
        }));
    };

    const handleQuitButtonClick = () => {
        if (window.confirm('Are you sure you want to quit?')) {
            window.location.href = '/';
        }
    };

    const handleTextInput = (e) => {
        setAnswers((prevAnswers) => ({
            ...prevAnswers,
            [currentQuestionIndex]: e.target.value,
        }));
    };

    const endGame = (e) => {
        e.preventDefault();
        const playerStats = {
            totalQuestions: questions.length,
            answeredQuestions: Object.keys(answers).length,
            answers: answers
        };

        console.log(playerStats);  // Debugging: Check stats in console

        setTimeout(() => {
            navigate('/play/quizSummary', { state: playerStats });  // Correct navigation
        }, 1000);

        setIsQuizEnded(true);
    };

    const renderOptions = () => {
        const selectedAnswer = answers[currentQuestionIndex] || '';

        if (!currentQuestion || !currentQuestion.options) return (
            <div className="text-input-container">
                <textarea
                    placeholder="Type your answer here..."
                    value={selectedAnswer}
                    onChange={handleTextInput}
                    rows="4"
                />
            </div>
        );

        const options = currentQuestion.options;

        if (options.length === 2) {
            return (
                <div className="options-container">
                    {options.map((option, index) => (
                        <button 
                            key={index} 
                            onClick={() => handleOptionClick(option)}
                            className={selectedAnswer === option ? "selected-option" : ""}
                        >
                            {option}
                        </button>
                    ))}
                </div>
            );
        }

        if (options.length === 5) {
            return (
                <div className="slider-container">
                    <input
                        type="range"
                        min="1"
                        max="5"
                        value={options.indexOf(selectedAnswer) + 1} 
                        onChange={(e) => handleOptionClick(options[e.target.value - 1])}
                    />
                    <div className="slider-labels">
                        {options.map((option, index) => (
                            <span key={index}>{option}</span>
                        ))}
                    </div>
                </div>
            );
        }

        if (options.length > 5) {
            return (
                <select onChange={(e) => handleOptionClick(e.target.value)} value={selectedAnswer}>
                    <option value="">Select an option</option>
                    {options.map((option, index) => (
                        <option key={index} value={option}>
                            {option}
                        </option>
                    ))}
                </select>
            );
        }
    };

    return (
        <>
            <Helmet><title>Quiz Page</title></Helmet>
            <div className="questions">
                {isQuizEnded ? (
                    <div>
                        <h3>Quiz Summary</h3>
                        <p>Total Questions: {questions.length}</p>
                        <p>Answered Questions: {Object.keys(answers).length}</p>
                        <p>Your Answers: {JSON.stringify(answers, null, 2)}</p> {/* Display answers */}
                    </div>
                ) : (
                    <>
                        <h2>Quiz Mode</h2>
                        <p>{currentQuestionIndex + 1} of {questions.length}</p>
                        <h5>{currentQuestion.question || "Loading question..."}</h5>

                        {renderOptions()}

                        <div className="button-container">
                            <button 
                                className="previous-button"
                                onClick={handlePreviousQuestion} 
                                disabled={currentQuestionIndex === 0}
                            >
                                Previous
                            </button>
                            <button 
                                className="next-button"
                                onClick={handleNextQuestion} 
                                disabled={currentQuestionIndex >= questions.length - 1}
                            >
                                Next
                            </button>
                            <button 
                                className="quit-button"
                                onClick={handleQuitButtonClick}
                            >
                                Quit
                            </button>
                            {currentQuestionIndex === questions.length - 1 && (
                                <button 
                                    className="submit-button"
                                    onClick={endGame}
                                >
                                    Submit
                                </button>
                            )}
                        </div>
                    </>
                )}
            </div>
        </>
    );
};

export default Play;