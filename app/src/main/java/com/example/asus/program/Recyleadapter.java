package com.example.asus.program;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;

public class Recyleadapter extends RecyclerView.Adapter <Recyleadapter.RecyclerViewHolder> {

    ArrayList<String> arrayList = new ArrayList<>();

    public Recyleadapter(ArrayList <String> arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tbrowlayout,parent,false);
       RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.textView.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public RecyclerViewHolder(View view)
        {

            super(view);

            textView = (TextView) view.findViewById(R.id.textnav);

        }
    }
}
