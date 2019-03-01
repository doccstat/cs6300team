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
import edu.gatech.seclass.crypto6300.data.entities.Attempts;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.viewmodels.AddCryptogramFragmentViewModel;

public class AddCryptogramFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    @BindView(R.id.txtCryptogramName)
    EditText etCryptogramName;

    @BindView(R.id.txtCryptogramSolution)
    EditText etCryptogramSolution;

    @BindView(R.id.categorySpinner)
    Spinner categorySpinner;

    private AddCryptogramFragmentViewModel viewModel;
    private User userParam;

    public AddCryptogramFragment() {
        // Required empty public constructor
    }

    public static AddCryptogramFragment newInstance(String param1, String param2) {
        AddCryptogramFragment fragment = new AddCryptogramFragment();
        Bundle args = new Bundle();
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
        return R.layout.fragment_add_cryptogram;
    }

    @Override
    public int getTitle() {
        return R.string.add_cryptogram;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(AddCryptogramFragmentViewModel.class);
    }

    @OnClick(R.id.addCryptogramButton)
    public void addCryptogram(View v) {

        String name = etCryptogramName.getText().toString();
        String solution = etCryptogramSolution.getText().toString();

        // TODO: change this property
        String category = categorySpinner.getSelectedItem().toString();

        // TODO: fix this with proper inputs
        Attempts attempts = new Attempts(3, 3, 3);

        viewModel.getCryptogramForName(name).observe(this, cryptogram -> {
            if (cryptogram == null) {

                if (isValidInput()) {
                    viewModel.addCryptogram(name, solution, 2, attempts);

                    new AlertDialog.Builder(getContext())
                            .setTitle("Success!")
                            .setMessage("Cryptogram '" + name + "' was added.")
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> Navigation.findNavController(v).popBackStack()).show();
                }
            } else {
                new AlertDialog.Builder(getContext())
                        .setTitle("Error!")
                        .setMessage("A cryptogram with that name already exists.")
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
