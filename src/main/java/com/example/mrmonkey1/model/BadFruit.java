package com.example.mrmonkey1.model;

import com.example.mrmonkey1.MrMonkeyGame;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class BadFruit extends Sprite{
    private static final String IMAGE_PATH = MrMonkeyGame.class.getResource("bad_fruits.png").toExternalForm();
    public static int MAX_FRUITS = 2;
    public static int FRUIT_WIDTH = 40;
    public static int FRUIT_HEIGHT = 40;

    public static double STEP_INCREMENT = 0.0;

    public BadFruit(){
        this((int)(Math.random() * MAX_FRUITS));
    }

    public BadFruit(int fruitType){
        super(FRUIT_WIDTH,FRUIT_HEIGHT);

        try{
            spriteImage = new Image(IMAGE_PATH);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        switch (fruitType){
            case 0:
                this.spriteX = 4;
                this.spriteY = 0;
                break;
            case 1:
                this.spriteX = 44;
                this.spriteY = 0;
                break;
        }

    }

    public void move(){
        this.y += (int) (1+STEP_INCREMENT);
    }

    public void increaseDifficulty(){
        STEP_INCREMENT += 0.2;
    }

}
