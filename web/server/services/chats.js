const mongoose = require('mongoose');
const Chat = require('../models/chats');
const User = require('../models/users');
const Messages = require('../models/messages');
const ChatCol = require('../models/chatCol');

const createChat= async (chatId, currentUser, contactUser, contactDisplayName, contactProfilePic) =>{
        const userChatCollection = mongoose.model(currentUser, Chat.schema, currentUser);
        const contactDetails = {username: contactUser, displayName: contactDisplayName, profilePic: contactProfilePic}
        const chat = new userChatCollection({_id: chatId,user: contactDetails},);
        return await chat.save();
}

const getChat = async (chatId, userOfRequest) => {
        const chatsCollection = mongoose.model('chatcols', ChatCol.schema, 'chatcols');
        const chat = await chatsCollection.findOne({_id: chatId}).exec();
        if (chat == null) {
                return null;
        }

        if (chat.userOne != userOfRequest && userOfRequest != chat.userTwo) {
                console.log("username isnt part of the chat wanted --- " + userOfRequest);
                return null;
        }

        const user1 = chat.userOne;
        const user2 = chat.userTwo;

        const userOneDetails = await User.findOne({username: user1})
        const userTwoDetails = await User.findOne({username: user2})


        const messagesModel = mongoose.model('messages', Messages.schema, 'messages');
        const msgsOfChat = await messagesModel.findOne({_id: chatId}).exec();
        const msgsArray = msgsOfChat.messages
        const newMsgsArray = []

        if (msgsArray) {
                for (let i = 0; i < msgsArray.length; i++) {
                        let tempDisplayName = null;
                        let tempProfilePic = null;
                        if (msgsArray[i].sender.username == user1) {
                                tempDisplayName = userOneDetails.displayName;
                                tempProfilePic = userOneDetails.profilePic;
                        } else {
                                tempDisplayName = userTwoDetails.displayName;
                                tempProfilePic = userTwoDetails.profilePic;
                        }
                        
                        let msgDetails = {
                                id: msgsArray[i]._id, 
                                created: msgsArray[i].created,
                                sender: {
                                        username: msgsArray[i].sender.username,
                                        displayName: tempDisplayName,
                                        profilePic: tempProfilePic        
                                },
                                content: msgsArray[i].content
                        }
                        newMsgsArray.push(msgDetails)
                }
        }


        

        const resVal = {
                id : chatId,
                users : [
                        {
                                username: user1,
                                displayName: userOneDetails.displayName,
                                profilePic: userOneDetails.profilePic
                        }, {
                                username: user2,
                                displayName: userTwoDetails.displayName,
                                profilePic: userTwoDetails.profilePic
                        }
                ],
                messages : newMsgsArray 
        }
        return resVal

}

const getChats = async (username) => {
        const userChatCollection = mongoose.model(username, Chat.schema, username);
        const temp = await userChatCollection.find().exec();
        if (!temp) {
                return temp;
        }

        retChatsArray = [];
        for (let i = 0; i < temp.length; i++) {
                let lastMessage
                if (temp[i].lastMessage != null) {
                        tempChat =  {
                                id : temp[i]._id,
                                user : {
                                        username : temp[i].user.username,
                                        displayName : temp[i].user.displayName,
                                        profilePic : temp[i].user.profilePic,
                                },
                                lastMessage: {
                                        id : temp[i].lastMessage._id,
                                        created : temp[i].lastMessage.created,
                                        content : temp[i].lastMessage.content
                                }
        
                        }
                } else {
                        tempChat =  {
                                id : temp[i]._id,
                                user : {
                                        username : temp[i].user.username,
                                        displayName : temp[i].user.displayName,
                                        profilePic : temp[i].user.profilePic,
                                },
                                lastMessage: null
                        }
                }
                retChatsArray.push(tempChat);
        }

        return (retChatsArray)
}

const removeChat = async (chatId) => {
        const chatsCollection = mongoose.model('chatcols', ChatCol.schema, 'chatcols');
        const chat = await chatsCollection.findOne({_id: chatId}).exec();
        if (chat == null) {
                return null;
        }
        const userOneCollection = mongoose.model(chat.userOne, Chat.schema, chat.userOne);
        const userTwoCollection = mongoose.model(chat.userTwo, Chat.schema, chat.userTwo);
        const messagesModel = mongoose.model('messages', Messages.schema, 'messages');
        const msgsOfChat = await messagesModel.findOne({_id: chatId}).exec();
        const chatUserOne = await userOneCollection.findOne({_id: chatId}).exec();
        const chatUserTwo = await userTwoCollection.findOne({_id: chatId}).exec();

        await chat.deleteOne();
        await chatUserOne.deleteOne();
        await chatUserTwo.deleteOne();
        await msgsOfChat.deleteOne();
        return 1;
}


module.exports = {
        createChat,
        getChat,
        getChats,
        removeChat
        };