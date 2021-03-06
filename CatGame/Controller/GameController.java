package CatGame.Controller;

/**
 * This class will route the calls from the Game view.
 * Author(s): Greg Dwyer, Hasler Zuniga, Anthony Barrera
 * Last Updated: 4/27/20
 */

import CatGame.Events.EventCodes;
import CatGame.Models.CollisionChecker;
import CatGame.Models.CollisionTest;
import CatGame.Models.Input;
import CatGame.Models.KeyboardInput;
import CatGame.Models.WriteToTxt;
import CatGame.Sprite.Cat;
import CatGame.Sprite.Mouse;
import CatGame.ViewManagers.GameView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController {

    private final GameView VIEW;
    private final Stage MENUSTAGE;
    private final Input INPUT;
    private final CollisionChecker COLLISION_CHECKER;
    private final WriteToTxt WRITE;
    private final SocialMediaApiAdaptor SOCIAL;

    public GameController(Stage _menuStage) {
        this.MENUSTAGE = _menuStage;
        this.VIEW = new GameView(this, this.MENUSTAGE);
        this.INPUT = new KeyboardInput(this);
        this.COLLISION_CHECKER = new CollisionChecker(this, this.VIEW.getMouse());
        this.WRITE = new WriteToTxt();
        this.SOCIAL = new SocialMediaApiAdaptor(this);
    }

    /**
     * This method tells the view to put a collected cheese item back on the board.
     *
     * @param _cheese This is the cheese to be placed.
     */
    public void replaceCheese(Node _cheese) {
        this.VIEW.replaceCheese(_cheese);
    }

    /**
     * This method will handle the EndGame buttons action.
     *
     * @param _code
     */
    public void handle(int _code) {
        switch (_code) {
            case EventCodes.YES_POST_TO_SOCIAL_MEDIA:
                //This is where you would handle the user input with score and api interface
                try {
                    this.WRITE.writeTo(this.VIEW.getUserInput(), this.VIEW.getScore());
                    this.SOCIAL.writeToSocialMedia(this.VIEW.getUserInput(), this.VIEW.getScore());
                } catch (IOException ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case EventCodes.NO_POST_TO_SOCIAL_MEDIA:
                //This is where you would handle the user input with score
                try {
                    this.WRITE.writeTo(this.VIEW.getUserInput(), this.VIEW.getScore());
                } catch (IOException ex) {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.exitGame();
                break;
            case EventCodes.EXIT:
                this.exitGame();
                break;
        }
    }

    /**
     * This method tells the view that the player has run into an enemy.
     */
    public void enemyCollsion() {
        this.VIEW.enemyCollision();
    }

    /**
     * This method gets a list of all of the pane's children and checks for collisions.
     */
    public void checkCollisions() {
         this.COLLISION_CHECKER.checkCollisionsList(this.VIEW.getMainPane().getChildren());
    }

    /**
     * This method ends the game.
     */
    public void endSubscene() {
        this.VIEW.exitGame();
        this.getViewStage().close();
        this.MENUSTAGE.show();
    }

    /**
     * This method ends the game.
     */
    public void showSuccessfulPost(String _input) {
        this.VIEW.showEndGameSuccess(_input);
    }

    /**
     * This method returns the user to the main menu and closes the game stage. KNOWN BUG - All of the path transitions continue to run and play sound after the stage is closed.
     */
    public void exitGame() {
        this.VIEW.exitGame();
        this.getViewStage().close();
        this.MENUSTAGE.show();
    }

    /**
     * This method calls the on the mouse class, and hands over the input device to be checked.
     *
     * @param _mouse
     */
    public void moveMouse(Mouse _mouse) {
        _mouse.moveMouse(this.INPUT);
    }

    /**
     * This method ends the claw shooting timeline. This is called during the exit game protocol.
     *
     * @param _cat
     */
    public void endClaws(Cat _cat) {
        _cat.endTimeline();
    }

    /**
     * This method is used to run the CollisionTest methods.
     */
    public void runCollisionTest() {
        AnchorPane pane = new AnchorPane();
        Mouse mouse = new Mouse(pane);
        CollisionChecker testChecker = new CollisionChecker(this, mouse, true);
        testChecker.checkCollisionsList(CollisionTest.testNormalCases(pane, mouse));
        pane.getChildren().remove(0, pane.getChildren().size());
        testChecker.checkCollisionsList(CollisionTest.testEdgeCases(pane, mouse));
        pane.getChildren().remove(0, pane.getChildren().size());
        testChecker.checkCollisionsList(CollisionTest.testErrorCases(pane, mouse));
        testChecker = null;
        pane = null;
    }

//================GETTERS======================
    public Scene getViewScene() {
        return this.VIEW.getMainScene();
    }

    public AnchorPane getViewPane() {
        return this.VIEW.getMainPane();
    }

    public Stage getViewStage() {
        return this.VIEW.getMainStage();
    }
}
