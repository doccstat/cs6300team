package edu.gatech.seclass.crypto6300.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class ChooseCryptogramAdapter extends RecyclerView.Adapter<ChooseCryptogramAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClick(ChooseCryptogram cryptogram);
    }

    private User user;
    private ItemClickListener listener;

    public ChooseCryptogramAdapter(@NonNull User user) {
        this.user = user;
    }

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

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
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
                if (listener != null) listener.onItemClick(cryptogram);
            });
        }
    }
}
