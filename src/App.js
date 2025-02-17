import './App.css';
import { Routes, Route } from 'react-router-dom';

import { Home } from './components/Home';
import { Quizinstructions } from './components/Quiz/Quizinstructions';
import { ConsentForm } from './components/Quiz/ConsentForm'; // Import the new ConsentForm component
import Play from './components/Quiz/Play';
import QuizSummary from './components/Quiz/QuizSummary';

function App() {
  return (
    <div>
      <Routes>
        {/* Route for Home */}
        <Route path="/" element={<Home />} />

        {/* Route for Quiz Instructions */}
        <Route path="/start/instructions" element={<Quizinstructions />} />

        {/* Route for Consent Form */}
        <Route path="/consent" element={<ConsentForm />} />

        {/* Route for Quiz Play */}
        <Route path="/play/quiz" element={<Play />} />

        {/* Route for Quiz Summary */}
        <Route path="/play/quizSummary" element={<QuizSummary />} />
      </Routes>
    </div>
  );
}

export default App;
