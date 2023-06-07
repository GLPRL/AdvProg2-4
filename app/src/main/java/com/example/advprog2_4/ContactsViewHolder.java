package com.example.advprog2_4;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsViewHolder extends RecyclerView.ViewHolder {
    TextView date,displayName;
    ImageView profilePicView;
    RelativeLayout relativeLayout;

    public ContactsViewHolder(View v) {
        super(v);
        profilePicView = v.findViewById(R.id.profilePicView);
        displayName = v.findViewById(R.id.displayName);
        date = v.findViewById(R.id.date);
        relativeLayout= v.findViewById(R.id.contactRelativeLayout);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change background color to blue (#0000FF)
                relativeLayout.setBackgroundResource(R.color.dark_white);

                // Create a Handler and post a delayed runnable
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Revert the background color to its original state
                        relativeLayout.setBackgroundResource(android.R.color.transparent);

                        // Start the ChatActivity and pass the values of displayName and image
                        Intent intent = new Intent(v.getContext(), ChatActivity.class);
                        intent.putExtra("displayName", displayName.getText().toString());
                        v.getContext().startActivity(intent);
                    }
                }, 500); // Delay for 0.5 seconds (500 milliseconds)
            }
        });
    }
}