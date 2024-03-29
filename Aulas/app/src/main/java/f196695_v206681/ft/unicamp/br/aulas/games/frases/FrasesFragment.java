package f196695_v206681.ft.unicamp.br.aulas.games.frases;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import f196695_v206681.ft.unicamp.br.aulas.R;

public class FrasesFragment extends Fragment {
    private View view;

    private RadioButton nomeCerto;

    private RadioGroup nomes;
    private RadioButton nome1;
    private RadioButton nome2;
    private RadioButton nome3;
    private RadioButton nome4;

    private TextView frase;
    private Button btnCheck;
    private Button btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_frases, container, false);
            nomes = view.findViewById(R.id.nomes);
            nome1 = view.findViewById(R.id.nome1);
            nome2 = view.findViewById(R.id.nome2);
            nome3 = view.findViewById(R.id.nome3);
            nome4 = view.findViewById(R.id.nome4);
            frase = view.findViewById(R.id.frase);

            new GetFrasesAsyncTask(FrasesFragment.this).execute();
        }

        btnCheck = view.findViewById(R.id.button_check);
        btnNext = view.findViewById(R.id.button_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomes.clearCheck();
                new GetFrasesAsyncTask(FrasesFragment.this).execute();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGame();
            }
        });

        return view;
    }

    private void checkGame() {
        btnCheck.setEnabled(false);

        if (nomeCerto.isChecked()) {
            Toast.makeText(getContext(), "Certo", Toast.LENGTH_LONG).show();
            new SavePlayWebClient(FrasesFragment.this).execute(nomeCerto.getText().toString(), "acerto");
        } else {
            Toast.makeText(getContext(), "Errado", Toast.LENGTH_LONG).show();
            new SavePlayWebClient(FrasesFragment.this).execute(nomeCerto.getText().toString(), "erro");
        }
    }

    public void newGame(JogoFrases jogo) {
        btnCheck.setEnabled(true);
        frase.setText(jogo.getFrase());

        List<String> opcoes = jogo.getNomes();
        opcoes.add(jogo.getNome());
        Collections.shuffle(opcoes);

        nome1.setText(opcoes.get(0));
        nome2.setText(opcoes.get(1));
        nome3.setText(opcoes.get(2));
        nome4.setText(opcoes.get(3));

        int index = opcoes.indexOf(jogo.getNome());

        switch (index) {
            case 0: nomeCerto = nome1;
                break;
            case 1: nomeCerto = nome2;
                break;
            case 2: nomeCerto = nome3;
                break;
            case 3: nomeCerto = nome4;
                break;
        }
    }

    public void enableCheckButton() {
        btnCheck.setEnabled(true);
    }
}
