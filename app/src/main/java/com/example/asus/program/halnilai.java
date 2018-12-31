package com.example.asus.program;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class halnilai extends AppCompatActivity {

    RecyclerView recyle;
    DatabaseReference reference;
    FirebaseUser fuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halnilai);
        recyle = (RecyclerView) findViewById(R.id.rcynilai);
        recyle.setHasFixedSize(true);
        recyle.setLayoutManager(new LinearLayoutManager(halnilai.this));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        ImageView text;
        ImageView img;
        Typeface fonte;
        TextView nama_view, nama_view2;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;


        }

        public void setNama(String nama) {
            nama_view = mView.findViewById(R.id.namamapel);
            nama_view.setText(nama);
        }

        public void setNilai(String nilai) {
            nama_view2 = mView.findViewById(R.id.nilaimapel);
            nama_view.setText(nilai);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        fuser = FirebaseAuth.getInstance().getCurrentUser();
      // Query databaseReference = FirebaseDatabase.getInstance().getReference("Nilai").orderByChild("id").startAt(fuser.getUid()).endAt(fuser.getUid() + "\uf8ff");
        reference = FirebaseDatabase.getInstance().getReference("Nilai");
        FirebaseRecyclerAdapter<Nilai, halnilai.ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Nilai, halnilai.ViewHolder>
                (Nilai.class,
                R.layout.controlnilai, halnilai.
                ViewHolder.class, reference) {
            @Override
            protected void populateViewHolder(halnilai.ViewHolder viewHolder, Nilai model, int position) {
                viewHolder.setNama(model.getNama());
                viewHolder.setNilai(model.getNilai());

            }
        };
        recyle.setAdapter(firebaseRecyclerAdapter);

    }


}
