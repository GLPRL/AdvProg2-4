import React from 'react';
import userData from '../../usersData'

function ModalFooter(props) {
    async function handleClick() {
        const input = document.getElementById("modalInput");
        const inputValue = input.value.trim();
        if (!inputValue) {
            alert("Enter Valid User Information");
            document.getElementById("modalInput").value = "";
            return;
        }
        let autor = 'Bearer ' + props.token
        let postResult = fetch('http://localhost:5000/api/Chats', {
                    method: 'POST',
                    headers: {
                        'accept': '*/*',
                        'Authorization': autor,
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: inputValue
                    }),
                })

                .then(async postResult => {
                    if (postResult.status == 200) {
                        const response  = await fetch('http://localhost:5000/api/Chats',{
                        method: 'GET',
                        headers: {
                            'Authorization': autor,
                            'accept': 'text/plain',
                        }
                    })
                        const contacts = await response.json();
                        props.setUserContacts(contacts);
                    }
                    
                })
    }
    return (
        <div className="modal-footer">
            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button add" id="addContact" className="btn btn-primary" data-dismiss="modal" onClick={handleClick}>Add</button>
        </div>
    );
}
export default ModalFooter;