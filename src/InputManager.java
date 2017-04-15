/**
 * Created by zTitiK on 13-Apr-17.
 */
public class InputManager {
    private boolean isRightPressed;
    private boolean isLeftPressed;
    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isSpacePressed;

    public InputManager() {
    }

    public void setRightPressed(boolean rightPressed) {
        isRightPressed = rightPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        isLeftPressed = leftPressed;
    }

    public void setUpPressed(boolean upPressed) {
        isUpPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        isDownPressed = downPressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        isSpacePressed = spacePressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isUpPressed() {
        return isUpPressed;
    }

    public boolean isDownPressed() {
        return isDownPressed;
    }

    public boolean isSpacePressed() {
        return isSpacePressed;
    }
}
