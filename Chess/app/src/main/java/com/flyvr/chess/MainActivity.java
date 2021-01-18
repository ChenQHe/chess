package com.flyvr.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ChessView mChessView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChessView = findViewById(R.id.chessView);
        findViewById(R.id.btn1).setOnClickListener(v -> mChessView.reset());
        findViewById(R.id.btn2).setOnClickListener(v -> finish());
        findViewById(R.id.btn3).setOnClickListener(v -> mChessView.regretChess());
    }
}