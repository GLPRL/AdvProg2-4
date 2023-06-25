import '../../../../stylesheets/chatWindow.css'
import pic from '../../../../images/default.jpeg'
function ContactTab(props) {
    if (props.currentContactDisplayName === null) {
        return(
            <div className="userTab">
                <img src= {pic} alt="" className="user-tab-right-image rounded-circle" id="contactImage"></img>
                <span className="userName userTopLeft" id="contactUser"></span>
                <span className="userName userTopLeft" id="contactUser">{props.currentContactDisplayName}</span>
            </div>
        );
    } else {
        return (
            <div className="userTab">
                <img src={`data:image/jpeg;base64, ${props.currentContactImage}`} alt=""
                     className="user-tab-right-image rounded-circle" id="contactImage"></img>
                <span className="userName userTopLeft" id="contactUser"></span>
                <span className="userName userTopLeft" id="contactUser">{props.currentContactDisplayName}</span>
            </div>
        );
    }
}
export default ContactTab;