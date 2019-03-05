package edu.gatech.seclass.crypto6300.ui.adapters;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.seclass.crypto6300.R;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<String> data = new ArrayList<>();
    private static ArrayList<String> result = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.solve_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<String> data) {
        if (data == null) return;
        this.data.clear();
        this.data.addAll(data);

        result.clear();
        for (String ignored : data) {
            result.add("");
        }

        notifyDataSetChanged();
    }

    public String getResultString() {
        return TextUtils.join("", result);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLetter;
        EditText etLetter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLetter = itemView.findViewById(R.id.tvLetter);
            etLetter = itemView.findViewById(R.id.etLetter);

            etLetter.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String answer = s.toString();
                    result.set(getAdapterPosition(), answer);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        void bind(String s) {
            if (s.isEmpty() || !Character.isLetter(s.charAt(0))) {
                etLetter.setVisibility(View.INVISIBLE);
            } else {
                etLetter.setVisibility(View.VISIBLE);
            }

            tvLetter.setText(s);
        }
    }
}
