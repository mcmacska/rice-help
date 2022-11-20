package com.example.rice_help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class rice_bowl extends AppCompatActivity {

    private int current_phone_id;

    ImageView phone_destroyed;
    ImageView bowl;

    Button back_button;
    Button put_phone_into_rice;

    TextView state;

    private SharedPreferences sp_destroy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_bowl);

        phone_destroyed = findViewById(R.id.phone_destroyed);
        bowl = findViewById(R.id.bowl);

        back_button = findViewById(R.id.back_button);
        put_phone_into_rice = findViewById(R.id.put_phone_into_rice);

        state = findViewById(R.id.state);

        //initial back button
        back_button.setEnabled(false);
        back_button.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
        back_button.setTextColor(getResources().getColor(R.color.rice_help_background_faded));

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

        //put phone into rice button
        put_phone_into_rice.setEnabled(false);
        put_phone_into_rice.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color_faded));
        put_phone_into_rice.setTextColor(getResources().getColor(R.color.rice_help_background_faded));

        has_finished();
    }

    public void rice_finished(){
        current_phone_id++;

        state.setText(R.string.phone_repaired_text);
        back_button.setEnabled(true);
        back_button.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color));
        back_button.setTextColor(getResources().getColor(R.color.rice_help_background));
    }

    public void has_finished(){
        Date current_Time = Calendar.getInstance().getTime();
        Log.d("current_time", String.valueOf(current_Time));

        if (true){

            rice_finished();
        }
    }

    public void back(View v){
        sp_destroy = getSharedPreferences("rice_help_data", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp_destroy.edit();

        editor.putInt("current_phone_id", current_phone_id);
        editor.putBoolean("destroyed", false);
        editor.putBoolean("is_in_rice", false);

        editor.commit();

//        bowl.setImageResource(R.drawable.bowl_of_rice);
//        put_phone_into_rice.setEnabled(true);
//        put_phone_into_rice.setBackgroundColor(getResources().getColor(R.color.rice_help_text_color));
//        put_phone_into_rice.setTextColor(getResources().getColor(R.color.rice_help_background));

        this.finish();
    }
}