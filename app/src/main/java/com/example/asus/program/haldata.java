package com.example.asus.program;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class haldata extends AppCompatActivity {

    private TextView nama, kelas, ttl, tempat, Nis, alamat,
    nohp, email, namaayah, noayah, namaibu, noibu, namawali, nowali, catatan;

    private FirebaseUser fuser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haldata);

        nama = (TextView) findViewById(R.id.datanama);
        kelas = (TextView) findViewById(R.id.datakelas);
        ttl = (TextView) findViewById(R.id.tl2);
        tempat = (TextView) findViewById(R.id.tl4);
        Nis = (TextView)findViewById(R.id.tl6);
        alamat = (TextView)findViewById(R.id.tl8);
        nohp = (TextView)findViewById(R.id.tl10);
        email = (TextView)findViewById(R.id.tl12);
        namaayah = (TextView)findViewById(R.id.tl14);
        noayah = (TextView)findViewById(R.id.tl16);
        namaibu = (TextView)findViewById(R.id.tl18);
        noibu = (TextView)findViewById(R.id.tl20);
        namawali = (TextView)findViewById(R.id.tl22);
        nowali = (TextView)findViewById(R.id.tl24);
        catatan = (TextView)findViewById(R.id.tl26);


        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                nama.setText(user.getUsername());
                kelas.setText(user.getKelas());
                ttl.setText(user.getTtl());
                tempat.setText(user.getTempat());
                Nis.setText(user.getNis());
                alamat.setText(user.getAlamat());
                nohp.setText(user.getNohp());
                email.setText(user.getEmail());
                namaayah.setText(user.getNamaayah());
                noayah.setText(user.getNoayah());
                namaibu.setText(user.getNamaibu());
                noibu.setText(user.getNoibu());
                namawali.setText(user.getNamawali());
                nowali.setText(user.getNowali());
                catatan.setText(user.getCatatan());

                String url = user.getImageUrl();

                CircleImageView gambar = findViewById(R.id.fotopp2);
                Glide.with(gambar.getContext())
                        .load(url)
                        .into(gambar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
