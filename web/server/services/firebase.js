import {getMessaging} from "firebase-admin/messaging";
import {response} from "express";

function getFBToken(srcUser) {
    //Need to iterate over users, grab the FB token from a field, build a message with the sender's name.
    //fbtoken = ???;
    //TODO
    const message = {
        data: {
            user: srcUser
        },
        token: fbtoken
    };

    getMessaging().send(message)
        .then((response) => {
        console.log("sent message");
        })
        .catch((error) => {
            console.log("error sending ", error);
        });
}