package com.example.moodybuds;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.ViewHolder> {

    Context context;
    private List<ProfileCard> profileCards;

    public MainScreenAdapter(List<ProfileCard> cards, Context context){
        profileCards = cards;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout profileCard;
        public TextView personName;
        public SeekBar moodBar;
        public ImageView profilePhoto;
        public TextView previewText;



        public ViewHolder(View itemView) {
            super(itemView);

            personName = (TextView) itemView.findViewById(R.id.personName);
            moodBar = (SeekBar) itemView.findViewById(R.id.moodBar);
            profilePhoto = (ImageView) itemView.findViewById(R.id.profile);
            previewText = (TextView) itemView.findViewById(R.id.previewText);
            profileCard = (RelativeLayout) itemView.findViewById(R.id.profileCard);
        }

        public void bind(final ProfileCard profile) {
            //Set item views based on views and data model
            personName.setText(profile.getName());
            moodBar.setProgress(profile.getRatingNumber());
//            if(profile.getPhotoURLString() != "") {
//                GlideApp.with(context)
//                        .load("https://square.github.io/picasso/static/sample.png")
//                        .into(profilePhoto);
//            }
            previewText.setText(profile.getGrateful());

            profileCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserDetailPageActivity.class);
                    intent.putExtra(ProfileCard.class.getName(), Parcels.wrap(profile));
                    Toast.makeText(context, profile.getName(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
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
        holder.bind(profile);

    }

    @Override
    public int getItemCount() {
        return profileCards.size();
    }


}
