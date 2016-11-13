package nu.paheco.patrik.lgmqttremote;

import android.preference.PreferenceFragment;

/**
 * Created by patrik on 11/10/16.
 */

public class prefFragment extends PreferenceFragment {


    @Override
    public void onResume() {
        super.onResume();
        addPreferencesFromResource(R.xml.preferences);

    }
}