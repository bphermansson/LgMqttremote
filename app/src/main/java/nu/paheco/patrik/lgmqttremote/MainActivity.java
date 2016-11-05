package nu.paheco.patrik.lgmqttremote;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// http://lirc.sourceforge.net/remotes/

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    String mqttip = "192.168.1.79";
    String mqttuser = "emonpi";
    String mqttpass = "emonpimqtt2016";
    String topic="irsender";
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void btnClick(View view) {
        System.out.println("btnClick");
        switch (view.getId()) {
            case R.id.btnPower:
                code="2,20DF10EF,32,0";
                mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);
                break;
            case R.id.btnInfo:
                // 2 is the manu id from the Esp code
                code="2,20DF55AA,32,0";
                mqttsend(code);
                break;
        }
    }
    public void mqttsend(String code) {
        mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);

    }
}
