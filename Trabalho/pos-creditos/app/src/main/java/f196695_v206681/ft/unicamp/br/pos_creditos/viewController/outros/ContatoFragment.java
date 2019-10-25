package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.gmail.GmailSend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ContatoFragment extends Fragment {

    View view;
    MainActivity mainActivity;

    TextView textViewEmail;
    TextView textViewAssunto;
    TextView textViewMensagem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.contato_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_contato, container, false);
            mainActivity = (MainActivity) getActivity();

            textViewEmail = view.findViewById(R.id.contato_email);
            textViewAssunto = view.findViewById(R.id.contato_assunto);
            textViewMensagem = view.findViewById(R.id.contato_mensagem);

            if (FirebaseAuth.getInstance() != null)
                textViewEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

            view.findViewById(R.id.contato_enviar).setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = getString(R.string.app_email);
                    String assunto = textViewAssunto.getText().toString();
                    String mensagem =
                            "RESPONDER À\n" + textViewEmail.getText() +
                            "\n\nMENSAGEM\n" + textViewMensagem.getText();

                    textViewEmail.setText("");
                    textViewAssunto.setText("");
                    textViewMensagem.setText("");

                    int mensagemRetorno = GmailSend.sendEmail(email, assunto, mensagem) ? R.string.contato_sucesso : R.string.contato_falha;
                    mainActivity.showToast(getString(mensagemRetorno));
                    mainActivity.redirectToFragment(R.id.navigation_drawer_home);
                }
            });
        }

        textViewEmail.requestFocus();
        mainActivity.showKeyboard();

        return view;
    }

}
