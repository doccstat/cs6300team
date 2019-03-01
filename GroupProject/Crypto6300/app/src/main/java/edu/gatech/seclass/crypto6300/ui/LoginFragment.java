package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.UserKt;
import edu.gatech.seclass.crypto6300.data.viewmodels.LoginFragmentViewModel;

public class LoginFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private LoginFragmentViewModel viewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public int getTitle() {
        return R.string.login;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel.class);
    }

    @OnClick(R.id.btnLogin)
    public void clickLogin(View v) {

        // TODO: handle login validation properly
        if (isValidInput()) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            viewModel.login(username, password).observe(this, user -> {
                if (user != null) {

                    Bundle args = new Bundle();
                    args.putParcelable(ARG_PARAM1, user);

                    if (UserKt.isAdmin(user)) {
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_adminMenuFragment, args);
                    } else {
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_playerMenuFragment, args);
                    }
                } else {
                    Toast.makeText(getContext(), R.string.error_wrong_username_or_password, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean isValidInput() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty()) {
            etUsername.setError(getString(R.string.error_username_required));
            etUsername.requestFocus();
            return false;
            // } else if () {
            // TODO: possibly check required length
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.error_password_required));
            etPassword.requestFocus();
            return false;
            // } else if () {
            // TODO: possibly check required length
        } else {
            etPassword.setError(null);
        }

        return true;
    }


}
