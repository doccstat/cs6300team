package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.gatech.seclass.crypto6300.ui.LoginFragment;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

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
