package com.example.userauthantication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextInputLayout t1, t2;
    ProgressBar bar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextInputLayout)findViewById(R.id.emailid);
        t2 = (TextInputLayout)findViewById(R.id.password);
        bar = (ProgressBar)findViewById(R.id.progressBar);
    }

    public void senddata(View view) {
        String email = t1.getEditText().getText().toString();
        String password = t2.getEditText().getText().toString();
        mAuth = FirebaseAuth.getInstance();
        if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter Email ID & Password.",Toast.LENGTH_SHORT).show();
        }else if(email.isEmpty() ){
            Toast.makeText(MainActivity.this,"Email cannot be empty !",Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(MainActivity.this,"Password cannot be empty !",Toast.LENGTH_SHORT).show();
        }

        else {
            bar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(), "Process Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    public void gotologin(View view) {
        Intent login = new Intent(MainActivity.this, Login.class);
        startActivity(login);
    }
}