package f196695_v206681.ft.unicamp.br.pos_creditos.gui.outros;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContatoFragment extends Fragment {

    View view;

    public ContatoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Contato");

        if (view == null)
            view = inflater.inflate(R.layout.fragment_contato, container, false);

        return view;
    }

}
