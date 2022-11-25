package com.example.rice_help;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class winner extends AppCompatActivity {

    TextView game_completed;

    SharedPreferences sp_destroy;

    int times_game_completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        game_completed = findViewById(R.id.game_completed3);

        times_game_completed = 0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                times_game_completed = extras.getInt("times_game_completed");
            }
        } else {
            times_game_completed = (int) savedInstanceState.getSerializable("times_game_completed");
        }

        times_game_completed++;

        String game_first = getString(R.string.game_completed);
        String game_second = getString(R.string.number_of_times);

        String game_completed_content = game_first + " " + times_game_completed + " " + game_second;

        game_completed.setText(game_completed_content);
    }

    public void back(View v){
        sp_destroy = getSharedPreferences("rice_help_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp_destroy.edit();
        editor.putInt("current_phone_id", 1);
        editor.putInt("times_game_completed", times_game_completed);

        editor.commit();

        this.finish();
    }
}