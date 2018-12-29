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

public class halmasuk extends AppCompatActivity {

    ImageView img;
    EditText et1, et2;
    Button btn;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halmasuk);

        btn = (Button)findViewById(R.id.masuk11);
        et1 = (EditText)findViewById(R.id.masukemail1);
        et2 = (EditText)findViewById(R.id.masukpassword1);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Tunggu");


        auth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String txt_email = et1.getText().toString();
                String txt_password = et2.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    progressDialog.dismiss();
                    Toast.makeText(halmasuk.this,"Isi Yang Kosong!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {

                                        Intent intent = new Intent(halmasuk.this, halfoto.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        progressDialog.dismiss();
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        progressDialog.dismiss();
                                        Toast.makeText(halmasuk.this,"Login Gagal",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                // startActivity(new Intent(MainActivity.this,halnavigation.class));
            }
        });


    }


}
