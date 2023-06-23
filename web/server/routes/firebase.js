const fbTokenController = require('../controllers/firebase');
const express = require ('express');

const router = express.Router();


router.post('/:username', fbTokenController.updateToken);
module.exports = router;