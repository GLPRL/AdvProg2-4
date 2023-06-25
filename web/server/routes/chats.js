const chatController = require('../controllers/chats');
const messageController = require('../controllers/messages');

const express = require('express');

const router = express.Router();

router.post('/', chatController.createChat);
router.get('/', chatController.getChats);
router.get('/:id', chatController.getChat);
router.delete('/:id', chatController.removeChat);
router.post('/:id/Messages', messageController.createMessage);
router.get('/:id/Messages', messageController.getMessages);
module.exports = router;