package com.example.boss.go_song;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;

public class viewData extends AppCompatActivity {

    Button bplay,bpause,bstop;
    MediaPlayer mplayer;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        bplay = (Button) findViewById(R.id.play);
        bpause = (Button) findViewById(R.id.pause);
        bstop = (Button) findViewById(R.id.stop);

        stateAwal();

        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
                bplay.setEnabled(false);
                bstop.setEnabled(true);
                bpause.setEnabled(true);
            }
        });
       bstop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               stop();
           }
       });
       bpause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               pause();
           }
       });

    }
    public void stateAwal(){
       bplay.setEnabled(true);
       bpause.setEnabled(false);
       bstop.setEnabled(false);
    }

    private void play(){
       mplayer = MediaPlayer.create(this, R.raw.sugeng_dalu);
        try{
           mplayer.prepare();
       }
       catch (IllegalStateException e){
           e.printStackTrace();
       }
       catch(IOException e){
           e.printStackTrace();
       }
       mplayer.start();
        mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stateAwal();
            }
        });
    }

    private void pause(){
       if(mplayer.isPlaying())
           if(mplayer != null){
               mplayer.pause();
       }else{
           if(mplayer != null){
               mplayer.start();
           }
       }
       stateAwal();
    }
    private void stop(){
       mplayer.stop();
       try{
           mplayer.prepare();
       }
       catch(Throwable t){
           t.printStackTrace();
       }
       stateAwal();
    }
}
