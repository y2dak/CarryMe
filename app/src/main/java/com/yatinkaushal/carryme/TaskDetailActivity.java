package com.yatinkaushal.carryme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yatinkaushal.carryme.models.CarryTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CarryTask carryTask = getIntent().getParcelableExtra(Globals.TASK);
        ((TextView)findViewById(R.id.textView2)).setText(carryTask.username);
        ((TextView)findViewById(R.id.textView4)).setText(carryTask.description);
        ((TextView)findViewById(R.id.textView3)).setText(carryTask.name);
        ((TextView)findViewById(R.id.textView9)).setText("Reward: " + carryTask.reward);
        Button button = (Button) findViewById(R.id.button3);
        button.setText("Carry " + carryTask.username);
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load(carryTask.userPhotoUrl)
                .into(circleImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }

}
