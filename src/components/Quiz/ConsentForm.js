import React, { Fragment, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Helmet } from 'react-helmet';

export const ConsentForm = () => {
  useEffect(() => {
    // Scroll to top when the component is mounted
    window.scrollTo(0, 0);
  }, []); // Empty array ensures it runs only on mount

  return (
    <Fragment>
      <Helmet>
        <title>Rural Mind - Consent Form</title>
      </Helmet>
      <div
        className="consent-form container"
        style={{ maxWidth: '800px', margin: '0 auto', padding: '20px' }}
      >
        <h1 style={{ color: 'black', textAlign: 'center' }}>Consent Form</h1>
        <div style={{ marginBottom: '30px' }}>
          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Explanation of research and what you will be doing:
          </p>
          <p style={{ marginBottom: '20px' }}>
            You must be at least 18 years of age to participate in this study. You are being asked to participate in a research study that will help recognize the biggest stress factors for production agriculture workers. This research can benefit people employed by the production agriculture industry as well as rural agriculture communities. After you read this consent form, we will ask you to answer 21 questions. This entire survey should take approximately 10 minutes.
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Research Benefits and Risks:
          </p>
          <p style={{ marginBottom: '20px' }}>
            The benefit of this research is to help identify the biggest stress factors for production agriculture workers in order to help the public and mental health professionals recognize which individuals need mental health services. The risks of this survey are minimal, but it may induce emotional reactions. You may exit the survey at any time. If the survey were to cause an emotional reaction, there are resources available like the SAMHSA’s National Helpline. The hotline, "is a free, confidential, 24/7, 365-day-a-year treatment referral and information service for individuals and families facing mental and/or substance use disorders." The number for the hotline is 1-800-662-HELP (4357).
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Voluntary Participation:
          </p>
          <p style={{ marginBottom: '20px' }}>
            Participation in this study is completely voluntary. If you decide not to participate, there will be no consequences. Please be aware that if you decide to participate, you may stop participating at any time, and you may decide not to answer any specific question. By signing this form, you are attesting that you have read and understand the information above, and you freely give my consent/assent to participate.
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Your rights to participate, say no, or withdraw:
          </p>
          <p style={{ marginBottom: '20px' }}>
            Participation in this research project is voluntary. You have the right to say no. You may change your mind at any time. You may stop participating at any time.
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Costs and Compensation for being in the study:
          </p>
          <p style={{ marginBottom: '20px' }}>
            You will not be compensated for your time. You will incur no costs.
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Confidentiality of the data:
          </p>
          <p style={{ marginBottom: '20px' }}>
            Your confidentiality will be protected to the maximum extent allowed by the law. No identifying information will be collected. The survey data collected for this research study will be protected on a password-protected computer with duo authentication. Only the North Scott School District's Science Fair Designated Supervisor will have access to the data. The data will then be provided to the appointed researchers with no identifying information.
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Online Survey:
          </p>
          <p style={{ marginBottom: '20px' }}>
            There is always the possibility of tampering from an outside source when using the internet for collecting information. Confidentiality of your responses will be protected once the data is downloaded from the internet; there is always a possibility of hacking or other security breaches that could threaten the confidentiality of your responses. Know that you are free to decide not to answer any questions.
          </p>

          <p style={{ fontWeight: 'bold', fontSize: '18px', marginBottom: '20px' }}>
            Contact Information:
          </p>
          <p style={{ marginBottom: '20px' }}>
            If you have any questions or concerns, please contact our Science Fair Designated Supervisor Jacob Hunter at{' '}
            <a href="mailto:jacob.hunter@north-scott.k12.ia.us">jacob.hunter@north-scott.k12.ia.us</a>.
          </p>
        </div>
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
            to="/play/quiz"
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
            Proceed to Questions
          </Link>
        </div>
      </div>
    </Fragment>
  );
};
