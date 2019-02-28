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

        @BindView(R.id.row_username)
        TextView username;
        @BindView(R.id.row_firstname)
        TextView firstname;
        @BindView(R.id.row_lastname)
        TextView lastname;
        @BindView(R.id.row_wins)
        TextView wins;
        @BindView(R.id.row_losses)
        TextView losses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
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
            wins.setText(user.getWins());
            losses.setText(user.getLosses());
        }
    }
}
