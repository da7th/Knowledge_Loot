package com.example.android.knowledgeloot;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by da7th on 8/15/2016.
 */
public class LootCollection extends AppCompatActivity {

    private ArrayList<Loot> lootList;
    private LootAdapter lootAdapter;
    private LootDBHelper myDB;
    private String LOG_TAG = "MainActivity";
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loot_collection);

        listView = (ListView) findViewById(R.id.collection_list);

        myDB = new LootDBHelper(this);

        lootList = new ArrayList<Loot>();
        lootAdapter = new LootAdapter(this, lootList);

        listView.setAdapter(lootAdapter);

        lootList.add(new Loot("First aid for someone who’s unresponsive and not breathing\n" +
                "-Check breathing by tilting their head backwards and looking and feeling for breaths.\n" +
                "-Call 999 as soon as possible, or get someone else to do it.\n" +
                "-Push firmly downwards in the middle of the chest and then release.\n" +
                "-Push at a regular rate until help arrives."));
        lootList.add(new Loot("First aid for someone who’s bleeding heavily\n" +
                "-Put pressure on the wound with whatever is available to stop or slow down the flow of blood.\n" +
                "-As soon as possible, call 999 or get someone else to do it.\n" +
                "-Keep pressure on the wound until help arrives."));
        lootList.add(new Loot("First aid for burns\n" +
                "-Cool the burn under cold running water for at least ten minutes.\n" +
                "Loosely cover the burn with cling film or a clean plastic bag.\n" +
                "If necessary, call 999 or get someone else to do it."));
        lootList.add(new Loot("First aid for a diabetic emergency\n" +
                "-Give them something sweet to eat or a non-diet drink.\n" +
                "Reassure the person. If there is no improvement, call 999 or get someone else to do it."));
        lootList.add(new Loot("First aid for a heart attack\n" +
                "-The person may have persistent, vice-like chest pain, which may spread to their arms, neck, jaw, back or stomach.\n" +
                "-Call 999 immediately or get someone else to do it.\n" +
                "-Make sure they are in a position that is comfortable for them (e.g. sit them on the floor, leaning against a wall or chair).\n" +
                "-Give them constant reassurance while waiting for the ambulance."));
        lootList.add(new Loot("First aid for a stroke\n" +
                "-Think FAST. Face: is there weakness on one side of the face? Arms: can they raise both arms? Speech: is their speech easily understood? Time: to call 999.\n" +
                "-Immediately call 999 or get someone else to do it."));

        viewAllData();
        viewDetails();

        if (lootAdapter.isEmpty()) {

            listView.setEmptyView(findViewById(R.id.collection_list_empty_view));
        } else {

            lootAdapter.notifyDataSetChanged();
        }
    }

    private void viewAllData() {

        Cursor res = myDB.readAllData();
        if (res.getCount() == 0) {

            Toast.makeText(this, "nothing to show", Toast.LENGTH_SHORT);
            return;
        }

        while (res.moveToNext()) {
            lootList.add(new Loot(res.getString(1)));
        }
    }

    private void viewDetails(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Loot currentLoot = lootAdapter.getItem(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(LootCollection.this);
                builder.setMessage(currentLoot.getInfo());
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
