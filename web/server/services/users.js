const User = require('../models/users');
const createUser= async (username,password,displayName,profilePic) =>{
        const user = new User ({username: username , password: password, displayName: displayName, profilePic: profilePic});
        return await user.save();
}

module.exports = {createUser};