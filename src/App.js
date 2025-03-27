import './App.css';
import { Routes, Route } from 'react-router-dom';

import { Home } from './components/Home.js';
import { Quizinstructions } from './components/Quiz/Quizinstructions.js';
import QuizSummary from './components/Quiz/QuizSummary.js';
import { Play } from './components/Quiz/Play.js';
function App() {
  return (
    <div>
      <Routes>
        {/* Route for Home */}
        <Route path="/" element={<Home />} />

        {/* Route for Quiz Instructions */}
        <Route path="/start/instructions" element={<Quizinstructions />} />
        <Route path="play/Quiz" element =  {<Play />}/>
        <Route path="play/quizSummary" element = {<QuizSummary />} />
        {/* <Route path="/login" component={Login} /> */}
        {/* <Route path="/register" component={Register} /> */}

      </Routes>
    </div>
  );
}
export default App;
