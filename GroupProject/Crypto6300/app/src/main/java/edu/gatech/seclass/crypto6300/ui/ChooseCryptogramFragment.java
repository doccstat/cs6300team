package edu.gatech.seclass.crypto6300.ui;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.viewmodels.ChooseCryptogramFragmentViewModel;
import edu.gatech.seclass.crypto6300.ui.adapters.ChooseCryptogramAdapter;

public class ChooseCryptogramFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "user";

    private User userParam;

    @BindView(R.id.choose_cryptogram_rv)
    RecyclerView recyclerView;

    private ChooseCryptogramFragmentViewModel viewModel;
    private ChooseCryptogramAdapter adapter;

    public ChooseCryptogramFragment() {
        // Required empty public constructor
    }

    public static ChooseCryptogramFragment newInstance(User user) {
        ChooseCryptogramFragment fragment = new ChooseCryptogramFragment();
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
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        viewModel = ViewModelProviders.of(this).get(ChooseCryptogramFragmentViewModel.class);
        viewModel.getList(String.valueOf(userParam.getId())).observe(this, chooseCryptogramList -> {

            List<ChooseCryptogram> filteredList = new ArrayList<>();
            for (ChooseCryptogram c : chooseCryptogramList) {
                if (!c.isCompleted()) {
                    filteredList.add(c);
                }
            }

            adapter.setCryptogramList(filteredList);
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChooseCryptogramAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_choose_cryptogram;
    }

    @Override
    public int getTitle() {
        return R.string.choose_cryptogram;
    }
}
