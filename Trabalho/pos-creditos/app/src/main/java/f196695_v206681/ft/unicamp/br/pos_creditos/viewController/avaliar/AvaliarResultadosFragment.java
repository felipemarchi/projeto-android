package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.os.Bundle;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.OmdbApi;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AvaliarResultadosFragment extends Fragment {
    View view;
    TextView textViewTexto;
    TextView textViewSubtexto;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    AvaliarResultadosRecyclerViewAdapter adapter;

    String titulo;
    String ano;
    int indexTipo;

    public AvaliarResultadosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar_resultados, container, false);

            textViewTexto = view.findViewById(R.id.avaliar_resultados_texto);
            textViewSubtexto = view.findViewById(R.id.avaliar_resultados_subtexto);
            progressBar = view.findViewById(R.id.avaliacao_loading);
            recyclerView = view.findViewById(R.id.avaliar_resultados_recycler_view);
        }

        if (ano.equals(""))
            textViewTexto.setText("Busca por \"" + titulo + "\"");
        else
            textViewTexto.setText("Busca por \"" + titulo + " " + ano + "\"");
        textViewSubtexto.setText("");
        progressBar.setVisibility(View.VISIBLE);
        adapter = new AvaliarResultadosRecyclerViewAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new AvaliarResultadosRecyclerViewAdapter.SearchResultOnClickListener() {
            @Override
            public void abrirTelaAvaliacao(Filme filme) {
                ((MainActivity) getActivity()).mostrarAvaliarFilmeFragment(filme);
            }
        });

        new OmdbApi() {
            @Override
            public void setAdapterFilmes() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Retorno retorno = getRetorno();
                        if(retorno.Search != null) {
                            progressBar.setVisibility(View.GONE);
                            textViewSubtexto.setText(retorno.totalResults + " resultados");
                            adapter.setFilmes(retorno.Search);
                        } else {
                            Toast.makeText(getContext(), "Nenhum resultado encontrado!", Toast.LENGTH_LONG).show();
                            ((MainActivity) getActivity()).onBackPressed();
                        }
                    }
                });
            }

            @Override
            public void onFailure() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Sem conexão com internet!", Toast.LENGTH_LONG).show();
                        ((MainActivity) getActivity()).onBackPressed();
                    }
                });
            }
        }.buscarFilme(titulo, ano, indexTipo);

        return view;
    }

    public void carregarAtributos(String titulo, String ano, int indexTipo) {
        this.titulo = titulo;
        this.ano = ano;
        this.indexTipo = indexTipo;
    }

}
