package edu.gatech.seclass.crypto6300.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.viewmodels.AddPlayerFragmentViewModel;

public class AddPlayerFragment extends BaseFragment {

    @BindView(R.id.txtPlayerFirstName)
    TextView tvFirstName;

    @BindView(R.id.txtPlayerLastName)
    TextView tvLastName;

    @BindView(R.id.txtPlayerUsername)
    TextView tvUsername;

    @BindView(R.id.txtPlayerPassword)
    TextView tvPassword;

    @BindView(R.id.categorySpinner)
    Spinner categorySpinner;

    private AddPlayerFragmentViewModel viewModel;

    public AddPlayerFragment() {
        // Required empty public constructor
    }

    public static AddPlayerFragment newInstance(String param1, String param2) {
        AddPlayerFragment fragment = new AddPlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_player;
    }

    @Override
    public int getTitle() {
        return R.string.add_player;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(AddPlayerFragmentViewModel.class);
    }

    @OnClick(R.id.addPlayerButton)
    public void addPlayerButton(View v) {
        String firstname = tvFirstName.getText().toString();
        String lastname = tvLastName.getText().toString();
        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();
        String categoryString = categorySpinner.getSelectedItem().toString();

        viewModel.getUserForUsername(username).observe(this, user -> {
            if (user == null) {
                int category = 0;
                switch (categoryString) {
                    case "Easy":
                        category = 1;
                        break;
                    case "Normal":
                        category = 2;
                        break;
                    case "Hard":
                        category = 3;
                        break;
                }

                if (isValidInput()) {
                    viewModel.addPlayer(firstname, lastname, username, password, category);

                    new AlertDialog.Builder(getContext())
                            .setTitle("Success!")
                            .setMessage("User '" + username + "' was added.")
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> Navigation.findNavController(v).popBackStack()).show();
                }
            } else {
                new AlertDialog.Builder(getContext())
                        .setTitle("Error!")
                        .setMessage("A user with that username already exists.")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        }).show();
            }
        });

    }

    private boolean isValidInput() {
        // TODO
        return false;
    }
}
