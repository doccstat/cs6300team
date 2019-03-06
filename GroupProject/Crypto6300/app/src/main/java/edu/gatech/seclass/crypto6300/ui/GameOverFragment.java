package edu.gatech.seclass.crypto6300.ui;

import android.view.View;

import androidx.navigation.Navigation;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;

public class GameOverFragment extends BaseFragment {

    public GameOverFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_game_over;
    }

    @Override
    public int getTitle() {
        return R.string.game_over;
    }

    @OnClick(R.id.returnToMenu)
    public void returnToMenu(View v) {
        Navigation.findNavController(v).navigate(R.id.action_gameOverFragment_to_playerMenuFragment);
    }
}
