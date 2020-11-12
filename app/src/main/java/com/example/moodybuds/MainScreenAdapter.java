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

    Context context;
    private List<ProfileCard> profileCards;

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

    public MainScreenAdapter(List<ProfileCard> cards, Context context){
        profileCards = cards;
        this.context = context;

    }

    @NonNull
    @Override
    public MainScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_profile_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainScreenAdapter.ViewHolder holder, int position) {
        ProfileCard profile = profileCards.get(position);
        //Set item views based on views and data model
        TextView name = holder.personName;
        name.setText(profile.getName());
        TextView preview = holder.previewText;
        preview.setText(profile.getGrateful());
        SeekBar mood = holder.moodBar;
//         need to figure out mood bar
        mood.setProgress(profile.getRatingNumber());

        ImageView image = holder.profile;

        if(profile.getPhotoURLString() != "") {
            GlideApp.with(context)
                    .load("https://square.github.io/picasso/static/sample.png")
                    .into(image);
        }

    }

    @Override
    public int getItemCount() {
        return profileCards.size();
    }


}
