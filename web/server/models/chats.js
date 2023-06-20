const mongoose = require('mongoose');


const lastMsgSchema = new mongoose.Schema({
    _id: {
        type: Number,
        required: false
    },
    created: {
        type: String,
        required:false
    },
    content: {
        type: String,
        required:false
    }
})

const chatSchema = new mongoose.Schema({
    _id: {
        type: Number,
        required: false
    },
    user: {
        username: {
            type: String,
            required: false
        },
        displayName: {
            type: String,
            required: false
        },
        profilePic: {
            type: String,
            required: false
        }
    },
    lastMessage: {
        type:lastMsgSchema,
        default: null,
        required: false
    },
});

const Chat = mongoose.model('Chat', chatSchema);

module.exports = Chat;

