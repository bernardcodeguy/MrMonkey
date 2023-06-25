package com.example.mrmonkey1.view;


import com.example.mrmonkey1.MrMonkeyGame;
import com.example.mrmonkey1.control.Controller;
import com.example.mrmonkey1.model.BadFruit;
import com.example.mrmonkey1.model.GoodFruit;
import com.example.mrmonkey1.model.MonkeyPlayer;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


//La scena di gioco principale per il gioco
public class GameScene extends GeneralScene{

    private static final String BACKGROUND_IMG = MrMonkeyGame.class.getResource("background.jpg").toExternalForm();



    private static final String BACKGROUND_MUSIC;

    static {
        try {
            BACKGROUND_MUSIC = MrMonkeyGame.class.getResource("in_game.wav").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String EAT_SOUND;

    static {
        try {
            EAT_SOUND = MrMonkeyGame.class.getResource("eat.wav").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }



    private static final String HURT_SOUND;

    static {
        try {
            HURT_SOUND = MrMonkeyGame.class.getResource("hurt.wav").toURI().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    private Image backgroundImg;
    private MonkeyPlayer monkey;

    private GoodFruit goodFruit1 = null;
    private GoodFruit goodFruit2 = null;
    private GoodFruit goodFruit3 = null;
    private GoodFruit goodFruit4 = null;
    private BadFruit badFruit1 = null;
    private BadFruit badFruit2 = null;
    private BadFruit badFruit3 = null;
    public static int score = 0;
    private int health = 100;

    public GameScene(){
        super();

        try{
            backgroundImg = new Image(BACKGROUND_IMG);
            this.monkey = MonkeyPlayer.getInstance();
        }catch (Exception e){

        }
    }


    @Override
    public void draw() {

        reset();
        sound = new Media(BACKGROUND_MUSIC);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        clip = new AudioClip(EAT_SOUND);
        clip1 = new AudioClip(HURT_SOUND);
        activeKeys.clear();
        monkey.moveTo(380,335);



        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);

                gc.drawImage(backgroundImg ,0,0,GAME_WIDTH,GAME_HEIGHT);
                monkey.draw(gc);



                if(goodFruit1 != null){
                    goodFruit1.draw(gc);
                }

                if(goodFruit2 != null){
                    goodFruit2.draw(gc);
                }

                if(goodFruit3 != null){
                    goodFruit3.draw(gc);
                }
                if(goodFruit4 != null){
                    goodFruit4.draw(gc);
                }


                if(badFruit1 != null){
                    badFruit1.draw(gc);
                }

                if(badFruit2 != null){
                    badFruit2.draw(gc);
                }
                if(badFruit3 != null){
                    badFruit3.draw(gc);
                }

                updateHUD();

                if(activeKeys.contains(KeyCode.ESCAPE)){
                    this.stop();
                    Controller.setScene(MrMonkeyGame.WELCOME_SCENE);
                    mediaPlayer.stop();
                }else if(activeKeys.contains(KeyCode.ENTER)){
                    this.stop();
                    Controller.setScene(MrMonkeyGame.CREDITS_SCENE);
                    mediaPlayer.stop();
                }else if(activeKeys.contains(KeyCode.LEFT)){
                    monkey.move(MonkeyPlayer.LEFT);
                }else if(activeKeys.contains(KeyCode.RIGHT) ){
                    monkey.move(MonkeyPlayer.RIGHT);
                }else if(activeKeys.contains(KeyCode.UP) ){
                    monkey.move(MonkeyPlayer.JUMP);
                }else if(activeKeys.contains(KeyCode.A)){
                    monkey.run(MonkeyPlayer.LEFT);
                }else if(activeKeys.contains(KeyCode.S)){
                    monkey.run(MonkeyPlayer.RIGHT);
                }




                try {
                    goodFruitActivities();

                    badFruitActivities();
                }catch (Exception e){

                }

                if(health == 0){
                    this.stop();
                    mediaPlayer.stop();
                    Controller.setScene(MrMonkeyGame.CREDITS_SCENE);
                }

            }
        }.start();
    }

    private void badFruitActivities() {
        if (score >= 0) {
            if(badFruit1 == null){
                badFruit1 = new BadFruit();
                badFruit1.moveTo((int)(Math.random() * (GeneralScene.GAME_WIDTH - BadFruit.FRUIT_WIDTH)),0);
            }else{
                badFruit1.move();
                if(badFruit1.collideWidth(monkey)){
                    clip1.play();
                    health-=25;
                    monkey.resetPosition();
                    badFruit1 = null;
                }else if(badFruit1.getY() > GeneralScene.GAME_HEIGHT){
                    badFruit1 = null;
                }
            }
        }
        if (score >= 100) {
            if(badFruit2 == null){
                badFruit2 = new BadFruit();
                badFruit2.moveTo((int)(Math.random() * (GeneralScene.GAME_WIDTH - BadFruit.FRUIT_WIDTH)),0);
            }else{
                badFruit2.move();
                if(badFruit2.collideWidth(monkey)){
                    clip1.play();
                    health-=25;
                    monkey.resetPosition();
                    badFruit2 = null;
                }else if(badFruit2.getY() > GeneralScene.GAME_HEIGHT){
                    badFruit2 = null;
                }
            }
        }

        if (score >= 250) {
            if(badFruit3 == null){
                badFruit3 = new BadFruit();
                badFruit3.moveTo((int)(Math.random() * (GeneralScene.GAME_WIDTH - BadFruit.FRUIT_WIDTH)),0);
            }else{
                badFruit3.move();
                if(badFruit3.collideWidth(monkey)){
                    clip1.play();
                    health-=25;
                    monkey.resetPosition();
                    badFruit3 = null;
                }else if(badFruit3.getY() > GeneralScene.GAME_HEIGHT){
                    badFruit3 = null;
                }
            }
        }
    }
    private void goodFruitActivities() {

        if (score >= 0) {
            if (goodFruit1 == null) {
                goodFruit1 = new GoodFruit();
                goodFruit1.moveTo((int) (Math.random() * (GeneralScene.GAME_WIDTH - GoodFruit.FRUIT_WIDTH)), 0);
            } else {
                goodFruit1.move();

                if (goodFruit1.collideWidth(monkey)) {
                    clip.play();
                    score += 10;
                    goodFruit1.increaseDifficulty();
                    badFruit1.increaseDifficulty();
                    goodFruit1 = null;
                } else if (goodFruit1.getY() > GeneralScene.GAME_HEIGHT) {
                    goodFruit1 = null;
                }
            }

        }

        if (score >= 50) {
            if (goodFruit2 == null) {
                goodFruit2 = new GoodFruit();
                goodFruit2.moveTo((int) (Math.random() * (GeneralScene.GAME_WIDTH - GoodFruit.FRUIT_WIDTH)), 0);
            } else {
                goodFruit2.move();

                if (goodFruit2.collideWidth(monkey)) {
                    clip.play();
                    score += 10;
                    goodFruit2.increaseDifficulty();

                    goodFruit2 = null;
                } else if (goodFruit2.getY() > GeneralScene.GAME_HEIGHT) {
                    goodFruit2 = null;
                }
            }

        }

        if (score >= 150) {
            if (goodFruit3 == null) {
                goodFruit3 = new GoodFruit();
                goodFruit3.moveTo((int) (Math.random() * (GeneralScene.GAME_WIDTH - GoodFruit.FRUIT_WIDTH)), 0);
            } else {
                goodFruit3.move();

                if (goodFruit3.collideWidth(monkey)) {
                    clip.play();
                    score += 10;
                    goodFruit3.increaseDifficulty();
                    //badFruit1.increaseDifficulty();
                    goodFruit3 = null;
                } else if (goodFruit3.getY() > GeneralScene.GAME_HEIGHT) {
                    goodFruit3 = null;
                }
            }

        }

        if (score >= 200) {
            if (goodFruit4 == null) {
                goodFruit4 = new GoodFruit();
                goodFruit4.moveTo((int) (Math.random() * (GeneralScene.GAME_WIDTH - GoodFruit.FRUIT_WIDTH)), 0);
            } else {
                goodFruit4.move();

                if (goodFruit4.collideWidth(monkey)) {
                    clip.play();
                    score += 10;
                    goodFruit4.increaseDifficulty();
                    //badFruit1.increaseDifficulty();
                    goodFruit4 = null;
                } else if (goodFruit4.getY() > GeneralScene.GAME_HEIGHT) {
                    goodFruit4 = null;
                }
            }
        }


    }


    private void reset(){
        monkey.resetPosition();
        health = 100;
        score = 0;

        goodFruit1 = null;
        goodFruit2 = null;
        goodFruit3 = null;
        goodFruit4 = null;

        badFruit1 = null;
        badFruit2 = null;
        badFruit3 = null;

        BadFruit.STEP_INCREMENT = 0.0;
        GoodFruit.STEP_INCREMENT = 0.0;
    }

    private void updateHUD(){
        Font myFont = Font.font("Roboto", FontWeight.NORMAL,18);
        gc.setFont(myFont);
        gc.setFill(Color.web("#1200FF"));
        gc.fillText("SCORE: "+score, 20,GeneralScene.GAME_HEIGHT-15);

        gc.setFill(Color.web("#1200FF"));
        gc.fillText("HEALTH: "+ health +"%", GeneralScene.GAME_WIDTH-120,GeneralScene.GAME_HEIGHT-15);
    }
}
