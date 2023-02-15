package com.example.final_prepration_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password,conform_password,city,age;
    Button signUpBtn;
    RadioGroup rdGp;
    CheckBox Shockey,Sfootball,Scricket;

    TextView alreadyRegister;

    String game =" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  connection with the xml code
        name = findViewById(R.id.xName);
        email = findViewById(R.id.xEmail);
        password = findViewById(R.id.xPassword);
        conform_password = findViewById(R.id.xCpassword);
        city = findViewById(R.id.xCity);
        age = findViewById(R.id.xAge);
        signUpBtn = findViewById(R.id.signUpBtn);
        rdGp = findViewById(R.id.rdGroup);
        Scricket = findViewById(R.id.cricket);
        Sfootball = findViewById(R.id.football);
        Shockey = findViewById(R.id.hockey);

        alreadyRegister = findViewById(R.id.alreadyRegister);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valid_registeration();
            }
        });

        alreadyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLogin = new Intent(getApplicationContext(),Login_form.class);  //check
                startActivity(goToLogin);
            }
        });


    }


    public void Valid_registeration(){

//        get value from the xml
          String Sname = name.getText().toString().trim();
          String Semail = email.getText().toString().trim();
          String Spassword = password.getText().toString().trim();
          String Scomform_password = conform_password.getText().toString().trim();
          String Scity = city.getText().toString().trim();
          String Sage = age.getText().toString().trim();

//          Radio groups code
        RadioButton gender = findViewById(rdGp.getCheckedRadioButtonId());
         String Sgender = gender.getText().toString();

//        checkbox

        if(Shockey.isChecked()){
            game += " hockey ";
        }
        if(Sfootball.isChecked()){
            game += " football ";
        }
        if(Scricket.isChecked()){
            game += " hockey ";
        }

        if(Semail.isEmpty()){
            email.setError("please Enter Email");  //check
            email.requestFocus();                 //check
            return;
        }
        if(Spassword.isEmpty()){
            password.setError("please enter password");
            password.requestFocus();
            return;
        }


        if(Sname.isEmpty() || Scomform_password.isEmpty() || Scity.isEmpty() || Sage.isEmpty()){
            Toast.makeText(this, "Some Field are empty", Toast.LENGTH_SHORT).show();
            return;
        }else{

            int ageInt = Integer.parseInt(Sage);  //check


//            match password and firebase Authatication
            if(Spassword.equals(Scomform_password)){
//              progress bar
                ProgressBar myProgress = findViewById(R.id.progress);
                myProgress.setVisibility(View.VISIBLE);
                FirebaseAuth  mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            Real time database code
                            UserData data = new UserData(Sname,Scity,ageInt,Sgender,game);   //check
                            FirebaseDatabase.getInstance().getReference("user").child(mAuth.getCurrentUser().getUid()).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
//                                      database condition
                                    if(task.isSuccessful()){
                                         myProgress.setVisibility(View.GONE);
                                        Intent gotoLogin2 = new Intent(getApplicationContext(),Login_form.class);
                                        startActivity(gotoLogin2);
                                    }else{
                                        Toast.makeText(MainActivity.this, "Data did Not add", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Toast.makeText(MainActivity.this, "sucessfully Register", Toast.LENGTH_SHORT).show();

                        }else{
                            myProgress.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "something Want wrong in Registration", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//
            }else {
                Toast.makeText(this, "password and conform password did not match", Toast.LENGTH_SHORT).show();
            }
        }
//


    }
}