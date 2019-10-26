package f196695_v206681.ft.unicamp.br.aulas.internet;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import f196695_v206681.ft.unicamp.br.aulas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InternetFragment extends Fragment {

    private View lview;


    public InternetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_internet, container, false);
        }

        final TextView textView = lview.findViewById(R.id.textView);
        final EditText editText = lview.findViewById(R.id.editText);
        final Spinner requestTypeSpinner = lview.findViewById(R.id.spinner);


        lview.findViewById(R.id.btnViaCep).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = "https://aulas-a1728.firebaseio.com/" + editText.getText().toString() + "/.json";
                        String requestMethod = requestTypeSpinner.getSelectedItem().toString();

                        if (requestMethod.equals("GET") || requestMethod.equals("DELETE")) {
                            new MyFirstWebClient(textView).execute(url, requestMethod);
                        } else {
                            String json = "{\"Nome\":\"Vinícius Abrantes Pereira\"}";
                            new MyFirstWebClient(textView).execute(url, requestMethod, json);
                        }
                    }
                }
        );


        return lview;
    }

}
