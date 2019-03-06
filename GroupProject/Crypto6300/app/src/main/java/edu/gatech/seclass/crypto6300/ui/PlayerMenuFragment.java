package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class PlayerMenuFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";
    private User userParam;

    @BindView(R.id.welcome)
    TextView welcome;

    public PlayerMenuFragment() {
        // Required empty public constructor
    }

    public static PlayerMenuFragment newInstance(User user) {
        PlayerMenuFragment fragment = new PlayerMenuFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userParam = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_player_menu;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        welcome.setText(String.format("%s %s!", getString(R.string.welcome_message), userParam.getFirstName()));
    }

    @Override
    public int getTitle() {
        return R.string.player_menu;
    }

    @OnClick(R.id.chooseCryptogramItem)
    public void chooseCryptogramItem(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_playerMenuFragment_to_chooseCryptogramFragment, args);
    }

    @OnClick(R.id.viewPlayerStatisticsItem)
    public void viewPlayerStatisticsItem(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_playerMenuFragment_to_playerStatisticsFragment, args);
    }

    @OnClick(R.id.logoutPlayerItem)
    public void logoutPlayer(View v) {
        Navigation.findNavController(v).navigate(R.id.action_playerMenuFragment_to_loginFragment);
    }
}
