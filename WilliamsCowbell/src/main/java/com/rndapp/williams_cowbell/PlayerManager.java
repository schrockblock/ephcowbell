package com.rndapp.williams_cowbell;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;

/**
 * Created by ell on 11/7/13.
 */
public class PlayerManager {
    private ArrayList<PlayingModel> players = new ArrayList<PlayingModel>(3);

    public PlayerManager(Context context) {
        MediaPlayer sound1 = MediaPlayer.create(context, R.raw.goofy_short);
        MediaPlayer sound2 = MediaPlayer.create(context, R.raw.normal_short);
        MediaPlayer sound3 = MediaPlayer.create(context, R.raw.tinny_short);

        PlayingModel player1 = new PlayingModel(sound1);
        PlayingModel player2 = new PlayingModel(sound2);
        PlayingModel player3 = new PlayingModel(sound3);

        players.add(player1);
        players.add(player2);
        players.add(player3);
    }

    public void play(int index){
        players.get(index).startLooping();
    }

    public void pause(){
        for (PlayingModel playingModel : players){
            playingModel.pause();
        }
    }
}
