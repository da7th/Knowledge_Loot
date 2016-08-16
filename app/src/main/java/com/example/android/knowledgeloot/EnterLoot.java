package com.example.android.knowledgeloot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by da7th on 8/15/2016.
 */
public class EnterLoot extends AppCompatActivity {

    private TextView toConfirmBtn;
    private EditText enteredLoot;
    private String checkLength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_loot);

        toConfirmBtn = (TextView) findViewById(R.id.loot_confirmation_button);
        enteredLoot = (EditText) findViewById(R.id.entered_loot);



        //calls the confirmation method when the view is clicked
        toConfirmScrn();
    }


    private void toConfirmScrn(){

        final Intent toConfirmationScrnIntent = new Intent(EnterLoot.this, LootConfirmation.class);

        toConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkLength = enteredLoot.getText().toString();

                if(checkLength.length() < 10){

                }else{

                    toConfirmationScrnIntent.putExtra("loot",checkLength);
                    startActivity(toConfirmationScrnIntent);
                }

            }
        });
    }

}
