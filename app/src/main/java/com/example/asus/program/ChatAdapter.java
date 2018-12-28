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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHoder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> chatList;
    private String imageurl;

    FirebaseUser fuser;

    public ChatAdapter(Context mContext,List<Chat> chatList, String imageurl){
        this.chatList = chatList;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);

            return new ChatAdapter.ViewHoder(view);
        }
        else {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);

            return new ChatAdapter.ViewHoder(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHoder holder, int position) {


        Chat chat =chatList.get(position);
        holder.tampil_pesan.setText(chat.getMessage());

        if (imageurl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }


    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{

        public TextView tampil_pesan;
        public ImageView profile_image;
        public ViewHoder(View itemView) {
            super(itemView);

            tampil_pesan = itemView.findViewById(R.id.tampil_pesan);
            profile_image = itemView.findViewById(R.id.ppchat);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        if (chatList.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;

        }else {
            return MSG_TYPE_LEFT;
        }
    }
}

