const mongoose = require('mongoose');


const chatColSchema = new mongoose.Schema({
    _id: {
        type: Number,
        required: false
    },
    userOne: {
        type: String,
        required: false
    },
    userTwo: {
        type: String,
        require: false
    }
});

const ChatCol = mongoose.model('ChatCol', chatColSchema);

module.exports = ChatCol;