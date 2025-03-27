import React, { Fragment } from 'react';
import { Link } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import answer from '../../assets/img/question-screenshot.png';
import fiftyFifty from '../../assets/img/question-screenshot.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'; // Import FontAwesomeIcon
import { faAsterisk } from '@fortawesome/free-solid-svg-icons'; // Import the specific icon
import { faBookmark } from '@fortawesome/free-regular-svg-icons'; // Import the regular bookmark icon
import '@fortawesome/fontawesome-free/css/all.min.css';
import ruralMindQs from '../../assets/img/Rural-MindQs.png';
import sliderQsExample from '../../assets/img/SliderQs-example.png';
import './QuizInstructions.scss'; // Import the SCSS file

export const Quizinstructions = () => (
    <Fragment>
        <Helmet><title>Rural Mind - Quiz Instructions</title></Helmet>
        <div className="instructions container">
            <h1>Instructions</h1>
            <h1 className='instructions-point'><FontAwesomeIcon icon={ faBookmark } /> Ensure you read these instructions from start to finish.</h1>
            {/* <ul className="browser-default" id="main-list"> */}
                <h1 className='instructions-point'> <FontAwesomeIcon icon={faAsterisk} style={{color:"red"}} />  The form consists of 21 questions</h1>
                <h1 className='instructions-point'><FontAwesomeIcon icon={faAsterisk} style={{color:"red"}} /> Select the option which best answers the question by selecting it, <br/> choosing from a drop down menu, <br></br> or moving the slider bar.</h1>
            {/* </ul> */}
            <img className='screen-ex' src={ruralMindQs} alt="Rural Mind Questions Screenshot" />
            <img className='screen-ex' src={sliderQsExample} alt="Slider Questions Example Screenshot" />
            <div className="button-container">
                <Link to="/" className="back-button">Back to Home</Link>
                <Link to="/consent" className="proceed-button">Proceed to Quiz</Link>
            </div>
        </div>
    </Fragment>
);