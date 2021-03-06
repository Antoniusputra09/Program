package com.example.asus.program;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends Fragment {
    private userAdapter userAdapter;
    private RecyclerView recyclerView;
    private List<User> user_itemList;
    private DatabaseReference databaseReference;

    EditText cari;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcyuserchat);
        recyclerView.setHasFixedSize(true);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(UserFragment.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        user_itemList = new ArrayList<>();
        //readuser();

        cari = (EditText) view.findViewById(R.id.cariuser);

        cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                cariUser(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }

    private void cariUser(String s) {

    final FirebaseUser fuser  = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").startAt(s).endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_itemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);


                    assert user != null;
                    assert fuser != null;
                    if (!user.getId().equals(fuser.getUid())){
                        user_itemList.add(user);
                    }
                }

                userAdapter = new userAdapter(getContext(),user_itemList, false);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setUsername(String nama_user){
            TextView nama_view = mView.findViewById(R.id.username_chat);
            nama_view.setText(nama_user);
        }
        public void setImageUrl(String fotopp){
            CircleImageView gambar = mView.findViewById(R.id.pp_user_chat);
            Glide.with(gambar.getContext())
                    .load(fotopp)
                    .into(gambar);
        }

        public void setStatus(String status){
            TextView txt_status = mView.findViewById(R.id.textstt);
            txt_status.setText(status);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
         databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseRecyclerAdapter<User, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter
                <User, ViewHolder>(
                User.class,
                R.layout.users_item,
                ViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, User model, int position) {
                final String postkey = getRef(position).getKey();
                viewHolder.setImageUrl(model.getImageUrl());
                viewHolder.setUsername(model.getUsername());
                viewHolder.setStatus(model.getStatus());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(getActivity(),halchat
                                .class);
                        in.putExtra("userid", postkey);
                        startActivity(in);
                        getActivity().finish();
                    }
                });

                //       viewHolder.setImgUrl(model.getImgUrl());

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    /*private void readuser() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_itemList.clear();
                //snapshot ;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                User user = snapshot.getValue(User.class);

                assert user != null;
                assert firebaseUser != null;
                if (user.getId().equals(firebaseUser.getUid())) {
                    user_itemList.add(user);
                }
            }
                userAdapter = new userAdapter(getContext(),user_itemList);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    */

}