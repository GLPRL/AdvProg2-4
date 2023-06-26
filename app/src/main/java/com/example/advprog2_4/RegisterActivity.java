package com.example.advprog2_4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.advprog2_4.api.WebServiceAPI;
import com.example.advprog2_4.objects.UserRegisterObject;
import com.example.advprog2_4.objects.UserRegisterResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && data!= null){
            Uri selectedImage= data.getData();
            ImageView image = findViewById(R.id.uploadImage);
            image.setImageURI(selectedImage);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        EditText etUsernameLogin = findViewById(R.id.etUsernameLogin);
        EditText etDisplay = findViewById(R.id.etDisplayName);
        EditText etPassword = findViewById(R.id.etPasswordLogin);
        EditText etVerifyPassword = findViewById(R.id.verifyPassword);
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        Button btnLoginPage = findViewById(R.id.btnLoginPage);
        Button btnUpload = findViewById(R.id.btnUpload);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });
        btnLoginPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder messageBuilder = new StringBuilder();
                boolean validRegister = true;
                String username = etUsernameLogin.getText().toString();
                String displayName= etDisplay.getText().toString();
                String password = etPassword.getText().toString();
                String verifyPassword = etVerifyPassword.getText().toString();
                ImageView imageView = findViewById(R.id.uploadImage);
                if(imageView.getDrawable()== null){
                    messageBuilder.append("Must upload image\n\n");
                    validRegister=false;
                }
                if (username.length() <2 || username.length() >16) {
                    messageBuilder.append("Username should be 2-16 characters.\n\n");
                    validRegister=false;
                }
                if(displayName.length()<2 || displayName.length()>16) {
                    messageBuilder.append("Display name should be 2-16 characters.\n\n");
                    validRegister=false;
                }
                if (!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*") || password.length() <6 || password.length()>16) {
                    messageBuilder.append("Password should contain both letters and numbers. 8-16 in length\n\n");
                    validRegister = false;
                }
                if (!verifyPassword.equals(password)) {
                    messageBuilder.append("Password and Verify Password do not match.\n\n");
                    validRegister = false;
                }
                if(!validRegister) {
                    builder.setTitle("Validation Error");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Handle OK button click
                        }
                    });
                    builder.setMessage(messageBuilder.toString());
                    builder.show();
                }
                else{
                     //converting image to string
                    BitmapDrawable imageDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap imageBitmap = imageDrawable.getBitmap();
                    ByteArrayOutputStream imageAsString = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG,10,imageAsString);
                    //if(!(imageBitmap.compress(Bitmap.CompressFormat.JPEG,10,imageAsString))){
                    //    imageBitmap.compress(Bitmap.CompressFormat.PNG,10,imageAsString);
                    //};
                    byte[] temp = imageAsString.toByteArray();
                    String image = Base64.encodeToString(temp, Base64.DEFAULT);

                    UserRegisterObject userData = new UserRegisterObject(username, password, displayName, image);
                    postUser(userData);
                    if (Global.getInstance().isWasRegisterValid()) {
                        Global.getInstance().setWasRegisterValid(false);
                        //builder.setTitle("Registered Successfully");
                    } else {
                        //builder.setTitle("Register was Unsuccessful, try again with different details");
                    }
                }
            }
        });
    }

    private void postUser(UserRegisterObject userData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WebServiceAPI webServiceAPI = retrofit.create(WebServiceAPI.class);

        Call<UserRegisterResponse> call = webServiceAPI.postUser("application/json", "text/plain" , userData);
        call.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                Global global = Global.getInstance();
                int status = response.code();
                if (status == 200) {
                    global.setWasRegisterValid(true);
                    builder.setTitle("Registered Successfully");
                    //Log.i("RegisterActivity", "REQ STAT IS 200\n");
                } else if (status == 409){
                    global.setWasRegisterValid(false);
                    builder.setTitle("Register was Unsuccessful, try again with different details");
                    //Log.i("RegisterActivity", "REQ STAT IS 409!\n");
                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setMessage("");
                builder.show();
            }
            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                Global global = Global.getInstance();
                global.setWasRegisterValid(false);
                Log.e("RegisterActivity", "error msg is : " + t.getMessage());
            }
        });

    }




}
