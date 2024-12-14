import java.awt.event.ActionEvent;
import javax.swing.*;

public class Hop {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 800;
    private static final int DELAY = 40;

    private final JFrame frame;
    private final Field field;
    private final Axel axel;
    private Timer timer;
    private final GamePanel gamePanel;

    public Hop() {
        this.field = new Field(WIDTH, HEIGHT);
        this.axel = new Axel(field, WIDTH / 2, field.START_ALTITUDE);
        this.gamePanel = new GamePanel(field, axel);

        this.frame = new JFrame("Hop!");
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void round() {
        axel.update();
        field.update();
        gamePanel.updateScore();
        frame.repaint();
    }

    public boolean over() {
        return axel.getY() <= field.START_ALTITUDE; // Fin si Axel touche la lave
    }

    public static void main(String[] args) {
        Hop game = new Hop();

        game.timer = new Timer(DELAY, (ActionEvent e) -> {
            game.round();
            if (game.over()) {
                game.timer.stop();
                game.frame.remove(game.gamePanel);
            }
        });
        game.timer.start();
    }
}
