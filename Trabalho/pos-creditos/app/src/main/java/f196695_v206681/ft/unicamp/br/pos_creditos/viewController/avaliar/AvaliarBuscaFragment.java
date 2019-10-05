package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.OmdbApi;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        adapter = new AvaliarBuscaRecyclerViewAdapter();
        adapter.setOnClickListener(new AvaliarBuscaRecyclerViewAdapter.SearchResultOnClickListener() {
            @Override
            public void abrirTelaAvaliacao(Filme filme) {
                ((MainActivity) getActivity()).mostrarAvaliarFilmeFragment(filme);
            }
        });

        recyclerView = view.findViewById(R.id.avaliar_busca_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        new OmdbApi() {

            @Override
            public void setAdapterFilmes() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(getFilmes() != null) {
                            adapter.setFilmes(getFilmes());
                        } else {
                            Toast.makeText(getContext(), "Nenhum resultado encontrado!", Toast.LENGTH_LONG).show();
                            ((MainActivity) getActivity()).goToPreviousFragment();
                        }
                    }
                });
            }
        }.buscarPorFilme(titulo, ano, indexTipo);

        return view;
    }

    public void carregarAtributos(String titulo, String ano, int indexTipo) {
        this.titulo = titulo;
        this.ano = ano;
        this.indexTipo = indexTipo;
    }

}
