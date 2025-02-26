package com.royal.tictactoe;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameScreenActivity extends AppCompatActivity {
    TextView tvPlayerName;
    ImageButton imgBtn[] = new ImageButton[9];
    Integer PlayerNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove action bar
        getSupportActionBar().hide();

        // force to open app in light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // change action bar color after super.onCreate()
//        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fffffc"))); //Orange
//        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvPlayerName = findViewById(R.id.tvTicTacToePlayerName);
        if (PlayerNumber == 1) {
            tvPlayerName.setText("Player 1 ( O )");
            tvPlayerName.setTextColor(ContextCompat.getColor(this, R.color.black));
        } else {
            tvPlayerName.setText("Player 2 ( X )");
            tvPlayerName.setTextColor(ContextCompat.getColor(this, R.color.orange));
        }

        imgBtn[0] = findViewById(R.id.imgBtn1);
        imgBtn[1] = findViewById(R.id.imgBtn2);
        imgBtn[2] = findViewById(R.id.imgBtn3);
        imgBtn[3] = findViewById(R.id.imgBtn4);
        imgBtn[4] = findViewById(R.id.imgBtn5);
        imgBtn[5] = findViewById(R.id.imgBtn6);
        imgBtn[6] = findViewById(R.id.imgBtn7);
        imgBtn[7] = findViewById(R.id.imgBtn8);
        imgBtn[8] = findViewById(R.id.imgBtn9);

        // for passing the index of the box to playByID()
//        for (int i = 0; i < imgBtn.length; i++) {
//            final int index = i;
//            imgBtn[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    playByID(index);
//                }
//            });
//        }

        // for passing the View of the box to play()
        for (int i = 0; i < imgBtn.length; i++) {
            imgBtn[i].setOnClickListener(this::play);
        }
    }

    public void playByID(int i) {
        Log.e("play:", "-" + i);
    }

    public void resetGame(View view) {
    }

    public void play(View view) {
        ImageButton btnClick = findViewById(view.getId());

        // this condition prevents user to click on already filled box
        if (btnClick.getBackground().toString().contains("RippleDrawable")) {
            // switching players after every click
            // PlayerNumber = (PlayerNumber == 1) ? 2 : 1;
            if (PlayerNumber == 1) {
                PlayerNumber = 2;
                tvPlayerName.setText("Player 2 ( X )");
                tvPlayerName.setTextColor(ContextCompat.getColor(this, R.color.orange));
                btnClick.setBackground(getDrawable(R.drawable.circle_24));

            } else {
                PlayerNumber = 1;
                tvPlayerName.setText("Player 1 ( O )");
                tvPlayerName.setTextColor(ContextCompat.getColor(this, R.color.black));
                btnClick.setBackground(getDrawable(R.drawable.cross_24));
            }
        }else{
            Toast.makeText(this,"Already Filled",Toast.LENGTH_SHORT).show();
        }

    }
}