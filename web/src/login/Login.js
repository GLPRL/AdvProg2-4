import {Link, Navigate} from 'react-router-dom';
import {useRef, useState} from "react";
import {registerData} from './Register'
import FormGroupLogin from "./FormGroupLogin";

export var reqUsername;
export const isLoggedIn = {value: false};
function Login( { setToken,setUser}){
    const usernameRef = useRef(null);
    const passwordRef = useRef(null);
    const passwordError = useRef (null);
    const usernameError = useRef(null);
    const [shouldNavigate, setShouldNavigate] = useState(false);
    const handleSubmit = async (event) => {
        event.preventDefault(); // prevent the default form submission behavior
        let validLogin = true;
        const username = usernameRef.current.value;
        const password = passwordRef.current.value;


        const response = await fetch('http://localhost:5000/api/Tokens', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'accept': '*/*'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        if (response.status !== 200) {
            usernameError.current.textContent='Wrong username';
            passwordError.current.textContent='Wrong password';
            return; // Exit the function if response status is not 200
        }

        const data = await response.text(); // Parse the response body as JSON
         setToken(data);
         setUser(username);
        isLoggedIn.value = true;
        setShouldNavigate(true);
        reqUsername = username;
    };
    const handlePasswordChange =(event) =>{
        passwordError.current.textContent='';
    }
    const handleUsernameChange = (event) => {
        usernameError.current.textContent='';
    }
    if(shouldNavigate) {
        return(<Navigate to="/chat" />)
    }
    isLoggedIn.value = false;
    return(

        <>

            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"></meta>
                        <link rel="stylesheet"
                              href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
                              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                              crossOrigin="anonymous"></link>
                            <title>Hello, world!</title>
            </head>
            <body>
        <div className="center logreg">
            <div className="login">
                <h1 className="margin5 text">Sign-in</h1>
                <form onSubmit={handleSubmit}>

                    <FormGroupLogin label="Username" id="username" placeholder="Enter username" type="text" onChange={handleUsernameChange} inputRef={usernameRef} errorRef={usernameError}
                    />
                    <FormGroupLogin label="Password" id="password" placeholder="Enter password" type="password" onChange={handlePasswordChange} inputRef={passwordRef} errorRef={passwordError}
                    />
                    <button type="submit" className="btn btn-info  text margin5">Log in</button>     <Link to="/register" className="btn btn-dark text" role="button">Sign Up</Link>
                </form>
            </div>
            <br></br>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossOrigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossOrigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossOrigin="anonymous"></script>
            </body>
        </>
    )
}
export default Login;