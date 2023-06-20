const jwt = require("jsonwebtoken");
const User = require('../models/users');
const getToken= async (req, res) => {
   const username =req.body.username;
   const password=req.body.password;
    try {
        // Find user in the MongoDB collection
        const user = await User.findOne({ username, password });

        if (user) {
            // Generate and return a token
            const token = jwt.sign({ username }, 'key');
            res.status(200).send(token);
        } else {
            res.status(401).json({ error: 'Invalid credentials' });
        }
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
}
const isValidToken =async(token) =>{
    try {
        const testToken = jwt.verify(token, 'key');

    } catch (err) {
        return false;
    }
    return true;

}

const isValidTokenWithDetails =async(token) =>{
    let testToken
    try {
        testToken = jwt.verify(token, 'key');
    } catch (err) {
        return false;
    }
    return testToken;

}
module.exports={
    getToken,isValidToken, isValidTokenWithDetails
}