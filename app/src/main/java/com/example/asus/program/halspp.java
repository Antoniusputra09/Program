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

    TextView tv, ketbln1, ketbln2, ketbln3, ketbln4, ketbln5, ketbln6, ketbln7, ketbln8, ketbln9, ketbln10, ketbln11, ketbln12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halspp);
        tv = (TextView) findViewById(R.id.username_spp);
        ketbln1 = (TextView) findViewById(R.id.ketbln1);
        ketbln2 = (TextView) findViewById(R.id.ketbln2);
        ketbln3 = (TextView) findViewById(R.id.ketbln3);
        ketbln4 = (TextView) findViewById(R.id.ketbln4);
        ketbln5 = (TextView) findViewById(R.id.ketbln5);
        ketbln6 = (TextView) findViewById(R.id.ketbln6);
        ketbln7 = (TextView) findViewById(R.id.ketbln7);
        ketbln8 = (TextView) findViewById(R.id.ketbln8);
        ketbln9 = (TextView) findViewById(R.id.ketbln9);
        ketbln10 = (TextView) findViewById(R.id.ketbln10);
        ketbln11= (TextView) findViewById(R.id.ketbln11);
        ketbln12 = (TextView) findViewById(R.id.ketbln12);

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
                String februari = user.getFebruari();
                String maret = user.getMaret();
                String april = user.getApril();
                String mei = user.getMei();
                String juni = user.getJuni();
                String juli = user.getJuli();
                String agustus = user.getAgustus();
                String september = user.getSeptember();
                String oktober = user.getOktober();
                String november = user.getNovember();
                String desember = user.getDesember();


                CircleImageView gambar = findViewById(R.id.dpspp);
                Glide.with(gambar.getContext())
                        .load(url)
                        .into(gambar);

                if(januari.equals("Lunas")){
                      ketbln1.setTextColor(R.color.hijau);
                    }


                if(februari.equals("Lunas")){
                    ketbln2.setTextColor(R.color.hijau);
                }

                if(maret.equals("Lunas")){
                    ketbln3.setTextColor(R.color.hijau);
                }

                if(april.equals("Lunas")){
                    ketbln4.setTextColor(R.color.hijau);
                }

                if(mei.equals("Lunas")){
                    ketbln5.setTextColor(R.color.hijau);
                }

                if(juni.equals("Lunas")){
                    ketbln6.setTextColor(R.color.hijau);
                }

                if(juli.equals("Lunas")){
                    ketbln7.setTextColor(R.color.hijau);
                }

                if(agustus.equals("Lunas")){
                    ketbln8.setTextColor(R.color.hijau);
                }

                if(september.equals("Lunas")){
                    ketbln9.setTextColor(R.color.hijau);
                }

                if(oktober.equals("Lunas")){
                    ketbln10.setTextColor(R.color.hijau);
                }

                if(november.equals("Lunas")){
                    ketbln11.setTextColor(R.color.hijau);
                }


                if(desember.equals("Lunas")){
                    ketbln12.setTextColor(R.color.hijau);
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
