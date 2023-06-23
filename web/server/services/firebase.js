import {getMessaging} from "firebase-admin/messaging";

const fbToken = require("../models/firebase")

const sendMessage = async (srcUser, destUser) => {
    //Need to iterate over users, grab the FB token from a field, build a message with the sender's name.
    //fbtoken = ???;
    //TODO
    const firebaseCollection = mongoose.model("fbtokens", fbToken.schema, "fbtokens");
    const user = await firebaseCollection.findOne({username: destUser}).exec();
    if (user == null || user.token == null) {           //user not found or not android user
        return
    }

    const message = {
        data: {
            source: srcUser
        },
        token: user.token
    };


    getMessaging().send(message)
        .then((response) => {
            console.log("sent message");
        })
        .catch((error) => {
            console.log("error sending ", error);
        });
}