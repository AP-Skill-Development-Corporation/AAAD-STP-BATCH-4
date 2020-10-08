package com.example.examplefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

    public void register(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void reset(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.reset,null,false);
        final EditText mail = v.findViewById(R.id.remail);
        builder.setView(v);
        builder.setCancelable(false);
        builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String umail = mail.getText().toString();
                if (umail.isEmpty()){
                    Toast.makeText(MainActivity.this, "mail cant be empty",
                            Toast.LENGTH_SHORT).show();
                }
                auth.sendPasswordResetEmail(umail).addOnCompleteListener(MainActivity.this,
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Mail sent",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "failed to send", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}