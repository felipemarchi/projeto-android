package f196695_v206681.ft.unicamp.br.aulas.puzzle;

import android.widget.ImageView;

import java.util.List;

public abstract class AbstractPuzzle {
    protected Board board;
    protected List<ImageView> imageViewList;

    public AbstractPuzzle(Board board, List<ImageView> imageViewList) {
        this.board = board;
        this.imageViewList = imageViewList;

        startGame();
        redraw();

        for (int i = 0; i < this.board.getNumLines(); i++) {
            for (int j = 0; j < this.board.getNumColumns(); j++) {
                addListener(this.imageViewList.get(i * this.board.getNumColumns() + j), i, j);
            }
        }
    }

    public void startGame() {
        board.startGame();
    }

    protected void redraw() {
        for (int i = 0; i < board.getNumLines(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {

                int index = i * board.getNumColumns() + j;
                int imagem = board.getGameBlock(i, j);

                imageViewList.get(index).setImageResource(imagem);
            }
        }
    }

    public abstract void addListener(ImageView imageView, int line, int column);

    public abstract boolean endGame();
}
