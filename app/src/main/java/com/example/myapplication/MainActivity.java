package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Authentication
        AuthUser currentUser = Amplify.Auth.getCurrentUser();
        Intent intent = null;
        if (currentUser == null) {
            //Go to login screen
            intent = new Intent(getApplicationContext(), loginActivity.class);
        }
        else {
            //Go to Game screen
            intent = new Intent(getApplicationContext(), RegisterActivity.class);
        }

        //start activity
        startActivity(intent);
        finish();
    }

}