package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.core.Amplify;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onPressLogout(View view) {
        Amplify.Auth.signOut(
                () -> {
                    Log.i("AuthQuickstart", "Signed out successfully");
                    Intent intent = new Intent(this, loginActivity.class);
                    startActivity(intent);
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );

    }
}