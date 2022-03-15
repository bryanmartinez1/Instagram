package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccountActivity extends AppCompatActivity {
    public static final String TAG = "CreateAccountActivity";
    private EditText createAccountUserName;
    private EditText createAccountPassword;
    private EditText createAccountConfirmPassword;
    private EditText createAccountBio;
    private Button confirmCreateAccount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
// -------------------------------------------------------------
// Sets Logo to Action Bar
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater =(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.actionbar_logo, null);
        actionBar.setCustomView(actionBarView);
// -------------------------------------------------------------

        createAccountUserName = findViewById(R.id.CreateAccountUserName);
        createAccountBio = findViewById(R.id.CreateAccountProfileBio);
        createAccountPassword = findViewById(R.id.CreateAccountPassword);
        createAccountConfirmPassword = findViewById(R.id.CreateAccountConfirmPassword);
        confirmCreateAccount = findViewById(R.id.confirmCreateAccountButton);
        confirmCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick confirm create account button");
                String username = createAccountUserName.getText().toString();
                if(username == null) {
                    Toast.makeText(CreateAccountActivity.this, "Username is needed", Toast.LENGTH_SHORT).show();
                    return;
                }
                String bio = createAccountBio.getText().toString();
                String password = createAccountPassword.getText().toString();
                String confirmPassword = createAccountConfirmPassword.getText().toString();
                if(password == null || confirmPassword == null) {
                    Toast.makeText(CreateAccountActivity.this, "Password Box Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Password Box Cannot Be Empty");
                }
                else {
                    if(password.equals(confirmPassword)) {
                        createUser(username, password, bio);
                    }
                    else if (!password.equals(confirmPassword)){
                        Toast.makeText(CreateAccountActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Passwords Do Not Match ");
                    }
                }

            }
        });
    }

    private void createUser(String username, String password, @Nullable String bio) {
        Log.i(TAG, "Attempting to create account: " + username);

        //Creates the new User

        ParseUser user = new ParseUser();
        //Core Properties
        user.setUsername(username);
        user.setPassword(password);

        if(bio != null) {
            user.put("userBio", bio);
        }
        else {
            bio =" ";
            user.put("userBio", bio);
        }

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    //Logins in New User
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if(e != null) {
                                Log.e(TAG, "Issue with login", e);
                                Toast.makeText(CreateAccountActivity.this, "Issue with Login", Toast.LENGTH_SHORT);
                                return;
                            }
                            goMainActivity();
                            Toast.makeText(CreateAccountActivity.this, "Success", Toast.LENGTH_SHORT);
                        }

                    });
                } else {
                    Log.e(TAG, "Issue with creating account", e);
                    Toast.makeText(CreateAccountActivity.this, "Issue with Login", Toast.LENGTH_SHORT);
                    return;
                }
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
