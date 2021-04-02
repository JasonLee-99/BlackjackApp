package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.generated.model.User;

public class EmailConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);
    }

    public void onConfirmButtonPressed(View view) {
        //1. Confirm the code
        //2. Re-login
        //3. Save the user details such as names in data-store

        EditText textConfirmationCode = findViewById(R.id.txtConfirmationCode);

        Amplify.Auth.confirmSignUp(
                getEmail(),
                textConfirmationCode.getText().toString(),
                this::confirmationSuccess,
                this::onError
        );
    }

    private void confirmationSuccess(AuthSignUpResult authSignUpResult) {
        reLogin();
    }

    private void onError(AuthException e) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void reLogin() {
        String username = getEmail();
        String password = getPassword();

        Amplify.Auth.signIn(
                username,
                password,
                this::onLoginSuccess,
                this::onError
        );
    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        String name = getName();
        String email = getEmail();

        Amplify.DataStore.save(
                User.builder().name(name).email(email).build(),
                this::onSavedSucess,
                this::onError
        );
    }

    private <T extends Model> void onSavedSucess(DataStoreItemChange<T> tDataStoreItemChange) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private void onError(DataStoreException e) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private String getName() {
        return getIntent().getStringExtra("name");
    }

    private String getPassword() {
       return getIntent().getStringExtra("password");
    }

    private String getEmail() {
       return getIntent().getStringExtra("email");
    }
}