package com.yatinkaushal.carryme.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yatinkaushal.carryme.Globals;
import com.yatinkaushal.carryme.R;
import com.yatinkaushal.carryme.TaskDetailActivity;
import com.yatinkaushal.carryme.models.CarryTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yatinkaushal on 2/3/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    Context context;
    ArrayList<CarryTask> tasks;

    public FeedAdapter(Context context, ArrayList<CarryTask> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(tasks.get(position).name);
        Glide.with(context)
                .load(tasks.get(position).userPhotoUrl)
                .into(holder.circleImageView);
        holder.username.setText(tasks.get(position).username);
        holder.description.setText(tasks.get(position).description);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CardView cardView;
        CircleImageView circleImageView;
        TextView username;
        TextView description;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textView);
            username = (TextView) itemView.findViewById(R.id.textView5);
            description = (TextView) itemView.findViewById(R.id.textView6);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.imageView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    intent.putExtra(Globals.TASK, tasks.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
