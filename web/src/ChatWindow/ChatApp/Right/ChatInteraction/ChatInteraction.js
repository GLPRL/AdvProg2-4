import React from "react";
import io from "socket.io-client"
import {useEffect} from "react";

export const socket = io.connect("http://localhost:5000")

function ChatInteraction(props) {
    async function handleClick() {
        if (props.currentUser == 0) {
            document.getElementById("outText").value = "";
            return;
        }
        const content = document.getElementById("outText").value;
        if (content === "") {
            return;
        }
        const contactUser = document.getElementById("contactUser").value;
        if (contactUser === "") {
            return;
        }
        let autor = 'Bearer ' + props.token
        let userAdress = 'http://localhost:5000/api/Chats/' + props.currentUser + '/Messages'
        const response = await fetch(userAdress, {
            method: 'POST',
            headers: {
                'accept': 'text/plain',
                'Authorization': autor,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                msg: content
            }),
        })

        const responseGet = await fetch(userAdress, {
            method: 'GET',
            headers: {
                'Authorization': autor,
                'accept': 'text/plain',
            }
        })

        const responseGetContacts = await fetch('http://localhost:5000/api/Chats/', {
            method: 'GET',
            headers: {
                'Authorization': autor,
                'accept': 'text/plain',
            }
        })
        const contacts = await responseGetContacts.json();  //->
        props.setUserContacts(contacts);
        const contactMessages = await responseGet.json();
        props.setCurrentContactMsgs(contactMessages);
        const newMsg = {text: content, floatValue: "float-right"};
        document.getElementById("outText").value = "";
        socket.emit("newMessage", props.currentUser);
        const element = document.getElementById("msgScroll");
        element.scrollTop = element.scrollHeight;
    }

    const chat = props.currentUser;
    useEffect(() => {
        socket.on("receiveMessage", handleMessageReceived);

        return () => {
            socket.off("receiveMessage", handleMessageReceived);
        };
    }, [props.currentUser]);

    const handleMessageReceived = async (chatID) => {

        let autor = 'Bearer ' + props.token
        let x= chatID-props.currentUser;
        let p=x;
        const responseGetContacts = await fetch('http://localhost:5000/api/Chats/', {
            method: 'GET',
            headers: {
                'Authorization': autor,
                'accept': 'text/plain',
            }
        })
        const contacts = await responseGetContacts.json();  //->
        props.setUserContacts(contacts);
        if (props.currentUser === chatID  ) {
            let userAddress = 'http://localhost:5000/api/Chats/' + props.currentUser + '/Messages'

            const responseGet = await fetch(userAddress, {
                method: 'GET',
                headers: {
                    'Authorization': autor,
                    'accept': 'text/plain',
                }
            })
            const contactMessages = await responseGet.json();
            if(props.currentUser === chatID && p===0 && x===0){
                props.setCurrentContactMsgs(contactMessages);
            }

        }
        const element = document.getElementById("msgScroll");
        element.scrollTop = element.scrollHeight;
    }

    return (
        <div className="sendLine">
            <input type="text" id="outText" className="textOut"></input>
            <button className="btn btn-success outbtn" onClick={handleClick}>Send</button>
        </div>
    );
}

export default ChatInteraction;