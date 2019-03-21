package com.example.asus.program.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.program.R;
import com.example.asus.program.data.Data;

import java.util.List;

public class adapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public adapter (Activity activity,List<Data> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.list_row,null);

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView nama = (TextView) view.findViewById(R.id.nama);
        TextView alamat = (TextView) view.findViewById(R.id.alamatt);

        Data data = items.get(position);
        id.setText(data.getId());
        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());

        return view;
    }
}
