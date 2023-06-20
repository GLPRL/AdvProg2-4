import React from 'react';
const FormGroupRegister = ({ label, type, id, placeholder, value, onChange, error1,error2, smallText }) => {
    return (
        <div className="form-group">
            <label htmlFor={id}>{label}</label>
            <div className="placeregister">
                <input type={type} className="form-control" id={id} placeholder={placeholder} value={value} onChange={onChange} />
                <small> {smallText} </small>
                {error1 && <small className="form-text text-danger">{error1}</small>}
                {error2 && <small className="form-text text-danger">{error2}</small>}
            </div>
        </div>
    );
};
export default FormGroupRegister;