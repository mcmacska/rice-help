package com.example.rice_help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class destroy_phone extends AppCompatActivity {

    ImageView phone_to_destroy;

    Map<Integer, String> phone_map_2 = new HashMap<Integer, String>();

    Integer number_of_phones_2 = 11;

    private SharedPreferences sp_destroy;

    private int current_phone_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destroy_phone);

        phone_to_destroy = findViewById(R.id.phone_to_destroy);

        map_fill();

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

    private void map_fill(){
        for (int i = 1; i <= number_of_phones_2; i++) {
            String phone_name = "phone_" + i;
            phone_map_2.put(i, phone_name);
        }
    }

    private void set_phone(int phoneid_){
        if (phoneid_ == 0){
            System.out.println("No phone.");
            phone_to_destroy.setImageResource(R.drawable.demo_phone);
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
            phone_to_destroy.setImageResource(phone_id);
        }
    }

    public void destroy_phone_(View v){
        sp_destroy = getSharedPreferences("rice_help_data", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp_destroy.edit();

        editor.putInt("current_phone_id", current_phone_id);
        editor.putBoolean("destroyed", true);

        editor.commit();

        this.finish();
    }

    public void cancel_(View v){
        this.finish();
    }
}