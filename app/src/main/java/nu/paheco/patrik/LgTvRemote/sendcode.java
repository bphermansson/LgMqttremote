package nu.paheco.patrik.LgTvRemote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.Toast;

import static nu.paheco.patrik.LgTvRemote.MainActivity.topic;

/**
 * Created by patrik on 12/10/16.
 */


public class sendcode{
    private Context mContext;

    public void vsendcode(String code) {
    System.out.println("In sendcode, code=" + code);
    // A code can be just one code, or several codes divided by a comma.
    // Split them up and send them
    String[] items = code.split(",");
    for (String codetosend : items) {
        //System.out.println("item = " + codetosend);
        String wholeCode = "2,20DF" + codetosend + ",32,1";
        System.out.println("wholecode: " + wholeCode);
        mqttsend(wholeCode, mContext);
    }
}
    public void mqttsend(String code, Context context) {
        System.out.println("sendcode.mqttsend");
        // Get stored preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String mqttip = sharedPref.getString("Mqtt server ip", "N/A");
        String mqttuser = sharedPref.getString("Mqtt server username", "N/A");
        String mqttpass = sharedPref.getString("Mqtt server password", "N/A");
        System.out.println(mqttip);

        if (mqttip.equals("N/A") || mqttuser.equals("N/A") || mqttpass.equals("N/A")) {
            System.out.println("Settings error");

            // Settings not configured
            //Context context = getApplicationContext();
            //Toast tea = Toast.makeText(context, R.string.nosettings, Toast.LENGTH_SHORT);
            //tea.show();
        }
        else {
            System.out.println("All ok, send Mqtt");
            mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);
            //Context context = getApplicationContext();
            //Toast tea = Toast.makeText(context, R.string.mess_sent, Toast.LENGTH_SHORT);
            //tea.show();
            //infolabel.setText(R.string.mess_sent);
        }
        System.out.println("sendcode done");

    }

}

