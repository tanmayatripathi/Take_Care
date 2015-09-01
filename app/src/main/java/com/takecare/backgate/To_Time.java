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
    String[] details = new String[7];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_time);

        to_time=(TextView)findViewById(R.id.start_time_q);
        to_time.setText("On what time would you want the service to end?");

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
        time_selected.setText(" "+String.valueOf(hour)+" : "+String.valueOf(minute)+" "+am_pm);

        imageButton_from=(ImageView)findViewById(R.id.imageButton_from);

        details = getIntent().getStringArrayExtra("DETAILS");

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String hour = Integer.toString(((view.getCurrentHour().intValue()) > 12) ? ((view.getCurrentHour().intValue()) - 12) : (view.getCurrentHour().intValue()));
                minute = timePicker.getCurrentMinute();
                String am_pm = get_am_pm(timePicker.getCurrentHour());
                time_selected.setText(" "+String.valueOf(hour)+" : "+String.valueOf(minute)+" "+am_pm);
                details[3] = String.valueOf(time_selected.getText());
            }
        });

        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(To_Time.this, Details.class);
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
