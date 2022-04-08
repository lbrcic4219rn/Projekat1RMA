package rs.raf.projekat1.view.activities;

import static rs.raf.projekat1.view.activities.MainActivity.ADMIN_EMAIL;
import static rs.raf.projekat1.view.activities.MainActivity.ADMIN_LOGGED;
import static rs.raf.projekat1.view.activities.MainActivity.ADMIN_PASSWORD;
import static rs.raf.projekat1.view.activities.MainActivity.ADMIN_USERNAME;
import static rs.raf.projekat1.view.activities.MainActivity.EMAIL_KEY;
import static rs.raf.projekat1.view.activities.MainActivity.IS_LOGGED_IN;
import static rs.raf.projekat1.view.activities.MainActivity.USERNAME_KEY;
import static rs.raf.projekat1.view.activities.MainActivity.USER_EMAIL;
import static rs.raf.projekat1.view.activities.MainActivity.USER_LOGGED;
import static rs.raf.projekat1.view.activities.MainActivity.USER_PASSWORD;
import static rs.raf.projekat1.view.activities.MainActivity.USER_USERNAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.projekat1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText emailEdit;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }
    private void init() {
        initView();
        initListeners();
    }

    private void initListeners() {
        loginButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            String email = emailEdit.getText().toString();

            //validation
            if(!isValid(username, password, email)) return;


            boolean adminCondition = username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD) && email.equals(ADMIN_EMAIL);
            if(adminCondition){
                sharedPreferences
                        .edit()
                        .putString(IS_LOGGED_IN, ADMIN_LOGGED)
                        .putString(USERNAME_KEY, username)
                        .putString(EMAIL_KEY, email)
                        .apply();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(IS_LOGGED_IN, ADMIN_LOGGED);
                startActivity(intent);
                finish();
                return;
            }

            boolean userCondition = username.equals(USER_USERNAME) && password.equals(USER_PASSWORD) && email.equals(USER_EMAIL);
            if(userCondition){
                sharedPreferences
                        .edit()
                        .putString(IS_LOGGED_IN, USER_LOGGED)
                        .putString(USERNAME_KEY, username)
                        .putString(EMAIL_KEY, email)
                        .apply();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(IS_LOGGED_IN, USER_LOGGED);
                startActivity(intent);
                finish();
                return;
            }

            Toast.makeText(this, "INVALID CREDENTIALS", Toast.LENGTH_LONG).show();

        });
    }

    private void initView() {
        usernameEdit = findViewById(R.id.loginUsername);
        passwordEdit = findViewById(R.id.loginPassword);
        emailEdit = findViewById(R.id.loginEmail);
        loginButton = findViewById(R.id.loginButton);
        System.out.println("BUTTON: " + loginButton);
    }

    private boolean isValid(String username, String password, String email){
        if(username.trim().length() == 0 || password.trim().length() == 0 || email.trim().length() == 0){
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_LONG).show();
            return false;
        }

        if(password.length() < 5){
            Toast.makeText(this, "Password must be at least 5 characters long", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}