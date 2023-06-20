const mongoose = require('mongoose');
const Id = require('../models/ids');




const checkIdCollection = async () => {
    const ids = mongoose.model('idCollection', Id.schema, 'idCollection');
    const isIdCollectionExists = await ids.exists();
    
    if (isIdCollectionExists) {
    } else {
        const temp2 = await messageIdCreate();
        const temp1 = await chatIdCreate();
        
        
    }
}

const chatIdCreate = async () => {
    const ids = mongoose.model('idCollection', Id.idSchema, 'idCollection');
    const id = new ids({
        name: 'chats', currentId: 0
    })
    return await id.save();
}

const messageIdCreate = async () => {
    const ids = mongoose.model('idCollection', Id.idSchema, 'idCollection');
    const id2 = new ids({
        name: 'messages', currentId: 0
    })
    return await id2.save();
}

const nextId = async (idName) => {
    const ids = mongoose.model('idCollection', Id.idSchema, 'idCollection');
    const nextId = await ids.findOneAndUpdate({name: idName}, {$inc: {currentId: 1}}, {new: true})
    if (nextId) {
        return nextId.currentId;
    }
    return nextId.currentId;

}

module.exports = {
    checkIdCollection,
    nextId
    };