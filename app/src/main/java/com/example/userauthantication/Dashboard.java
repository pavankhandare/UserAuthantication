package com.example.userauthantication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    TextView email, uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        email = (TextView)findViewById(R.id.email);
        uid = (TextView)findViewById(R.id.uid);

        email.setText("Email ID : "+getIntent().getStringExtra("email").toString());
        uid.setText("UID : "+getIntent().getStringExtra("uid").toString());
    }

    public void gotomain(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Dashboard.this, MainActivity.class));
        Toast.makeText(getApplicationContext(),"Logout Successfully....!",Toast.LENGTH_SHORT).show();
        finish();
    }
}