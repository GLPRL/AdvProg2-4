const mongoose = require('mongoose');
const bodyParser = require("body-parser");
const usersRouter = require('./routes/users');
const chatsRouter = require('./routes/chats');
const cors = require('cors');
const tokenService = require('./services/token');
const idService = require('./services/ids');
const chatService = require('./services/chats');

const express = require("express");
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const {join} = require("path");
const io = new Server(server, {
    cors: {
        origin: "http://localhost:5000",
    }
});

app.use(express.static('public'))

app.use(cors());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.json());
app.use('/api/Users',usersRouter);
app.use('/api/Chats',chatsRouter);

mongoose.connect('mongodb://127.0.0.1:27017/chatApp', {
    useNewUrlParser: true,
    useUnifiedTopology: true,
}).then(() => {
    //
})
    .catch((error) => {
        //
    });

app.post('/api/Tokens',tokenService.getToken);
app.use(express.static(join(__dirname, "..", "build")));

// checking if idCollection exists, if not, creates it.
idService.checkIdCollection();

io.on("connection", (socket) => {
    io.emit("hello", "there")
    socket.on("foo", (data) => {
        //
    })
    socket.on("newMessage", (chatID) => {
        //
        socket.broadcast.emit("receiveMessage", chatID)
    })
})

server.listen(5000, () => {
    //
});