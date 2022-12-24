package com.example.rice_help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class rice_bowl extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 2000;

    int time_for_rice_sec = 30;


    private int current_phone_id;

    ImageView phone_destroyed;
    ImageView bowl;

    Button back_button;
    Button put_phone_into_rice;

    TextView state;

    private SharedPreferences sp_destroy;

    private TextView game_completed;

    private int times_game_completed;

    private Boolean in_bowl;

    //initial current_time
    Calendar current_Time_init = Calendar.getInstance();
    long timeInSecs_init = current_Time_init.getTimeInMillis();
    Date current_Time_1_min = new Date(timeInSecs_init);

    Boolean put_phone = false;

    volatile Boolean destroy_handler = false;

    private String added_time = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_bowl);

        Objects.requireNonNull(getSupportActionBar()).hide();

        phone_destroyed = findViewById(R.id.phone_destroyed);
        bowl = findViewById(R.id.bowl);

        back_button = findViewById(R.id.back_button);
        put_phone_into_rice = findViewById(R.id.put_phone_into_rice);

        game_completed = findViewById(R.id.game_completed2);

        state = findViewById(R.id.state);

        //initial back button
        back_button.setEnabled(false);
        back_button.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
        back_button.setTextColor(getResources().getColor(R.color.rice_help_background_faded));

        //get phone id from bundle
        current_phone_id = 0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                current_phone_id = extras.getInt("id");
            }
        } else {
            current_phone_id = (int) savedInstanceState.getSerializable("id");
        }

        set_phone(current_phone_id);

        //get number of times game is completed from bundle
        times_game_completed = 0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                times_game_completed = extras.getInt("times_game_completed");
            }
        } else {
            times_game_completed = (int) savedInstanceState.getSerializable("times_game_completed");
        }

        String game_first = getString(R.string.game_completed);
        String game_second = getString(R.string.number_of_times);

        String game_completed_content = game_first + " " + times_game_completed + " " + game_second;

        game_completed.setText(game_completed_content);

        //get if it is in bowl
        in_bowl = false;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                in_bowl = extras.getBoolean("in_bowl");
            }
        } else {
            in_bowl = (boolean) savedInstanceState.getSerializable("in_bowl");
        }

        //get added_time
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                added_time = extras.getString("added_time");
            }
        } else {
            added_time = (String) savedInstanceState.getSerializable("added_time");
        }



        if (in_bowl){
            put_phone_empty();
        }
    }

    @Override
    protected void onResume() {
        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                //check if button has been pressed
                if (put_phone && !destroy_handler){
                    has_finished();
                }
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        super.onResume();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    private void set_phone(int phoneid_){
        if (phoneid_ == 0){
            System.out.println("No phone.");
            phone_destroyed.setImageResource(R.drawable.demo_phone_broken);
        }else {
            System.out.println(phoneid_);
            int phone_id;

            switch(phoneid_) {
                case 1:
                    phone_id = R.drawable.phone_1_broken;
                    break;
                case 2:
                    phone_id = R.drawable.phone_2_broken;
                    break;
                case 3:
                    phone_id = R.drawable.phone_3_broken;
                    break;
                case 4:
                    phone_id = R.drawable.phone_4_broken;
                    break;
                case 5:
                    phone_id = R.drawable.phone_5_broken;
                    break;
                case 6:
                    phone_id = R.drawable.phone_6_broken;
                    break;
                case 7:
                    phone_id = R.drawable.phone_7_broken;
                    break;
                case 8:
                    phone_id = R.drawable.phone_8_broken;
                    break;
                case 9:
                    phone_id = R.drawable.phone_9_broken;
                    break;
                case 10:
                    phone_id = R.drawable.phone_10_broken;
                    break;
                case 11:
                    phone_id = R.drawable.phone_11_broken;
                    break;
                default:
                    phone_id = R.drawable.demo_phone_broken;
                    break;

            }
            phone_destroyed.setImageResource(phone_id);
        }
    }

    public void put_phone(View v){

        bowl.setImageResource(R.drawable.bowl_of_rice_full);
        phone_destroyed.setImageResource(R.drawable.empty_phone);

        state.setText(R.string.phone_in_the_bowl_text);


        //get the current time
        Calendar current_Time = Calendar.getInstance();
        long timeInSecs = current_Time.getTimeInMillis();
        long added_time = (timeInSecs + (time_for_rice_sec * 1000L));
        current_Time_1_min = new Date(added_time);


        //save rice state
        sp_destroy = getSharedPreferences("rice_help_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp_destroy.edit();
        editor.putBoolean("is_in_rice", true);
        editor.putString("added_time", String.valueOf(added_time));

        editor.commit();


        //put phone into rice button
        put_phone_into_rice.setEnabled(false);
        put_phone_into_rice.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
        put_phone_into_rice.setTextColor(getResources().getColor(R.color.rice_help_background_faded));

        put_phone = true;

    }

    public void put_phone_empty(){
        Log.d("put_phone", "1");
        bowl.setImageResource(R.drawable.bowl_of_rice_full);
        phone_destroyed.setImageResource(R.drawable.empty_phone);

        state.setText(R.string.phone_in_the_bowl_text);

        //put phone into rice button
        put_phone_into_rice.setEnabled(false);
        put_phone_into_rice.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
        put_phone_into_rice.setTextColor(getResources().getColor(R.color.rice_help_background_faded));

        //get the current time
        current_Time_1_min = new Date(Long.parseLong(added_time) + (time_for_rice_sec * 1000L));

        put_phone = true;
    }

    public void rice_finished(){
        handler.removeCallbacksAndMessages(null);
        current_phone_id++;
        bowl.setImageResource(R.drawable.bowl_of_rice_full_finished);


        state.setText(R.string.phone_repaired_text);
        back_button.setEnabled(true);
        back_button.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color));
        back_button.setTextColor(getResources().getColor(R.color.rice_help_background));


        //set state
        sp_destroy = getSharedPreferences("rice_help_data", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp_destroy.edit();

        editor.putInt("current_phone_id", current_phone_id);
        editor.putBoolean("destroyed", false);
        editor.putBoolean("is_in_rice", false);
        editor.putInt("times_game_completed", times_game_completed);
        editor.putString("added_time", "");

        editor.commit();

    }

    public void has_finished(){

        Date current_Time = Calendar.getInstance().getTime();


        //check times
        if (current_Time.after(current_Time_1_min)){
            destroy_handler = true;
            rice_finished();
        }
    }

    public void back(View v){
//        sp_destroy = getSharedPreferences("rice_help_data", MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sp_destroy.edit();
//
//        editor.putInt("current_phone_id", current_phone_id);
//        editor.putBoolean("destroyed", false);
//        editor.putBoolean("is_in_rice", false);
//        editor.putInt("times_game_completed", times_game_completed);
//        editor.putInt("added_time", 0);
//
//        editor.commit();

//        bowl.setImageResource(R.drawable.bowl_of_rice);
//        put_phone_into_rice.setEnabled(true);
//        put_phone_into_rice.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color));
//        put_phone_into_rice.setTextColor(getResources().getColor(R.color.rice_help_background));

        this.finish();
    }
}