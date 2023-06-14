package com.example.advprog2_4;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MessageItem> messages;

    public MessagesAdapter(List<MessageItem> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getSide()==1) {
            return 1; // Right-aligned message
        } else {
            return 0; // Left-aligned message
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            // Inflate left-aligned message layout
            View view = inflater.inflate(R.layout.message_view_left, parent, false);
            return new LeftMessageViewHolder(view);
        } else {
            // Inflate right-aligned message layout
            View view = inflater.inflate(R.layout.message_view_right, parent, false);
            return new RightMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageItem message = messages.get(position);

        if (holder instanceof LeftMessageViewHolder) {
            LeftMessageViewHolder leftHolder = (LeftMessageViewHolder) holder;
            leftHolder.messageTextView.setText(message.getText());
        } else {
            RightMessageViewHolder rightHolder = (RightMessageViewHolder) holder;
            rightHolder.messageTextView.setText(message.getText());
        }
    }

    private static class LeftMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;

        LeftMessageViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.leftMessageTextView);
        }
    }

    // ViewHolder for right-aligned messages
    private static class RightMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;

        RightMessageViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.rightMessageTextView);
        }
    }
}
