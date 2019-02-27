package edu.gatech.seclass.crypto6300.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.ui.LoginFragment;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements
        AdminMenuFragment.OnFragmentInteractionListener,
        AddCryptogramFragment.OnFragmentInteractionListener,
        AddPlayerFragment.OnFragmentInteractionListener,
        ChooseCryptogramFragment.OnFragmentInteractionListener,
        GameOverFragment.OnFragmentInteractionListener,
        GameWonFragment.OnFragmentInteractionListener,
        PlayerMenuFragment.OnFragmentInteractionListener,
        PlayerStatisticsFragment.OnFragmentInteractionListener,
        SolveCryptogramFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
