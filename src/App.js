import './App.css';
import { Routes, Route } from 'react-router-dom';
import { Home } from './components/Home.js';
import { Quizinstructions } from './components/Quiz/Quizinstructions.js';
import QuizSummary from './components/Quiz/QuizSummary.js';
import { Play } from './components/Quiz/Play.js';
import { ConsentForm } from './components/Quiz/ConsentForm.js'; // Import the new ConsentForm component
function App() {
  return (
    <div>
      <Routes>
        {/* Route for Home */}
        <Route path="/" element={<Home />} />
s
        {/* Route for Quiz Instructions */}
        <Route path="/start/instructions" element={<Quizinstructions />} />
        <Route path="play/Quiz" element={<Play />} />
        <Route path="Summary" element={<QuizSummary />} />
        {/* Route for Consent Form */}
        <Route path="/consent" element={<ConsentForm />} />
        {/* Route for Quiz Play */}
        <Route path="/play/quiz" element={<Play />} />
      </Routes>
    </div>
  );
}

export default App;
