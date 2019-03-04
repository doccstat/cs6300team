package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.viewmodels.SolveCryptogramFragmentViewModel;
import edu.gatech.seclass.crypto6300.ui.adapters.GameAdapter;
import timber.log.Timber;

public class SolveCryptogramFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";
    private static final String ARG_PARAM2 = "attempt";

    private User userParam;
    private String attemptIdParam;
    private SolveCryptogramFragmentViewModel viewModel;

    @BindView(R.id.encryptedPhrase)
    TextView encryptedPhrase;

    @BindView(R.id.solve_cryptogram_rv)
    RecyclerView recyclerView;

    private GameAdapter adapter;

    public SolveCryptogramFragment() {
        // Required empty public constructor
    }

    public static SolveCryptogramFragment newInstance(User user) {
        SolveCryptogramFragment fragment = new SolveCryptogramFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userParam = getArguments().getParcelable(ARG_PARAM1);
            attemptIdParam = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_solve_cryptogram;
    }

    @Override
    public int getTitle() {
        return R.string.solve_cryptogram;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SolveCryptogramFragmentViewModel.class);

        initRecyclerView();

        viewModel.getEncryptedPhrase(attemptIdParam).observe(this, attempt -> {
            if (attempt == null) return;

            String phrase = attempt.getEncryptedPhrase();
            ArrayList<String> phraseAsList = new ArrayList<>(Arrays.asList((phrase.split(""))));

            // First element is usually a blank character, remove it
            phraseAsList.remove(0);

            encryptedPhrase.setText(phrase);
            adapter.setData(phraseAsList);
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));
        adapter = new GameAdapter();
        recyclerView.setAdapter(adapter);
    }

    private int getSpanCount() {
        return 10;
    }

    @OnClick(R.id.submitSubmission)
    public void submitSubmission(View v) {

        Timber.e("result=[" + adapter.getResultString() + "]");
        viewModel.submitSolution(attemptIdParam, adapter.getResultString()).observe(this, isSolved -> {

            Timber.e("isSolved? " + isSolved);
            if (isSolved) {
                Navigation.findNavController(v).navigate(R.id.action_solveCryptogramFragment_to_gameWonFragment);
            } else {
                // if completed
//                if () {
//                    Navigation.findNavController(v).navigate(R.id.action_solveCryptogramFragment_to_gameOverFragment);
//                } else {
//
//                }
            }
        });
    }
}
