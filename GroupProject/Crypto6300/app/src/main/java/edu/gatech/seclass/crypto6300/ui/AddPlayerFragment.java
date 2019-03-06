package edu.gatech.seclass.crypto6300.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.viewmodels.AddPlayerFragmentViewModel;
import edu.gatech.seclass.crypto6300.utils.PlayerUtils;

public class AddPlayerFragment extends BaseFragment {

    @BindView(R.id.txtPlayerFirstName)
    EditText etFirstName;

    @BindView(R.id.txtPlayerLastName)
    EditText etLastName;

    @BindView(R.id.txtPlayerUsername)
    EditText etUsername;

    @BindView(R.id.txtPlayerPassword)
    EditText etPassword;

    @BindView(R.id.categorySpinner)
    Spinner categorySpinner;

    private AddPlayerFragmentViewModel viewModel;
    private AlertDialog dialog;
    private boolean isAdded = false;

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
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String categoryString = categorySpinner.getSelectedItem().toString();

        viewModel.getUserForUsername(username).observe(this, user -> {
            if (isAdded) return;

            if (dialog != null && dialog.isShowing()) dialog.dismiss();

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
                    isAdded = true;

                    dialog = new AlertDialog.Builder(getContext())
                            .setTitle(R.string.success)
                            .setMessage(String.format(getString(R.string.user_was_added), username))
                            .setCancelable(false)
                            .setPositiveButton(R.string.ok, (dialog, which) -> Navigation.findNavController(v).popBackStack())
                            .show();
                }
            } else {
                dialog = new AlertDialog.Builder(getContext())
                        .setTitle(R.string.error)
                        .setMessage(R.string.username_exists)
                        .setCancelable(false)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });
    }

    private boolean isValidInput() {
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (firstname.isEmpty()) {
            etFirstName.setError(getString(R.string.error_firstname));
            return false;
        } else if (!PlayerUtils.isValidFirstnameLength(firstname)) {
            etFirstName.setError(getString(R.string.error_firstname_length));
            return false;
        } else if (!PlayerUtils.isLettersOnly(firstname)) {
            etFirstName.setError(getString(R.string.error_letters_only));
            return false;
        } else {
            etFirstName.setError(null);
        }

        if (lastname.isEmpty()) {
            etLastName.setError(getString(R.string.error_lastname));
            return false;
        } else if (!PlayerUtils.isValidLastnameLength(lastname)) {
            etLastName.setError(getString(R.string.error_lastname_length));
            return false;
        } else if (!PlayerUtils.isLettersOnly(firstname)) {
            etLastName.setError(getString(R.string.error_letters_only));
            return false;
        } else {
            etLastName.setError(null);
        }

        if (username.isEmpty()) {
            etUsername.setError(getString(R.string.error_username));
            return false;
        } else if (!PlayerUtils.isValidUsernameLength(username)) {
            etUsername.setError(getString(R.string.error_username_length));
            return false;
        } else if (!PlayerUtils.isValidLettersNumAndUnderscore(username)) {
            etUsername.setError(getString(R.string.error_username_chars));
            return false;
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.error_password));
            return false;
        } else if (!PlayerUtils.isValidPasswordLength(password)) {
            etPassword.setError(getString(R.string.error_password_length));
            return false;
        } else if (!PlayerUtils.isValidLettersNumAndUnderscore(username)) {
            etPassword.setError(getString(R.string.error_password_chars));
            return false;
        } else {
            etPassword.setError(null);
        }

        return true;
    }
}
