function RegisterModal({ onClose }) {
    return (
        <div className="modal show modalRegister" tabIndex="-1" role="dialog">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">You have successfully registered!</h5>
                        <button type="button" className="close" data-dismiss="modal" aria-label="Close" onClick={onClose}>
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div className="modal-body">
                        <p>Press the continue button in order to get to log-in page</p>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-info" data-dismiss="modal" onClick={onClose}>
                            Continue
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default RegisterModal;