package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;

public class AvaliarFilmeFragment extends Fragment {
    View view;
    TextView tituloFilme;
    Filme filme;

    public AvaliarFilmeFragment(Filme filme) {
        this.filme = filme;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar_filme, container, false);
            tituloFilme = view.findViewById(R.id.avaliar_filme_titulo);
            tituloFilme.setText(filme.getTitle());
        }
        return view;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
        tituloFilme.setText(filme.getTitle());
    }
}
