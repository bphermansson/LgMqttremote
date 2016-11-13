package nu.paheco.patrik.lgmqttremote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by patrik on 11/12/16.
 */

public class keypad extends Activity {
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keypad);
        String powercode = getString(R.string.power);
        System.out.println(powercode);


    }

    public void keypadbtnClick(View view) {
        System.out.println("keypadbtnClick");
        // Pointer to send method
        MainActivity main=new MainActivity();

        switch (view.getId()) {
            case R.id.btn1:
                code = getString(R.string.d1);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn2:
                code = getString(R.string.d2);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn3:
                code = getString(R.string.d3);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn4:
                code = getString(R.string.d4);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn5:
                code = getString(R.string.d5);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn6:
                code = getString(R.string.d6);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn7:
                code = getString(R.string.d7);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn8:
                code = getString(R.string.d8);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn9:
                code = getString(R.string.d9);
                System.out.println(code);
                main.mqttsend(code);
                break;
            case R.id.btn0:
                code = getString(R.string.d0);
                System.out.println(code);
                main.mqttsend(code);
                break;

        }

        }
}