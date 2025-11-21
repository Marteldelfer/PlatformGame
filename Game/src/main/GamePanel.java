package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        importImage();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    private void importImage() {
        try (InputStream is = getClass().getResourceAsStream("/player_sprites.png")) {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());;
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        setMoving(true);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        updateAnimationTick();

        setAnimation();
        updatePosition();

        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
        Toolkit.getDefaultToolkit().sync();
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case RIGHT -> xDelta += 5;
                case UP -> yDelta -= 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            if (playerAction != RUNNING) {
                aniIndex = 0;
            }
            playerAction = RUNNING;
        } else {
            if (playerAction != IDLE) {
                aniIndex = 0;
            }
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }
}
