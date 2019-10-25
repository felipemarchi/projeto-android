package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AvaliarFragment extends Fragment {
    View view;
    MainActivity mainActivity;

    EditText editTextTitulo;
    EditText editTextAno;
    Spinner spinnerTipo;
    Button buttonBuscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.avaliar_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar, container, false);
            mainActivity = (MainActivity) getActivity();

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
                        buttonBuscar.setBackgroundTintList(getResources().getColorStateList(R.color.colorAccent));
                    }
                    else {
                        buttonBuscar.setEnabled(false);
                        buttonBuscar.setBackgroundTintList(getResources().getColorStateList(R.color.common_google_signin_btn_text_light_disabled));
                    }
                }
            });

            editTextAno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!editTextTitulo.getText().toString().isEmpty()) {
                        openResultsFragment();
                    }
                }
            });

            buttonBuscar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openResultsFragment();
                }
            });
        }

        buttonBuscar.setEnabled(false);
        editTextTitulo.requestFocus();
        mainActivity.showKeyboard();

        return view;
    }

    private void openResultsFragment() {
        String titulo = editTextTitulo.getText().toString();
        String ano = editTextAno.getText().toString();
        int indexTipo = spinnerTipo.getSelectedItemPosition();

        editTextTitulo.setText("");
        editTextAno.setText("");
        spinnerTipo.setSelection(0);

        Bundle arguments = new Bundle();
        arguments.putString("titulo", titulo);
        arguments.putString("ano", ano);
        arguments.putString("indexTipo", Integer.toString(indexTipo));
        mainActivity.redirectToFragment(R.id.avaliar_resultados_titulo, arguments);
    }

}
