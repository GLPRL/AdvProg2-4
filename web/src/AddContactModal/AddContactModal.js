import ModalFooter from "./ModalFooter/ModalFooter";
import ModalBody from "./ModalBody/ModalBody";
import ModalHeader from "./ModalHeader/ModalHeader";
function AddContactModal(props) {
    return (
        <div className="modal fade" id="exampleModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <ModalHeader />
                    <ModalBody />
                    <ModalFooter idCount={props.idCount} handleIdCount={props.handleIdCount} token={props.token} setUserContacts={props.setUserContacts}/>
                </div>
            </div>
        </div>
    );
}
export default AddContactModal;