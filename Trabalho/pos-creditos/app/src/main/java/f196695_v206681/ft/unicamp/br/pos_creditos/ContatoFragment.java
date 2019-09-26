package f196695_v206681.ft.unicamp.br.pos_creditos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContatoFragment extends Fragment {

    View view;

    public ContatoFragment() {
        // Required empty public constructor
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
