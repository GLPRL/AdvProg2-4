import './App.css';
import './stylesheets/chatWindow.css'
import './stylesheets/login.css'
import './images/background.jpg'
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from './login/Login'
import Register from './login/Register'
import ChatWindow from './ChatWindow/ChatWindow'
import {useState} from "react";

function App() {

    const [token, setToken] = useState(null);
    const [user, setUser] = useState(null);
    return (
        <Router>
            <html lang="en">
            <Routes>
                <Route path="/" element={<Login setToken={setToken} setUser={setUser}/>}/>
                <Route path="/register" element={<Register setToken={setToken}/>}/>
                <Route path="/chat" element={<ChatWindow token={token} user={user}/>}/>
            </Routes>
            </html>
        </Router>
    );
}

export default App;
