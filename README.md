# AdvProg2-4
ChatApp for Android

### Chat Application for Android

An implemenation of a chat application, connecting to SWAGGER DB, written in Java.

The app is composed of 5 intents/screens:
1) Login Page
2) Settings: Change theme (dark/light modes) and connection settings
3) Registration Page
4) Chats Page - Displaying your chats with other users
5) Chat Page - Your messages with a contact.

We implementated a local DB using Dao and Room, to minimize the requests to the DB server.

When receiving new message from a contact, you'll also be notified of the new message, using Firebase.

![image](https://github.com/GLPRL/AdvProg2-4/assets/116657293/a596ddb8-7148-494b-9e3d-e47b3dd4ea0d)


## Run Instructions
Clone the repository.
Start the server by 

``` cd web
cd server
node app.js

For starting the application, start Android Studio. You can start the emulator in the program,
or start in the phone.

If you want an APK, then do the following: Build -> Build Bundle(s)/APK(s) > Build APK(s) .
You can send the APK to your phone and run it.
