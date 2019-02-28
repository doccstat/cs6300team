package edu.gatech.seclass.crypto6300.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;

public class ChooseCryptogramAdapter extends RecyclerView.Adapter<ChooseCryptogramAdapter.ViewHolder> {

    private List<Cryptogram> cryptogramList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_crypto_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cryptogramList.get(position));
    }

    @Override
    public int getItemCount() {
        return cryptogramList.size();
    }

    public void setCryptogramList(List<Cryptogram> cryptogramList) {
        if (cryptogramList == null) return;
        this.cryptogramList.clear();
        this.cryptogramList.addAll(cryptogramList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        Cryptogram cryptogram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.cryptogram_name);
        }

        public void bind(Cryptogram cryptogram) {
            this.cryptogram = cryptogram;
            label.setText(cryptogram.getName());

            itemView.setOnClickListener(v -> {

                // TODO: handle navigation if not completed
                Navigation.findNavController(v).navigate(R.id.action_chooseCryptogramFragment_to_solveCryptogramFragment);
            });
        }
    }
}
