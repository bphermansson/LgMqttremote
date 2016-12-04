package nu.paheco.patrik.LgTvRemote;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by patrik on 11/10/16.
 */

public class preferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.d("In preferences.java","Show prefs");
        System.out.println("Preferences.java");

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new prefFragment())
                .commit();

    }
}