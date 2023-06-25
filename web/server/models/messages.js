const mongoose = require('mongoose');

const allMessagesSchema = new mongoose.Schema({
    _id: {
        type: Number,
        required: false
    },
    messages: {
        type: [ {_id: {
            type: Number,
            required: false
        },
        created: {
            type: String,
            required:false,
        },
        sender: {
            username: {
                type: String,
                required: false
            }
        },
        content: {
            type: String,
            required:false
        } } ],
        default: []
    }
})


const Message = mongoose.model('Message', allMessagesSchema);

module.exports = Message;