package com.takecare.backgate;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Verification extends Activity {

    TextView name_value;
    TextView email_value;
    TextView phone_value;
    TextView from_date;
    TextView from_time;
    TextView to_date;
    TextView to_time;
    TextView package_sel_val;
    String[] details_array = new String[7];
    String[] incoming_text=new String[1];
    String msg = "Android: ";
    private static final String username = "takecareapp@yahoo.com";
    private static final String password = "T3st@pp";
    private static final String email = "tanmayatripathi@gmail.com";
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
        incoming_text = getIntent().getStringArrayExtra("FLOW_LEVEL_DETAILS");
        name_value = (TextView)findViewById(R.id.name_value_1);
        email_value = (TextView)findViewById(R.id.email_value_1);
        phone_value = (TextView)findViewById(R.id.phone_value_1);
        from_date = (TextView)findViewById(R.id.from_date_value);
        from_time = (TextView)findViewById(R.id.from_time_value);
        to_date = (TextView)findViewById(R.id.to_date_value);
        to_time = (TextView)findViewById(R.id.to_time_value);
        package_sel_val=(TextView)findViewById(R.id.package_sel_val);
        name_value.setText(details_array[4]);
        email_value.setText(details_array[5]);
        phone_value.setText(details_array[6]);
        from_date.setText(details_array[0]);
        if(incoming_text[0].equals("ENTERTAINMENT")){
            from_time.setText("NA");
            to_date.setText("NA");
            to_time.setText("NA");
            TextView from_text=(TextView)findViewById(R.id.from_text);
            from_text.setText("Service date:");
            package_sel_val.setText("Entertainment Package");
        }
        else if(incoming_text[0].equals("HOUR")){
            from_time.setText(details_array[1]);
            to_date.setText(details_array[2]);
            to_time.setText(details_array[3]);
            package_sel_val.setText("Hourly Package");
        }
        else if(incoming_text[0].equals("WEEKLY")){
            from_time.setText("NA");
            to_date.setText(details_array[2]);
            to_time.setText("NA");
            package_sel_val.setText("Weekly Package");
        }
        else if(incoming_text[0].equals("WEEKEND")){
            from_time.setText("NA");
            to_date.setText(details_array[2]);
            to_time.setText("NA");
            package_sel_val.setText("Weekend Package");
        }
        else if(incoming_text[0].equals("MONTHLY")){
            from_time.setText("NA");
            to_date.setText(details_array[2]);
            to_time.setText("NA");
            package_sel_val.setText("Monthly Package");
        }
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
        MimeMultipart multipart = new MimeMultipart("related");
        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<!-- Inliner Build Version 4380b7741bb759d6cb997545f3add21ad48f010b -->\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\" \"http://www.w3.org/TR/REC-html40/loose.dtd\">\n" +
                "<html>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tr><td bgcolor=\"#00bdc3\" width='100%'><h1 style=\"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>\n" +
                "</td></tr>\n" +
                "</table>\n" +
                "<p>Hi Team</p>\n" +
                "<p>The following individual would like your help-</p>\n" +
                "<hr>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<td>Name: </td> \n" +
                "                    <td>"+details_array[4]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>e-mail: </td> \n" +
                "                    <td>"+details_array[5]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>Phone no: </td> \n" +
                "                    <td>"+details_array[6]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>From Date: </td> \n" +
                "                    <td>"+details_array[0]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>From Time: </td> \n" +
                "                    <td>"+details_array[1]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>To Date: </td> \n" +
                "                    <td>"+details_array[2]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>To Time: </td> \n" +
                "                    <td>"+details_array[3]+"</td>  \n" +
                "                  </tr>\n" +
                "<tr>\n" +
                "<td>Package Requested: </td> \n" +
                "                    <td>"+incoming_text[0]+"</td>  \n" +
                "                  </tr>\n" +
                "</table>\n" +
                "<hr>\n" +
                "<h5 grey>This is a system generated email, do not reply to this email id.</h5>\n" +
                "                <p>Thanks and Regards</p>\n" +
                "<table>\n" +
                "<tr><td bgcolor=\"#000000\" width='100%'> \n" +
                "                <header style=background: black;><img src=\"http://www.wetakecare.co.in/images/logo.png\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</header>\n" +
                "</td></tr>\n" +
                "</table>\n" +
                "</body></html>";
        //String htmlText = "<H1>Hello</H1><img src=\"http://www.wetakecare.co.in/images/logo.png\">";
        messageBodyPart.setContent(htmlText, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPart);
        //MimeMultipart multipart = new MimeMultipart("related");
        //BodyPart messageBodyPart = new MimeBodyPart();
        //String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
        //messageBodyPart.setContent(htmlText, "text/html");
        // add it
        //multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        //message.setText(messagebody);
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
            progressDialog = ProgressDialog.show(Verification.this,"","Taking Care", true, false);
            //progressDialog=new ProgressDialog(getBaseContext());
            //progressDialog = new ProgressDialog(BackupRestoreActivityContext);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setIcon(R.drawable.logo);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.setIndeterminateDrawable(Verification.this.getDrawable(R.drawable.pb_animation));
            progressDialog.show();
        }

        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent myIntent = new Intent(Verification.this, Thank_You.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Verification.this.startActivity(myIntent);
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
                boolean network_available=isNetworkAvailable();
                if(network_available){
                    String subject = details_array[4]+" has requested TakeCare";
                    //String messagebody = details_array[0] + " " + details_array[1] + " to " + details_array[2] + " " +details_array[3] + " \nContact " + details_array[4] + " " + details_array[5] + " " + details_array[6];
                    //String messagebody = "<html><body><table><tr><td><br/>" +details_array[0] +"</td></tr><br/><br/>"+"Get <b> Best Score </b> in your Android Phone.<br/>"+"<a href=\\\"" + details_array[5] + "\\\">" + details_array[6]+ "</a>";
                    String messagebody="NA";
                    sendMail(email,subject,messagebody);
                }
                else{
                    Toast.makeText(Verification.this, "Network is currently unavailable.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
