package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.viewmodels.UserViewModel;

public class GameOverFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";
    private User userParam;
    private UserViewModel viewModel;

    @BindView(R.id.tvWon)
    TextView tvWon;

    @BindView(R.id.tvLost)
    TextView tvLost;

    public GameOverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userParam = getArguments().getParcelable(ARG_PARAM1);
        }

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_game_over;
    }

    @Override
    public int getTitle() {
        return R.string.game_over;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getUserById(String.valueOf(userParam.getId())).observe(this, user -> {
            if (user != null) {
                tvWon.setText(String.valueOf(user.getWins()));
                tvLost.setText(String.valueOf(user.getLosses()));
            }
        });
    }

    @OnClick(R.id.returnToMenu)
    public void returnToMenu(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_gameOverFragment_to_playerMenuFragment, args);
    }
}
