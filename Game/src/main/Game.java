package main;

public class Game implements Runnable {
    
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            if (System.nanoTime() - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = System.nanoTime();
                frames++;
            }
            if ((System.currentTimeMillis() - lastCheck) >= 1000) {
                System.out.println(frames);
                frames = 0;
                lastCheck = System.currentTimeMillis();
            }
        }
    }
    private void startGameLoop() {
        gameThread = new Thread(this);  
        gameThread.start();
    }
}
