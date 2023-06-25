function ChatMessage({message, floatValue}) {
    if (floatValue == "float-right") {
        const classN = ["bg-warning p-2 border rounded-pill shadow-lg mb-3", floatValue].join(' ');
        return( 
            <tr>
                <td>
                    <p className={classN} >{message}</p>
                </td>
            </tr>);
    } else {
        const classN = ["bg-info p-2 border rounded-pill shadow-lg mb-3", floatValue].join(' ');
        return( 
            <tr>
                <td>
                    <p className={classN} >{message}</p>
                </td>
            </tr>);
    }
}
export default ChatMessage;