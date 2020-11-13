package com.example.moodybuds;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnTouchListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.ViewHolder> {

    Context context;
    private List<ProfileCard> profileCards;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

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
        public ImageView alert;

        public ViewHolder(View itemView) {
            super(itemView);

            personName = (TextView) itemView.findViewById(R.id.personName);
            moodBar = (SeekBar) itemView.findViewById(R.id.moodBar);
            profilePhoto = (ImageView) itemView.findViewById(R.id.profile);
            previewText = (TextView) itemView.findViewById(R.id.previewText);
            profileCard = (RelativeLayout) itemView.findViewById(R.id.profileCard);
            alert = itemView.findViewById(R.id.alert);

            moodBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

        }

        public void bind(final ProfileCard profile) {

            // get storage reference for each person
            StorageReference profileRef = storageReference.child(profile.getUID());

            // get current user
            currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            //Set item views based on views and data model
            personName.setText(profile.getName());
            moodBar.setProgress(profile.getRatingNumber());
            if(profile.isNeedsHelp()) {
                alert.setVisibility(View.VISIBLE);
            } else {
                alert.setVisibility(View.INVISIBLE);
            }

            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profilePhoto);
                }
            });

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

            if(profile.getRatingNumber()<=30){
                profileCard.findViewById(R.id.iV_smile).setVisibility(View.INVISIBLE);
                profileCard.findViewById(R.id.meh).setVisibility(View.INVISIBLE);
                profileCard.findViewById(R.id.iV_sad).setVisibility(View.VISIBLE);
            }
            if(profile.getRatingNumber()>30 && profile.getRatingNumber()<70){
                profileCard.findViewById(R.id.iV_smile).setVisibility(View.INVISIBLE);
                profileCard.findViewById(R.id.meh).setVisibility(View.VISIBLE);
                profileCard.findViewById(R.id.iV_sad).setVisibility(View.INVISIBLE);
            }
            if(profile.getRatingNumber()>=70){
                profileCard.findViewById(R.id.iV_smile).setVisibility(View.VISIBLE);
                profileCard.findViewById(R.id.meh).setVisibility(View.INVISIBLE);
                profileCard.findViewById(R.id.iV_sad).setVisibility(View.INVISIBLE);
            }

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
