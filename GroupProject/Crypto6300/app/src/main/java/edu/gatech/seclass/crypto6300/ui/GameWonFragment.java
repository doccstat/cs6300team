package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class GameWonFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";
    private User userParam;

    public GameWonFragment() {
        // Required empty public constructor
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
        return R.layout.fragment_game_won;
    }

    @Override
    public int getTitle() {
        return R.string.game_won;
    }

    @OnClick(R.id.returnToMenu)
    public void returnToMenu(View v) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userParam);
        Navigation.findNavController(v).navigate(R.id.action_gameWonFragment_to_playerMenuFragment, args);
    }
}
