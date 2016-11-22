package nu.paheco.patrik.lgmqttremote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by patrik on 11/22/16.
 */

public class help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        TextView title = (TextView) findViewById(R.id.helpTitle);
        TextView helptext = (TextView) findViewById(R.id.helpText);
        title.setText(R.string.helptitle);
        helptext.setText(R.string.helptext);
    }
}