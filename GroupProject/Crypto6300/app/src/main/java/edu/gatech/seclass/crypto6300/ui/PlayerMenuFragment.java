package edu.gatech.seclass.crypto6300.ui;

import android.view.View;

import androidx.navigation.Navigation;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;

public class PlayerMenuFragment extends BaseFragment {

    public PlayerMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_player_menu;
    }

    @Override
    public int getTitle() {
        return R.string.player_menu;
    }

    @OnClick(R.id.chooseCryptogramItem)
    public void chooseCryptogramItem(View v) {
        Navigation.findNavController(v).navigate(R.id.action_playerMenuFragment_to_chooseCryptogramFragment);
    }

    @OnClick(R.id.viewPlayerStatisticsItem)
    public void viewPlayerStatisticsItem(View v) {
        Navigation.findNavController(v).navigate(R.id.action_playerMenuFragment_to_playerStatisticsFragment);
    }
}
