package com.example.advprog2_4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.advprog2_4.objects.Chat;
import com.example.advprog2_4.objects.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {


    Context context;
    List<Chat> contacts;

    public ContactsAdapter(Context context, List<Chat> items) {
        this.context = context;
        this.contacts = items;
    }


    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_view,parent,false));
    }

    @Override
    public void onBindViewHolder( ContactsViewHolder holder, int position) {
        holder.displayName.setText(contacts.get(position).getUser().getDisplayName());
        if (contacts.get(position).getLastMessage() != null) {
            holder.date.setText(contacts.get(position).getLastMessage().getCreated());
        } else {
            holder.date.setText("");
        }
        holder.profilePicView.setImageResource(R.drawable.pic1);
        if (holder.itemView != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Change background color
                    int backgroundColor = context.getResources().getColor(R.color.dark_white);
                    v.setBackgroundColor(backgroundColor);

                    // Start new activity
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ChatActivity.class);
                    int adapterPosition = holder.getAdapterPosition();
                    String displayName = contacts.get(adapterPosition).getUser().getDisplayName();
                    intent.putExtra("displayName", displayName);
                    // Pass any necessary data to the new activity
                    context.startActivity(intent);

                    // Reset background color after 5000 milliseconds
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int defaultColor = context.getResources().getColor(android.R.color.transparent);
                            v.setBackgroundColor(defaultColor);
                        }
                    }, 500);
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}