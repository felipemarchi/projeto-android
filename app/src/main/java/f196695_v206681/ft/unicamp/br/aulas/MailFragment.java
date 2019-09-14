package f196695_v206681.ft.unicamp.br.aulas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {
    View view;
    EditText destinatario;
    EditText mensagem;

    public MailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_mail, container, false);

        destinatario = view.findViewById(R.id.destinatario);
        mensagem = view.findViewById(R.id.mensagem);


        Button button = view.findViewById(R.id.btn_enviar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensagem = "Enviado para: ";
                mensagem += MailFragment.this.destinatario.getText().toString() + "\n";
                mensagem += "Mensagem: " + MailFragment.this.mensagem.getText().toString();
                ((MainActivity) getActivity()).abrirAutoresFragment(mensagem);
            }
        });
        return view;
    }

}
