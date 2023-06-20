const messageService = require('../services/messages')
const tokenVerifer = require('../services/token')
const idGetter = require('../services/ids')

const createMessage = async (req, res) => {
    if(!req.headers.authorization){
       return res.status(401).send();
    }
    const token = req.headers.authorization.split(' ')[1]
    const validity =  await tokenVerifer.isValidTokenWithDetails(token)
    const currentUser = validity.username
    if(!validity){
        res.status(401).send();
    }

    const messageId = await idGetter.nextId("messages");
    const messageContent = req.body.msg
    const chatId = req.params.id


    const retVal = await messageService.createMessage(chatId , currentUser, messageContent, messageId);

    if (!retVal) {
        return res.status(400).json({title : "Unauthorized", status: "401"});
    }
    return res.status(200).json(retVal);

}

const getMessages = async(req, res) => {
    if(!req.headers.authorization){
       return res.status(401).send();
    }
    const token = req.headers.authorization.split(' ')[1]
    const validity =  await tokenVerifer.isValidTokenWithDetails(token)

    if(!validity){
        res.status(401).send();
    }

    const retVal = await messageService.getMessages(req.params.id, validity.username);
    if (!retVal) {
        return res.status(400).json({title : "Unauthorized", status: "401"});
    }
    return res.status(200).json(retVal);
}

module.exports = {
    createMessage,
    getMessages
};