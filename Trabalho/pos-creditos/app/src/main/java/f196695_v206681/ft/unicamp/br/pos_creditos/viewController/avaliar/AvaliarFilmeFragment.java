package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.avaliar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase.FirebaseConection;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;

public class AvaliarFilmeFragment extends Fragment {
    View view;
    Filme filme;
    Calendar data;

    ImageView imagePoster;
    TextView textViewTitulo;
    RatingBar ratingBar;
    TextView textViewData;
    TextView textViewComentario;
    Button buttonAvaliar;

    public AvaliarFilmeFragment(Filme filme) {
        this.filme = filme;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar_filme, container, false);

            imagePoster = view.findViewById(R.id.avaliar_filme_poster);
            textViewTitulo = view.findViewById(R.id.avaliar_filme_titulo);
            ratingBar = view.findViewById(R.id.avaliar_filme_estrelas);
            textViewData = view.findViewById(R.id.avaliar_filme_data);
            textViewComentario = view.findViewById(R.id.avaliar_filme_comentario);
            buttonAvaliar = view.findViewById(R.id.avaliar_filme_avaliar);

            textViewData.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    AvaliarDataDialogFragment datePicker = new AvaliarDataDialogFragment();

                    if (data != null)
                        datePicker.setCalendar(data);

                    datePicker.show(getFragmentManager(), "data_fragment");
                }
            });

            buttonAvaliar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).hideKeyboard();

                    if (data == null) {
                        data = Calendar.getInstance();
                    }

                    filme.setAvaliacao(ratingBar.getRating(), data.getTime(), textViewComentario.getText().toString());
                    Toast.makeText(getContext(), "Salvando avaliação", Toast.LENGTH_SHORT).show();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (new FirebaseConection() {
                                @Override
                                public void onSuccess() {
                                    ((MainActivity) getActivity()).goBackToHomeFragment();
                                    Toast.makeText(getContext(), "Avaliação salva", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure() {
                                    Toast.makeText(getContext(), "Operação falhou", Toast.LENGTH_SHORT).show();
                                }
                            }).salvarAvaliacao(filme);
                        }
                    });
                }
            });

        }

        filme.getSetPoster(imagePoster);
        textViewTitulo.setText(filme.getTitle());
        textViewData.setText(DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
        return view;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void setData(Calendar data) {
        this.data = data;
        textViewData.setText(DateFormat.getDateInstance().format(data.getTime()));
    }
}
