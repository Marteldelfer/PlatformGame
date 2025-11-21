package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        importImage();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
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

    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null);
    }
}
