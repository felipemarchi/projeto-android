package f196695_v206681.ft.unicamp.br.aulas.puzzle;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class EmptyPuzzle extends AbstractPuzzle {
    public VibrationBridge vibrationBridge;

    public EmptyPuzzle(Board board, ArrayList<ImageView> imageViews) {
        super(board, imageViews);
    }

    public void addListener(ImageView imageView, final int line, final int column) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //requestVibration();
                int linhaDummy = board.getDummyCell()[0];
                int colunaDummy = board.getDummyCell()[1];

                System.out.println("--------\nClick in (" + line + "," + column + ")");
                System.out.println("Dummy in (" + linhaDummy+ "," + colunaDummy + ")");

                if (linhaDummy == line || colunaDummy == column) {
                    //mover dummy entre linhas
                    int diff = abs(linhaDummy - line);
                    if (vibrationBridge != null && diff > 0)
                        vibrationBridge.requestVibration();

                    for (int i = 0; i < diff; i++) {
                        if (linhaDummy > line) {
                            board.swap(linhaDummy, colunaDummy, linhaDummy - 1, colunaDummy);
                            linhaDummy--;
                        } else {
                            board.swap(linhaDummy, colunaDummy, linhaDummy + 1, colunaDummy);
                            linhaDummy++;
                        }
                    }

                    //mover dummy entre colunas
                    diff = abs(colunaDummy - column);
                    if (vibrationBridge != null && diff > 0)
                        vibrationBridge.requestVibration();

                    for (int j = 0; j < diff; j++) {
                        if (colunaDummy > column) {
                            board.swap(linhaDummy, colunaDummy, linhaDummy, colunaDummy-1);
                            colunaDummy--;
                        } else {
                            board.swap(linhaDummy, colunaDummy, linhaDummy, colunaDummy+1);
                            colunaDummy++;
                        }
                    }

                    board.atualizaPosicaoDummy();

                    redraw();
                }
            }
        });
    }

    public boolean endGame() {
        for (int i = 0; i < this.board.getNumLines(); i++) {
            for (int j = 0; j < this.board.getNumColumns(); j++) {
                if (board.getGameBlock(i, j) != board.getCorrectBlock(i, j))
                    return false;
            }
        }
        return true;
    }

    public void setVibrationBridge(VibrationBridge vibrationBridge) {
        this.vibrationBridge = vibrationBridge;
    }

    public interface VibrationBridge {
        public void requestVibration();
    }
}