package com.samhith.arduinocontrol;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HelloWorldActivity extends Activity {

    Button sendButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // load the layout
        setContentView(R.layout.activity_arduino_control);

        sendButton = (Button) findViewById(R.id.sendButton);
    sendButton.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        send();
        Toast.makeText(getApplicationContext(),"Request Sending",Toast.LENGTH_SHORT).show();

    }
});
    }
    // this is the function that gets called when you click the button
    public void send()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }
        HttpClient httpclient = new DefaultHttpClient();
        // put the address to your server and receiver file here
        HttpPost httppost = new HttpPost("http://192.168.1.102/phpscript.php");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            // we wont be receiving the parameter ID in your server, but it is here to show you how you can send more data
            //nameValuePairs.add(new BasicNameValuePair("id", "12345"));
            // message is the parameter we are receiving, it has the value of 1 which is the value that will be sent from your server to your Arduino board
            nameValuePairs.add(new BasicNameValuePair("message", "1"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            Toast.makeText(getApplicationContext(),"Request Sent",Toast.LENGTH_LONG).show();
             // send the parameter to the server
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error 2",Toast.LENGTH_LONG).show();

            // TODO Auto-generated catch block
        }
    }
}
