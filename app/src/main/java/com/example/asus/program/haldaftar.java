package com.example.asus.program;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.images.ImageRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

public class haldaftar extends AppCompatActivity {

    String email, pass1, pass2;
    EditText edit1, edit2, edit3, edit4, kelas, ttl, tempat, Nis, alamat,
            nohp, namaayah, noayah, namaibu, noibu, namawali, nowali, catatan;
    Button btn;
    Uri imguri;
    ImageView img;
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
        kelas = (EditText) findViewById(R.id.kelas);
        ttl = (EditText) findViewById(R.id.ttl);
        tempat = (EditText) findViewById(R.id.tempat);
        Nis = (EditText) findViewById(R.id.Nis);
        alamat = (EditText) findViewById(R.id.alamat);
        nohp = (EditText) findViewById(R.id.nohp);
        namaayah = (EditText) findViewById(R.id.namaayah);
        noayah = (EditText) findViewById(R.id.noayah);
        namaibu = (EditText) findViewById(R.id.namaibu);
        noibu = (EditText) findViewById(R.id.noibu);
        namawali = (EditText) findViewById(R.id.namawali);
        nowali = (EditText) findViewById(R.id.nowali);
        catatan = (EditText) findViewById(R.id.catatan);

        img = (ImageView) findViewById(R.id.uploadfoto);
        btn= (Button) findViewById(R.id.tomboldaftar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Tunggu");

        auth = FirebaseAuth.getInstance();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gambar();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String txt_username = edit4.getText().toString();
                String txt_email = edit1.getText().toString();
                String txt_password = edit3.getText().toString();
                String txt_kelas = kelas.getText().toString();
                String txt_ttl = ttl.getText().toString();
                String txt_tempat = tempat.getText().toString();
                String txt_Nis = Nis.getText().toString();
                String txt_alamat = alamat.getText().toString();
                String txt_nohp = nohp.getText().toString();
                String txt_namaayah = namaayah.getText().toString();
                String txt_noayah = noayah.getText().toString();
                String txt_namaibu = namaibu.getText().toString();
                String txt_noibu = noibu.getText().toString();
                String txt_namawali = namawali.getText().toString();
                String txt_nowali = nowali.getText().toString();
                String txt_catatan = catatan.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) ){
                    progressDialog.dismiss();
                    Toast.makeText(haldaftar.this,"Isi Yang Kosong!!", Toast.LENGTH_SHORT).show();
                }
                else if (txt_password.length()<6){
                    Toast.makeText(haldaftar.this,"Password kurang panjang",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else{
                    daftar(txt_username, txt_email, txt_password, txt_kelas, txt_ttl, txt_tempat, txt_Nis, txt_alamat, txt_nohp,
                            txt_namaayah, txt_noayah, txt_namaibu, txt_noibu, txt_namawali, txt_nowali, txt_catatan);
                    uploadimage();

                    progressDialog.dismiss();
                    startActivity(new Intent(haldaftar.this,MainActivity.class));
                }

            }
        });
        //daftar();
    }

    private void daftar(final String username, final String email, String password, final String kelas, final String ttl, final String tempat, final String nis,
                        final String alamat, final String nohp, final String namayah, final String noayah, final String namaibu, final String noibu, final String namawali,
                        final String nowali, final String catatan){

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
                            hashMap.put("email", email);
                            hashMap.put("username", username);
                            hashMap.put("kelas", kelas);
                            hashMap.put("ttl", ttl);
                            hashMap.put("tempat", tempat);
                            hashMap.put("nis", nis);
                            hashMap.put("alamat", alamat);
                            hashMap.put("nohp", nohp);
                            hashMap.put("namaayah", namayah);
                            hashMap.put("noayah", noayah);
                            hashMap.put("namaibu", namaibu);
                            hashMap.put("noibu", noibu);
                            hashMap.put("namawali", namawali);
                            hashMap.put("nowali", nowali);
                            hashMap.put("catatan", catatan);
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


    private  void  gambar(){

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAutoZoomEnabled(true)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .start(haldaftar.this);
    }

    private  void uploadimage(){
        String txt_username = edit4.getText().toString();

        final StorageReference audioRef = FirebaseStorage.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Images/*").child("Fotopp.jpg").child(txt_username);

        audioRef.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                audioRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        assert firebaseUser !=null;
                        String userid = firebaseUser.getUid();

                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                        databaseReference.child("imageUrl").setValue(uri.toString());

                        Toast.makeText(haldaftar.this, "upload success", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri uri = result.getUri();
                imguri = uri;
                img.setImageURI(uri);
            }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();

                if(BuildConfig.DEBUG) error.printStackTrace();
            }
        }
    }

}

