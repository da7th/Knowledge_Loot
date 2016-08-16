package com.example.android.knowledgeloot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by da7th on 8/15/2016.
 */
public class LootConfirmation extends AppCompatActivity {

    TextView acceptBtn, rejectBtn, lootPreview;
    LootDBHelper myDB;
    String lootReceived;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loot_confirmation);

        myDB = new LootDBHelper(this);

        acceptBtn = (TextView) findViewById(R.id.confirm_loot_accept);
        rejectBtn = (TextView) findViewById(R.id.confirm_loot_reject);
        lootPreview = (TextView) findViewById(R.id.loot_preview);

        Intent receivedIntent = getIntent();
        lootReceived = receivedIntent.getStringExtra("loot");

        lootPreview.setText(lootReceived);

        acceptLoot();
        rejectLoot();
    }

    private void acceptLoot(){

        final Intent toHome = new Intent(LootConfirmation.this, MainActivity.class);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myDB.insertData(lootReceived)){

                    startActivity(toHome);
                }else{
                    Log.v("Data Insert:","Data Inserted");
                }
            }
        });


    }

    private void rejectLoot(){

        final Intent toHome = new Intent(LootConfirmation.this, MainActivity.class);

        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(toHome);
            }
        });
    }


}
