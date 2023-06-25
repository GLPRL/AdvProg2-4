import { useState } from "react";
import userData from "./../../../../usersData";
import ChatMessage from "./ChatMessage"
function ChatMessages(props) {
let messageArray = props.currentContactMsgs;
const reverseMessageArray = messageArray.slice(0).reverse();

const messages = reverseMessageArray.map((item) => {
            if (item.sender.username == props.user) {
            return(<ChatMessage message={item.content}
                floatValue="float-right" />) 
            } else {
                return(<ChatMessage message={item.content}
                    floatValue="float-left" />) 
            }    
        })

    return(
        <div className="msgScroll" id="msgScroll">
            <table className="table table-borderless ">
            <tbody>
            {    
                messages
            }
            </tbody>
            </table>
        </div>
    );
}
export default ChatMessages;