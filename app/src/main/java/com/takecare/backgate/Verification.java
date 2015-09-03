package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private static final String username = "takecareapp@yahoo.com";
    private static final String password = "T3st@pp";
    private static final String email = "silver.ballers@gmail.com";
    ImageView imageButton_from;

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
        imageButton_from = (ImageView)findViewById(R.id.imageButton_from);
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

    private void sendMail(String email, String subject, String messagebody){
        Session session = createSessionObject();

        try{
            Message message = createMessage(email, subject, messagebody, session);
            new SendMailTask().execute(message);
        }catch (AddressException e){
            e.printStackTrace();
        }catch (MessagingException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }

    private Message createMessage(String email, String subject, String messagebody, Session session) throws MessagingException, UnsupportedEncodingException{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("takecareapp@yahoo.com", "TakeCare"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messagebody);
        return message;

    }

    private Session createSessionObject(){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        properties.put("mail.smtp.port","587");

        return Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }

        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void>{
        private ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Verification.this,"Please Wait","Sending Mail", true, false);
        }

        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }


        @Override
        protected Void doInBackground(Message... messages) {
            try{
                Transport.send(messages[0]);
            }catch (MessagingException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void addListenerOnButton() {
        imageButton_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "Take Care App Demo";
                String messagebody = details_array[0] + " " + details_array[1] + " to " + details_array[2] + " " +details_array[3] + " \nContact " + details_array[4] + " " + details_array[5] + " " + details_array[6];
                sendMail(email,subject,messagebody);
            }
        });
    }
}
