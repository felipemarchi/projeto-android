package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;

public class DetalhesFragment extends Fragment {

    View view;
    private Filme filme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.detalhes_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_detalhes, container, false);
        }

        return view;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
