package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends Activity {

    private RelativeLayout rel_lay_hourly;
    private RelativeLayout rel_lay_weekly;
    private RelativeLayout rel_lay_weekend;
    private RelativeLayout rel_lay_monthly;
    private RelativeLayout rel_lay_entertainment;

    private TextView read_more_hourly;
    private TextView read_more_weekly;
    private TextView read_more_weekend;
    private TextView read_more_monthly;
    private TextView read_more_entertainment;

    private TextView hourly_service;
    private TextView weekly_service;
    private TextView weekend_service;
    private TextView monthly_service;
    private TextView entertainment_service;

    private ImageView imageButton_hour;
    private ImageView imageButton_weekly;
    private ImageView imageButton_weekend;
    private ImageView imageButton_monthly;
    private ImageView imageButton_entertainment;

    String[] service_read_more = new String[1];

    String hourly_text = "<font color=#00000>Hourly </font> <font color=#00bdc3>Service</font>";
    String weekly_text = "<font color=#00000>Weekly </font> <font color=#00bdc3>Service</font>";
    String weekend_text = "<font color=#00000>Weekend </font> <font color=#00bdc3>Service</font>";
    String monthly_text = "<font color=#00000>Monthly</font> <font color=#00bdc3>Service</font>";
    String entertainment_text = "<font color=#00000>Entertainment</font> <font color=#00bdc3>Service</font>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hourly_service=(TextView)findViewById(R.id.hourly_service);
        hourly_service.setText(Html.fromHtml(hourly_text));

        weekly_service=(TextView)findViewById(R.id.weekly_service);
        weekly_service.setText(Html.fromHtml(weekly_text));

        weekend_service=(TextView)findViewById(R.id.weekend_service);
        weekend_service.setText(Html.fromHtml(weekend_text));

        monthly_service=(TextView)findViewById(R.id.monthly_service);
        monthly_service.setText(Html.fromHtml(monthly_text));

        entertainment_service=(TextView)findViewById(R.id.entertainment_service);
        entertainment_service.setText(Html.fromHtml(entertainment_text));

        imageButton_hour=(ImageView)findViewById(R.id.imageButton_hour);
        imageButton_weekend=(ImageView)findViewById(R.id.imageButton_weekend);
        imageButton_weekly=(ImageView)findViewById(R.id.imageButton_weekly);
        imageButton_monthly=(ImageView)findViewById(R.id.imageButton_monthly);
        imageButton_entertainment=(ImageView)findViewById(R.id.imageButton_entertainment);

        read_more_hourly=(TextView)findViewById(R.id.read_more_hourly);
        read_more_weekly=(TextView)findViewById(R.id.read_more_weekly);
        read_more_weekend=(TextView)findViewById(R.id.read_more_weekend);
        read_more_monthly=(TextView)findViewById(R.id.read_more_monthly);
        read_more_entertainment=(TextView)findViewById(R.id.read_more_entertainment);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want
        imageButton_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
                    if(rel_lay_hourly.getVisibility()==view.VISIBLE) {
                        Intent myIntent = new Intent(MainActivity.this, From_Date.class);
                        service_read_more[0]="HOUR";
                        myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                        MainActivity.this.startActivity(myIntent);
                    }
                    else{
                        try {
                            animate_hourly(view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }

            }
        });

        imageButton_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
                    if(rel_lay_weekly.getVisibility()==view.VISIBLE) {
                        Intent myIntent = new Intent(MainActivity.this, From_Date.class);
                        service_read_more[0]="WEEKLY";
                        myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                        MainActivity.this.startActivity(myIntent);
                    }
                    else{
                        try {
                            animate_weekly(view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }

            }
        });

        imageButton_weekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
                    if(rel_lay_weekend.getVisibility()==view.VISIBLE) {
                        Intent myIntent = new Intent(MainActivity.this, From_Date.class);
                        service_read_more[0]="WEEKEND";
                        myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                        MainActivity.this.startActivity(myIntent);
                    }
                    else{
                        try {
                            animate_weekend(view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }

            }
        });

        imageButton_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
                    if(rel_lay_monthly.getVisibility()==view.VISIBLE) {
                        Intent myIntent = new Intent(MainActivity.this, From_Date.class);
                        service_read_more[0]="MONTHLY";
                        myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                        MainActivity.this.startActivity(myIntent);
                    }
                    else{
                        try {
                            animate_monthly(view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }

            }
        });

        imageButton_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
                    if(rel_lay_entertainment.getVisibility()==view.VISIBLE) {
                        Intent myIntent = new Intent(MainActivity.this, From_Date.class);
                        service_read_more[0]="ENTERTAINMENT";
                        myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                        MainActivity.this.startActivity(myIntent);
                    }
                    else{
                        try {
                            animate_entertainment(view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }

            }
        });

        read_more_hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
                if(rel_lay_hourly.getVisibility()==view.VISIBLE) {
                    Intent myIntent = new Intent(MainActivity.this, Read_More.class);
                    service_read_more[0]="HOUR";
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                    MainActivity.this.startActivity(myIntent);
                }
                else{
                    try {
                        animate_hourly(view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        read_more_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
                if(rel_lay_weekly.getVisibility()==view.VISIBLE) {
                    Intent myIntent = new Intent(MainActivity.this, Read_More.class);
                    service_read_more[0]="WEEKLY";
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                    MainActivity.this.startActivity(myIntent);
                }
                else{
                    try {
                        animate_weekly(view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        read_more_weekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
                if(rel_lay_weekend.getVisibility()==view.VISIBLE) {
                    Intent myIntent = new Intent(MainActivity.this, Read_More.class);
                    service_read_more[0]="WEEKEND";
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                    MainActivity.this.startActivity(myIntent);
                }
                else{
                    try {
                        animate_weekend(view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        read_more_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
                if(rel_lay_monthly.getVisibility()==view.VISIBLE) {
                    Intent myIntent = new Intent(MainActivity.this, Read_More.class);
                    service_read_more[0]="MONTHLY";
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                    MainActivity.this.startActivity(myIntent);
                }
                else{
                    try {
                        animate_monthly(view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        read_more_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
                if(rel_lay_entertainment.getVisibility()==view.VISIBLE) {
                    Intent myIntent = new Intent(MainActivity.this, Read_More.class);
                    service_read_more[0]="ENTERTAINMENT";
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", service_read_more);
                    MainActivity.this.startActivity(myIntent);
                }
                else{
                    try {
                        animate_entertainment(view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void animate_hourly(View view) throws Exception {
        //hourly_service_press=(TextView)findViewById(R.id.hourly_service_press);
        //String name = getIDName(view, R.id.class);
        rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        rel_lay_hourly.setVisibility(TextView.VISIBLE);
        rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
        rel_lay_weekly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
        rel_lay_weekend.setVisibility(TextView.INVISIBLE);
        rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
        rel_lay_monthly.setVisibility(TextView.INVISIBLE);
        rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
        rel_lay_entertainment.setVisibility(TextView.INVISIBLE);

        //rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        //rel_lay_hourly.setVisibility(TextView.VISIBLE);
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.anim);
        animation.setDuration(200);
        rel_lay_hourly.setAnimation(animation);
        rel_lay_hourly.animate();
        animation.start();
        Animation animation_rev   =    AnimationUtils.loadAnimation(this, R.anim.anim_reverse);
        animation_rev.setDuration(200);
        rel_lay_weekly.setAnimation(animation_rev);
        rel_lay_weekly.animate();
        rel_lay_weekend.setAnimation(animation_rev);
        rel_lay_weekend.animate();
        rel_lay_monthly.setAnimation(animation_rev);
        rel_lay_monthly.animate();
        rel_lay_entertainment.setAnimation(animation_rev);
        rel_lay_entertainment.animate();
        animation_rev.start();
    }

    public void animate_weekly(View view) throws Exception {
        //hourly_service_press=(TextView)findViewById(R.id.hourly_service_press);
        //String name = getIDName(view, R.id.class);
        rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        rel_lay_hourly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
        rel_lay_weekly.setVisibility(TextView.VISIBLE);
        rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
        rel_lay_weekend.setVisibility(TextView.INVISIBLE);
        rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
        rel_lay_monthly.setVisibility(TextView.INVISIBLE);
        rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
        rel_lay_entertainment.setVisibility(TextView.INVISIBLE);

        //rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        //rel_lay_hourly.setVisibility(TextView.VISIBLE);
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.anim);
        animation.setDuration(200);
        rel_lay_weekly.setAnimation(animation);
        rel_lay_weekly.animate();
        animation.start();
        Animation animation_rev   =    AnimationUtils.loadAnimation(this, R.anim.anim_reverse);
        animation_rev.setDuration(200);
        rel_lay_hourly.setAnimation(animation_rev);
        rel_lay_hourly.animate();
        rel_lay_weekend.setAnimation(animation_rev);
        rel_lay_weekend.animate();
        rel_lay_monthly.setAnimation(animation_rev);
        rel_lay_monthly.animate();
        rel_lay_entertainment.setAnimation(animation_rev);
        rel_lay_entertainment.animate();
        animation_rev.start();
    }

    public void animate_weekend(View view) throws Exception {
        //hourly_service_press=(TextView)findViewById(R.id.hourly_service_press);
        //String name = getIDName(view, R.id.class);
        rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        rel_lay_hourly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
        rel_lay_weekly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
        rel_lay_weekend.setVisibility(TextView.VISIBLE);
        rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
        rel_lay_monthly.setVisibility(TextView.INVISIBLE);
        rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
        rel_lay_entertainment.setVisibility(TextView.INVISIBLE);

        //rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        //rel_lay_hourly.setVisibility(TextView.VISIBLE);
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.anim);
        animation.setDuration(200);
        rel_lay_weekend.setAnimation(animation);
        rel_lay_weekend.animate();
        animation.start();
        Animation animation_rev   =    AnimationUtils.loadAnimation(this, R.anim.anim_reverse);
        animation_rev.setDuration(200);
        rel_lay_weekly.setAnimation(animation_rev);
        rel_lay_weekly.animate();
        rel_lay_hourly.setAnimation(animation_rev);
        rel_lay_hourly.animate();
        rel_lay_monthly.setAnimation(animation_rev);
        rel_lay_monthly.animate();
        rel_lay_entertainment.setAnimation(animation_rev);
        rel_lay_entertainment.animate();
        animation_rev.start();
    }

    public void animate_monthly(View view) throws Exception {
        //hourly_service_press=(TextView)findViewById(R.id.hourly_service_press);
        //String name = getIDName(view, R.id.class);
        rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        rel_lay_hourly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
        rel_lay_weekly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
        rel_lay_weekend.setVisibility(TextView.INVISIBLE);
        rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
        rel_lay_monthly.setVisibility(TextView.VISIBLE);
        rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
        rel_lay_entertainment.setVisibility(TextView.INVISIBLE);

        //rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        //rel_lay_hourly.setVisibility(TextView.VISIBLE);
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.anim);
        animation.setDuration(200);
        rel_lay_monthly.setAnimation(animation);
        rel_lay_monthly.animate();
        animation.start();
        Animation animation_rev   =    AnimationUtils.loadAnimation(this, R.anim.anim_reverse);
        animation_rev.setDuration(200);
        rel_lay_weekly.setAnimation(animation_rev);
        rel_lay_weekly.animate();
        rel_lay_weekend.setAnimation(animation_rev);
        rel_lay_weekend.animate();
        rel_lay_hourly.setAnimation(animation_rev);
        rel_lay_hourly.animate();
        rel_lay_entertainment.setAnimation(animation_rev);
        rel_lay_entertainment.animate();
        animation_rev.start();
    }

    public void animate_entertainment(View view) throws Exception {
        //hourly_service_press=(TextView)findViewById(R.id.hourly_service_press);
        //String name = getIDName(view, R.id.class);
        rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        rel_lay_hourly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekly = (RelativeLayout) findViewById(R.id.rel_lay_weekly);
        rel_lay_weekly.setVisibility(TextView.INVISIBLE);
        rel_lay_weekend = (RelativeLayout) findViewById(R.id.rel_lay_weekend);
        rel_lay_weekend.setVisibility(TextView.INVISIBLE);
        rel_lay_monthly = (RelativeLayout) findViewById(R.id.rel_lay_monthly);
        rel_lay_monthly.setVisibility(TextView.INVISIBLE);
        rel_lay_entertainment = (RelativeLayout) findViewById(R.id.rel_lay_entertainment);
        rel_lay_entertainment.setVisibility(TextView.VISIBLE);

        //rel_lay_hourly = (RelativeLayout) findViewById(R.id.rel_lay_hourly);
        //rel_lay_hourly.setVisibility(TextView.VISIBLE);
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.anim);
        animation.setDuration(200);
        rel_lay_entertainment.setAnimation(animation);
        rel_lay_entertainment.animate();
        animation.start();
        Animation animation_rev   =    AnimationUtils.loadAnimation(this, R.anim.anim_reverse);
        animation_rev.setDuration(200);
        rel_lay_weekly.setAnimation(animation_rev);
        rel_lay_weekly.animate();
        rel_lay_weekend.setAnimation(animation_rev);
        rel_lay_weekend.animate();
        rel_lay_monthly.setAnimation(animation_rev);
        rel_lay_monthly.animate();
        rel_lay_hourly.setAnimation(animation_rev);
        rel_lay_hourly.animate();
        animation_rev.start();
    }

    public static String getIDName(View view, Class<?> clazz) throws Exception {

        Integer id = view.getId();
        Field[] ids = clazz.getFields();
        for (int i = 0; i < ids.length; i++) {
            Object val = ids[i].get(null);
            if (val != null && val instanceof Integer
                    && ((Integer) val).intValue() == id.intValue()) {
                return ids[i].getName();
            }
        }

        return "";

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

/*public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_date);

        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.logo_action_bar);
    }

/*    public void animate(View view){
        LinearLayout dialog   = (LinearLayout)findViewById(R.id.dialog);
        dialog.setVisibility(LinearLayout.VISIBLE);
        Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.anim);
        animation.setDuration(500);
        dialog.setAnimation(animation);
        dialog.animate();
        animation.start();
    }

}*/