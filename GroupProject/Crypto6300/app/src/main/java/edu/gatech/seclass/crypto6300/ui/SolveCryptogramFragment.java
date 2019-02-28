package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;

import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class SolveCryptogramFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    private User userParam;

    public SolveCryptogramFragment() {
        // Required empty public constructor
    }

    public static SolveCryptogramFragment newInstance(User user) {
        SolveCryptogramFragment fragment = new SolveCryptogramFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userParam = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_solve_cryptogram;
    }

    @Override
    public int getTitle() {
        return R.string.solve_cryptogram;
    }
}
