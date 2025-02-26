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
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameScreenActivity extends AppCompatActivity {
    TextView tvPlayerName;
    ImageButton imgBtn[] = new ImageButton[9];
    String imgBtnValue[] = new String[9];
    AppCompatButton btnResetGame;
    Integer PlayerNumber = 1;

    Boolean isWinner = false;


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
            final int index = i;
            imgBtnValue[i] = "";
            imgBtn[i].setOnClickListener(view -> play(view, index));
        }

        btnResetGame = findViewById(R.id.btnResetGame);
        btnResetGame.setOnClickListener(this::resetGame);
    }

    void playByID(int i) {
        Log.e("play:", "-" + i);
    }

    void resetGame(View view) {
//        for (int i = 0; i < imgBtn.length; i++) {
//            imgBtn[i].setBackground(null);
//        }
//        PlayerNumber = 1;

        Log.d("TAG", "resetGame() Called : ");
        Toast.makeText(this, "Game Reset", Toast.LENGTH_SHORT).show();
    }

    void play(View view, int index) {
        imgBtnValue[index] = PlayerNumber == 1 ? "O" : "X";
        ImageButton btnClick = findViewById(view.getId());

        // this condition prevents user to click on already filled box
        if (btnClick.getBackground().toString().contains("RippleDrawable") && !isWinner) {
            // switching players after every click
            // PlayerNumber = (PlayerNumber == 1) ? 2 : 1;

            btnClick.setBackground(PlayerNumber == 1 ? getDrawable(R.drawable.circle_24) : getDrawable(R.drawable.cross_24));

            if (checkWinner()) {
                Toast.makeText(this, "Player " + PlayerNumber + " is the Winner", Toast.LENGTH_SHORT).show();
                isWinner = true;
                return;
            }

            if (PlayerNumber == 1) {
                PlayerNumber = 2;
                tvPlayerName.setText("Player 2 ( X )");
                tvPlayerName.setTextColor(ContextCompat.getColor(this, R.color.orange));
            } else {
                PlayerNumber = 1;
                tvPlayerName.setText("Player 1 ( O )");
                tvPlayerName.setTextColor(ContextCompat.getColor(this, R.color.black));
            }


        } else {
            if (isWinner) {
                Toast.makeText(this, "Game is Already Finished", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Already Filled", Toast.LENGTH_SHORT).show();
            }

        }

    }

    Boolean checkWinner() {

        for (int i = 0; i < 9; i += 3) {

            if (!imgBtnValue[i].isEmpty() && imgBtnValue[i].equals(imgBtnValue[i + 1]) && imgBtnValue[i].equals(imgBtnValue[i + 2])) {
                imgBtn[i].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                imgBtn[i + 1].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                imgBtn[i + 2].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!imgBtnValue[i].isEmpty() && imgBtnValue[i].equals(imgBtnValue[i + 3]) && imgBtnValue[i].equals(imgBtnValue[i + 6])) {
                imgBtn[i].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                imgBtn[i + 3].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                imgBtn[i + 6].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                return true;
            }
        }

        if (!imgBtnValue[0].isEmpty() && imgBtnValue[0].equals(imgBtnValue[4]) && imgBtnValue[0].equals(imgBtnValue[8])) {
            imgBtn[0].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            imgBtn[4].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            imgBtn[8].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            return true;
        }

        if (!imgBtnValue[2].isEmpty() && imgBtnValue[2].equals(imgBtnValue[4]) && imgBtnValue[2].equals(imgBtnValue[6])) {
            imgBtn[2].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            imgBtn[4].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            imgBtn[6].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            return true;
        }

        return false;

    }
}