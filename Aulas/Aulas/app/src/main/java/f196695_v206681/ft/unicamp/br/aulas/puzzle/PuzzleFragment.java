package f196695_v206681.ft.unicamp.br.aulas.puzzle;


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import f196695_v206681.ft.unicamp.br.aulas.R;

public class PuzzleFragment extends Fragment {

    View view;
    EmptyPuzzle puzzle;
    Spinner gridSpinner;

    public PuzzleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Jogo 1");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_puzzle, container, false);

            gridSpinner = view.findViewById(R.id.grid_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grid_options, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gridSpinner.setAdapter(adapter);

            view.findViewById(R.id.new_grid_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    LinearLayout gridLayout = view.findViewById(R.id.grid_layout);
                    gridLayout.removeAllViews();

                    if (gridSpinner.getSelectedItem().toString().equals("Gislaine")) {
                        startPuzzle(0, view);
                    } else {
                        startPuzzle(1, view);
                    }
                }
            });

            view.findViewById(R.id.visualize_grid_button).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        puzzle.drawCorrectBoard();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        puzzle.redraw();
                    }
                    return true;
                }
            });

            startPuzzle(0, view);
        }

        return view;
    }

    private void startPuzzle(int puzzle, View view) {
        Board board = Boards.getPuzzle(puzzle);
        ArrayList<ImageView> imageViews = new ArrayList();

        LinearLayout puzzleLayout = view.findViewById(R.id.grid_layout);

        for (int i = 0; i < board.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j< board.getNumColumns(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(board.getWidth(), board.getHeight()));

                row.addView(imageView);
                imageViews.add(imageView);
            }
            puzzleLayout.addView(row);
        }

        this.puzzle = new EmptyPuzzle(board, imageViews);
        this.puzzle.setPuzzleBridge(new EmptyPuzzle.PuzzleBridge() {
            @Override
            public void requestVibration(int ms) {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(ms);
            }

            @Override
            public void showWinPopup() {
                Toast.makeText(getContext(), "Você ganhou!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
