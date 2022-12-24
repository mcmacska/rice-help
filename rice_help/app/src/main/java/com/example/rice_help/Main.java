package com.example.rice_help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends AppCompatActivity {

    Map<Integer, String> phone_map = new HashMap<Integer, String>();

    Button button_destroy;

    Button rice_button;

    Integer number_of_phones = 11;

    private int current_phone_id;

    TextView current_phone_text;

    private Boolean is_destroyed;

    private Boolean is_in_rice;

    private SharedPreferences sp_main;

    private ImageView phone;

    private TextView game_completed;

    private int times_game_completed;

    private String added_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Objects.requireNonNull(getSupportActionBar()).hide();

        map_fill();

        phone = findViewById(R.id.phone);

        button_destroy = findViewById(R.id.button_destroy);

        rice_button = findViewById(R.id.rice_button);

        current_phone_text = findViewById(R.id.current_phone);

        game_completed = findViewById(R.id.game_completed);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sp_main = getSharedPreferences("rice_help_data", MODE_PRIVATE);
        current_phone_id = sp_main.getInt("current_phone_id", 1);
        is_destroyed = sp_main.getBoolean("destroyed", false);
        is_in_rice = sp_main.getBoolean("is_in_rice", false);
        times_game_completed = sp_main.getInt("times_game_completed", 0);
        added_time = sp_main.getString("added_time", "");


        //is in rice?
        if (is_in_rice){
            //jump to bowl
            empty_to_rice_bowl();

        }


        String game_first = getString(R.string.game_completed);
        String game_second = getString(R.string.number_of_times);

        String game_completed_content = game_first + " " + times_game_completed + " " + game_second;

        game_completed.setText(game_completed_content);

        if (is_destroyed){
            set_phone_broken(current_phone_id);

            //destroy button
            button_destroy.setEnabled(false);
            button_destroy.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
            button_destroy.setTextColor(getResources().getColor(R.color.rice_help_background_faded));

            String content = "(destroyed)";
            current_phone_text.setText(content);

            //rice button
            rice_button.setEnabled(true);
            rice_button.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color));
            rice_button.setTextColor(getResources().getColor(R.color.rice_help_background));
        }else{
            set_phone(current_phone_id);

            //destroy button
            button_destroy.setEnabled(true);
            button_destroy.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color));
            button_destroy.setTextColor(getResources().getColor(R.color.rice_help_background));


            String content = "";
            current_phone_text.setText(content);
            rice_button.setEnabled(false);
            rice_button.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
            rice_button.setTextColor(getResources().getColor(R.color.rice_help_background_faded));
        }

    }

    private void map_fill(){
        for (int i = 1; i <= number_of_phones; i++) {
            String phone_name = "phone_" + i;
            phone_map.put(i, phone_name);
        }

        System.out.println(phone_map.get(1));
    }

    private void set_phone(int phoneid_){
        if (phoneid_ == 0){
            System.out.println("No phone.");
            phone.setImageResource(R.drawable.demo_phone);
        }else if (phoneid_ > 11){
            //go to winner activity
            to_winner();

        }else {
            System.out.println(phoneid_);
            int phone_id;

            switch(phoneid_) {
                case 1:
                    phone_id = R.drawable.phone_1;
                    break;
                case 2:
                    phone_id = R.drawable.phone_2;
                    break;
                case 3:
                    phone_id = R.drawable.phone_3;
                    break;
                case 4:
                    phone_id = R.drawable.phone_4;
                    break;
                case 5:
                    phone_id = R.drawable.phone_5;
                    break;
                case 6:
                    phone_id = R.drawable.phone_6;
                    break;
                case 7:
                    phone_id = R.drawable.phone_7;
                    break;
                case 8:
                    phone_id = R.drawable.phone_8;
                    break;
                case 9:
                    phone_id = R.drawable.phone_9;
                    break;
                case 10:
                    phone_id = R.drawable.phone_10;
                    break;
                case 11:
                    phone_id = R.drawable.phone_11;
                    break;
                default:
                    phone_id = R.drawable.demo_phone;
                    break;

            }
            phone.setImageResource(phone_id);
        }
    }

    private void set_phone_broken(int phoneid_){
        if (phoneid_ == 0){
            System.out.println("No phone.");
            phone.setImageResource(R.drawable.demo_phone);
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
            phone.setImageResource(phone_id);
        }
    }

    public void destroy_phone(View v){
        Intent i = new Intent(Main.this, destroy_phone.class);
        i.putExtra("id", current_phone_id);
        startActivity(i);
    }

    public void to_rice_bowl(View v){
        Intent i = new Intent(Main.this, rice_bowl.class);
        i.putExtra("id", current_phone_id);
        i.putExtra("times_game_completed", times_game_completed);
        i.putExtra("in_bowl", false);
        i.putExtra("added_time", "");
        startActivity(i);
    }

    public void empty_to_rice_bowl(){
        Intent i = new Intent(Main.this, rice_bowl.class);
        i.putExtra("id", current_phone_id);
        i.putExtra("times_game_completed", times_game_completed);
        i.putExtra("in_bowl", true);
        i.putExtra("added_time", added_time);
        startActivity(i);
    }

    public void to_winner(){
        Intent i = new Intent(Main.this, winner.class);
        i.putExtra("times_game_completed", times_game_completed);
        startActivity(i);
    }
}