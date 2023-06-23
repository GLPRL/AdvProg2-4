const mongoose = require('mongoose');

const firebaseTokenSchema = new mongoose.schema({
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

const fbToken = mongoose.model("fbtoken", firebaseTokenSchema);

module.exports = fbToken;