package com.yatinkaushal.carryme.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yatinkaushal.carryme.Globals;
import com.yatinkaushal.carryme.MainActivity;
import com.yatinkaushal.carryme.R;
import com.yatinkaushal.carryme.TaskDetailActivity;
import com.yatinkaushal.carryme.models.CarryTask;

import java.util.ArrayList;
import java.util.Date;

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
        holder.reward.setText(tasks.get(position).reward);
        holder.timestamp.setText(timeSince(tasks.get(position).date));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private String timeSince(Date date) {
        if (date == null) return "just now";
        long seconds = (new Date().getTime() - date.getTime()) / 1000;
        long interval = seconds / 31536000;
        if (interval > 1) {
            return interval + " years ago";
        } else if(interval == 1) {
            return "1 year ago";
        }
        interval = seconds / 2592000;
        if (interval > 1) {
            return interval + " months ago";
        } else if (interval == 1) {
            return "1 month ago";
        }
        interval = seconds / 86400;
        if (interval > 1) {
            return interval + " days ago";
        } else if (interval == 1) {
            return "1 day ago";
        }
        interval = seconds / 3600;
        if (interval > 1) {
            return interval + " hours ago";
        } else if (interval == 1){
            return "1 hour ago";
        }
        interval = seconds / 60;
        if (interval > 1) {
            return interval + " minutes ago";
        } else if (interval == 1) {
            return "1 minute ago";
        }
        return "just now";
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CardView cardView;
        CircleImageView circleImageView;
        TextView username;
        TextView reward;
        TextView timestamp;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.textView);
            username = (TextView) itemView.findViewById(R.id.textView5);
            reward = (TextView) itemView.findViewById(R.id.textView6);
            timestamp = (TextView) itemView.findViewById(R.id.textView7);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.imageView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    intent.putExtra(Globals.TASK, tasks.get(getAdapterPosition()));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(((MainActivity)context), (View)circleImageView, "image");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        context.startActivity(intent, options.toBundle());
                    } else {
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
