package com.example.asus.program;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class halnavigation extends AppCompatActivity {

    RecyclerView recyle;
    DatabaseReference databaseReference;
    ActionBarDrawerToggle swipe;
    RecyclerView.Adapter adapter;
    TextView nama1, kelas1;
    ImageView img1;
    CardView cv, cv2, cv3, cv4;
    private FirebaseAuth mAuth;
    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halnavigation);
        Toolbar toolbarhome = findViewById(R.id.home_toolbar);
        NavigationView navigationView = findViewById(R.id.navigasi_drawerhome);
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        cv = (CardView)findViewById(R.id.card1);
        cv2 = (CardView) findViewById(R.id.card2);
        cv3 = (CardView) findViewById(R.id.card13);
        cv4 = (CardView) findViewById(R.id.card4);
        img1 = (ImageView) findViewById(R.id.tombolchat);
        nama1 = (TextView) findViewById(R.id.nama1);
        kelas1 = (TextView) findViewById(R.id.kelas1);
        //textView = (TextView) findViewById(R.id.textdata);
        //recyle.setHasFixedSize(true);
        //recyle.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbarhome);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipe = new ActionBarDrawerToggle(halnavigation.this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(swipe);
        swipe.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.Logout:
                        showDialog();
                        break;
                }

                return true;
            }
        });

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                nama1.setText(user.getUsername());
                kelas1.setText(user.getKelas());
                String url = user.getImageUrl();


                CircleImageView gambar = findViewById(R.id.fotopp1);
                Glide.with(gambar.getContext())
                        .load(url)
                        .into(gambar);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        pindah();


    }

    private void showDialog() {
        AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        popDialog.setTitle("Keluar")
                .setMessage("Anda Ingin Keluar ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAuth.signOut();
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        popDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(swipe.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void pindah(){

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(halnavigation.this,haldata.class));
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(halnavigation.this,viewpager.class));
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(halnavigation.this,halnilai.class));
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(halnavigation.this,MainActivity.class));
            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(halnavigation.this,halspp.class));
            }
        });
    }


}
