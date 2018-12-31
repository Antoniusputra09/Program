package com.example.asus.program;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class halspp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser fuser;
    DatabaseReference databaseReference, reference;

    TextView tv, ketbln1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halspp);
        tv = (TextView) findViewById(R.id.username_spp);
        ketbln1 = (TextView) findViewById(R.id.ketbln1);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                tv.setText(user.getUsername());
                String url = user.getImageUrl();
                String januari = user.getJanuari();


                CircleImageView gambar = findViewById(R.id.dpspp);
                Glide.with(gambar.getContext())
                        .load(url)
                        .into(gambar);

                if(januari.equals("Lunas")){
                      ketbln1.setTextColor(R.color.colorPrimary);
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

  /*     reference = FirebaseDatabase.getInstance().getReference("Spp").child(fuser.getUid());
       reference.addValueEventListener(new ValueEventListener() {
           @SuppressLint("ResourceAsColor")
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Spp spp = dataSnapshot.getValue(Spp.class);
               //String januari = spp.getJanuari();
               ketbln1.setText(spp.getJanuari());

               //if(januari.equals("Lunas")){
                 //  ketbln1.setTextColor(R.color.colorPrimary);
               //}
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
*/
    }
}
