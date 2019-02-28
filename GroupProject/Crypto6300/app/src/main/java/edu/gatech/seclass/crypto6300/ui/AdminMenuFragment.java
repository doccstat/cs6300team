package edu.gatech.seclass.crypto6300.ui;

import android.view.View;

import androidx.navigation.Navigation;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;

public class AdminMenuFragment extends BaseFragment {

    public AdminMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_admin_menu;
    }

    @Override
    public int getTitle() {
        return R.string.administrator_menu;
    }

    @OnClick(R.id.addPlayerItem)
    public void addPlayerItem(View v) {
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_addPlayerFragment);
    }

    @OnClick(R.id.addCryptogramItem)
    public void addCryptogramItem(View v) {
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_addCryptogramFragment);
    }

    @OnClick(R.id.viewPlayerStatisticsItem)
    public void viewPlayerStatisticsItem(View v) {
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_playerStatisticsFragment);
    }
}
