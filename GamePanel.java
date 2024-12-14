import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {
    private static final int BLOCK_HEIGHT = 10;
    private static final int AXEL_WIDTH = 20;
    private static final int AXEL_HEIGHT = 20;

    private final Axel axel;
    private final Field field;
    private int score = 0;

    public GamePanel(Field field, Axel axel) {
        this.field = field;
        this.axel = axel;
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlocks(g);
        drawAxel(g);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    private void drawBlocks(Graphics g) {
        g.setColor(Color.BLACK);
        ArrayList<Block> visibleBlocks = field.getBlocksInView();
        for (Block block : visibleBlocks) {
            int x = block.getX();
            int y = getHeight() - (block.getY() - field.START_ALTITUDE); // Ajustement de l'altitude
            int width = block.getWidth();
            g.fillRect(x, y, width, BLOCK_HEIGHT);
        }
    }

    private void drawAxel(Graphics g) {
        g.setColor(Color.BLACK);
        int x = axel.getX();
        int y = getHeight() - (axel.getY() - field.START_ALTITUDE); // Ajustement de l'altitude
        g.fillOval(x, y, AXEL_WIDTH, AXEL_HEIGHT);
    }

    public void updateScore() {
        score = Math.max(score, axel.getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: axel.setLeft(true); break;
            case KeyEvent.VK_RIGHT: axel.setRight(true); break;
            case KeyEvent.VK_UP: axel.setJumping(true); break;
            case KeyEvent.VK_DOWN: axel.setDiving(true); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: axel.setLeft(false); break;
            case KeyEvent.VK_RIGHT: axel.setRight(false); break;
            case KeyEvent.VK_UP: axel.setJumping(false); break;
            case KeyEvent.VK_DOWN: axel.setDiving(false); break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
