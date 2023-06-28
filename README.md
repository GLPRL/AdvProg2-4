# AdvProg2-4
ChatApp for Android

### Chat Application for Android

An implemenation of a chat application, connecting to SWAGGER DB, written in Java.

The app is composed of 5 intents/screens:
1) Login Page
2) Settings: Change theme (dark/light modes) and connection settings.
   
   <sub>Note that a correct address must begin with "http://", it will be checked.</sub> 
4) Registration Page
5) Chats Page - Displaying your chats with other users
6) Chat Page - Your messages with a contact.

We implementated a local DB using Dao and Room, to minimize the requests to the DB server.

When receiving new message from a contact, you'll also be notified of the new message, using Firebase.

![image](https://github.com/GLPRL/AdvProg2-4/assets/116657293/a596ddb8-7148-494b-9e3d-e47b3dd4ea0d)


## Run Instructions
Clone the repository.
Start the server by 

```
cd web
cd server
node app.js
```
When opening the application, at the top left there is a settings button, in there enter the server address.
Afterwards you can log in/register as you wish.
If you're running the emulator on Android Studio, and the server is running on the same computer,
then the server address in the emulator will be: http://10.0.2.2:5000

IMPORTANT : Do not upload profile pictures that have dimensions bigger than 4000x4000.
            Upload images of type .jpeg or .png only.

For starting the application, start Android Studio. You can start the emulator in the program,
or start in the phone.

If you want an APK, then do the following: Build -> Build Bundle(s)/APK(s) > Build APK(s) .
You can send the APK to your phone and run it.



