const mongoose = require('mongoose');

const idSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    currentId: {
        type: Number,
        default: 0,
        required: false,
    }
});

const Id = mongoose.model('Id', idSchema);

module.exports = Id;

