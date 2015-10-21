package com.takecare.backgate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class From_Date extends Activity {
    DatePicker date_pick_from;
    ImageView imageButton_from;
    TextView date_selected;
    TextView temp_text;
    String[] details_array = new String[7];
    String[] incoming_text=new String[1];
    String linear_value;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_date);

        imageButton_from= (ImageView) findViewById(R.id.imageButton_from);

        temp_text = (TextView) findViewById(R.id.from_text);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        incoming_text = getIntent().getStringArrayExtra("FLOW_LEVEL_DETAILS");

        if (incoming_text[0].equals("ENTERTAINMENT")){
            temp_text.setText("On: ");
        }

        date_pick_from = (DatePicker) findViewById(R.id.date_pick_from);

        String d1 = sdf.format(new Date());
        Date d = null;
        try {
            d = sdf.parse(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date_pick_from.setMinDate(d.getTime() + 1 * 24 * 60 * 60 * 1000);


        int day = date_pick_from.getDayOfMonth();
        String month_word = getMonth(date_pick_from.getMonth() + 1);
        int year = date_pick_from.getYear();

        date_selected = (TextView) findViewById(R.id.date_selected);
        date_selected.setText(String.valueOf(" " + day) + " " + month_word + " " + String.valueOf(year));
        details_array[0] = String.valueOf(date_selected.getText());


        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want
        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(incoming_text[0].equals("ENTERTAINMENT")){
                    Intent myIntent = new Intent(From_Date.this, Details.class);
                    myIntent.putExtra("DETAILS", details_array);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    From_Date.this.startActivity(myIntent);
                }
                else if(incoming_text[0].equals("WEEKLY") || incoming_text[0].equals("WEEKEND") || incoming_text[0].equals("MONTHLY")){
                    Intent myIntent = new Intent(From_Date.this, To_Date.class);
                    myIntent.putExtra("DETAILS", details_array);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    From_Date.this.startActivity(myIntent);
                }
                else {
                    Intent myIntent = new Intent(From_Date.this, From_Time.class);
                    myIntent.putExtra("DETAILS", details_array);
                    myIntent.putExtra("FLOW_LEVEL_DETAILS", incoming_text);
                    From_Date.this.startActivity(myIntent);
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

                date_selected = (TextView) findViewById(R.id.date_selected);
                date_selected.setText(String.valueOf(" " + day) + " " + month_word + " " + String.valueOf(year));
                details_array[0] = String.valueOf(date_selected.getText());

                /*if(incoming_text[0].equals("ENTERTAINMENT")){
                    date_selected=(TextView)findViewById(R.id.date_selected);
                    date_selected.setText(" "+month_word+" "+String.valueOf(year));
                    details_array[0] = String.valueOf(date_selected.getText());
                }
                else {
                    date_selected = (TextView) findViewById(R.id.date_selected);
                    date_selected.setText(String.valueOf(" " + day) + " " + month_word + " " + String.valueOf(year));
                    details_array[0] = String.valueOf(date_selected.getText());
                }*/
                //Log.d("Note: ",details_array[0]);
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
            return "Sep";
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
