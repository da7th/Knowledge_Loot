package com.example.android.knowledgeloot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by da7th on 8/15/2016.
 */
public class LootAdapter extends ArrayAdapter<Loot> {


    public LootAdapter(Context context, ArrayList<Loot> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Loot currentLoot = getItem(position);

        TextView newLootEntered = (TextView) listView.findViewById(R.id.new_loot_collected);
        newLootEntered.setText(currentLoot.getInfo());

        return listView;
    }
}
