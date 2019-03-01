package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import edu.gatech.seclass.crypto6300.R;

public class AddPlayerFragment extends BaseFragment {

    public AddPlayerFragment() {
        // Required empty public constructor
    }

    public static AddPlayerFragment newInstance(String param1, String param2) {
        AddPlayerFragment fragment = new AddPlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_player;
    }

    @Override
    public int getTitle() {
        return R.string.add_player;
    }
}
