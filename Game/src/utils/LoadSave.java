package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";


    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        try (InputStream is = LoadSave.class.getResourceAsStream("/" + filename)) {
            if (is == null) {throw new FileNotFoundException("/" + filename);}
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return img;
    }

    public static int[][] getLevelData() {
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) value = 0;
                levelData[j][i] = value;
            }
        }
        return levelData;
    }
}
