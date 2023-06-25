package com.example.mrmonkey1.model;

import com.example.mrmonkey1.MrMonkeyGame;
import com.example.mrmonkey1.view.GeneralScene;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

//Modello per il giocatore principale del gioco
public class MonkeyPlayer extends AnimatedSprite{
    private static MonkeyPlayer instance = null;
    public static final int MONKEY_WIDTH = 80;
    public static final int MONKEY_HEIGHT = 96;

    public static final int STEP = 4;

    public static final int RUN = 8;
    //private static final String MONKEY_IMG = "assets/monkey.png";
    private static final String MONKEY_IMG = MrMonkeyGame.class.getResource("monkey.png").toExternalForm();



    public MonkeyPlayer() {
        super(MONKEY_WIDTH, MONKEY_HEIGHT);
        try{
            spriteImage = new Image(MONKEY_IMG);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        spriteXCoordinates[RIGHT] =  new int[]{410,490 ,570 ,490};
        spriteYCoordinates[RIGHT] =  new int[]{0,0 ,0 ,0};
        spriteXCoordinates[LEFT] =  new int[]{1210,1290 ,1370 ,1290};
        spriteYCoordinates[LEFT] =  new int[]{0,0 ,0 ,0};
    }

    public static MonkeyPlayer getInstance() {
        if(instance == null){
            instance = new MonkeyPlayer();
        }
        return instance;
    }

    public void run(int movement) {
        int newX = x;
        if(movement == LEFT){
            newX -= Math.min(RUN,x);
        }else if(movement == RIGHT){
            newX += Math.min(RUN, GeneralScene.GAME_WIDTH - MONKEY_WIDTH-x+20);
        }
        moveTo(newX,y);
        animate(movement);
    }

    public void move(int movement){
        int newX = x;
        if(movement == LEFT){
            newX -= Math.min(STEP,x);
        }else if(movement == RIGHT){
            newX += Math.min(STEP, GeneralScene.GAME_WIDTH - MONKEY_WIDTH-x+20);
        }
        moveTo(newX,y);
        animate(movement);
    }





    public void resetPosition(){
        moveTo(GeneralScene.GAME_WIDTH/2 - MONKEY_WIDTH/2,335);
    }
}
