import { Fragment } from 'react';
import { Helmet } from 'react-helmet';
import { Link } from 'react-router-dom';
import '../styles/componets/_home.scss';


export const Home = () => {
  return (
    <Fragment>
      <Helmet>
        <title>Rural Mind - Home</title>
      </Helmet>
      <div id="home">
        <section>
          <div style={{ textAlign: 'center' }}>
            <span className="mdi mdi-cube-outline cube"></span>
          </div>
          <h1>Rural Mind</h1>
          <div className="start-button-container">
            <ul list-style-type="none">
              <li  ><Link className= "start-button" to="start/instructions">Start</Link></li>
            </ul>
          </div>
          <div className="auth-container">
            <Link to="/login" className="auth-buttons" id="login-button" >Login</Link>
            <Link to="/register"className="auth-buttons" id="signup-button">Register</Link>
          </div>
        </section>
      </div>
    </Fragment>
  );
};
