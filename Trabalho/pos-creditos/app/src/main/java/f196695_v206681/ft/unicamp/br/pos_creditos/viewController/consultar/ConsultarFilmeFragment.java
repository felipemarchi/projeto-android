package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;

public class ConsultarFilmeFragment extends Fragment {
    View view;
    ConsultarFilmeAdapter adapter;

    ImageView imagePoster;
    TextView textTitulo;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    String tituloSelecionado;

    public void setTituloSelecionado(String tituloSelecionado) {
        this.tituloSelecionado = tituloSelecionado;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.consultar_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_consultar_filme, container, false);

            imagePoster = view.findViewById(R.id.consultar_filme_poster);
            textTitulo = view.findViewById(R.id.consultar_filme_titulo);
            recyclerView = view.findViewById(R.id.consultar_filme_recycler_view);
            progressBar = view.findViewById(R.id.consultar_filme_loading);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        adapter = new ConsultarFilmeAdapter();
        progressBar.setVisibility(View.GONE);

        List<Filme> filmes = new ArrayList<>();
        for (Filme filme : ((MainActivity) getActivity()).getAvaliacoes()) {
            if (filme.getTitle().equals(tituloSelecionado)) {
                filmes.add(filme);
            }
        }

        filmes.get(0).setBackdropImage(imagePoster);
        textTitulo.setText(filmes.get(0).getTitle());

        adapter.setFilmes(filmes);
        adapter.ordenarFilmesPorData();
        recyclerView.setAdapter(adapter);

        return view;
    }
}
