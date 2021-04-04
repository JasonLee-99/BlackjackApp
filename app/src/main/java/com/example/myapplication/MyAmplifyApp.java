package com.example.myapplication;

import android.content.Intent;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.AmplifyModelProvider;

public class MyAmplifyApp extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Log.i("MyAmplifyApp", "Initialized Amplify");

            AmplifyModelProvider modelProvider = AmplifyModelProvider.getInstance();
            Amplify.addPlugin(new AWSDataStorePlugin(modelProvider));
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());

            Amplify.configure(getApplicationContext());
            Log.i("amplify", "configured 🥳" );
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }

}
