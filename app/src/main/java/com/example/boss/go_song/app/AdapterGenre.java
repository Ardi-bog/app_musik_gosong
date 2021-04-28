package com.example.boss.go_song.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.boss.go_song.R;

import java.util.List;

public class AdapterGenre extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<dataGenre> items;

    public AdapterGenre(Activity activity, List<dataGenre> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.tampil_genre, null);

        TextView genre = (TextView) convertView.findViewById(R.id.txt_genre);

        dataGenre data = items.get(position);

        genre.setText(data.getGenre());

        return convertView;
    }
}
