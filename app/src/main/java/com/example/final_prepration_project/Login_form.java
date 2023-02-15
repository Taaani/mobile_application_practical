package com.example.final_prepration_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_form extends AppCompatActivity {
   EditText lEmail,lPassword;
   Button LoginBtn;

   TextView goToRigister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        lEmail = findViewById(R.id.lEmail);
        lPassword = findViewById(R.id.lPassword);
        LoginBtn = findViewById(R.id.loginBtn);
        goToRigister = findViewById(R.id.gotoRegister);


        goToRigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTosigup = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goTosigup);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_authentication();
            }
        });
    }


    public void  Login_authentication(){

        String email  = lEmail.getText().toString().trim();
        String password = lPassword.getText().toString().trim();

        if(email.isEmpty()){
            lEmail.setError("enter email");
            lEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            lPassword.setError("enter password");
            lPassword.requestFocus();
            return;
        }
        ProgressBar loginProgress = findViewById(R.id.lProgress);
        loginProgress.setVisibility(View.VISIBLE);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginProgress.setVisibility(View.GONE);
                    Intent goToHome = new Intent(getApplicationContext(),HomePage.class);
                    startActivity(goToHome);
                    Toast.makeText(Login_form.this, "you are successfully login", Toast.LENGTH_SHORT).show();
                }else{
                    loginProgress.setVisibility(View.GONE);
                    Toast.makeText(Login_form.this, "someThing want wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}