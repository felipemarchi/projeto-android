package f196695_v206681.ft.unicamp.br.aulas.biografias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import f196695_v206681.ft.unicamp.br.aulas.R;
import f196695_v206681.ft.unicamp.br.aulas.alunos.Aluno;
import f196695_v206681.ft.unicamp.br.aulas.alunos.Alunos;

public class BiografiasFragment extends Fragment {
    private View view;

    private ImageView imageView;
    private TextView nomeTextView;
    private TextView descricaoTextView;

    private int position;

    public BiografiasFragment() {
        this.position = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_biografias, container, false);
            imageView = view.findViewById(R.id.imagem);
            nomeTextView = view.findViewById(R.id.alunos);
            descricaoTextView = view.findViewById(R.id.descricao);

            view.findViewById(R.id.button_anterior).setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position > 0) {
                        position--;
                        mostrarAluno();
                    }
                }

            });

            view.findViewById(R.id.button_proximo).setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position < Alunos.alunos.length - 1) {
                        position++;
                        mostrarAluno();
                    }
                }
            });
            ((Button) view.findViewById(R.id.button_anterior)).setText("<");
            ((Button) view.findViewById(R.id.button_proximo)).setText(">");

            mostrarAluno();
        return view;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void mostrarAluno() {
        Aluno aluno = Alunos.alunos[position];
        imageView.setImageResource(aluno.getFoto());
        nomeTextView.setText(aluno.getNome());
        descricaoTextView.setText(Html.fromHtml(aluno.getDescricao()));
    }

}
