const mongoose = require('mongoose');
const fbToken = require('../models/firebase');
//import {getMessaging} from "firebase-admin/messaging";



const sendMessage = async (srcUser, destUser) => {
    //Need to iterate over users, grab the FB token from a field, build a message with the sender's name.
    //fbtoken = ???;
    //TODO
    const firebaseCollection = mongoose.model("fbtokens", fbToken.schema, "fbtokens");
    const user = await firebaseCollection.findOne({username: destUser}).exec();     //find token of the destination user
    if (user.token == null) {           //user not found or not android user
        return
    }

    const message = {               //build message to send
        data: {
            source: srcUser
        },
        token: user.token
    };


    //getMessaging().send(message)                //send message
        //.then((response) => {
        //    console.log("sent message");
        //})
        //.catch((error) => {
        //    console.log("error sending ", error);
        //});
}

const createToken = async (user) => {
    const firebaseCollection = mongoose.model("fbtokens", fbToken.schema, "fbtokens");
    const usernameTokenList = await firebaseCollection.find().exec();
    const fireBaseToken = "";
    const doc = new firebaseCollection({username: user, token: fireBaseToken});
    return await doc.save();
}

module.exports = {
                    createToken                    
                };