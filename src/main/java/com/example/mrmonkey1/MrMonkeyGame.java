package com.example.mrmonkey1;

import com.example.mrmonkey1.control.Controller;
import com.example.mrmonkey1.view.CreditScene;
import com.example.mrmonkey1.view.GameScene;
import com.example.mrmonkey1.view.GeneralScene;
import com.example.mrmonkey1.view.WelcomeScene;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;


//Punto di ingresso dell'applicazione
public class MrMonkeyGame extends Application {
    public static final int MAX_SCENES = 3;
    public static final int WELCOME_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int CREDITS_SCENE = 2;

    public static final GeneralScene[] scenes = new GeneralScene[MAX_SCENES];

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        this.stage = stage;


        scenes[0] = new WelcomeScene();
        scenes[1] = new GameScene();
        scenes[2] = new CreditScene();

        stage.setTitle("Mr Monkey");
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("background.jpg")));
        Controller controller = new Controller(scenes,stage);
        //setScene(WELCOME_SCENE);
        controller.setScene(WELCOME_SCENE);

        stage.show();

    }

    public static void exit(){
        stage.hide();
    }

    public static void main(String[] args) {
        launch();
    }
}