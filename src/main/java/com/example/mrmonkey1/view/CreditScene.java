package com.example.mrmonkey1.view;


import com.example.mrmonkey1.MrMonkeyGame;
import com.example.mrmonkey1.control.Controller;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.URISyntaxException;


//La scena del gioco finito
public class CreditScene extends GeneralScene{
    //private static final String BACKGROUND_MUSIC = "assets/game_over.wav";

    private static final String BACKGROUND_MUSIC;

    static {
        try {
            BACKGROUND_MUSIC = MrMonkeyGame.class.getResource("game_over.wav").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public CreditScene(){
        super();
    }

    public void showCreditsMessage(){
        Font myFont = Font.font("Arial", FontWeight.NORMAL,32);
        gc.setFont(myFont);
        gc.setFill(Color.RED);
        gc.fillText("Game Over", 325,200);


        myFont = Font.font("Arial", FontWeight.NORMAL,20);
        gc.setFont(myFont);
        gc.setFill(Color.YELLOW);
        gc.fillText("Your score: "+GameScene.score, 340,225);
        gc.setFill(Color.GREEN);
        gc.fillText("Press Spacebar to go back to welcome screen", 200,275);
        gc.fillText("Press V to start game again", 280,295);

    }



    @Override
    public void draw() {
        clip = new AudioClip(BACKGROUND_MUSIC);
        clip.play();
        /*
        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
                showCreditsMessage();
                if(activeKeys.contains(KeyCode.SPACE)){
                    this.stop();
                    MrMonkey.setScene(MrMonkey.WELCOME_SCENE);
                    clip.stop();
                } else if (activeKeys.contains(KeyCode.V)) {
                    this.stop();
                    MrMonkey.setScene(MrMonkey.GAME_SCENE);
                    clip.stop();
                }
            }
        }.start();*/


        activeKeys.clear();

        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
                showCreditsMessage();
                if(activeKeys.contains(KeyCode.SPACE)){

                    this.stop();
                    Controller.setScene(MrMonkeyGame.WELCOME_SCENE);

                }else if(activeKeys.contains(KeyCode.V)){
                    this.stop();
                    Controller.setScene(MrMonkeyGame.GAME_SCENE);
                }
            }
        }.start();
    }
}
