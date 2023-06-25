function FormGroupLogin ({ label, id, placeholder, type, onChange, inputRef, errorRef }){
    return (
        <div className="form-group text">
            <label htmlFor={id}>{label}</label>
            <div className="place">
                <input
                    className="form-control"
                    id={id}
                    placeholder={placeholder}
                    type={type}
                    onChange={onChange}
                    ref={inputRef}
                />
                <small className="text-danger" ref={errorRef}></small>
            </div>
        </div>
    );
}
export default FormGroupLogin;