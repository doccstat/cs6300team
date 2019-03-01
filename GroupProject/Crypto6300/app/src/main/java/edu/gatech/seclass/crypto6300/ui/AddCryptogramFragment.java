package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import edu.gatech.seclass.crypto6300.R;

public class AddCryptogramFragment extends BaseFragment {

    public AddCryptogramFragment() {
        // Required empty public constructor
    }

    public static AddCryptogramFragment newInstance(String param1, String param2) {
        AddCryptogramFragment fragment = new AddCryptogramFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_cryptogram;
    }

    @Override
    public int getTitle() {
        return R.string.add_cryptogram;
    }
}
