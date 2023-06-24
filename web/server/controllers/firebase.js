const mongoose = require('mongoose');
const fbTokenService = require('../services/firebase');
const fbToken = require('../models/firebase');
const tokenVerifer = require('../services/token')

const updateToken = async (req, res) => {

    if (!req.headers.authorization) {
        return res.status(401).send();
    }
    const token = req.headers.authorization.split(' ')[1]
    const validity = await tokenVerifer.isValidTokenWithDetails(token)
    if (!validity) {
        res.status(401).send();
    }

    const newToken = req.body.fbToken;
    const username = validity.username
    const serviceReq = await fbTokenService.updateToken(username, newToken);
    return res.status(200).json(serviceReq);
}

module.exports = {
    updateToken,
}