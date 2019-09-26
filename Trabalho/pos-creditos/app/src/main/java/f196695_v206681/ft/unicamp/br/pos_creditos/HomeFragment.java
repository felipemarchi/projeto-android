package f196695_v206681.ft.unicamp.br.pos_creditos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Pós-créditos");

        if (view == null)
            view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

}
