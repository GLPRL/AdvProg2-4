const mongoose = require('mongoose');

const firebaseTokenSchema = new mongoose.Schema({
    username: {
        type: String,
        required: false
    },
    token: {
        type: String,
        require: false,
        default: null
    }
});

const fbToken = mongoose.model('fbToken', firebaseTokenSchema);

module.exports = fbToken;