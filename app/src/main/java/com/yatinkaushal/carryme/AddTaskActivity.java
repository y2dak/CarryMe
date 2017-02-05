package com.yatinkaushal.carryme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yatinkaushal.carryme.models.CarryTask;
import com.yatinkaushal.carryme.models.User;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";
    private FirebaseDatabase database;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mUser = getIntent().getParcelableExtra(Globals.USER);
        database = FirebaseDatabase.getInstance();
        final EditText nameTxt = (EditText) findViewById(R.id.editText);
        final EditText descriptionTxt = (EditText) findViewById(R.id.editText2);
        final EditText rewardTxt = (EditText) findViewById(R.id.editText3);
        Button carryMe = (Button) findViewById(R.id.button2);
        carryMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameTxt.getText().toString().trim().length() != 0 && descriptionTxt.getText().toString().trim().length() != 0 && rewardTxt.getText().toString().trim().length() != 0) {
                    DatabaseReference reference = database.getReference();
                    CarryTask carryTask = new CarryTask(nameTxt.getText().toString(), mUser.id, mUser.name, mUser.photoUrl, descriptionTxt.getText().toString(), rewardTxt.getText().toString());
                    DatabaseReference reference1 = reference.child(Globals.TASKS).push();
                    carryTask.id = reference1.getKey();
                    reference1.setValue(carryTask);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
