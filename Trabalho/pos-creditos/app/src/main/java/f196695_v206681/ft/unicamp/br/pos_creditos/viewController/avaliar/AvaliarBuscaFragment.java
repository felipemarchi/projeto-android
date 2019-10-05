package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.FuncoesAPI;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AvaliarBuscaFragment extends Fragment {
    View view;
    AvaliarBuscaRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    String titulo;
    String ano;
    int indexTipo;

    public AvaliarBuscaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_avaliar_busca, container, false);

        Filme[] filmes;
        Retorno retornoBuscaPorFilme = FuncoesAPI.buscarPorFilme(titulo, ano, indexTipo);
        if (retornoBuscaPorFilme != null)
            filmes = retornoBuscaPorFilme.Search;
        else
            filmes = new Filme[]{};

        adapter = new AvaliarBuscaRecyclerViewAdapter(new ArrayList(Arrays.asList(filmes)));
        adapter.setOnClickListener(new AvaliarBuscaRecyclerViewAdapter.SearchResultOnClickListener() {
            @Override
            public void abrirTelaAvaliacao() {
                ((MainActivity) getActivity()).mostrarAvaliarFilmeFragment();
            }
        });

        recyclerView = view.findViewById(R.id.avaliar_busca_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void carregarAtributos(String titulo, String ano, int indexTipo) {
        this.titulo = titulo;
        this.ano = ano;
        this.indexTipo = indexTipo;
    }

}
