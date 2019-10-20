package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

public class AvaliarResultadosFragment extends Fragment {
    View view;
    OmdbApi omdbApi;
    MainActivity mainActivity;
    AvaliarResultadosAdapter adapter;

    TextView textViewTexto;
    TextView textViewSubtexto;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    String titulo;
    String ano;
    int indexTipo;
    int pagina;
    int total;

    public void carregarAtributos(Bundle arguments) {
        this.titulo = arguments.getString("titulo");
        this.ano = arguments.getString("ano");
        this.indexTipo = Integer.parseInt(arguments.getString("indexTipo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.avaliar_resultados_titulo);
        carregarAtributos(getArguments());

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar_resultados, container, false);
            mainActivity = (MainActivity) getActivity();
            pagina = 1;

            textViewTexto = view.findViewById(R.id.avaliar_resultados_texto);
            textViewSubtexto = view.findViewById(R.id.avaliar_resultados_subtexto);
            progressBar = view.findViewById(R.id.avaliar_resultados_loading);
            recyclerView = view.findViewById(R.id.avaliar_resultados_recycler_view);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(1) && (total > pagina*10)) {
                        progressBar.setVisibility(View.VISIBLE);
                        omdbApi.buscarFilme(titulo, ano, indexTipo, ++pagina);
                    }
                }
            });

            omdbApi = new OmdbApi() {
                @Override
                public void onSuccess() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Retorno retorno = getRetorno();
                            progressBar.setVisibility(View.GONE);
                            total = Integer.parseInt(retorno.totalResults);
                            if(retorno.Search != null) {
                                textViewSubtexto.setText(total + " " + getString(R.string.avaliar_resultados_sub));
                                adapter.setFilmes(retorno.Search);
                            } else {
                                mainActivity.showToast(getString(R.string.avaliar_resultados_null));
                                mainActivity.onBackPressed();
                            }
                        }
                    });
                }
                @Override
                public void onFailure() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.showToast(getString(R.string.avaliar_resultados_internet));
                            mainActivity.onBackPressed();
                        }
                    });
                }
            };
        }

        String texto = getString(R.string.avaliar_resultados_texto) + " \"" + titulo;
        texto += ano.equals("") ? "\"" : " " + ano + "\"";
        textViewTexto.setText(texto);
        textViewSubtexto.setText("");
        progressBar.setVisibility(View.VISIBLE);

        adapter = new AvaliarResultadosAdapter();
        adapter.setOnClickListener(new AvaliarResultadosAdapter.SearchResultOnClickListener() {
            @Override
            public void onClick(Filme filme) {
                mainActivity.redirectToAvaliarFilme(filme);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        omdbApi.buscarFilme(titulo, ano, indexTipo, pagina);

        return view;
    }
}
