package f196695_v206681.ft.unicamp.br.aula03.alunos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import f196695_v206681.ft.unicamp.br.aula03.R;

public class AlunosFragment extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    MyFirstAdapter mAdapter;


    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_alunos, container, false);
        }

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));
        mAdapter.setMyOnItemClickListener(
                new MyFirstAdapter.MyOnItemClickListener() {
                    @Override
                    public void mostrarNomeAluno(String nome) {
                        Toast.makeText(getActivity(), nome, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

}
