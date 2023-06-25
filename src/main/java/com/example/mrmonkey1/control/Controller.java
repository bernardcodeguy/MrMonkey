package com.example.mrmonkey1.control;


import com.example.mrmonkey1.view.GeneralScene;

import javafx.stage.Stage;

public class Controller {

    private static Stage stage;
    public static final int MAX_SCENES = 3;
    public static final int WELCOME_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int CREDITS_SCENE = 2;

    private static GeneralScene[] scenes;

    public Controller(GeneralScene[] scenes, Stage stage) {
        this.stage = stage;
        this.scenes = scenes;
    }

    public static void setScene(int numScene) {
        stage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }
}
