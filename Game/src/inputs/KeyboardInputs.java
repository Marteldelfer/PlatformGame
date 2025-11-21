package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;

import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> gamePanel.setDirection(UP);
            case KeyEvent.VK_A -> gamePanel.setDirection(LEFT);
            case KeyEvent.VK_S -> gamePanel.setDirection(DOWN);
            case KeyEvent.VK_D -> gamePanel.setDirection(RIGHT);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.setMoving(false);
    }
}
