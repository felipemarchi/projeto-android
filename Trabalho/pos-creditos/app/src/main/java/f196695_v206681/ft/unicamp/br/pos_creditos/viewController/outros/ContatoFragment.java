package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ContatoFragment extends Fragment {

    View view;

    public ContatoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Contato");

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_contato, container, false);

            final TextView email = view.findViewById(R.id.contato_email);
            final TextView assunto = view.findViewById(R.id.contato_assunto);
            final TextView mensagem = view.findViewById(R.id.contato_mensagem);

            view.findViewById(R.id.contato_enviar).setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String assuntoEmail = assunto.getText().toString();
                    String mensagemFormatada =
                            "RESPONDER À\n" + email.getText() +
                            "\n\nMENSAGEM\n" + mensagem.getText();

                    email.setText("");
                    assunto.setText("");
                    mensagem.setText("");

                    ((MainActivity) getActivity()).enviarContato(assuntoEmail, mensagemFormatada);
                }
            });
        }

        return view;
    }

}