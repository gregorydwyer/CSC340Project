package CatGame.ViewManagers;

/**
 * This is the Menu View Manager
 * Author(s) - Greg, Erika Sudderth
 * Last updated - 3/31/20
 */
import CatGame.Controller.GameController;
import CatGame.Models.CollisionObjects;
import CatGame.Sprite.*;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class GameView extends ViewManager {

    private static final String BACKGROUND = "/resources/imgs/game_background.png";
    private AnimationTimer timer;
    private GameController controller;
    private Cat cat;
    private Mouse mouse;

    public GameView(GameController _cont, Stage _oldStage) {
        this.controller = _cont;
        this.mainPane = new AnchorPane();
        this.mainScene = new Scene(this.mainPane, GameView.WIDTH, GameView.HEIGHT);
        this.mainStage = new Stage();
        this.mainStage.setScene(this.mainScene);
        this.mainStage.setTitle(this.TITLE);
        this.mainStage.setResizable(false);
        this.mainStage.sizeToScene();
        _oldStage.hide();
        mainStage.show();
        //Create cat and mouse objects
        this.createSprites();
        this.initializeTimer();

        BackgroundImage img = new BackgroundImage(new Image(GameView.BACKGROUND, 820, 800, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.mainPane.setBackground(new Background(img));
    }

    private void createSprites() {
        int maxHairballNum = 20;
        for(int counter = 0; counter < maxHairballNum; counter++) {
            Hairball hairball = new Hairball(this.mainPane);
        }

        int maxCheeseNum = 20;
        for(int counter = 0; counter < maxHairballNum; counter++) {
            Cheese cheese = new Cheese(this.mainPane);
        }

        this.mouse = new Mouse(this.mainPane);
        this.cat = new Cat(this.mainPane);
    }

    /**
     * This is the game loop. Anything that needs to happen on the cycle should be put inside the "handle."
     */
    private void initializeTimer() {
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.moveMouse(mouse);
                mouse.checkCollision(cat);
                //mouse.checkCollision(cat);
            }
        };
        this.timer.start();
    }

//=================  GETTERS ===============
    public Cat getCat() {
        return this.cat;
    }

    public Mouse getMouse() {
        return this.mouse;
    }

    public Scene getMainScene() {
        return this.mainScene;
    }
}
