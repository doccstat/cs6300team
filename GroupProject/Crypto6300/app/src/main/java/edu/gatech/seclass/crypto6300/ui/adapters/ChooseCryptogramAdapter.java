package edu.gatech.seclass.crypto6300.ui.adapters;

import android.os.Bundle;
import android.util.Pair;
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
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram;

public class ChooseCryptogramAdapter extends RecyclerView.Adapter<ChooseCryptogramAdapter.ViewHolder> {

    private List<ChooseCryptogram> cryptogramList = new ArrayList<>();

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

    public void setCryptogramList(List<ChooseCryptogram> cryptogramList) {
        if (cryptogramList == null) return;
        this.cryptogramList.clear();
        this.cryptogramList.addAll(cryptogramList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        TextView status;
        ChooseCryptogram cryptogram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.cryptogram_name);
            status = itemView.findViewById(R.id.cryptogram_status);
        }

        public void bind(ChooseCryptogram data) {
            this.cryptogram = data;
            label.setText(cryptogram.getName());
            status.setVisibility(data.getAttemptsRemaining() > 0 ? View.VISIBLE: View.INVISIBLE);

            itemView.setOnClickListener(v -> {
                Bundle args = new Bundle();
                Navigation.findNavController(v).navigate(R.id.action_chooseCryptogramFragment_to_solveCryptogramFragment);
            });
        }
    }
}
