package com.example.android.knowledgeloot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView newLootBtn, collectionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectionBtn = (TextView) findViewById(R.id.collection_clickable);
        newLootBtn = (TextView) findViewById(R.id.collect_new_clickable);

        //call to the collection method
        toCollection();
        //call to the enter item method
        toEnterItem();
    }

    //this will be called when the view to the collection is clicked.
    private void toCollection(){

        final Intent toCollectionIntent = new Intent(MainActivity.this, LootCollection.class);

        collectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(toCollectionIntent);
            }
        });
    }

    private void toEnterItem(){

        final Intent toEnterItemIntent = new Intent(MainActivity.this, EnterLoot.class);

        newLootBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(toEnterItemIntent);
            }
        });
    }
}
