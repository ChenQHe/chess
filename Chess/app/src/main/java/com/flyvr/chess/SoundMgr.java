package com.flyvr.chess;

import android.media.MediaPlayer;

/**
 * Created by android studio
 * author:chen
 */
public class SoundMgr {

    private final static MediaPlayer hit = MediaPlayer.create(MyApp.getInstance(), R.raw.hit);
    private final static MediaPlayer refuse = MediaPlayer.create(MyApp.getInstance(), R.raw.refuse);
    private final static MediaPlayer click = MediaPlayer.create(MyApp.getInstance(), R.raw.click);

    public static void playHit() {
        if (hit.isPlaying()) {
            hit.stop();
        } else {
            hit.start();
        }
    }

    public static void playRefuse() {
        if (hit.isPlaying()) {
            refuse.stop();
        } else {
            refuse.start();
        }
    }

    public static void playClick() {
        if (hit.isPlaying()) {
            click.stop();
        } else {
            click.start();
        }
    }
}
