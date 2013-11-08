package com.rndapp.williams_cowbell;

import android.media.MediaPlayer;

/**
 * Created by ell on 10/23/13.
 */
public class PlayingModel{
    protected MediaPlayer player;

    public PlayingModel(MediaPlayer player){
        this.player = player;
    }

    public void startLooping(){
        player.setLooping(true);
        player.start();
    }

    public void pause(){
        if (player.isPlaying()) player.pause();
    }
}
