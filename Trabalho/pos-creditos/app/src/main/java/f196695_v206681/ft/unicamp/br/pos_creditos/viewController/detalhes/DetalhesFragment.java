package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.detalhes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;

public class DetalhesFragment extends Fragment {

    View view;
    ImageView backdropImage;
    TextView textTitulo;
    TextView textAvaliacao;
    TextView textDataLancamento;
    TextView textSinopse;
    TextView textGenero;

    private Filme filme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.detalhes_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_detalhes, container, false);
            backdropImage = view.findViewById(R.id.detalhes_backdrop_image);
            filme.setBackdropImage(backdropImage);

            textTitulo = view.findViewById(R.id.detalhes_titulo);
            textTitulo.setText(filme.getTitle());

            textSinopse = view.findViewById(R.id.detalhes_sinopse);
            textSinopse.setText(filme.getOverview());

            textAvaliacao = view.findViewById(R.id.detalhes_avaliacao);
            textAvaliacao.setText(Double.toString(filme.getVote_average()));

            textDataLancamento = view.findViewById(R.id.detalhes_data_lancamento);

            if (filme.getRelease_date() != null) {
                try {
                    textDataLancamento.setText(filme.getRelease_date());
                } catch (IndexOutOfBoundsException e) {
                    textDataLancamento.setText("--");
                }
            } else {
                textDataLancamento.setText("--");
            }

            textGenero = view.findViewById(R.id.detalhes_genero);
            StringBuilder genero = new StringBuilder();
            for(long id: filme.getGenre_ids()) {
                genero.append(Utils.getGenerosMap().get(id) + ", ");
            }
            genero.replace(genero.length() - 2, genero.length(), ".");
            textGenero.setText(genero.toString());
        }

        return view;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}