package com.example.asus.program;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHoder> {

    private Context mContext;
    private List <User> userList;
    private boolean ischat;

    public userAdapter(Context mContext,List<User> userList, boolean ischat){
        this.userList = userList;
        this.mContext = mContext;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.users_item, parent,false);

        return new userAdapter.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {

        final User user_item = userList.get(position);
        holder.username.setText(user_item.getUsername());
        holder.status.setText(user_item.getStatus());
        if (user_item.getImageUrl().equals("default")){
            holder.pp.setImageResource(R.drawable.boy1);
        }
        else {
            Glide.with(mContext).load(user_item.getImageUrl()).into(holder.pp);
        }

        if (ischat){
            if (user_item.getStatus().equals("online")){
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            }else {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
            }
        }else {
            holder.img_on.setVisibility(View.GONE);
            holder.img_off.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, halchat.class);
                intent.putExtra("userid", user_item.getId());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView pp;
        private ImageView img_on;
        private ImageView img_off;
        private TextView status;

        public ViewHoder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_chat);
            pp = itemView.findViewById(R.id.pp_user_chat);
            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);
            status = itemView.findViewById(R.id.textstt);
        }
    }
}
