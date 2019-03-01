package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.entities.UserKt;
import edu.gatech.seclass.crypto6300.data.viewmodels.PlayerStatisticsFragmentViewModel;
import edu.gatech.seclass.crypto6300.ui.adapters.PlayerStatsAdapter;

public class PlayerStatisticsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    private User userParam;

    @BindView(R.id.playerList)
    RecyclerView recyclerView;

    private PlayerStatisticsFragmentViewModel viewModel;
    private PlayerStatsAdapter adapter;

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

        initRecyclerView();

        viewModel = ViewModelProviders.of(this).get(PlayerStatisticsFragmentViewModel.class);
        viewModel.getPlayerStatistics().observe(this, data -> {
            adapter.setPlayerList(UserKt.isAdmin(userParam), data);
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PlayerStatsAdapter();
        adapter.setPlayerList(UserKt.isAdmin(userParam), new ArrayList<>());
        recyclerView.setAdapter(adapter);
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
