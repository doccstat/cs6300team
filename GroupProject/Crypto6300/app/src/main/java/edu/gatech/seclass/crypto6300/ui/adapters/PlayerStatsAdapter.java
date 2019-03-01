package edu.gatech.seclass.crypto6300.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.seclass.crypto6300.R;
import edu.gatech.seclass.crypto6300.data.entities.User;

public class PlayerStatsAdapter extends RecyclerView.Adapter<PlayerStatsAdapter.ViewHolder> {

    private List<User> playerList = new ArrayList<>();
    private boolean isAdminViewingContent = false;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.stat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(playerList.get(position));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void setPlayerList(boolean isAdminViewingContent, List<User> playerList) {
        if (playerList == null) return;
        this.isAdminViewingContent = isAdminViewingContent;
        this.playerList.clear();
        this.playerList.addAll(playerList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView firstname;
        TextView lastname;
        TextView wins;
        TextView losses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.row_username);
            firstname = itemView.findViewById(R.id.row_firstname);
            lastname = itemView.findViewById(R.id.row_lastname);
            wins = itemView.findViewById(R.id.row_wins);
            losses = itemView.findViewById(R.id.row_losses);
        }

        void bind(User user) {
            if (isAdminViewingContent) {
                firstname.setVisibility(View.VISIBLE);
                lastname.setVisibility(View.VISIBLE);

                firstname.setText(user.getFirstName());
                lastname.setText(user.getLastName());
            } else {
                firstname.setVisibility(View.INVISIBLE);
                lastname.setVisibility(View.INVISIBLE);
            }

            username.setText(user.getUsername());
            wins.setText(String.valueOf(user.getWins()));
            losses.setText(String.valueOf(user.getLosses()));
        }
    }
}
