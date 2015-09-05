package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Read_More extends Activity {

    ImageView imageButton_from;
    TextView service_exp;
    TextView service_exp_2;
    String[] incoming_text=new String[1];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_more);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        imageButton_from=(ImageView)findViewById(R.id.imageButton_from);

        initialiseVariables();
        incoming_text = getIntent().getStringArrayExtra("FLOW_LEVEL_DETAILS");

        if(incoming_text[0].equals("HOUR")){
            service_exp.setText(R.string.hourly_service);
            service_exp_2.setText(R.string.hourly_service_2);
        }
        else if(incoming_text[0].equals("WEEKLY")){
            service_exp.setText(R.string.weekly_service);
            service_exp_2.setText(R.string.weekly_service_2);
        }
        else if(incoming_text[0].equals("WEEKEND")){
            service_exp.setText(R.string.weekend_service);
            service_exp_2.setText(R.string.weekend_service_2);
        }
        else if(incoming_text[0].equals("MONTHLY")){
            service_exp.setText(R.string.monthly_service);
            service_exp_2.setText(R.string.monthly_service_2);
        }
        else if(incoming_text[0].equals("ENTERTAINMENT")){
            service_exp.setText(R.string.entertainment_service);
            service_exp_2.setText(R.string.entertainment_service_2);
        }

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    Intent myIntent = new Intent(Read_More.this, From_Date.class);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    Read_More.this.startActivity(myIntent);
                }
                else{
                    Toast.makeText(Read_More.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initialiseVariables(){
        service_exp = (TextView)findViewById(R.id.service_exp);
        service_exp_2 = (TextView)findViewById(R.id.service_exp_2);
        //email_value = (EditText)findViewById(R.id.email_value);
        //phone_value = (EditText)findViewById(R.id.phone_value);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
