package com.example.asus.program;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class haldaftar extends AppCompatActivity {

    String email, pass1, pass2;
    EditText edit1, edit2, edit3, edit4;
    Button btn;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haldaftar);

        edit1= (EditText)findViewById(R.id.daftaremail);
        edit2= (EditText) findViewById(R.id.daftarpass);
        edit3= (EditText) findViewById(R.id.ulangpass);
        edit4 = (EditText) findViewById(R.id.daftarusername);
        btn= (Button) findViewById(R.id.tomboldaftar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Tunggu");

        auth = FirebaseAuth.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String txt_username = edit4.getText().toString();
                String txt_email = edit1.getText().toString();
                String txt_password = edit3.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) ){
                    progressDialog.dismiss();
                    Toast.makeText(haldaftar.this,"Isi Yang Kosong!!", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length()<6){
                    Toast.makeText(haldaftar.this,"Password kurang panjang",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else{
                    daftar(txt_username, txt_email, txt_password);
                    progressDialog.dismiss();
                    startActivity(new Intent(haldaftar.this,MainActivity.class));
                }

            }
        });
        //daftar();
    }

    private void daftar(final String username, String email, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser !=null;
                            String userid = firebaseUser.getUid();

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("imageUrl", "Default");

                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(haldaftar.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(haldaftar.this,"Tak Bisa daftar",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*private void daftar(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                awal();

            }
        });
    }

    private void awal(){

        email = edit1.getText().toString().trim();
        pass1 = edit2.getText().toString().trim();
        pass2 = edit3.getText().toString().trim();

        if(!syarat()){

            progressDialog.dismiss();
            Toast.makeText(this, "Daftar gagal", Toast.LENGTH_SHORT).show();
        }
        else {

            sukses();
        }

    }

    private void sukses(){

        final FirebaseUser user = auth.getCurrentUser();
        auth.createUserWithEmailAndPassword(email, pass1)
                .addOnCompleteListener(haldaftar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        Toast.makeText(haldaftar.this, "Tunggu", Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(haldaftar.this, "Daftar gagal" + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(haldaftar.this, "Cek Email Anda", Toast.LENGTH_SHORT).show();
                                        try {
                                            Thread.sleep(2000);
                                            startActivity(new Intent(haldaftar.this, MainActivity.class));
                                            finish();
                                        } catch (Exception e) {
                                        }
                                    } else {
                                        Toast.makeText(haldaftar.this,"Berhasil", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(haldaftar.this, "Email Tidak dapat diverifikasi", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

    }

    private boolean syarat(){

        boolean benar = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Masukkan Email yang benar", Toast.LENGTH_SHORT).show();
            benar = false;

            if (pass1.isEmpty() && pass2.isEmpty()) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Isi password!", Toast.LENGTH_SHORT).show();
                benar = false;
            }
            if (!pass2.equals(pass1)) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Password tak sama", Toast.LENGTH_SHORT).show();
                benar = false;
            }
            return benar;
        }

        return benar;
    }

*/

}

