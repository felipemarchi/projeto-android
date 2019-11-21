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

import java.text.DateFormat;
import java.util.Calendar;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase.FirebaseSalvar;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.viewController.outros.LoadingDialogFragment;

public class AvaliarFilmeFragment extends Fragment {
    View view;
    MainActivity mainActivity;

    ImageView imagePoster;
    TextView textViewTitulo;
    RatingBar ratingBar;
    TextView textViewData;
    TextView textViewComentario;
    Button buttonAvaliar;

    Filme filme;
    Calendar calendar;

    public AvaliarFilmeFragment() {
        this.filme = new Filme();
    }

    public void setFilme(Filme filme) {
        this.filme = Filme.getClone(filme);
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        textViewData.setText(DateFormat.getDateInstance().format(calendar.getTime()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.avaliar_filme_titulo);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_avaliar_filme, container, false);
            mainActivity = (MainActivity) getActivity();

            imagePoster = view.findViewById(R.id.avaliar_filme_poster);
            textViewTitulo = view.findViewById(R.id.avaliar_filme_titulo);
            ratingBar = view.findViewById(R.id.avaliar_filme_estrelas);
            textViewData = view.findViewById(R.id.avaliar_filme_data);
            textViewComentario = view.findViewById(R.id.avaliar_filme_comentario);
            buttonAvaliar = view.findViewById(R.id.avaliar_filme_avaliar);

            textViewData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AvaliarDataDialogFragment datePicker = new AvaliarDataDialogFragment();
                    if (calendar != null)
                        datePicker.setCalendar(calendar);
                    datePicker.show(getFragmentManager(), "data_fragment");
                }
            });

            buttonAvaliar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
                    loadingDialogFragment.show(getFragmentManager(), "loading_fragment");

                    if (calendar == null)
                        calendar = Calendar.getInstance();
                    filme.setAvaliacao(ratingBar.getRating(), calendar.getTime(), textViewComentario.getText().toString());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (new FirebaseSalvar() {
                                @Override
                                public void onSuccess() {
                                    mainActivity.getAvaliacoes().add(filme);
                                    ratingBar.setRating(0);
                                    textViewComentario.setText("");
                                    LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) getFragmentManager().findFragmentByTag("loading_fragment");
                                    loadingDialogFragment.dismiss();
                                    mainActivity.showToast(getString(R.string.avaliar_filme_sucesso));
                                    mainActivity.popAllFragments();
                                }

                                @Override
                                public void onFailure() {
                                    LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) getFragmentManager().findFragmentByTag("loading_fragment");
                                    loadingDialogFragment.dismiss();
                                    mainActivity.showToast(getString(R.string.avaliar_filme_falha));
                                }
                            }).salvarAvaliacao(filme);
                        }
                    });
                }
            });
        }

        filme.setPosterImage(imagePoster);
        textViewTitulo.setText(filme.getTitle());
        ratingBar.setRating(0);
        textViewData.setText(DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
        textViewComentario.setText("");

        return view;
    }
}
