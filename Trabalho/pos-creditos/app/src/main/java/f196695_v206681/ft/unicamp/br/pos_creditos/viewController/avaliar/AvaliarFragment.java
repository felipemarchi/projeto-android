package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AvaliarFragment extends Fragment {

    View view;
    EditText editTextTitulo;
    EditText editTextAno;
    Spinner spinnerTipo;

    public AvaliarFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Avaliar");

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar, container, false);
            editTextTitulo = view.findViewById(R.id.avaliar_titulo);
            editTextAno = view.findViewById(R.id.avaliar_ano);
            spinnerTipo = view.findViewById(R.id.avaliar_tipo);

            view.findViewById(R.id.avaliar_buscar).setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titulo = editTextTitulo.getText().toString();
                    String ano = editTextAno.getText().toString();
                    int indexTipo = spinnerTipo.getSelectedItemPosition();
                    ((MainActivity) getActivity()).mostrarAvaliarBuscaFragment(titulo, ano, indexTipo);
                }
            });
        }

        return view;
    }

}
