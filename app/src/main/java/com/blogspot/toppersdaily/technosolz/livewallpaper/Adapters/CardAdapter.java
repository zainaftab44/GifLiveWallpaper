package com.blogspot.toppersdaily.technosolz.livewallpaper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Variables;
import com.blogspot.toppersdaily.technosolz.livewallpaper.R;

import java.util.List;


public class CardAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private List<String> mData;

    public CardAdapter(List<String> data) {
        inflater = (LayoutInflater) Variables.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.swipe_card_view, parent, false);
        TextView textViewCard = (TextView) convertView.findViewById(R.id.swipeStack);
        textViewCard.setText(mData.get(position));

        return convertView;
    }
}