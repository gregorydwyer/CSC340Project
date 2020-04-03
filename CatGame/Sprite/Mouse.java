package CatGame.Sprite;

/**
 * Mouse class that extends abstract Sprite class. Initializes the mouse's list of cheese objects it holds.
 *
 * @author Erika Sudderth, Greg Dwyer Last updated: 3/31/20
 */
import CatGame.Models.Input;
import CatGame.ViewManagers.GameView;
import CatGame.Sprite.Cat;
import CatGame.ViewManagers.ViewManager;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import CatGame.Models.CollisionObjects;

public class Mouse extends Sprite {

    protected ArrayList<Cheese> cheeseList;
    private final String IMAGE = "/resources/imgs/mouse_sprite_map.png";
    private final int DIMENSIONS = 32;
    private final int SPRITE_COLUMNS = 4;
    private final int FRAME_COUNT = 4;
    private final Duration FRAME_DURATION = Duration.millis(500);
    private final int OFFSET = 0;
    private final double STARTING_X = (ViewManager.getWidth() / 2) - (this.DIMENSIONS / 2);
    private final double STARTING_Y = ViewManager.getHeight() - (this.DIMENSIONS);
    private final int MOUSE_SPEED = 5;
//    private final Group animationGroup;
    private GameView game;

    private final int x_pos;
    private final int y_pos;
    private CollisionObjects collision;
    private Cat cat;

    public Mouse(AnchorPane _pane) {
        this.setAnimationFields();
        this.x_pos = (int) this.STARTING_X;
        this.y_pos = (int) this.STARTING_Y;

        this.initialPos();
        this.animationGroup = new Group(this.spriteImage);
        _pane.getChildren().add(animationGroup);
    }

    /**
     * Use these to update the mouse's location in the X axis.
     *
     * @param _distance This is the mouse's movement speed.
     */
    public void moveXLeft() {
        if (this.animationGroup.getLayoutX() >= -(ViewManager.getWidth() / 2)) {
            this.animationGroup.setLayoutX(this.animationGroup.getLayoutX() - this.MOUSE_SPEED);
        }
    }

    public void moveXRight() {
        if (this.animationGroup.getLayoutX() <= (ViewManager.getWidth() / 2)) {
            this.animationGroup.setLayoutX(this.animationGroup.getLayoutX() + this.MOUSE_SPEED);
        }

    }

    /**
     * Use these to update the mouse's location in the X axis.
     *
     * @param _distance This is the mouse's movement speed.
     */
    public void moveYUp() {
        if (this.animationGroup.getLayoutY() >= -ViewManager.getHeight() + (3 * this.DIMENSIONS)) {
            this.animationGroup.setLayoutY(this.animationGroup.getLayoutY() - this.MOUSE_SPEED);
        }
    }

    public void moveYDown() {
        if (this.animationGroup.getLayoutY() <= 0) {
            this.animationGroup.setLayoutY(this.animationGroup.getLayoutY() + this.MOUSE_SPEED);
        }
    }

    /**
     * Method that calls the moveY and moveX methods in response to the key event. Continues movement as long as the key is pressed.
     */
    public void moveMouse(Input _input) {
        if (_input.isUp()) {
            this.moveYUp();
        }
        if (_input.isDown()) {
            this.moveYDown();
        }
        if (_input.isLeft()) {
            this.moveXLeft();
        }
        if (_input.isRight()) {
            this.moveXRight();
        }

    }
//    This method will check collision by intersection.

    public void collision(Sprite animation) {
//        if(animation.getBounds().intersects(getBounds())){
//           System.out.println("Collided");
//       }

        collision = new CollisionObjects(getX(), getY());
        if (collision.collisionCheck(animation)) {
            System.out.println("Collided with Cat!");
        }
        System.out.println(cat.spriteImage.getLayoutX());
    }

    /**
     * This method can be used to set an explicit location for the sprite.
     */
    private void initialPos() {
        this.spriteImage.setLayoutX(this.x_pos);
        this.spriteImage.setLayoutY(this.y_pos);
    }

    /**
     * This method sets up the animation for the sprite.
     */
    private void setAnimationFields() {
        this.spriteImage = new ImageView(this.IMAGE);
        this.animation = new SpriteAnimation(this.spriteImage, this.FRAME_DURATION, this.FRAME_COUNT, this.SPRITE_COLUMNS, this.OFFSET, this.OFFSET, this.DIMENSIONS, this.DIMENSIONS);
        this.spriteImage.setViewport(new Rectangle2D(this.OFFSET, this.OFFSET, this.DIMENSIONS, this.DIMENSIONS));
        this.animation.setCycleCount(Animation.INDEFINITE);
        this.animation.play();
    }

//=================  GETTERS ===============
    public ArrayList<Cheese> getCheeseObjList() {
        return this.cheeseList;
    }

    public Group getAnimationGroup() {
        return animationGroup;
    }

    public int getX() {
        return (int) getAnimationGroup().getLayoutX();
    }

    public int getY() {
        return (int) getAnimationGroup().getLayoutY();
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(getX(), getY(), this.DIMENSIONS, this.DIMENSIONS);
    }

//=================  SETTERS ===============
    public void setProjectileObjList(ArrayList<Cheese> _cheeseObjList) {
        this.cheeseList = _cheeseObjList;
    }

}
