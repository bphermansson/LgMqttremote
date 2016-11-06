package nu.paheco.patrik.lgmqttremote;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.R.attr.delay;

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
                code="2,20DF10EF,32,1";
                //mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);
                mqttsend(code);
                break;
            case R.id.btnInfo:
                // 2 is the manu id from the Esp code
                code="2,20DF55AA,32,1";
                mqttsend(code);
                break;
            case R.id.btn13:
                // 2 is the manu id from the Esp code
                // Send '1'
                code="2,20DF8877,32,1";
                mqttsend(code);
                // Send '3'
                code="2,20DFC837,32,1";
                mqttsend(code);
                break;
            case R.id.btn4:
                code="2,20DF28D7,32,1";
                mqttsend(code);
                break;
            case R.id.btnInput:
                code="2,20DFD02F,32,1";
                mqttsend(code);
                break;
            case R.id.Pup:
                code="2,20DF00FF,32,1";
                mqttsend(code);
                break;
            case R.id.Pdwn:
                code="2,20DF807F,32,1";
                mqttsend(code);
                break;
            case R.id.volup:
                code="2,5EA158A7,32,1";
                mqttsend(code);
                break;
            case R.id.voldwn:
                code="2,5EA1D827,32,1";
                mqttsend(code);
                break;
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
        }
        Context context = getApplicationContext();
        Toast tea = Toast.makeText(context, "Mqtt message sent", Toast.LENGTH_SHORT);
        tea.show();
    }
    public void mqttsend(String code) {
        mqttPublish.main(mContext, mqttip, mqttuser, mqttpass, topic, code);

    }
}
