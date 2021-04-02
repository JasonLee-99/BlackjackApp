package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onPressLogin(View view) {
        EditText textEmail = findViewById(R.id.txtEmail);
        EditText textPassword = findViewById(R.id.txtPassword);

        Amplify.Auth.signIn(
                textEmail.getText().toString(),
                textPassword.getText().toString(),
                this::onLoginSuccess,
                this::onLoginError
        );
    }

    private void onLoginError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });

    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void onJoinPressed(View view) {

    }
}