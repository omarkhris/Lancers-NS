import React, { Fragment } from 'react'; // Import Fragment
import { Link } from 'react-router-dom'; // Import Link
import { Helmet } from 'react-helmet'; // Import Helmet
import '@fortawesome/fontawesome-free/css/all.min.css';
import ruralMindQs from '/Users/jordanjones/Applications/front-end/rural/src/assets/img/Rural-MindQs.png'; // Import the first image
import sliderQsExample from '/Users/jordanjones/Applications/front-end/rural/src/assets/img/SliderQs-example.png'; // Import the second image

export const Quizinstructions = () => (
    <Fragment>
       <Helmet><title>Rural Mind - Quiz Instructions</title></Helmet>
       <div className="instructions container">
            <h1 style={{ color: 'black' }}>Instructions</h1> {/* Changed color to black */}
            <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
                Ensure you read these instructions from start to finish.
            </p>
            <ul className="browser-default" id="main-list" style={{ listStyleType: 'disc', paddingLeft: '20px' }}>
                <li style={{ fontWeight: 'bold', fontSize: '16px', marginBottom: '15px' }}>
                    The form consists of 21 questions
                </li>
                <li style={{ fontWeight: 'bold', fontSize: '16px', marginBottom: '15px' }}>
                    Select the option which best answers the question by selecting it, choosing from a drop down menu, or moving the slider bar.
                </li>
            </ul>
            <img
                src={ruralMindQs}
                alt="Rural Mind Questions Screenshot"
                style={{
                    display: 'block',
                    margin: '20px 0',
                    maxWidth: '50%',
                    height: 'auto',
                }}
            />
            <img
                src={sliderQsExample}
                alt="Slider Questions Example Screenshot"
                style={{
                    display: 'block',
                    margin: '20px 0',
                    maxWidth: '50%',
                    height: 'auto',
                }}
            />
            <div style={{ display: 'flex', justifyContent: 'center', gap: '40px', marginTop: '30px' }}>
                <Link
                    to="/"
                    style={{
                        padding: '10px 20px',
                        textDecoration: 'none',
                        color: 'white',
                        backgroundColor: '#6c757d',
                        borderRadius: '5px',
                        transition: 'background-color 0.3s ease',
                    }}
                    onMouseOver={(e) => (e.target.style.backgroundColor = '#5a6268')}
                    onMouseOut={(e) => (e.target.style.backgroundColor = '#6c757d')}
                >
                    Back to Home
                </Link>
                <Link
                    to="/consent" // Updated to navigate to the consent form
                    style={{
                        padding: '10px 20px',
                        textDecoration: 'none',
                        color: 'white',
                        backgroundColor: '#28a745',
                        borderRadius: '5px',
                        transition: 'background-color 0.3s ease',
                    }}
                    onMouseOver={(e) => (e.target.style.backgroundColor = '#218838')}
                    onMouseOut={(e) => (e.target.style.backgroundColor = '#28a745')}
                >
                    Proceed to Quiz
                </Link>
            </div>
        </div>
    </Fragment>
);