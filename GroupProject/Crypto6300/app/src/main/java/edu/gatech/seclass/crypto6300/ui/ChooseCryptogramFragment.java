package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class ChooseCryptogramFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    private User userParam;

    public ChooseCryptogramFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChooseCryptogramFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseCryptogramFragment newInstance(User user) {
        ChooseCryptogramFragment fragment = new ChooseCryptogramFragment();
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
        return R.layout.fragment_choose_cryptogram;
    }

    @Override
    public int getTitle() {
        return R.string.choose_cryptogram;
    }
}
