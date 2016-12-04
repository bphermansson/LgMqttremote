package nu.paheco.patrik.LgTvRemote;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by patrik on 11/10/16.
 */

public class prefFragment extends PreferenceFragment {


    @Override
    public void onResume() {
        super.onResume();
        //addPreferencesFromResource(R.xml.preferences);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}