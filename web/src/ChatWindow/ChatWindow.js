import ChatApp from "./ChatApp/ChatApp"
import {Link, Navigate} from "react-router-dom";
import {isLoggedIn} from "../login/Login"
import "../stylesheets/chatWindow.css"
import AddContactModal from "../AddContactModal/AddContactModal";
import userData from "../usersData"
import React, { useState } from "react";

function ChatWindow({ setToken, token,user }) {
    const [idCount, setIdCount] = useState(0);
    const [userContacts,setUserContacts] = useState([]);
    const [currentContactImage, setCurrentContactImage] = useState("")
    const [currentContactDisplayName, setCurrentContactDisplayName] = useState(null);

    function handleIdCount() {
        setIdCount(idCount + 1);
    }

    function onClickLogout() {
        userData.splice(0,idCount);
    }
    if(!isLoggedIn.value) {
        return(<Navigate to="/" />)
    }

    return (
        <>
            <head>
                <link rel="stylesheet"
                      href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
                      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                      crossOrigin="anonymous"></link>
                <link rel="stylesheet"
                      href="/src/stylesheets/chatWindow.css"></link>
            </head>

            <Link to="/" role="button" className="btn-sm btn-danger logoutbutton text" onClick={onClickLogout}>Logout</Link>
            <ChatApp setCurrentContactDisplayName={setCurrentContactDisplayName} currentContactDisplayName={currentContactDisplayName} currentContactImage={currentContactImage} 
                    setCurrentContactImage={setCurrentContactImage} user={user} token={token} userContacts={userContacts} setUserContacts={setUserContacts}/>
            <AddContactModal idCount={idCount} handleIdCount={handleIdCount} token={token} setUserContacts={setUserContacts}/>
        </>
    )
}
export default ChatWindow;