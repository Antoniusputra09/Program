package com.example.asus.program;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class halfoto extends AppCompatActivity {

    Button btn;
    TextView tv;
    ImageView img;
    DatabaseReference databaseReference;
    FirebaseUser fuser;
    Uri imguri;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halfoto);
        btn = (Button) findViewById(R.id.btnupload);
        tv = (TextView) findViewById(R.id.fotousername);
        img = (ImageView) findViewById(R.id.uploadfoto);

        auth = FirebaseAuth.getInstance();

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                tv.setText(user.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gambar();

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = tv.getText().toString();

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

                                Toast.makeText(halfoto.this, "upload success", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(halfoto.this, halnavigation.class));
                            }
                        });
                    }
                });
            }
        });


    }

    private  void  gambar(){

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAutoZoomEnabled(true)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .start(halfoto.this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
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
