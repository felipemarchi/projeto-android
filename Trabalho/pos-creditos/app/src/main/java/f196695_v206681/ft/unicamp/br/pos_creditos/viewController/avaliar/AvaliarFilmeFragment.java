package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class AvaliarFilmeFragment extends Fragment {
    View view;

    public AvaliarFilmeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_avaliar_filme, container, false);
        return view;
    }

}
