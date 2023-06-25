import ContactTab from "./ContactTab/ContactTab";
import ChatMessages from "./ChatMessages/ChatMessages";
import ChatInteraction from "./ChatInteraction/ChatInteraction";
function Right(props) {

    return(
        <div className="contactBox border-right col-8">
            <ContactTab currentContactDisplayName={props.currentContactDisplayName} currentContactImage={props.currentContactImage}/>
            <ChatMessages user={props.user} currentUser={props.currentUser} token={props.token} firstPrint={props.firstPrint} setfirstPrint={props.setfirstPrint} 
                            currentContactMsgs={props.currentContactMsgs}/>
            <ChatInteraction currentContactDisplayName={props.currentContactDisplayName} setUserContacts={props.setUserContacts} setContactIdAndTime={props.setContactIdAndTime} currentContactMsgs={props.currentContactMsgs}
                            setCurrentContactMsgs={props.setCurrentContactMsgs} currentUser={props.currentUser} token={props.token} firstPrint={props.firstPrint} setfirstPrint={props.setfirstPrint} />
        </div>
    );
}
export default Right;