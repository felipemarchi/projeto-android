package f196695_v206681.ft.unicamp.br.assista_me.navigation.cadastro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import f196695_v206681.ft.unicamp.br.assista_me.R;

public class BuscaFilme extends Fragment {
    View view;

    public BuscaFilme() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view =  inflater.inflate(R.layout.fragment_busca_filme, container, false);
        return view;
    }

}
