package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase.FirebaseBuscar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        adapter = new ConsultarRecyclerViewAdapter();

        (new FirebaseBuscar() {
            @Override
            public void onSuccess() {
                adapter.setFilmes(getFilmes());
            }
        }).buscarAvaliacoes();

        recyclerView.setAdapter(adapter);
        return view;
    }

}
