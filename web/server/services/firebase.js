const mongoose = require('mongoose');
const fbToken = require('../models/firebase');
const {getMessaging} = require("firebase-admin/messaging");

const sendMessage = async (srcUser, destUser) => {

    const firebaseCollection = mongoose.model("fbtokens", fbToken.schema, "fbtokens");
    const user = await firebaseCollection.findOne({username: destUser}).exec();     //find token of the destination user
    if (user.token == "") {           //user not found or not android user
        return
    }

    const message = {               //build message to send
        notification: {
            title: srcUser
        },
        token: user.token
    };


    getMessaging().send(message)                //send message
        .then((response) => {
            //do nothing
        })
        .catch((error) => {
            console.log("error sending ", error);
        });
}

const createToken = async (user) => {
    const firebaseCollection = mongoose.model("fbtokens", fbToken.schema, "fbtokens");
    const usernameTokenList = await firebaseCollection.find().exec();
    const fireBaseToken = "";
    const doc = new firebaseCollection({username: user, token: fireBaseToken});
    return await doc.save();
}

const updateToken = async (user, newToken) => {
    const firebaseCollection = mongoose.model("fbtokens", fbToken.schema, "fbtokens");
    const update = {token: newToken};
    const userTokenUpdate = await firebaseCollection.findOneAndUpdate({username: user}, update, {new: true});
    return;
}

module.exports = {
    createToken,
    updateToken,
    sendMessage
};