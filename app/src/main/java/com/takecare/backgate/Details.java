package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class Details extends Activity {

    ImageView imageButton_from;
    EditText name_value;
    EditText phone_value;
    EditText email_value;
    String[] details = new String[7];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        imageButton_from=(ImageView)findViewById(R.id.imageButton_from);

        initialiseVariables();

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Details.this, Verification.class);
                details[4] = String.valueOf(name_value.getText());
                details[5] = String.valueOf(email_value.getText());
                details[6] = String.valueOf(phone_value.getText());
                myIntent.putExtra("DETAILS",details);
                Details.this.startActivity(myIntent);
            }
        });
    }

    public void initialiseVariables(){
        name_value = (EditText)findViewById(R.id.name_value);
        email_value = (EditText)findViewById(R.id.email_value);
        phone_value = (EditText)findViewById(R.id.phone_value);
    }
}
