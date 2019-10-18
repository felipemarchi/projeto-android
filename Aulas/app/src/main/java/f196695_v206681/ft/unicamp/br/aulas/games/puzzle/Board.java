package f196695_v206681.ft.unicamp.br.aulas.games.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private ArrayList<Integer> gameIndex;
    private int[] dummyCell = new int[2];

    private int numLines;
    private int numColumns;
    private List<Integer> blocks;
    private int width;
    private int height;

    Board(int numLines, int numColumns, List blocks, int width, int height) {
        this.numLines = numLines;
        this.numColumns = numColumns;
        this.blocks = blocks;
        this.width = width;
        this.height = height;
        this.dummyCell = new int[2];
        gameIndex = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            gameIndex.add(i);
        }
        this.startGame();
    }

    public int getNumLines() {
        return numLines;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getDummyCell() {
        return dummyCell;
    }

    public void startGame() {
        Collections.shuffle(this.gameIndex);
        atualizaPosicaoDummy();
    }

    public void atualizaPosicaoDummy() {
        int dummyCell = getCorrectBlock(0,0);

        for (int i = 0; i < this.numLines; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                if (getGameBlock(i,j) == dummyCell) {
                    this.dummyCell[0] = i;
                    this.dummyCell[1] = j;
                }
            }
        }
        System.out.println("New dummy: (" + this.dummyCell[0] + "," + this.dummyCell[1] + ")");
    }

    public int getCorrectBlock(int line, int column) {
        int index = line*this.numColumns+column;
        return this.blocks.get(index);
    }

    public int getGameBlock(int line, int column) {
        int index = line*this.numColumns+column;
        return this.blocks.get(gameIndex.get(index));
    }

    public void swap(int line1, int column1, int line2, int column2) {
        System.out.println("Swapping (" + line1 + "," + column1 + ") with (" + line2 + "," + column2 + ")");
        int index1 =  line1*this.numColumns+column1;
        int index2 =  line2*this.numColumns+column2;

        Collections.swap(gameIndex,index1,index2);
    }
}
