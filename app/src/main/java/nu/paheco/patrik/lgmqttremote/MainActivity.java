package nu.paheco.patrik.lgmqttremote;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.delay;

// http://lirc.sourceforge.net/remotes/

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    /*
    String mqttip = "192.168.1.79";
    String mqttuser = "emonpi";
    String mqttpass = "emonpimqtt2016";
    */
    String topic="irsender";
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get stored preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String mqttip = sharedPref.getString("Mqtt server ip", "N/A");
        String mqttuser = sharedPref.getString("Mqtt server username", "N/A");
        String mqttpass = sharedPref.getString("Mqtt server password", "N/A");
        System.out.println(mqttip + " " + mqttuser + " " + mqttpass);

        if (mqttip.equals("N/A") || mqttuser.equals("N/A") || mqttpass.equals("N/A")) {
            // Settings not configured
            TextView infolabel = (TextView) findViewById(R.id.infolabel);
            infolabel.setText(R.string.notset);
        }
    }
    public void btnClick(View view) {
        System.out.println("btnClick");
        switch (view.getId()) {
            case R.id.btnPower:
                //code="2,20DF10EF,32,1";
                System.out.println("Power");
                code = getString(R.string.power);
                mqttsend(code);
                break;
            /*
            case R.id.btnInfo:
                // 2 is the manu id from the Esp code
                //code="2,20DF55AA,32,1";
                code = getString(R.string.info);
                System.out.println(code);
                mqttsend(code);
                break;
            */
            case R.id.btn13:
                // 2 is the manu id from the Esp code
                // Send '1'
                code = getString(R.string.d1);
                System.out.println(code);
                //code="2,20DF8877,32,1";
                mqttsend(code);
                // Send '3'
                //code="2,20DFC837,32,1";
                code = getString(R.string.d3);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.btn4:
                // Button '4'
                //code="2,20DF28D7,32,1";
                code = getString(R.string.d4);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.btnSvt2:
                // Button '2'
                //code="2,20DF48B7,32,1";
                code = getString(R.string.d2);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.btnInput:
                //code="2,20DFD02F,32,1";
                code = getString(R.string.input);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.Pup:
                //code="2,20DF00FF,32,1";
                code = getString(R.string.pup);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.Pdwn:
                //code="2,20DF807F,32,1";
                code = getString(R.string.pdwn);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.volup:
                //code="2,5EA158A7,32,1";
                code = getString(R.string.volup);
                System.out.println(code);
                mqttsend(code);
                break;
            case R.id.voldwn:
                //code="2,5EA1D827,32,1";
                code = getString(R.string.voldwn);
                System.out.println(code);
                mqttsend(code);
                break;

            case R.id.btnYoutube:
                System.out.println("Launch Youtube");
                //com.google.android.youtube
                Intent videoClient = new Intent(Intent.ACTION_VIEW);
                //videoClient.setData("www.youtube.com/watch?v=yqIQvE5R1tU");
                videoClient.setData(Uri.parse("https://www.youtube.com/watch?v=EwSdmxyayx0&amp;feature=youtube_gdata"));//you can try here your own video url

                videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
                startActivity(videoClient);

                break;
            /*
            case R.id.test:
                // Power on
                code="2,20DF10EF,32,1";
                mqttsend(code);
                // Wait 5s
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 5s
                        // Send input change two times
                        code="2,20DFD02F,32,1";
                        mqttsend(code);
                        code="2,20DFD02F,32,1";
                        mqttsend(code);
                    }
                }, 5000);
                break;
            */
            case R.id.btnKeypad:
                startActivity(new Intent(this, keypad.class));
                break;
                // Open keypad activity
        }
    }
    public void mqttsend(String code) {

        // Get stored preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String mqttip = sharedPref.getString("Mqtt server ip", "N/A");
        String mqttuser = sharedPref.getString("Mqtt server username", "N/A");
        String mqttpass = sharedPref.getString("Mqtt server password", "N/A");
        if (mqttip.equals("N/A") || mqttuser.equals("N/A") || mqttpass.equals("N/A")) {
            // Settings not configured
            TextView txtInfo=(TextView) findViewById(R.id.infolabel);
            txtInfo.setText("Settings not configured");
        }
        else {
            mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);
            Context context = getApplicationContext();
            Toast tea = Toast.makeText(context, "Mqtt message sent", Toast.LENGTH_SHORT);
            tea.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Log.d("Menu: ", "Create menu");
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Log.d("id", String.valueOf(id));
        switch (id) {
            case R.id.settings:
                startActivity(new Intent(this, preferences.class));
                break;
            case R.id.help:
                //startActivity(new Intent(this, help.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
