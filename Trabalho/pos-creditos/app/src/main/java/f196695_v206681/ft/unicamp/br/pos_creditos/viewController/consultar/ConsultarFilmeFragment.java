package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase.FirebaseBuscar;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.FilmeAssistido;

public class ConsultarFilmeFragment extends Fragment {
    View view;
    ConsultarFilmeAdapter adapter;

    ImageView imagePoster;
    TextView textTitulo;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    String tituloSelecionado;
    public void setTituloSelecionado(String tituloSelecionado) { this.tituloSelecionado = tituloSelecionado; }

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
        (new FirebaseBuscar() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);

                List<FilmeAssistido> filmes = getFilmes();
                filmes.get(0).getSetBackdrop(imagePoster);
                textTitulo.setText(filmes.get(0).getTitle());

                adapter.setFilmes(filmes);
                adapter.ordenarFilmesPorData();
            }
        }).buscarFilmeAvaliado(tituloSelecionado);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
