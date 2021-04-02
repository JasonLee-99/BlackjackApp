package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onPressJoinPressed(View view) {
        EditText textEmail = findViewById(R.id.txtEmail);
        EditText textPassword = findViewById(R.id.txtPassword);

        Amplify.Auth.signUp(
                textEmail.getText().toString(),
                textPassword.getText().toString(),
                AuthSignUpOptions.builder().userAttribute(
                        AuthUserAttributeKey.email(), textEmail.getText().toString()
                ).build(),
                this::onJoinSuccess,
                this::onJoinError
        );
    }

    private void onJoinSuccess(AuthSignUpResult authSignUpResult) {
        EditText textEmail = findViewById(R.id.txtEmail);
        EditText textPassword = findViewById(R.id.txtPassword);
        EditText textName = findViewById(R.id.txtName);

        Intent intent = new Intent(this, EmailConfirmationActivity.class);
        intent.putExtra("email", textEmail.getText().toString());
        intent.putExtra("password", textPassword.getText().toString());
        intent.putExtra("name", textName.getText().toString());

        startActivity(intent);
    }

    private void onJoinError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });
    }

}