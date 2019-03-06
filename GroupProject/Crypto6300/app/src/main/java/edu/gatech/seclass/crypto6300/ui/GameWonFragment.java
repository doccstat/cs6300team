package edu.gatech.seclass.crypto6300.ui;

import android.view.View;

import androidx.navigation.Navigation;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;

public class GameWonFragment extends BaseFragment {

    public GameWonFragment() {
        // Required empty public constructor
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
        Navigation.findNavController(v).navigate(R.id.action_gameWonFragment_to_playerMenuFragment);
    }
}
