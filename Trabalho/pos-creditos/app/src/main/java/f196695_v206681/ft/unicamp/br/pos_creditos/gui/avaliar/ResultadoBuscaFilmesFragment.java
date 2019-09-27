package f196695_v206681.ft.unicamp.br.pos_creditos.gui.avaliar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.fake_data.ResultadoBusca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultadoBuscaFilmesFragment extends Fragment {
    View view;
    ResultadoBuscaFilmesRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    public ResultadoBuscaFilmesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_resultado_busca, container, false);

        recyclerView = view.findViewById(R.id.resultado_busca_filmes_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ResultadoBuscaFilmesRecyclerViewAdapter(new ArrayList(Arrays.asList(ResultadoBusca.buscaFilmesResultado)));
        adapter.setOnClickListener(new ResultadoBuscaFilmesRecyclerViewAdapter.SearchResultOnClickListener() {
            @Override
            public void abrirTelaAvaliacao() {
                ((MainActivity) getActivity()).mostrarAvaliacaoFragment();
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

}
