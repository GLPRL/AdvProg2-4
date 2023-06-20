const User = require('../models/users');
const userService = require('../services/users');
const tokenService = require('../services/token');
const createUser = async (req,res) => {
    const username=req.body.username;
    const user =await User.findOne({username});
    if(user){
        res.status(409).json({ title: "Conflict",status: "409"});
    }
    else {
        res.status(200).json(await userService.createUser(req.body.username, req.body.password, req.body.displayName, req.body.profilePic));
    }

}
const getUser= async(req,res)=>{
    const { username } = req.params;
    if(!req.headers.authorization){
       return res.status(401).send();
    }
    const token = req.headers.authorization.split(' ')[1]
    if(! await tokenService.isValidToken(token)){
        res.status(401).send();
    }
    const user =await User.findOne({username});
    if(!user){
      return  res.status(401).send("no user found");
    }
    else{
        const userInfo = {username: user.username, displayName: user.displayName, profilePic: user.profilePic};
        return res.status(200).json(userInfo);
    }

}

module.exports = {
    createUser,
    getUser
};