import {registerData} from "../../../../login/Register";
import {reqUsername} from "../../../../login/Login"
import React from "react";
import {useState} from "react";
function  UserTab(props) {
    const [userData, setUserData] = useState(null);

    const getUserData = async () => {

            const response = await fetch(`http://localhost:5000/api/Users/${props.user}`, {
                headers: {
                    accept: "text/plain",
                    Authorization: `Bearer ${props.token}`
                }
            });
            if (response.ok) {
                const data = await response.json();
                setUserData(data);

            }

    };

    if (!userData) {
        getUserData();
    }
    return (
        <div className="userTab">
            {userData && (
                <>
                    <img src={`data:image/jpeg;base64, ${userData.profilePic}`} alt="Profile Picture" className="user-tab-left-image rounded-circle" id="chooseImg" />
                    <span className="userName userTopLeft">{userData.displayName}</span>
                </>
            )}
            <span>
      <svg xmlns="http://www.w3.org/2000/svg" fill="white" className="addContact" viewBox="0 0 16 16" data-toggle="modal" data-target="#exampleModal">
        <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
        <path fillRule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z" />
      </svg>
    </span>
        </div>
    );

}
export default UserTab;