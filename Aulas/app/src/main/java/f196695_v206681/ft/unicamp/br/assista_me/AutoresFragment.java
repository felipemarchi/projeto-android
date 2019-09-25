package f196695_v206681.ft.unicamp.br.assista_me;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutoresFragment extends Fragment {
    View view;
    String conteudoEmail = "Enviado para:\nMensagem:";

    public AutoresFragment() {
    }

    public void onStart() {
        super.onStart();
        ((TextView)view.findViewById(R.id.mail_content)).setText(conteudoEmail);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_autores, container, false);
        return view;
    }

    public void  setMailContent(String conteudoEmail) {
        this.conteudoEmail = conteudoEmail;
    }
}
