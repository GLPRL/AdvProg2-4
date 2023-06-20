const mongoose = require('mongoose');
const Message = require('../models/messages');
const User = require('../models/users');
const Chat = require('../models/chats');
const ChatCol = require('../models/chatCol');

const createMessage= async (chatId, currentUser, messageContent, messageId) =>{
    const chatsCollection = mongoose.model('chatcols', ChatCol.schema, 'chatcols');
    const chat = await chatsCollection.findOne({_id: chatId}).exec();
        if (chat == null) {
                return null;
        }

        if (chat.userOne != currentUser && currentUser != chat.userTwo) {
                return null;
        }

    const messagesModel = mongoose.model('messages', Message.schema, 'messages');

    const userMessages = await messagesModel.findById(chatId)
    const currDate = new Date().toLocaleString();
    const messageDetails = {_id: messageId, sender: {username:currentUser}, created : currDate ,content: messageContent}
    if (userMessages.messages == null) {
        userMessages.messages = []
    }

    userMessages.messages.unshift(messageDetails);

    const user =await User.findOne({username: currentUser});
    const resData = {
        id : messageId, created : currDate, sender : {
            username: currentUser, displayName: user.displayName, profilePic : user.profilePic }, content: messageContent
        }
    await userMessages.save();

    const updateData = {idOfChat: chatId, idOfMessage: messageId, sender: {username:currentUser}, created: currDate, content: messageContent}
    await updateLastMessages(updateData);
    return resData
}

const getMessages = async (chatId, username) =>{
    const chatsCollection = mongoose.model('chatcols', ChatCol.schema, 'chatcols');
    const chat = await chatsCollection.findOne({_id: chatId}).exec();
        if (chat == null) {
                return null;
        }

        if (chat.userOne != username && username != chat.userTwo) {
                return null;
        }



    const messagesModel = mongoose.model('messages', Message.schema, 'messages');
    const userMessages = await messagesModel.findById(chatId)
    const msgsArray = userMessages.messages
    if (msgsArray == null) {
        return []
    }
    return msgsArray
}

const updateLastMessages = async (msgData) => {
    const user1 = msgData.sender.username;
    const userChatCollection = mongoose.model(user1, Chat.schema, user1);
    
    const lastM = {_id: msgData.idOfMessage, created: msgData.created, content: msgData.content}
    const chatDocInUser = await userChatCollection.findOneAndUpdate({_id:msgData.idOfChat}, {lastMessage:lastM}, {new: true})
    const contactUsername = chatDocInUser.user.username
    const contactChatCollection = mongoose.model(contactUsername, Chat.schema, contactUsername);
    const contactDocInUser = await contactChatCollection.findOneAndUpdate({_id:msgData.idOfChat}, {lastMessage:lastM}, {new: true})
}



module.exports = {
    createMessage,
    getMessages
    };