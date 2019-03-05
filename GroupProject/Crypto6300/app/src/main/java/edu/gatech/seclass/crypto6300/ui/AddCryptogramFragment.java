package edu.gatech.seclass.crypto6300.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
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

    @BindView(R.id.easy_attempts_txt)
    EditText etEasyAttempts;
    @BindView(R.id.normal_attempts_txt)
    EditText etNormalAttempts;
    @BindView(R.id.hard_attempts_txt)
    EditText etHardAttempts;

    private AddCryptogramFragmentViewModel viewModel;
    private User userParam;
    private boolean isCryptogramAdded = false;

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
        String easy_attempts= etEasyAttempts.getText().toString();
        String normal_attempts= etNormalAttempts.getText().toString();
        String hard_attempts= etHardAttempts.getText().toString();
        Integer easy=0;
        Integer normal=0;
        Integer hard=0;

        if (isNumber(easy_attempts)){
             easy =  Integer.parseInt(easy_attempts);
             if (easy<=0){ etEasyAttempts.setError(getString(R.string.error_negative_attempts));}
             else {etEasyAttempts.setError(null);}
        }
        else{
            etEasyAttempts.setError(getString(R.string.error_number_required));
            etEasyAttempts.requestFocus();
        }

        if (isNumber(normal_attempts)){
             normal =  Integer.parseInt(normal_attempts);
            if (normal<=0){ etNormalAttempts.setError(getString(R.string.error_negative_attempts));}
            else if (normal>easy){
                etNormalAttempts.setError(null);
                new AlertDialog.Builder(getContext())
                        .setTitle("Warning!")
                        .setMessage("Are you sure? Normal category has more attempts than Easy category!")
                        .setCancelable(true)
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        }).show();
            }
        }
        else{
            etNormalAttempts.setError(getString(R.string.error_number_required));
            etHardAttempts.requestFocus();
        }

        if (isNumber(hard_attempts)){
             hard =  Integer.parseInt(hard_attempts);
             if (hard<=0){etHardAttempts.setError(getString(R.string.error_negative_attempts));}
             else if (hard> normal || hard>easy){
                 new AlertDialog.Builder(getContext())
                         .setTitle("Warning!")
                         .setMessage("Are you sure? Hard category doesn't have the lowest attempts!")
                         .setCancelable(true)
                         .setPositiveButton("OK", (dialog, which) -> {
                             dialog.dismiss();
                         }).show();
             }
        }
        else{
            etHardAttempts.setError(getString(R.string.error_number_required));
            etHardAttempts.requestFocus();
        }


        // TODO: fix this with proper inputs
        Attempts attempts = new Attempts(easy, normal, hard);

        viewModel.getCryptogramForName(name).observe(this, cryptogram -> {
            if (isCryptogramAdded) return;

            if (cryptogram == null) {

                if (isValidInput()) {
                    viewModel.addCryptogram(name, solution, 2, attempts);
                    isCryptogramAdded = true;

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
        String name=etCryptogramName.getText().toString();
        String solution= etCryptogramSolution.getText().toString();
        if (name.isEmpty()) {
            etCryptogramName.setError(getString(R.string.error_cryptogram_name));
            etCryptogramName.requestFocus();
            return false;
        } else {
            etCryptogramName.setError(null);
        }

        if (solution.isEmpty()) {
            etCryptogramSolution.setError(getString(R.string.error_cryptogram_solution));
            etCryptogramSolution.requestFocus();
            return false;
        } else {
            etCryptogramSolution.setError(null);
        }
        return  true;
    }
    private boolean isNumber(String number){
        if(!TextUtils.isEmpty(number)){
            return TextUtils.isDigitsOnly(number);
        }else{
            return false;
        }
    }

}
