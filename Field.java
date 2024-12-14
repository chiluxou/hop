import java.util.ArrayList;

public class Field {
    public int ALTITUDE_GAP = 80;
    public int START_ALTITUDE = 40;
    public final int width, height;

    private int bottom, top;
    private ArrayList<Block> blocks;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.blocks = new ArrayList<>();
        this.bottom = START_ALTITUDE;
        this.top = START_ALTITUDE + height;
        generateInitialBlocks();
    }

    private void generateInitialBlocks() {
        int altitude = START_ALTITUDE;
        while (altitude < height) {
            blocks.add(Block.randomBlock(altitude, width, width / 4));
            altitude += ALTITUDE_GAP;
        }
    }

    public ArrayList<Block> getBlocksInView() {
        ArrayList<Block> visibleBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getY() >= bottom && block.getY() <= top) {
                visibleBlocks.add(block);
            }
        }
        return visibleBlocks;
    }

    public void update() {
        bottom += ALTITUDE_GAP / 10;
        blocks.removeIf(block -> block.getY() < bottom);

        while (blocks.get(blocks.size() - 1).getY() < top) {
            blocks.add(Block.randomBlock(top + ALTITUDE_GAP, width, width / 4));
            top += ALTITUDE_GAP;
        }
    }

    public void adjustDifficulty(int score) {
        if (score > 1000) ALTITUDE_GAP = 60;
        if (score > 2000) ALTITUDE_GAP = 40;
    }
}
