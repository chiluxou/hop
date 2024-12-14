import java.util.ArrayList;

public class Axel {
    public static final double MAX_FALL_SPEED = -20;
    public static final double JUMP_SPEED = 20;
    public static final double GRAVITY = 1;
    public static final double DIVE_SPEED = 3 * GRAVITY;
    public static final double LATERAL_SPEED = 8;

    private int x, y;
    private double dx, dy;
    private boolean falling, jumping, diving;
    private boolean left, right;
    private final Field field;

    public static final int AXEL_WIDTH = 20;  // Largeur du personnage
    public static final int AXEL_HEIGHT = 20; // Hauteur du personnage

    public Axel(Field f, int x, int y) {
        this.field = f;
        this.x = x;
        this.y = y;
        this.dy = 0; // Initialiser dy pour éviter des problèmes au début
        this.falling = true;
    }

    public void update() {
        computeMove();
        checkCollision(field.getBlocksInView());
    }

    public void computeMove() {
        // Gestion du mouvement horizontal
        if (left) dx = -LATERAL_SPEED;
        else if (right) dx = LATERAL_SPEED;
        else dx = 0;

        // Gestion du saut
        if (jumping && dy == 0) {  // Si Axel est au sol et qu'il saute
            dy = JUMP_SPEED;
            jumping = false;
        } else if (dy > MAX_FALL_SPEED) {
            dy -= GRAVITY;  // Gravité
        }

        // Gestion de la descente rapide
        if (diving) {
            dy -= DIVE_SPEED;
        }

        // Mise à jour des coordonnées
        x += dx;
        y += dy;
    }

    public void checkCollision(ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (y >= block.getY() && y + dy <= block.getY() &&
                x + AXEL_WIDTH > block.getX() && x < block.getX() + block.getWidth()) {
                y = block.getY(); // Place Axel sur le bloc
                dy = 0; // Arrête le mouvement vertical
                return;
            }
        }
    }

    // Getters et setters
    public int getX() { return x; }
    public int getY() { return y; }

    public void setLeft(boolean left) { this.left = left; }
    public void setRight(boolean right) { this.right = right; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }
    public void setDiving(boolean diving) { this.diving = diving; }
}