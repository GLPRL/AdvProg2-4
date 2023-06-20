const mongoose = require('mongoose');
const idCollection = mongoose.model('ids')

async function checkIdCollection() {
    const isIdCollectionExists = await idCollection.exists();
    
    if (isIdCollectionExists) {
        //
    } else {
        //
    }
}

module.exports = {
    checkIdCollection
}