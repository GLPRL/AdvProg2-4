package com.example.advprog2_4;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsViewHolder extends RecyclerView.ViewHolder {
    TextView date,displayName;
    ImageView profilePicView;

    public ContactsViewHolder(View v) {
        super(v);
        profilePicView = v.findViewById(R.id.profilePicView);
        displayName = v.findViewById(R.id.displayName);
        date = v.findViewById(R.id.date);
    }
}