package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends Activity {

    ImageView imageButton_from;
    EditText name_value;
    EditText phone_value;
    EditText email_value;
    TextView name_hint;
    TextView email_hint;
    TextView phone_hint;
    String[] details_array = new String[7];
    String[] incoming_text=new String[1];

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

        details_array = getIntent().getStringArrayExtra("DETAILS");
        incoming_text = getIntent().getStringArrayExtra("FLOW_LEVEL_DETAILS");

        initialiseVariables();

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!String.valueOf(name_value.getText()).matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")){
                    name_hint.setText("Please enter a valid name");
                }
                else if (String.valueOf(email_value.getText()).length() == 0){
                    email_hint.setText("Email cannot be empty");
                }
                else if (!String.valueOf(phone_value.getText()).matches("^[789][0-9]{9}") && String.valueOf(phone_value.getText()).length() != 10){
                    phone_hint.setText("Please enter a valid phone number");
                }
                else{
                    Intent myIntent = new Intent(Details.this, Verification.class);
                    details_array[4] = String.valueOf(name_value.getText());
                    details_array[5] = String.valueOf(email_value.getText());
                    details_array[6] = String.valueOf(phone_value.getText());
                    myIntent.putExtra("DETAILS", details_array);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    Details.this.startActivity(myIntent);
                }
            }
        });

        /*name_value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!String.valueOf(name_value.getText()).matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
                    name_value.setTextColor(Color.parseColor("#E17777"));
                    Toast.makeText(Details.this, "Please enter only alphabets", Toast.LENGTH_LONG).show();
                    //imageButton_from.setVisibility(View.GONE);
                }else {
                    name_value.setTextColor(Color.parseColor("#009688"));
                    imageButton_from.setVisibility(View.VISIBLE);
                }
            }
        });

        phone_value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!String.valueOf(phone_value.getText()).matches("^[789]\\d{9}$")){
                    phone_value.setTextColor(Color.parseColor("#E17777"));
                    //Toast.makeText(Details.this, "Please enter a valid Phone Number", Toast.LENGTH_LONG).show();
                }else {
                    phone_value.setTextColor(Color.parseColor("#009688"));
                    imageButton_from.setVisibility(View.VISIBLE);
                }
            }
        });

        email_value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (String.valueOf(email_value.getText()).length() == 0){
                    email_value.setTextColor(Color.parseColor("#E17777"));
                    Toast.makeText(Details.this, "Please enter a valid Email Id", Toast.LENGTH_LONG).show();
                    //imageButton_from.setVisibility(View.GONE);
                }else{
                    email_value.setTextColor(Color.parseColor("#009688"));
                    imageButton_from.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }

    public void initialiseVariables(){
        name_value = (EditText)findViewById(R.id.name_value);
        email_value = (EditText)findViewById(R.id.email_value);
        phone_value = (EditText)findViewById(R.id.phone_value);
        name_hint = (TextView)findViewById(R.id.name_hint);
        name_hint.setTextColor(Color.parseColor("#E17777"));
        email_hint = (TextView)findViewById(R.id.email_hint);
        email_hint.setTextColor(Color.parseColor("#E17777"));
        phone_hint = (TextView)findViewById(R.id.phone_hint);
        phone_hint.setTextColor(Color.parseColor("#E17777"));
    }
}
