package ui.pages.tutorialGame;

public class ArrowButton extends AbstractNavButton {

    public ArrowButton(String normalPath, String hoverPath, String clickPath, int width, int height) {
        super(normalPath, hoverPath, clickPath, width, height);
    }

    @Override
    public void setClickAction(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    @Override
    public void setButtonEnabled(boolean enabled) {
        this.buttonEnabled = enabled;

        if (!enabled) {
            setIcon(normalIcon);
            setEnabled(false);
        } else {
            setEnabled(true);
            setIcon(mouseInside ? hoverIcon : normalIcon);
        }
    }
}