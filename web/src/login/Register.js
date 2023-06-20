import {Link} from 'react-router-dom';
import {Navigate} from 'react-router-dom';
import React, { useState } from 'react';
import FormGroupRegister from "./FormGroupRegister";
import {isLoggedIn} from './Login'
export const registerData = [];
function imageToBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onloadend = () => {
            const base64String = reader.result.split(",")[1];
            resolve(base64String);
        };

        reader.onerror = () => {
            reject(new Error("Failed to read the image."));
        };

        reader.readAsDataURL(file);
    });
}
function Register(){

    const [password, setPassword] = useState('');
    const [passwordError1, setPasswordError1] = useState('');
    const [passwordError2, setPasswordError2] = useState('');
    const [verifyPassword, setVerifyPassword]=useState('');
    const [verifyError1, setVerifyError1]=useState('');
    const [username, setUsername]= useState('');
    const [usernameError, setUsernameError]= useState('');
    const [usernameError2, setUsernameError2]= useState('');
    const [displayName, setDisplay]=useState('');
    const [displayError1, setDisplayError1]=useState('');
    const [displayError2, setDisplayError2]=useState('');
    const [chooseImage,setImage]=useState('');
    const [imageError, setImageError]=useState('');
    const [shouldNavigate, setShouldNavigate] = useState(false);
    const [showPopup, setShowPopup] = useState(true);
    const [popupClosed,setPopupClosed]=useState(false)

    function handlePopupClose() {
        setPopupClosed(true);
        setShowPopup(false);
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        let validSubmission=1;
        if (password.length < 8) {
            setPasswordError1('Password must be at least 8 characters long.');
            validSubmission=0;
        }
        if (!/\d/.test(password) || !/[a-zA-Z]/.test(password)){
            setPasswordError2('Password must contain both letters and numbers');
            validSubmission=0;
        }
        if(verifyPassword != password){
            setVerifyError1('Does not match password');
            validSubmission=0;
        }
        if(username.length<2||username.length>16){
            setUsernameError('2-16 characters');
            validSubmission=0;
        }
        if(registerData.find(item => item.username === username)){
            setUsernameError2('username already in use')
            validSubmission=0;
        }
        if(displayName.length<2||displayName.length>16){
            setDisplayError1('2-16 in len');
            validSubmission=0;
        }
        if(!/^[a-zA-Z\s]+$/.test(displayName)){
           setDisplayError2('must be only letters') ;
            validSubmission =0;
        }
        if(!chooseImage){
            setImageError('No image chosen');
            validSubmission =0;
        }
            if (validSubmission) {
             const  response = await fetch('http://localhost:5000/api/Users', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: username,
                        password: password,
                        displayName: displayName,
                        profilePic: chooseImage,
                    }),
                })
                if (response.status !== 200){
                    setUsernameError("username already exists");
                    return;
                }


                setShouldNavigate(true);
            }
    };
    const handleImage =(event) => {
        const file = event.target.files[0];
        imageToBase64(file)
            .then((base64String) => {
                setImage(base64String);
                setImageError('');
            });
    }
    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
        setPasswordError1('');
        setPasswordError2('');
    };
    const handleVerify = (event) => {
        setVerifyPassword(event.target.value);
        setVerifyError1('');
    };

    const handleUsername =(event) =>{
        setUsername(event.target.value);
        setUsernameError('');
        setUsernameError2('');
    }

    const handleDisplay=(event)=>{
        setDisplay(event.target.value);
        setDisplayError1('');
        setDisplayError2('');
    }
    isLoggedIn.value = false;
    if(shouldNavigate){
        return(
            <>
                <head>
                    <link rel="stylesheet"
                          href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
                          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                          crossOrigin="anonymous"></link>
                </head>
                <body>
                {showPopup && (
                    <div
                        className="modal show modalRegister"
                        tabIndex="-1"
                        role="dialog"
                    >
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title">You have successfully registered!</h5>
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close" onClick={handlePopupClose}><span aria-hidden="true">&times;</span></button>
                                </div>
                                <div className="modal-body">
                                    <p>Press the continue button in order to get to log-in page</p>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-info" data-dismiss="modal" onClick={handlePopupClose}>Continue</button>
                                </div>
                            </div>
                        </div>
                    </div>
                )}
        {popupClosed && <Navigate to="/" />}
                </body>
            </>
        );
    }
 return(
<>
     <head>
         <title>Hello, world!</title>
             <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"></meta>
             <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossOrigin="anonymous"></link>
     </head>
    <body>
    <div className="center register text" >
        <div className="login">
            <h1 className="margin5 text">Register</h1>
            <form onSubmit={handleSubmit}>
                <FormGroupRegister label="Username" type="text" id="username" placeholder="Enter Username" value={username} onChange={handleUsername} error1={usernameError} error2={usernameError2} smallText="Must be 2-16 characters" />
                <FormGroupRegister label="Password" type="password" id="password" placeholder="Enter Password" value={password} onChange={handlePasswordChange} error1={passwordError1} error2={passwordError2} smallText="Your password must be at least 8 characters long and contain at least one number and letter " />
                <FormGroupRegister label="Verify Password" type="password" id="password-verify" placeholder="Verify Password" value={verifyPassword} onChange={handleVerify} error1={verifyError1} />
                <FormGroupRegister label="Display Name" type="text" id="DisplayName" placeholder="Enter Display Name" value={displayName} onChange={handleDisplay} error1={displayError1}  error2={displayError2} smallText="Only letters 2-16 in length" />
                <div className="form-group">
                    <small>Choose profile picture</small><input type="file" className="form-control-file" id="exampleFormControlFile1" onChange={handleImage} />
                    {imageError && <small className="form-text text-danger">{imageError}</small> }
                </div>
                <div className="form-group">
                    {chooseImage && (
                        <div>
                            Selected Image:
                            <img
                                src={`data:image/jpeg;base64, ${chooseImage}`}
                                alt="Profile Picture"
                                className="text-center marginLeft1"
                                id="chooseImg"
                            />
                        </div>
                    )}
                </div>
                <div className="text-center">
                    <button type="submit" className="btn btn-info marginRight">Register</button>
                    <span className="txtsize"> Already registered? click <Link to="/" className="orangelink">here</Link></span>
                </div>
            </form>
        </div>

        <br />
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossOrigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossOrigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossOrigin="anonymous"></script>
    </body>
    </>
 )
}
export default Register;
