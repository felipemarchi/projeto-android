package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class AvaliarDataDialogFragment extends DialogFragment {
    View view;
    Calendar calendar;

    DatePicker datePicker;
    Button buttonSalvar;

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar_data, container, false);

            datePicker = view.findViewById(R.id.avaliar_data);
            buttonSalvar = view.findViewById(R.id.avaliar_data_salvar);

            buttonSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ano = datePicker.getYear();
                    int mes = datePicker.getMonth();
                    int dia = datePicker.getDayOfMonth();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(ano, mes, dia);

                    AvaliarFilmeFragment avaliarFilmeFragment = (AvaliarFilmeFragment) getFragmentManager().findFragmentByTag("avaliar_filme_fragment");
                    avaliarFilmeFragment.setCalendar(calendar);

                    dismiss();
                }
            });
        }

        datePicker.setMaxDate(new Date().getTime());
        if (calendar != null)
            datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }
}
