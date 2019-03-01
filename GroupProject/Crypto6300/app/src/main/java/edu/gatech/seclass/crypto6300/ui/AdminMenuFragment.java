package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class AdminMenuFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    private User userParam;

    public AdminMenuFragment() {
        // Required empty public constructor
    }

    public static AdminMenuFragment newInstance(User user) {
        AdminMenuFragment fragment = new AdminMenuFragment();
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
        return R.layout.fragment_admin_menu;
    }

    @Override
    public int getTitle() {
        return R.string.administrator_menu;
    }

    @OnClick(R.id.addPlayerItem)
    public void addPlayerItem(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_addPlayerFragment, args);
    }

    @OnClick(R.id.addCryptogramItem)
    public void addCryptogramItem(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_addCryptogramFragment, args);
    }

    @OnClick(R.id.viewStatisticsItem)
    public void viewPlayerStatisticsItem(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_playerStatisticsFragment, args);
    }

    @OnClick(R.id.logoutAdminItem)
    public void logoutAdmin(View v) {
        Navigation.findNavController(v).navigate(R.id.action_adminMenuFragment_to_loginFragment);
    }
}
