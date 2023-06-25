package com.example.mrmonkey1.view;


import com.example.mrmonkey1.MrMonkeyGame;
import com.example.mrmonkey1.control.Controller;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


//La scena di benvenuto prima di iniziare il gioco
public class WelcomeScene extends GeneralScene{
   //private static final String BACKGROUND_MUSIC = "assets/main_menu.wav";
    private final String BACKGROUND_MUSIC = MrMonkeyGame.class.getResource("main_menu.wav").toURI().toString();
    //private static final String IMAGE_PATH = "assets/good_fruits.png";

    private final String IMAGE_PATH = MrMonkeyGame.class.getResource("good_fruits.png").toExternalForm();

    //private static final String IMAGE_PATH1 = "assets/bad_fruits.png";

    private final String IMAGE_PATH1 = MrMonkeyGame.class.getResource("bad_fruits.png").toExternalForm();
    private static final String greetingsMsg = "Hello Friend!\n";
    private static final String welcomeInstructions =
            "I am Mr. Monkey and i love fruits a lot especially banana\n" +
            "If I eat bad fruit, it reduces health because it is contaminated\n" +
            "with germs but Good fruits helps me with a good score\n" +
            "Why dont you start the game and find out";

    public WelcomeScene() throws IOException, URISyntaxException {
        super();
        showWelcomeMessage();
    }

    private void showWelcomeMessage() throws IOException {
        Font myFont = Font.font("Arial", FontWeight.NORMAL,32);
        gc.setFont(myFont);
        gc.setFill(Color.GREEN);
        gc.fillText("Mr Monkey", 340,100);
        myFont = Font.font("Arial", FontWeight.NORMAL,20);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText(greetingsMsg, 350,140);
        gc.fillText(welcomeInstructions, 205,160);
        gc.setFill(Color.YELLOW);
        gc.fillText("INSTRUCTIONS", 350,260);
        gc.setFill(Color.WHITE);
        gc.fillText("LEFT KEY = ", 205,280);
        gc.setFill(Color.GREEN);
        gc.fillText("MOVE LEFT", 345,280);
        gc.setFill(Color.WHITE);
        gc.fillText("RIGHT KEY = ", 205,300);
        gc.setFill(Color.GREEN);
        gc.fillText("MOVE RIGHT", 345,300);
        gc.setFill(Color.WHITE);
        gc.fillText("A & S KEY = ", 205,320);
        gc.setFill(Color.GREEN);
        gc.fillText("RUN LEFT OR RIGHT", 345,320);


        gc.setFill(Color.WHITE);
        gc.fillText("GOOD FRUITS = ", 205,330+30);
        Image goodFruit = new Image(IMAGE_PATH);
        gc.drawImage(goodFruit,4,0,40,40,380,300+30,40,40);
        gc.drawImage(goodFruit,40,0,40,40,420,300+30,40,40);
        gc.drawImage(goodFruit,80,0,40,40,460,300+30,40,40);

        gc.fillText("CONTAMINATED FRUITS = ", 205,360+30);
        Image badFruit = new Image(IMAGE_PATH1);
        gc.drawImage(badFruit,4,0,40,40,380+100,330+30,40,40);
        gc.drawImage(badFruit,44,0,40,40,420+100,330+30,40,40);



        myFont = Font.font("Arial", FontWeight.NORMAL,20);
        gc.setFont(myFont);
        gc.setFill(Color.GREEN);
        gc.fillText("Press Spacebar to start game", 300,475);

    }

    @Override
    public void draw() {
        sound = new Media(BACKGROUND_MUSIC);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        activeKeys.clear();

        new AnimationTimer(){
          @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
              try {
                  showWelcomeMessage();
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
              if(activeKeys.contains(KeyCode.SPACE)){
                    mediaPlayer.stop();
                    this.stop();
                    Controller.setScene(MrMonkeyGame.GAME_SCENE);

                }else if(activeKeys.contains(KeyCode.ESCAPE)){
                    mediaPlayer.stop();
                    this.stop();
                    MrMonkeyGame.exit();
                }
            }
        }.start();
    }
}
