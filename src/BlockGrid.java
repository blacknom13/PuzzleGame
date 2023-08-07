import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class BlockGrid {

    final int a = 255;
    private Block[][] grid;
    private final Color[] COLORS = {new Color(255, 0, 0, a),
            new Color(0, 255, 0, a), new Color(0, 0, 255, a),
            new Color(0, 255, 255, a), new Color(255, 0, 255, a),
            new Color(255, 255, 0, a), new Color(0, 0, 150, a),
            new Color(255, 73, 150, a), new Color(255, 128, 64, a)};
    private int destroyed;
    private int moves;
    private int score;
    private boolean dropped;
    private int level;
    private int size;
    private int bombX, bombY;
    private int transX, transY;
    private boolean thereIsBomb;
    private boolean thereIsTrans;

    // public static Color [] chosen;
    public BlockGrid() {

    }

    public BlockGrid(int size, int level) {
        // TODO Auto-generated constructor stub
        this.moves = 0;
        this.destroyed = 0;
        this.score = 0;
        this.dropped = false;
        this.size = size;
        this.level = level;
        this.thereIsBomb = false;
        this.thereIsTrans = false;
        Random r = new Random(System.currentTimeMillis());
        grid = new Block[size][size];
        // chosen=new Color[level *3];
        // chosen=Arrays.copyOfRange(chosen, 0, level*3);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Block(false, COLORS[r.nextInt(level * 3)]);
            }
        }
        // for (int i = 0; i < size; i++) {
        // for (int j = 0; j < size; j++) {
        // System.out.print("[" + grid[i][j].getColor().getRed() + ","
        // + grid[i][j].getColor().getGreen() + ","
        // + grid[i][j].getColor().getBlue() + "]  ");
        // }
        // System.out.println();
        // }
        // System.out.println("*******************************");
        // translate();
        // translate();
        // for (int i = 0; i < size; i++) {
        // for (int j = 0; j < size; j++) {
        // System.out.print("["+grid[i][j].getColor().getRed()+","+
        // grid[i][j].getColor().getGreen()+","+
        // grid[i][j].getColor().getBlue()+"]  ");
        // }
        // System.out.println();
        // }

    }

    public Vector<Block> newVector() {
        Random r = new Random(System.nanoTime());
        Vector<Block> temp = new Vector<>();
        for (int i = 0; i < grid.length; i++) {
            temp.add(new Block(false, COLORS[r.nextInt(level * 3)]));
        }
        for (int i = 0; i < grid.length; i++) {
            temp.elementAt(i).setDropping(5);
        }
        return temp;
    }

    public void remove(int x, int y, boolean first) {
        int k = 0;
        if (!grid[x][y].isVisited()) {
            // System.out.println("removed row: "+x+",coloumn: "+y);
            grid[x][y].setVisited(true);
            if (x + 1 < grid.length
                    && !grid[x + 1][y].isVisited()
                    && (grid[x + 1][y].getColor().equals(grid[x][y].getColor()) || grid[x + 1][y].isTransparent())
                    && !grid[x + 1][y].isBomb()) {
                if (grid[x + 1][y].isTransparent()) {
                    grid[x + 1][y].setColor(grid[x][y].getColor());
                    grid[x + 1][y].setTransparent(false);
                    this.thereIsTrans = false;
                }
                remove(x + 1, y, false);
                k++;
            }
            if (x - 1 > -1
                    && !grid[x - 1][y].isVisited()
                    && (grid[x - 1][y].getColor().equals(grid[x][y].getColor()) || grid[x - 1][y].isTransparent())
                    && !grid[x - 1][y].isBomb()) {
                if (grid[x - 1][y].isTransparent()) {
                    grid[x - 1][y].setColor(grid[x][y].getColor());
                    grid[x - 1][y].setTransparent(false);
                    this.thereIsTrans = false;
                }
                remove(x - 1, y, false);
                k++;
            }
            if (y + 1 < grid.length
                    && !grid[x][y + 1].isVisited()
                    && (grid[x][y + 1].getColor().equals(grid[x][y].getColor()) || grid[x][y + 1].isTransparent())
                    && !grid[x][y + 1].isBomb()) {
                if (grid[x][y + 1].isTransparent()) {
                    grid[x][y + 1].setColor(grid[x][y].getColor());
                    grid[x][y + 1].setTransparent(false);
                    this.thereIsTrans = false;
                }
                remove(x, y + 1, false);
                k++;
            }
            if (y - 1 > -1
                    && !grid[x][y - 1].isVisited()
                    && (grid[x][y - 1].getColor().equals(grid[x][y].getColor()) || grid[x][y - 1].isTransparent())
                    && !grid[x][y - 1].isBomb()) {
                if (grid[x][y - 1].isTransparent()) {
                    grid[x][y - 1].setColor(grid[x][y].getColor());
                    grid[x][y - 1].setTransparent(false);
                    this.thereIsTrans = false;
                }
                remove(x, y - 1, false);
                k++;
            }
            if (first && k < 1)
                grid[x][y].setVisited(false);
            else
                destroyed++;
        }

    }

    public void remove(int x, int y) {
        int upx, upy, downx, downy;
        // x+1>grid.length?upx=x:upx=x+1;
        if (x + 1 < grid.length)
            upx = x + 1;
        else
            upx = x;
        if (x - 1 > -1)
            downx = x - 1;
        else
            downx = x;

        if (y + 1 < grid.length)
            upy = y + 1;
        else
            upy = y;
        if (y - 1 > -1)
            downy = y - 1;
        else
            downy = y;
        for (int i = downx; i < upx + 1; i++)
            for (int j = downy; j < upy + 1; j++)
                if (!grid[i][j].isVisited()) {
                    destroyed++;
                    grid[i][j].removeStates();
                    this.thereIsBomb = false;
                }
    }

    public void dropping() {
        moves++;
        int x = grid.length;
        int y = grid[0].length;
        boolean firstDrop = true;
        int dropping;
        // int swap;

        Vector<Block> tempVisited = new Vector<>();
        Vector<Block> tempNot = new Vector<>();
        // Block [] temp2=new Block [y];
        for (int i = 0; i < y; i++) {
            dropping = 0;
            // swap=0;
            for (int j = x - 1; j >= 0; j--) {
                if (grid[j][i].isVisited()) {
                    tempVisited.insertElementAt(grid[j][i], 0);
                    dropping++;
                } else {
                    grid[j][i].setDropping(dropping);
                    tempNot.insertElementAt(grid[j][i], 0);
                    if (dropping > 0 && firstDrop) {
                        firstDrop = false;
                        dropped = true;
                    }
                }
            }

            // tempNot.
            translate();
            if (tempVisited.size() == grid.length) {
                tempVisited = newVector();
                dropped = true;
            }
            tempVisited.addAll(tempNot);
            tempVisited.toArray(grid[i]);
            translate();
            locateSpecialBlocks();
            tempVisited.clear();
            tempNot.clear();
            // clearDroppings();
            // System.out.println(temp.size());
        }
    }

    void translate() {
        Block[][] gridTemp = new Block[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                gridTemp[i][j] = new Block(grid[j][i]);
            }
        grid = gridTemp;
    }

    public void spawnBomb() {
        Random r = new Random(System.currentTimeMillis());
        int y = r.nextInt(grid[0].length);
        int top = firstNonEmptyCellInColumn(y);
        int x = r.nextInt(grid.length - top) + top;
//        System.out.println("Bomb spawned at x: " + x + ",y: " + y);
        grid[x][y].setBomb(true);
        bombX = x;
        bombY = y;
        this.thereIsBomb = true;
    }

    private int firstNonEmptyCellInColumn(int y) {
        for (int i = 0; i < grid.length; i++) {
            if (!grid[i][y].isVisited())
                return i;
        }
        return -1;
    }

    public void spawnTransparent() {
        Random r = new Random(System.currentTimeMillis());
        int y = r.nextInt(grid[0].length);
        int top = firstNonEmptyCellInColumn(y);
        int x = r.nextInt(grid.length - top) + top;
//        System.out.println("Transparent spawned at x: " + x + ",y: " +
//                y);
        grid[x][y].setTransparent(true);
        transX = x;
        transY = y;
        this.thereIsTrans = true;
    }

    void clearDroppings() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setDropping(0);
            }

        }
    }

    void locateSpecialBlocks() {
        if (thereIsBomb || thereIsTrans) {
            boolean flag = true;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j].isBomb()) {
                        bombX = i;
                        bombY = j;
                        if (!flag)
                            break;
                        else
                            flag = false;
                    }
                    if (grid[i][j].isTransparent()) {
                        transX = i;
                        transY = j;
                        if (!flag)
                            break;
                        else
                            flag = false;
                    }
                }
                if (!flag)
                    break;
            }
        }
    }

    public void printTheGrid() {
        for (Block[] blockLine : grid) {
            for (Block block : blockLine) {
                System.out.print(block.isVisited() + ", ");
            }
            System.out.println();
        }
    }

    public Block[][] getGrid() {
        return grid;
    }

    public void setGrid(Block[][] grid) {
        this.grid = grid;
    }

    public int getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(int destroyed) {
        this.destroyed = destroyed;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDropped() {
        return dropped;
    }

    public void setDropped(boolean dropped) {
        this.dropped = dropped;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBombX() {
        return bombX;
    }

    public void setBombX(int bombX) {
        this.bombX = bombX;
    }

    public int getBombY() {
        return bombY;
    }

    public void setBombY(int bombY) {
        this.bombY = bombY;
    }

    public int getTransX() {
        return transX;
    }

    public void setTransX(int transX) {
        this.transX = transX;
    }

    public int getTransY() {
        return transY;
    }

    public void setTransY(int transY) {
        this.transY = transY;
    }

}
