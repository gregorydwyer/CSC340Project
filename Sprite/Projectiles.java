package Sprite;

/**
 * Projectile class (for claws) that extends the Sprite class. Each object will
 * have a Boolean value to determine if it has been thrown or not.
 *
 * @author Erika Sudderth
 * Last updated: 3/19/20
 */

public class Projectiles extends Sprite {

    protected Boolean isThrown;

    /**
     * Constructor to use the abstract Sprite class methods with sprite location
     * and size.
     *
     * @param _initialSpriteXValue
     * @param _initialSpriteYValue
     * @param _initialSpriteWidth
     * @param _initialSpriteHeight
     * @param _spriteImage
     */
    public Projectiles(int _initialSpriteXValue, int _initialSpriteYValue, int _initialSpriteWidth, int _initialSpriteHeight, String _spriteImage) {
        super(_initialSpriteXValue, _initialSpriteYValue, _initialSpriteWidth, _initialSpriteHeight, _spriteImage);
    }

//=================  GETTERS ===============
    public Boolean getIsThrown() {
        return this.isThrown;
    }

//=================  SETTERS ===============
    public void setIsThrown(Boolean _isThrown) {
        this.isThrown = _isThrown;
    }
}