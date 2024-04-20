package com.example.esaychat.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.esaychat.ChatActivity;
import com.example.esaychat.R;
import com.example.esaychat.Model.ChatMessageModel;
import com.example.esaychat.utils.AndroidUtil;
import com.example.esaychat.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;



public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, ChatRecyclerAdapter.ChatModelViewHolder> {

    Context context;

    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder,int position, @NonNull ChatMessageModel model) {

        if(model.getSenderId().equals(FirebaseUtil.currentUserId())){

            holder.leftChatLayout.setVisibility(View.GONE);
            holder.rightChatLayout.setVisibility(View.VISIBLE);

            if (model.getMessage().startsWith("https:/"))
                {
                    holder.rightChatTextview.setVisibility(View.GONE);
                    holder.imagechatView.setVisibility(View.VISIBLE);

                    Glide.with(context).load(model.getMessage()).into(holder.imagechatView);

                }
                else {
                    holder.imagechatView.setVisibility(View.GONE);
                    holder.rightChatTextview.setVisibility(View.VISIBLE);

                    holder.rightChatTextview.setText(model.getMessage());
                }



        }else{
            holder.rightChatLayout.setVisibility(View.GONE);
            holder.leftChatLayout.setVisibility(View.VISIBLE);



            if (model.getMessage().startsWith("https:/"))
            {
                holder.leftChatTextview.setVisibility(View.GONE);
                holder.leftimagechatView.setVisibility(View.VISIBLE);

                Glide.with(context).load(model.getMessage()).into(holder.leftimagechatView);

            }
            else {
                holder.leftimagechatView.setVisibility(View.GONE);
                holder.leftChatTextview.setVisibility(View.VISIBLE);
                holder.leftChatTextview.setText(model.getMessage());
            }

        }
    }

    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row,parent,false);

        return new ChatModelViewHolder(view);
    }

    class ChatModelViewHolder extends RecyclerView.ViewHolder{

        LinearLayout rightChatLayout,leftChatLayout;
        TextView leftChatTextview,rightChatTextview;
        ImageView imagechatView,leftimagechatView;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);

            rightChatLayout = itemView.findViewById(R.id.right_chat_layout);
            rightChatTextview = itemView.findViewById(R.id.right_chat_textview);
            imagechatView = itemView.findViewById(R.id.right_chat_ImageView);

            leftChatLayout = itemView.findViewById(R.id.left_chat_layout);
            leftChatTextview=itemView.findViewById(R.id.left_chat_textview);
            leftimagechatView = itemView.findViewById(R.id.left_chat_ImageView);

        }
    }
}
