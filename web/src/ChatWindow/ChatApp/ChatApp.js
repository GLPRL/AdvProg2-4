import Left from "./Left/Left";
import Right from "./Right/Right";
import { useState } from "react";
import { useEffect } from "react";
function ChatApp(props) {
    const [currentUser, setCurrentUser] = useState(0);
    const [firstPrint, setfirstPrint] = useState(true);
    const [currentChatId, setCurrentChatId] = useState(null);
    const [currentContactMsgs, setCurrentContactMsgs] = useState([])
    const [contactIdAndTime,setContactIdAndTime] = useState([null,null]);

    useEffect(()=>{
    },[currentUser]);

    console.log("ChatApp CURRENTUSER: " + currentUser);

    return (
        <div className="container content row no-gutters text">
            <Left contactIdAndTime={contactIdAndTime} setCurrentContactDisplayName={props.setCurrentContactDisplayName} 
                  setCurrentContactImage={props.setCurrentContactImage} setCurrentUser={setCurrentUser}
                  user={props.user} userContacts={props.userContacts} token={props.token} setCurrentChatId={setCurrentChatId} 
                  setCurrentContactMsgs={setCurrentContactMsgs} setUserContacts={props.setUserContacts}/>
            <Right setContactIdAndTime={setContactIdAndTime} currentContactDisplayName={props.currentContactDisplayName} 
                   currentContactMsgs={currentContactMsgs} currentContactImage={props.currentContactImage} 
                   currentUser={currentUser} user={props.user} firstPrint={firstPrint} userContacts={props.userContacts}
                   token={props.token} setfirstPrint={setfirstPrint} setCurrentContactMsgs={setCurrentContactMsgs} 
                   setUserContacts={props.setUserContacts}
                   />
        </div>
    );
}
export default ChatApp;