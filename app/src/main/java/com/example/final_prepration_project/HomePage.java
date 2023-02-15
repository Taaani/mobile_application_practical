package com.example.final_prepration_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
   TextView wellCome,goTogoogle;
   Button Logout, listBtn, ProductBtn;
   EditText ProName,ProQ;
   RadioGroup rdGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        wellCome = findViewById(R.id.wellCome);
        Logout  = findViewById(R.id.logout);
        goTogoogle = findViewById(R.id.goToGoogle);
        listBtn = findViewById(R.id.listBtn);
//        product values....
        ProName = findViewById(R.id.ProName);
        ProQ  = findViewById(R.id.ProQ);
        rdGroup = findViewById(R.id.rdGroup1);
        ProductBtn = findViewById(R.id.addPro);

        ProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_product();
            }
        });



        FirebaseAuth mAuth = FirebaseAuth.getInstance();

//        go to listview
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToList = new Intent(getApplicationContext(),listActivity.class);
                startActivity(goToList );
            }
        });
//        logout button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mAuth.signOut();

                Toast.makeText(HomePage.this, "you are successfully logout", Toast.LENGTH_SHORT).show();
                Intent gotoLogin = new Intent(getApplicationContext(),Login_form.class);
                startActivity(gotoLogin);
            }
        });
//        Explcit intent go to google
        goTogoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTogoogle = new Intent(Intent.ACTION_VIEW);
                goTogoogle.setData(Uri.parse("https://www.google.com/"));
                startActivity(goTogoogle);
            }
        });

        FirebaseDatabase.getInstance().getReference().child("user").child(mAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                      String name = snapshot.child("sname").getValue(String.class);
                      wellCome.setText(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }


    public  void Add_product(){



        String pName = ProName.getText().toString().trim();
        String pQuantity = ProQ.getText().toString().trim();

        if(pName.isEmpty()){
            ProName.setError("please enter product name");
            ProName.requestFocus();
            return;
        }
        if(pQuantity.isEmpty()){
            ProQ.setError("enter product name");
            ProQ.requestFocus();
            return;
        }
        int Quantity_in_int = Integer.parseInt(pQuantity);
        RadioButton rdCheck_btn_id = findViewById(rdGroup.getCheckedRadioButtonId());

        String type = rdCheck_btn_id.getText().toString();

        if(type.equals("New")){
            int s = 1000 * Quantity_in_int;
            String converted_string = Integer.toString(s);
            String a = "product Name is :"+pName+"\n"+"Total price is :"+converted_string;
            Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
        }
        if(type.equals("Used")){
            int s = 800 * Quantity_in_int;
            String converted_string = Integer.toString(s);
            String a = "product Name is :"+pName+"\n"+"Total price is :"+converted_string;
            Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
        }
    }

}