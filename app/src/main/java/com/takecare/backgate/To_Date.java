package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class To_Date extends Activity {
    DatePicker date_pick_from;
    ImageView imageButton_from;
    TextView date_selected;
    TextView to_date;
    TextView to;
    String[] details_array = new String[7];
    String[] incoming_text=new String[1];
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_date);

        imageButton_from= (ImageView) findViewById(R.id.imageButton_from);
        to_date=(TextView)findViewById(R.id.start_date_q);
        to_date.setText("On what date would you want the service to end?");

        to=(TextView)findViewById(R.id.from_text);
        to.setText("To: ");

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        details_array = getIntent().getStringArrayExtra("DETAILS");
        incoming_text = getIntent().getStringArrayExtra("FLOW_LEVEL_DETAILS");

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        date_pick_from = (DatePicker) findViewById(R.id.date_pick_from);
        int day = date_pick_from.getDayOfMonth();
        String month_word = getMonth(date_pick_from.getMonth() + 1);
        int year = date_pick_from.getYear();

        String d1 = sdf.format(new Date());
        Date d = null;
        try {
            d = sdf.parse(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (incoming_text[0].equals("HOUR")){
            date_pick_from.setMaxDate(d.getTime() + 2 * 24 * 60 * 60 * 1000);
        }
        date_pick_from.setMinDate(d.getTime() + 1 * 24 * 60 * 60 * 1000);

        date_selected=(TextView)findViewById(R.id.date_selected);
        date_selected.setText(String.valueOf(" "+day)+" "+month_word+" "+String.valueOf(year));
        details_array[2] = String.valueOf(date_selected.getText());

        /*calend = (CalendarView) findViewById(R.id.calendView);

        ViewGroup vg = (ViewGroup) calend.getChildAt(0);
        View child = vg.getChildAt(0);

        if(child instanceof TextView) {
            ((TextView)child).setTextColor(getResources().getColor(R.color.black));
        }

        calend.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                //animate_rotate();
                Toast.makeText(
                        getBaseContext(),
                        "Selected Date is " + dayOfMonth + " / " + month
                                + " / " + year, Toast.LENGTH_LONG).show();
                from_date.setText("From: "+ dayOfMonth + " / " + month + " / " + year);
            }
        });*/

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want
        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(incoming_text[0].equals("WEEKLY") || incoming_text[0].equals("WEEKEND") || incoming_text[0].equals("MONTHLY")){
                    Intent myIntent = new Intent(To_Date.this, Details.class);
                    myIntent.putExtra("DETAILS", details_array);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    To_Date.this.startActivity(myIntent);
                }
                else {
                    Intent myIntent = new Intent(To_Date.this, To_Time.class);
                    myIntent.putExtra("DETAILS", details_array);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    To_Date.this.startActivity(myIntent);
                }
            }
        });

        date_pick_from.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Log.d("tag", "finally found the listener, the date is: year " + year + ", month " + month+1 + ", dayOfMonth " + dayOfMonth);
                int day = date_pick_from.getDayOfMonth();
                String month_word = getMonth(date_pick_from.getMonth() + 1);
                year = date_pick_from.getYear();

                date_selected=(TextView)findViewById(R.id.date_selected);
                date_selected.setText(String.valueOf(" "+day)+" "+month_word+" "+String.valueOf(year));
                details_array[2] = String.valueOf(date_selected.getText());
            }
        });
    }

    public String getMonth(int month) {
        if(month==1){
            return "Jan";
        }
        else if(month==2){
            return "Feb";
        }
        else if(month==3){
            return "Mar";
        }
        else if(month==4){
            return "Apr";
        }
        else if(month==5){
            return "May";
        }
        else if(month==6){
            return "Jun";
        }
        else if(month==7){
            return "Jul";
        }
        else if(month==8){
            return "Aug";
        }
        else if(month==9){
            return "Sept";
        }
        else if(month==10){
            return "Oct";
        }
        else if(month==11){
            return "Nov";
        }
        else if(month==12){
            return "Dec";
        }
        else return "";
    }

    /*public void animate_rotate() {
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(200);
        rotate.setInterpolator(new LinearInterpolator());

        imageButton_from= (ImageView) findViewById(R.id.imageButton_from);
        imageButton_from.startAnimation(rotate);
    }*/
}
