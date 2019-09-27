package f196695_v206681.ft.unicamp.br.pos_creditos.gui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.fake_data.ResultadoBusca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    HomeRecyclerViewAdapter adapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Pós-créditos");

        if (view == null)
            view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new HomeRecyclerViewAdapter(new ArrayList(Arrays.asList(ResultadoBusca.ultimosLancamentos)));

        recyclerView.setAdapter(adapter);

        return view;
    }

}
