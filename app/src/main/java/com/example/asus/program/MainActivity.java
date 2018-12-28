package com.example.asus.program;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    EditText et1, et2;
    Button  btn;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView)findViewById(R.id.daftar1);
        btn = (Button)findViewById(R.id.masuk1);
        et1 = (EditText)findViewById(R.id.masukemail);
        et2 = (EditText)findViewById(R.id.masukpassword);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Tunggu");

        auth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null){
            startActivity(new Intent(MainActivity.this,halnavigation.class));
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
               String txt_email = et1.getText().toString();
               String txt_password = et2.getText().toString();

               if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                   progressDialog.dismiss();
                   Toast.makeText(MainActivity.this,"Isi Yang Kosong!!", Toast.LENGTH_SHORT).show();
               }
               else {
                   auth.signInWithEmailAndPassword(txt_email, txt_password)
                           .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful())
                                   {

                                       Intent intent = new Intent(MainActivity.this, halnavigation.class);
                                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                       progressDialog.dismiss();
                                       startActivity(intent);
                                       finish();
                                   }else {
                                       progressDialog.dismiss();
                                       Toast.makeText(MainActivity.this,"Login Gagal",Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
               }

                // startActivity(new Intent(MainActivity.this,halnavigation.class));
            }
        });

        pindah();

    }

    private void pindah(){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,haldaftar.class));
            }
        });
    }

}
