package nu.paheco.patrik.LgTvRemote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static nu.paheco.patrik.LgTvRemote.R.id.btnPreset1;
import static nu.paheco.patrik.LgTvRemote.R.id.btnPreset2;
import static nu.paheco.patrik.LgTvRemote.R.id.btnPreset3;

// http://lirc.sourceforge.net/remotes/

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    String topic="irsender";
    String code = "";
    //TextView infolabel = (TextView) findViewById(R.id.infolabel);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get stored preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String mqttip = sharedPref.getString("Mqtt server ip", "N/A");
        String mqttuser = sharedPref.getString("Mqtt server username", "N/A");
        String mqttpass = sharedPref.getString("Mqtt server password", "N/A");
        //System.out.println(mqttip + " " + mqttuser + " " + mqttpass);

        // Set button preset labels
        Button Preset1 = (Button)findViewById(btnPreset1);
        Button Preset2 = (Button)findViewById(btnPreset2);
        Button Preset3 = (Button)findViewById(btnPreset3);
        String preset1text = sharedPref.getString("preset1", "Preset 1");
        String preset2text = sharedPref.getString("preset2", "Preset 2");
        String preset3text = sharedPref.getString("preset3", "Preset 3");
        Preset1.setText(preset1text);
        Preset2.setText(preset2text);
        Preset3.setText(preset3text);

        if (mqttip.equals("N/A") || mqttuser.equals("N/A") || mqttpass.equals("N/A")) {
            // Settings not configured
        }
    }
    public void btnClick(View view) {
        System.out.println("btnClick");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        switch (view.getId()) {
            case R.id.btnPower:
                //code="2,20DF10EF,32,1";
                System.out.println("Power");
                // Get button label
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                code = getString(R.string.power);
                mqttsend(code, buttonText);
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
            case R.id.btnPreset2:
                // 2 is the manu id from the Esp code
                // Send '1'
                code = getString(R.string.d1);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                // Send '3'
                code = getString(R.string.d3);
                System.out.println(code);
                mqttsend(code, buttonText);
                break;
            case R.id.btnPreset3:
                // Button '4'
                //code="2,20DF28D7,32,1";
                code = getString(R.string.d4);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                break;
            case btnPreset1:
                String code = sharedPref.getString("preset1code", "Preset 1");
                sendcode(code);
                // A code can be just one code, or several codes divided by a comma.
                // Split them up and send them
                String[] items = code.split(",");
                for (String codetosend : items)
                {
                    //System.out.println("item = " + codetosend);
                    String wholeCode= "2,20DF" + codetosend + ",32,1";
                    System.out.println(wholeCode);
                    // Get button label
                    b = (Button)view;
                    buttonText = b.getText().toString();
                    mqttsend(wholeCode, buttonText);
                }
                break;
            case R.id.btnInput:
                //code="2,20DFD02F,32,1";
                code = getString(R.string.input);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                break;
            case R.id.Pup:
                //code="2,20DF00FF,32,1";
                code = getString(R.string.pup);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                break;
            case R.id.Pdwn:
                //code="2,20DF807F,32,1";
                code = getString(R.string.pdwn);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                break;
            case R.id.volup:
                //code="2,5EA158A7,32,1";
                code = getString(R.string.volup);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                break;
            case R.id.voldwn:
                //code="2,5EA1D827,32,1";
                code = getString(R.string.voldwn);
                System.out.println(code);
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code, buttonText);
                break;

            case R.id.btnYoutube:
                System.out.println("Launch Youtube");
                //com.google.android.youtube
                //Intent videoClient = new Intent(Intent.ACTION_VIEW);
                //videoClient.setData("www.youtube.com/watch?v=yqIQvE5R1tU");
                //videoClient.setData(Uri.parse("https://www.youtube.com/watch?v=EwSdmxyayx0&amp;feature=youtube_gdata"));//you can try here your own video url

                //videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
                //startActivity(videoClient);
                String id = "";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
                startActivity(intent);
                break;
/*
            case R.id.btnMovie:
                // Power on
                code="2,20DF10EF,32,1";
                // Get button label
                b = (Button)view;
                buttonText = b.getText().toString();
                mqttsend(code,buttonText);
                // Wait 5s
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 5s
                        // Send input change two times
                        String buttonText="Movie";
                        code="2,20DFD02F,32,1";
                        mqttsend(code,buttonText);
                        code="2,20DFD02F,32,1";
                        mqttsend(code,buttonText);
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

    public void sendcode(String code){

        System.out.println("In sendcode, code="+code);

    }

    public void mqttsend(String code, String buttonText) {
        // Get stored preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String mqttip = sharedPref.getString("Mqtt server ip", "N/A");
        String mqttuser = sharedPref.getString("Mqtt server username", "N/A");
        String mqttpass = sharedPref.getString("Mqtt server password", "N/A");
        if (mqttip.equals("N/A") || mqttuser.equals("N/A") || mqttpass.equals("N/A")) {
            // Settings not configured
            Context context = getApplicationContext();
            Toast tea = Toast.makeText(context, R.string.nosettings, Toast.LENGTH_SHORT);
            tea.show();
        }
        else {
            mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);
            Context context = getApplicationContext();
            Toast tea = Toast.makeText(context, R.string.mess_sent, Toast.LENGTH_SHORT);
            tea.show();
            //infolabel.setText(R.string.mess_sent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                startActivity(new Intent(this, help.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
