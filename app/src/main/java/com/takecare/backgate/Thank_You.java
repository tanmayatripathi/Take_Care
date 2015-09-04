package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Thank_You extends Activity {

    ImageView imageButton_from;
    TextView service_exp;
    TextView service_exp_2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        imageButton_from=(ImageView)findViewById(R.id.imageButton_from);

        initialiseVariables();
        service_exp.setText(R.string.thank_you);
        service_exp_2.setText(R.string.intouch);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Thank_You.this, MainActivity.class);
                Thank_You.this.startActivity(myIntent);
                Toast.makeText(getApplicationContext(), "Returning back to the Home Page.",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void initialiseVariables(){
        service_exp = (TextView)findViewById(R.id.service_exp);
        service_exp_2 = (TextView)findViewById(R.id.service_exp_2);
        //email_value = (EditText)findViewById(R.id.email_value);
        //phone_value = (EditText)findViewById(R.id.phone_value);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
            //Things to Do
            Toast.makeText(getApplicationContext(), "Returning back to the Home Page.",
                    Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Thank_You.this, MainActivity.class);
            Thank_You.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
