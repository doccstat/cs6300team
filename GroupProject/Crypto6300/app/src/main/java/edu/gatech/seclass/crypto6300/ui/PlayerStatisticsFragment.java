package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;
import timber.log.Timber;

public class PlayerStatisticsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    private User userParam;

    public PlayerStatisticsFragment() {
        // Required empty public constructor
    }

    public static PlayerStatisticsFragment newInstance(User user) {
        PlayerStatisticsFragment fragment = new PlayerStatisticsFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Timber.e(userParam.toString());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_player_statistics;
    }

    @Override
    public int getTitle() {
        return R.string.player_statistics;
    }
}
