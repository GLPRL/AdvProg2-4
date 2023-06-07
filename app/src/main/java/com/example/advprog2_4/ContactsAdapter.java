package com.example.advprog2_4;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {


    Context context;
    List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> items) {
        this.context = context;
        this.contacts = items;
    }


    @Override
    public ContactsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_view,parent,false));
    }

    @Override
    public void onBindViewHolder( ContactsViewHolder holder, int position) {
        holder.displayName.setText(contacts.get(position).getDisplayName());
        holder.date.setText(contacts.get(position).getDate());
        holder.profilePicView.setImageResource(contacts.get(position).getProfilePic());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}