package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class AutoresFragment extends Fragment {

    View view;
    TextView textViewIdadeFelipe;
    TextView textViewIdadeVinicius;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.autores_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_autores, container, false);
            textViewIdadeFelipe = view.findViewById(R.id.autores_idade_felipe);
            textViewIdadeVinicius = view.findViewById(R.id.autores_idade_vinicius);
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int idade = calendar.get(Calendar.YEAR) - 1999;
        String stringIdade = idade + " " + getString(R.string.autores_anos);
        textViewIdadeFelipe.setText(stringIdade);
        textViewIdadeVinicius.setText(stringIdade);

        return view;
    }

}
