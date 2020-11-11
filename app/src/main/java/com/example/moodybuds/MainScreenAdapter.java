package com.example.moodybuds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView personName;
        public SeekBar moodBar;
        public ImageView profile;
        public TextView previewText;

        public ViewHolder(View itemView) {
            super(itemView);

            personName = (TextView) itemView.findViewById(R.id.personName);
            moodBar = (SeekBar) itemView.findViewById(R.id.moodBar);
            profile = (ImageView) itemView.findViewById(R.id.profile);
            previewText = (TextView) itemView.findViewById(R.id.previewText);
        }
    }

    private List<ProfileCard> profileCards;

    public MainScreenAdapter(List<ProfileCard> cards){
        profileCards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // This needs to be fixed
        View contactView = inflater.inflate(R.layout.activity_profile_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Needs to be fixed
        //MainScreen profile = profileCards.get(position);
        //TextView textView = holder.personName;
        //textView.setText(profile.getName());

    }

    @Override
    public int getItemCount() {
        return profileCards.size();
    }



}
