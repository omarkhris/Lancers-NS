import React, { useState, useEffect } from 'react';
import { Helmet } from 'react-helmet';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'; // Import axios

export const Play = () => {
    const [questions, setQuestions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [answers, setAnswers] = useState({});
    const [isQuizEnded, setIsQuizEnded] = useState(false);
    const [loading, setLoading] = useState(true);
    const [geminiResponse, setGeminiResponse] = useState(''); // New state for Gemini response
    const [apiLoading, setApiLoading] = useState(false); // Loading state for API call
    const navigate = useNavigate();

    // Fetch questions from API
    useEffect(() => {
        const fetchQuestions = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/questions'); // Adjust API URL if necessary
                if (!response.ok) {
                    throw new Error('Failed to fetch questions');
                }
                const data = await response.json();
                setQuestions(data);
            } catch (error) {
                console.error('Error fetching questions:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchQuestions();
    }, []);

    const handleNextQuestion = () => {
        if (currentQuestionIndex < questions.length - 1) {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
        }
    };

    const handlePreviousQuestion = () => {
        if (currentQuestionIndex > 0) {
            setCurrentQuestionIndex(currentQuestionIndex - 1);
        }
    };

    const handleQuitButtonClick = () => {
        if (window.confirm('Are you sure you want to quit?')) {
            navigate('/');
        }
    };

    const endGame = async (e) => { // Make endGame async
        e.preventDefault();
        setApiLoading(true); // Start loading

        try {
            // Construct the prompt for Gemini API
            const prompt = `You are a compassionate and knowledgeable mental health expert specializing in the well-being of agricultural workers.

                    A farmer has completed the following questionnaire about their stress levels:

                    ${JSON.stringify(answers)}

                    Based on these responses, please:

                    1.  Assess the farmer's overall stress level: (e.g., Low, Moderate, High). Be concise.
                    2.  Identify the 2-3 most significant stressors contributing to their stress. Focus on factors related to their work in agriculture (e.g., financial pressures, workload, weather). Be direct.
                    3.  Provide 3-4 practical and actionable recommendations tailored to their situation. These recommendations should help the farmer cope with their stress, improve their well-being, and address any potential mental health concerns. The recommendations should be direct and easy to follow.
                    4.  Include a brief encouragement to seek professional help if needed, and provide a link to a relevant mental health resource for farmers. Do mention if used in the generated response or not.
                

                    Your response should be easy to understand for someone with no prior knowledge of mental health. Use clear and simple language.

                    It is important to be empathetic and supportive while also promoting well-being.

                    The response should be structured into short paragraphs.
                    `;

            // API call to Gemini
            const apiKey = 'AIzaSyBjuYa_XEStWjfL10eoC3N2Qj0ep6sziig'; // Use environment variable for API key
            const apiUrl = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${apiKey}`;

            const requestData = {
                contents: [{ parts: [{ text: prompt }] }]
            };

            const response = await axios.post(apiUrl, requestData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            setGeminiResponse(response.data.candidates[0].content.parts[0].text); // Assuming this is the structure
            setIsQuizEnded(true); // Show the response
        } catch (error) {
            console.error('Gemini API Error:', error);
            setGeminiResponse('Error analyzing responses. Please check the console.');
        } finally {
            setApiLoading(false); // Stop loading
        }
    };

    const handleOptionClick = (option) => {
        setAnswers((prevAnswers) => ({
            ...prevAnswers,
            [currentQuestionIndex]: option,
        }));
    };

    // Handle text input
    const handleTextInput = (e) => {
        const { value } = e.target;
        setAnswers((prevAnswers) => ({
            ...prevAnswers,
            [currentQuestionIndex]: value,
        }));
    };

    // Render the options
    const renderOptions = () => {
        if (questions.length > 0) {
            const selectedAnswer = answers[currentQuestionIndex] || ''; // This should be kept if you need to track selected answers

            // If the current question type is TEXT (or there's no options field), render a text input
            if (questions[currentQuestionIndex]?.questionType === 'TEXT' || !questions[currentQuestionIndex]?.options) {
                return (
                    <div className="text-input-container">
                        <textarea
                            placeholder="Type your answer here..."
                            value={selectedAnswer} // Added value here
                            onChange={handleTextInput}
                            rows="4"
                        />
                    </div>
                );
            }

            const options = questions[currentQuestionIndex]?.options;

            // If there are 2 options, render them as buttons
            if (options.length === 2) {
                return (
                    <div className="options-container">
                        {options.map((option, index) => (
                            <button
                                key={index} // Added key here
                                onClick={() => handleOptionClick(option)}
                                className={`option ${selectedAnswer === option ? "selected-option" : ""}`}
                            >
                                {option.optionText || option} {/* Make sure to use optionText */}
                            </button>
                        ))}
                    </div>
                );
            }

            // If there are 5 options, render them as a slider
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
                                <span key={index}>
                                    {option.optionText || option} {/* Again, make sure to use optionText */}
                                </span>
                            ))}
                        </div>
                    </div>
                );
            }

            // If there are more than 5 options, render a select dropdown
            if (options.length > 5) {
                return (
                    <select onChange={(e) => handleOptionClick(e.target.value)} value={selectedAnswer}>
                        <option value="">Select an option</option>
                        {options.map((option, index) => (
                            <option key={index} value={option.id || option}> {/* Use option.id if it's an object */}
                                {option.optionText || option} {/* Display optionText */}
                            </option>
                        ))}
                    </select>
                );
            }
        }
        return <p>Loading options...</p>;
    };

    return (
        <>
            <Helmet><title>Quiz Page</title></Helmet>
            {/* SECURITY WARNING - READ THIS CAREFULLY! */}
            <div style={{ backgroundColor: 'yellow', padding: '10px', border: '1px solid red' }}>
                <h2>SECURITY WARNING:</h2>
                <p>This application is for LOCAL TESTING ONLY. The Gemini API key is exposed in the source code and should NEVER be used in a production environment. This key can be used by anyone to access the Gemini API and may incur charges.</p>
                <p>Do not deploy this code to a public server.</p>
            </div>

            <div className="questions">
                {loading ? (
                    <p>Loading questions...</p>
                ) : isQuizEnded ? (
                    <div>
                        <h3>Quiz Summary</h3>
                        <p>Total Questions: {questions.length}</p>
                        <p>Answered Questions: {Object.keys(answers).length}</p>
                        <p>Your Answers:</p>
                        <pre>{JSON.stringify(answers, null, 2)}</pre>
                        <h3>Gemini Analysis:</h3>
                        <p>{geminiResponse}</p> {/* Display Gemini response */}
                    </div>
                ) : questions.length > 0 ? (
                    <>
                        <h2>Quiz Mode</h2>
                        <p>{currentQuestionIndex + 1} of {questions.length}</p>
                        <h5>{questions[currentQuestionIndex]?.questionText || "Loading question..."}</h5>

                        {renderOptions()}

                        <div className="button-container">
                            <button onClick={handlePreviousQuestion} disabled={currentQuestionIndex === 0}>
                                Previous
                            </button>
                            <button onClick={handleNextQuestion} disabled={currentQuestionIndex >= questions.length - 1}>
                                Next
                            </button>
                            <button onClick={handleQuitButtonClick}>Quit</button>
                            {currentQuestionIndex === questions.length - 1 && (
                                <button onClick={endGame} disabled={apiLoading}>
                                    Submit
                                </button>
                            )}
                        </div>
                    </>
                ) : (
                    <p>No questions available.</p>
                )}
            </div>
        </>
    );
};

// import React, { useState, useEffect } from 'react';
// import { Helmet } from 'react-helmet';
// import { useNavigate } from 'react-router-dom';  // React Router v6
// import questionsData from '../../questions.json';

// const Play = () => {
//     const [questions, setQuestions] = useState([]);
//     const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
//     const [currentQuestion, setCurrentQuestion] = useState({});
//     const [answers, setAnswers] = useState({});
//     const [isQuizEnded, setIsQuizEnded] = useState(false);
//     const navigate = useNavigate();  // React Router navigation

//     // useEffect(() => {
//     //     setQuestions(questionsData);
//     //     if (questionsData.length > 0) {
//     //         setCurrentQuestion(questionsData[0]);
//     //     }
//     // }, []);
//     useEffect(() => {
//         fetch('http://localhost:8080/api/questions')
//             .then(response => {
//                 if (!response.ok) {
//                     throw new Error('Failed to fetch questions');
//                 }
//                 return response.json();
//             })
//             .then(data => {
//                 setQuestions(data);
//                 if (data.length > 0) {
//                     setCurrentQuestion(data[0]);
//                 }
//             })
//             .catch(error => console.error('Error fetching questions:', error));
//     }, []);

//     const handleNextQuestion = () => {
//         if (currentQuestionIndex < questions.length - 1) {
//             const newIndex = currentQuestionIndex + 1;
//             setCurrentQuestionIndex(newIndex);
//             setCurrentQuestion(questions[newIndex]);
//         }
//     };

//     const handlePreviousQuestion = () => {
//         if (currentQuestionIndex > 0) {
//             const newIndex = currentQuestionIndex - 1;
//             setCurrentQuestionIndex(newIndex);
//             setCurrentQuestion(questions[newIndex]);
//         }
//     };

//     const handleOptionClick = (option) => {
//         setAnswers((prevAnswers) => ({
//             ...prevAnswers,
//             [currentQuestionIndex]: option,
//         }));
//     };

//     const handleQuitButtonClick = () => {
//         if (window.confirm('Are you sure you want to quit?')) {
//             window.location.href = '/';
//         }
//     };

//     const handleTextInput = (e) => {
//         setAnswers((prevAnswers) => ({
//             ...prevAnswers,
//             [currentQuestionIndex]: e.target.value,
//         }));
//     };

//     const endGame = (e) => {
//         e.preventDefault();
//         const playerStats = {
//             totalQuestions: questions.length,
//             answeredQuestions: Object.keys(answers).length,
//             answers: answers
//         };

//         console.log(playerStats);  // Debugging: Check stats in console

//         setTimeout(() => {
//             navigate('/play/quizSummary', { state: playerStats });  // Correct navigation
//         }, 1000);

//         setIsQuizEnded(true);
//     };

//     const renderOptions = () => {
//         const selectedAnswer = answers[currentQuestionIndex] || '';

//         if (!currentQuestion || !currentQuestion.options) return (
//             <div className="text-input-container">
//                 <textarea
//                     placeholder="Type your answer here..."
//                     value={selectedAnswer}
//                     onChange={handleTextInput}
//                     rows="4"
//                 />
//             </div>
//         );

//         const options = currentQuestion.options;

//         if (options.length === 2) {
//             return (
//                 <div className="options-container">
//                     {options.map((option, index) => (
//                         <button
//                             key={index}
//                             onClick={() => handleOptionClick(option)}
//                             className={selectedAnswer === option ? "selected-option" : ""}
//                         >
//                             {option}
//                         </button>
//                     ))}
//                 </div>
//             );
//         }

//         if (options.length === 5) {
//             return (
//                 <div className="slider-container">
//                     <input
//                         type="range"
//                         min="1"
//                         max="5"
//                         value={options.indexOf(selectedAnswer) + 1}
//                         onChange={(e) => handleOptionClick(options[e.target.value - 1])}
//                     />
//                     <div className="slider-labels">
//                         {options.map((option, index) => (
//                             <span key={index}>{option}</span>
//                         ))}
//                     </div>
//                 </div>
//             );
//         }

//         if (options.length > 5) {
//             return (
//                 <select onChange={(e) => handleOptionClick(e.target.value)} value={selectedAnswer}>
//                     <option value="">Select an option</option>
//                     {options.map((option, index) => (
//                         <option key={index} value={option}>
//                             {option}
//                         </option>
//                     ))}
//                 </select>
//             );
//         }
//     };

//     return (
//         <>
//             <Helmet><title>Quiz Page</title></Helmet>
//             <div className="questions">
//                 {isQuizEnded ? (
//                     <div>
//                         <h3>Quiz Summary</h3>
//                         <p>Total Questions: {questions.length}</p>
//                         <p>Answered Questions: {Object.keys(answers).length}</p>
//                         <p>Your Answers: {JSON.stringify(answers, null, 2)}</p> {/* Display answers */}
//                     </div>
//                 ) : (
//                     <>
//                         <h2>Quiz Mode</h2>
//                         <p>{currentQuestionIndex + 1} of {questions.length}</p>
//                         <h5>{currentQuestion.question || "Loading question..."}</h5>

//                         {renderOptions()}

//                         <div className="button-container">
//                              <button onClick={handlePreviousQuestion} disabled={currentQuestionIndex === 0}>
//                                   Previous
//                              </button>
//                              <button onClick={handleNextQuestion} disabled={currentQuestionIndex >= questions.length - 1}>
//                                  Next
//                             </button>
//                              <button onClick={handleQuitButtonClick}>Quit</button>
//                              {currentQuestionIndex === questions.length - 1 && (
//                                  <button onClick={endGame}>Submit</button>
//                              )}
//                         </div>
//                     </>
//                 )}
//             </div>
//         </>
//     );
// };

// export default Play;

/////////////////////////////////////////////////////////////////////////////////
// import React, { useState, useEffect } from 'react';
// import { Helmet } from 'react-helmet';
// import { useNavigate } from 'react-router-dom';  // React Router v6

// const Play = () => {
//     const [questions, setQuestions] = useState([]);
//     const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
//     const [currentQuestion, setCurrentQuestion] = useState({});
//     const [answers, setAnswers] = useState({});
//     const [isQuizEnded, setIsQuizEnded] = useState(false);
//     const navigate = useNavigate();  // React Router navigation

//     useEffect(() => {
//         // Fetch questions from the API
//         fetch('http://localhost:8080/api/questions')
//             .then((response) => response.json())
//             .then((data) => {
//                 setQuestions(data);
//                 if (data.length > 0) {
//                     setCurrentQuestion(data[0]);
//                 }
//             })
//             .catch((error) => {
//                 console.error("Error fetching questions:", error);
//             });
//     }, []);  // Empty dependency array means it will run once when the component mounts

//     const handleNextQuestion = () => {
//         if (currentQuestionIndex < questions.length - 1) {
//             const newIndex = currentQuestionIndex + 1;
//             setCurrentQuestionIndex(newIndex);
//             setCurrentQuestion(questions[newIndex]);
//         }
//     };

//     const handlePreviousQuestion = () => {
//         if (currentQuestionIndex > 0) {
//             const newIndex = currentQuestionIndex - 1;
//             setCurrentQuestionIndex(newIndex);
//             setCurrentQuestion(questions[newIndex]);
//         }
//     };

//     const handleOptionClick = (option) => {
//         setAnswers((prevAnswers) => ({
//             ...prevAnswers,
//             [currentQuestionIndex]: option,
//         }));
//     };

//     const handleQuitButtonClick = () => {
//         if (window.confirm('Are you sure you want to quit?')) {
//             window.location.href = '/';
//         }
//     };

//     const handleTextInput = (e) => {
//         setAnswers((prevAnswers) => ({
//             ...prevAnswers,
//             [currentQuestionIndex]: e.target.value,
//         }));
//     };

//     const endGame = (e) => {
//         e.preventDefault();
//         const playerStats = {
//             totalQuestions: questions.length,
//             answeredQuestions: Object.keys(answers).length,
//             answers: answers
//         };

//         console.log(playerStats);  // Debugging: Check stats in console

//         setTimeout(() => {
//             navigate('/play/quizSummary', { state: playerStats });  // Correct navigation
//         }, 1000);

//         setIsQuizEnded(true);
//     };

//     const renderOptions = () => {
//         const selectedAnswer = answers[currentQuestionIndex] || '';

//         if (!currentQuestion || !currentQuestion.options) return (
//             <div className="text-input-container">
//                 <textarea
//                     placeholder="Type your answer here..."
//                     value={selectedAnswer}
//                     onChange={handleTextInput}
//                     rows="4"
//                 />
//             </div>
//         );

//         const options = currentQuestion.options;

//         if (options.length === 2) {
//             return (
//                 <div className="options-container">
//                     {options.map((option, index) => (
//                         <button
//                             key={index}
//                             onClick={() => handleOptionClick(option)}
//                             className={selectedAnswer === option ? "selected-option" : ""}
//                         >
//                             {option}
//                         </button>
//                     ))}
//                 </div>
//             );
//         }

//         if (options.length === 5) {
//             return (
//                 <div className="slider-container">
//                     <input
//                         type="range"
//                         min="1"
//                         max="5"
//                         value={options.indexOf(selectedAnswer) + 1}
//                         onChange={(e) => handleOptionClick(options[e.target.value - 1])}
//                     />
//                     <div className="slider-labels">
//                         {options.map((option, index) => (
//                             <span key={index}>{option}</span>
//                         ))}
//                     </div>
//                 </div>
//             );
//         }

//         if (options.length > 5) {
//             return (
//                 <select onChange={(e) => handleOptionClick(e.target.value)} value={selectedAnswer}>
//                     <option value="">Select an option</option>
//                     {options.map((option, index) => (
//                         <option key={index} value={option}>
//                             {option}
//                         </option>
//                     ))}
//                 </select>
//             );
//         }
//     };

//     return (
//         <>
//             <Helmet><title>Quiz Page</title></Helmet>
//             <div className="questions">
//                 {isQuizEnded ? (
//                     <div>
//                         <h3>Quiz Summary</h3>
//                         <p>Total Questions: {questions.length}</p>
//                         <p>Answered Questions: {Object.keys(answers).length}</p>
//                         <p>Your Answers: {JSON.stringify(answers, null, 2)}</p> {/* Display answers */}
//                     </div>
//                 ) : (
//                     <>
//                         <h2>Quiz Mode</h2>
//                         <p>{currentQuestionIndex + 1} of {questions.length}</p>
//                         <h5>{currentQuestion.question || "Loading question..."}</h5>

//                         {renderOptions()}

//                         <div className="button-container">
//                              <button onClick={handlePreviousQuestion} disabled={currentQuestionIndex === 0}>
//                                   Previous
//                              </button>
//                              <button onClick={handleNextQuestion} disabled={currentQuestionIndex >= questions.length - 1}>
//                                  Next
//                             </button>
//                              <button onClick={handleQuitButtonClick}>Quit</button>
//                              {currentQuestionIndex === questions.length - 1 && (
//                                  <button onClick={endGame}>Submit</button>
//                              )}
//                         </div>
//                     </>
//                 )}
//             </div>
//         </>
//     );
// };

// export default Play;
