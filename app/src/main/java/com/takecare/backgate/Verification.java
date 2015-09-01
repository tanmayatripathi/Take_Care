package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Verification extends Activity {

    TextView name_value;
    TextView email_value;
    TextView phone_value;
    TextView from_date;
    TextView from_time;
    TextView to_date;
    TextView to_time;
    String[] details_array = new String[7];
    String msg = "Android: ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);


        initialiseVariables();

        addListenerOnButton();
    }

    private void initialiseVariables() {
        details_array = getIntent().getStringArrayExtra("DETAILS");
        name_value = (TextView)findViewById(R.id.name_value_1);
        email_value = (TextView)findViewById(R.id.email_value_1);
        phone_value = (TextView)findViewById(R.id.phone_value_1);
        from_date = (TextView)findViewById(R.id.from_date_value);
        from_time = (TextView)findViewById(R.id.from_time_value);
        to_date = (TextView)findViewById(R.id.to_date_value);
        to_time = (TextView)findViewById(R.id.to_time_value);
        name_value.setText(details_array[4]);
        email_value.setText(details_array[5]);
        phone_value.setText(details_array[6]);
        from_date.setText(details_array[0]);
        from_time.setText(details_array[1]);
        to_date.setText(details_array[2]);
        to_time.setText(details_array[3]);
        Log.d(msg, details_array[4]);
        Log.d(msg, details_array[5]);
        Log.d(msg, details_array[6]);
        Log.d(msg, details_array[0]);
        Log.d(msg, details_array[1]);
        Log.d(msg, details_array[2]);
        Log.d(msg, details_array[3]);

    }

    public void addListenerOnButton() {
    }
}
