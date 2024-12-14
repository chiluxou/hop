import java.util.Random;

public class Block {
    private final int x, y;
    private final int width;

    public Block(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public static Block randomBlock(int altitude, int maxWidth, int maxBlockWidth) {
        Random random = new Random();
        int blockWidth = random.nextInt(maxBlockWidth) + maxBlockWidth / 2;
        int x = random.nextInt(maxWidth - blockWidth);
        return new Block(x, altitude, blockWidth);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
}
