package CatGame.Sprite;

/**
 * Abstract sprite class. Every movable object will have an image.
 *
 * @author Erika Sudderth, Greg Dwyer
 * Last updated: 4/9/20
 */

import javafx.scene.image.ImageView;

public abstract class Sprite {

    protected ImageView spriteImage;
    protected SpriteAnimation animation;

//=================  GETTERS ===============
    public ImageView getImage() {
        return this.spriteImage;
    }

//=================  SETTERS ===============
    public void setImage(String _imagePNG) {
        this.spriteImage = new ImageView(_imagePNG);
    }
}
