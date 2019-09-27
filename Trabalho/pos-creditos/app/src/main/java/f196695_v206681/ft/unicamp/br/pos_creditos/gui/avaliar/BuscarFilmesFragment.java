package f196695_v206681.ft.unicamp.br.pos_creditos.gui.avaliar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BuscarFilmesFragment extends Fragment {

    View view;
    String omdbKey = "ed9ff193";
    EditText editTextFilme;
    EditText editTextAno;

    public BuscarFilmesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Avaliar");

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_buscar_filmes, container, false);
            editTextFilme = view.findViewById(R.id.text_filme);
            editTextAno = view.findViewById(R.id.text_ano);

            view.findViewById(R.id.button_buscar).setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openSearchResultFragment();
                }
            });
        }
        return view;
    }

    private void openSearchResultFragment() {
        ((MainActivity) getActivity()).mostrarResultSearchFragment();
    }

}
