import React, { Fragment } from 'react';
import { Link } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import answer from '/Users/jordanjones/Applications/front-end/rural/src/assets/img/question-screenshot.png';
import fiftyFifty from '/Users/jordanjones/Applications/front-end/rural/src/assets/img/question-screenshot.png';
import '@fortawesome/fontawesome-free/css/all.min.css';



// import hints 
export const Quizinstructions = () => (
    <Fragment>
       < Helmet><title>Rural Mind - Quiz Instructions</title></Helmet>
       <div className="instructions container">
    <h1>Instructions</h1>
    <p>Ensure you read these instructions from start to finish.</p>
    <ul className="browser-default" id="main-list">
        <li>The form consists of 21 questions</li>
        <li>There are three types of questions. Yes or No, Ranking from 1-5, and optional short answer text questions.
            <img src={answer} alt="Rural Mind options example" />
        </li>
        <li>Select the option which best answers the question by clicking (or selecting) it.
            <img src={fiftyFifty} alt="Thats Image" />
        </li>
        <li>
            Each game has 2 lifetimes namely:
            <ul id="sublist">
                <li>2 50-50 chances</li>
                <li>5 Hints</li>
            </ul>
        </li>
        <li>
            Selecting a 50-50 lifeline by clicking the icon 
            <i className="fa-regular fa-lightbulb"></i> will remove 2 wrong answers, leaving the correct answer and one wrong answer.
            <img src={fiftyFifty} alt="Rural Mind fifty-Fifty example" />
        </li>
    </ul>
    <div>
        <span className="left">
            <Link to="/">Back to Home</Link>
        </span>
        <span className="right">
            <Link to="/play/quiz">Proceed to Questions</Link>
        </span>
    </div>
</div>

    </Fragment>
);

