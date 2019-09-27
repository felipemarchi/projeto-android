package f196695_v206681.ft.unicamp.br.pos_creditos.gui.consultar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.fake_data.FilmesAvaliados;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsultarFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ConsultarRecyclerViewAdapter adapter;

    public ConsultarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Consultar");

        if(view == null)
            view = inflater.inflate(R.layout.fragment_consultar, container, false);

        recyclerView = view.findViewById(R.id.consultar_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ConsultarRecyclerViewAdapter(new ArrayList(Arrays.asList(FilmesAvaliados.filmesAvaliados)));

        recyclerView.setAdapter(adapter);

        return view;
    }

}
