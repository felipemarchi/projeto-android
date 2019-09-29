package f196695_v206681.ft.unicamp.br.aulas.puzzle;


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import f196695_v206681.ft.unicamp.br.aulas.R;

public class PuzzleFragment extends Fragment {

    LinearLayout view;
    EmptyPuzzle abstractPuzzle;

    public PuzzleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Jogo 1");
        if (view == null) {
            view = (LinearLayout) inflater.inflate(R.layout.fragment_puzzle, container, false);
            startPuzzle(0, view);
        }

        return view;
    }

    private void startPuzzle(int puzzle, LinearLayout view) {
        Board board = Boards.getPuzzle(puzzle);
        ArrayList<ImageView> imageViews = new ArrayList();

        for (int i = 0; i < board.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j< board.getNumLines(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(board.getWidth(), board.getHeight()));

                row.addView(imageView);
                imageViews.add(imageView);
            }
            view.addView(row);
        }

        abstractPuzzle = new EmptyPuzzle(board, imageViews);
        abstractPuzzle.setVibrationBridge(new EmptyPuzzle.VibrationBridge() {
            @Override
            public void requestVibration() {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(30);
            }
        });
    }

}
