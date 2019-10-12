package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AvaliarFragment extends Fragment {

    View view;
    EditText editTextTitulo;
    EditText editTextAno;
    Spinner spinnerTipo;
    Button buttonBuscar;

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
            buttonBuscar = view.findViewById(R.id.avaliar_buscar);

            editTextTitulo.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {}
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        buttonBuscar.setEnabled(true);
                    }
                    else {
                        buttonBuscar.setEnabled(false);
                    }
                }
            });

            editTextTitulo.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) {
                        ((MainActivity) getActivity()).showKeyboard(editTextAno);
                    }
                    return true;
                }
            });

            buttonBuscar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).hideKeyboard();
                    String titulo = editTextTitulo.getText().toString();
                    String ano = editTextAno.getText().toString();
                    int indexTipo = spinnerTipo.getSelectedItemPosition();
                    ((MainActivity) getActivity()).mostrarAvaliarResultadosFragment(titulo, ano, indexTipo);
                }
            });
        }

        buttonBuscar.setEnabled(false);
        ((MainActivity) getActivity()).showKeyboard(editTextTitulo);
        return view;
    }

}
