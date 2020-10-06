package com.example.examplefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //Global Declaration of views
    EditText email,pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instantiation of the views
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String umail = email.getText().toString();
        String upass = pass.getText().toString();
        if (umail.isEmpty()|upass.isEmpty()){
            Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show();
        }
        else{
            auth.signInWithEmailAndPassword(umail,upass).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("Apssdc","Authentication Successful");
                        }else{
                            Log.i("Apssdc","Authentication Failed");
                        }
                        }
                    });
        }
    }
}