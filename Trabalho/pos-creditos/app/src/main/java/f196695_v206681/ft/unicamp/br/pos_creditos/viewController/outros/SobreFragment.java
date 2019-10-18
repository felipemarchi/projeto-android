package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SobreFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.sobre_titulo);

        if (view == null)
            view = inflater.inflate(R.layout.fragment_sobre, container, false);

        return view;
    }

}
