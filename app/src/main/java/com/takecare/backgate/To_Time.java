package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

public class To_Time extends Activity {

    TimePicker timePicker;
    TextView time_selected;
    ImageView imageButton_from;
    TextView to_time;
    TextView to;
    String[] details_array = new String[7];
    String min_str;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_time);

        to_time=(TextView)findViewById(R.id.start_time_q);
        to_time.setText("On what time would you want the service to end?");

        details_array = getIntent().getStringArrayExtra("DETAILS");

        to=(TextView)findViewById(R.id.from_text);
        to.setText("To: ");

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        timePicker=(TimePicker)findViewById(R.id.timePicker1);

        time_selected=(TextView)findViewById(R.id.time_selected);
        String hour = Integer.toString(((timePicker.getCurrentHour().intValue())>12)?((timePicker.getCurrentHour().intValue()) - 12) : (timePicker.getCurrentHour().intValue()));
        String am_pm = get_am_pm(timePicker.getCurrentHour());
        int minute = timePicker.getCurrentMinute();
        if(hour.length()==1){
            hour="0"+hour;
        }
        if(minute<10){
            min_str="0"+String.valueOf(minute);
        }else{
            min_str=String.valueOf(minute);
        }
        time_selected.setText(" "+String.valueOf(hour)+" : "+min_str+" "+am_pm);
        details_array[3] = String.valueOf(time_selected.getText());

        imageButton_from=(ImageView)findViewById(R.id.imageButton_from);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String hour = Integer.toString(((view.getCurrentHour().intValue()) > 12) ? ((view.getCurrentHour().intValue()) - 12) : (view.getCurrentHour().intValue()));
                minute = timePicker.getCurrentMinute();
                if(hour.length()==1){
                    hour="0"+hour;
                }
                if(minute<10){
                    min_str="0"+String.valueOf(minute);
                }else{
                    min_str=String.valueOf(minute);
                }
                String am_pm = get_am_pm(timePicker.getCurrentHour());
                time_selected.setText(" "+String.valueOf(hour)+" : "+min_str+" "+am_pm);
                details_array[3] = String.valueOf(time_selected.getText());
            }
        });

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(To_Time.this, Details.class);
                myIntent.putExtra("DETAILS", details_array);
                To_Time.this.startActivity(myIntent);
            }
        });
    }

    public String get_am_pm(int hour){
        if(hour>11)
            return "P.M.";
        else
            return "A.M.";
    }
}
