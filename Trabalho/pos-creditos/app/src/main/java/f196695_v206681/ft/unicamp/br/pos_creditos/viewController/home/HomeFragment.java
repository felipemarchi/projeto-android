package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Retorno;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    View view;
    LinearLayout posterLinearLayout;
    TextView filmesAssistidos;
    TextView avaliacaoMedia;
    TextView generoFavorito;

    LayoutInflater inflater;

    private Retorno retorno;
    private Map<ImageView, Filme> filmesMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.home_titulo);

        if (view == null) {
            this.inflater = inflater;
            view = inflater.inflate(R.layout.fragment_home, container, false);
            posterLinearLayout = view.findViewById(R.id.new_movies_linear_layout);
            filmesAssistidos = view.findViewById(R.id.home_filmes_assistidos_descricao);
            avaliacaoMedia = view.findViewById(R.id.home_avaliacao_media_descricao);
            generoFavorito = view.findViewById(R.id.home_genero_favorito_descricao);
        } else {
            updateCards();
        }

        buscarLancamentos();

        return view;
    }

    public void updateCards() {
        List<Filme> avaliacoes = ((MainActivity) getActivity()).getAvaliacoes();
        if (avaliacoes.size() == 0) return;

        //Calcula os dados necessários
        List<Filme> conjuntoFilmes = getConjuntoFilmes(avaliacoes);

        double soma = 0;

        Map<Long, Integer> generoCount = new HashMap<Long, Integer>();
        for (Filme filme : conjuntoFilmes) {
            soma += filme.getVote_average();
            for (long id_genero : filme.getGenre_ids()) {
                Integer qtd = generoCount.get(id_genero);
                if (qtd == null) {
                    generoCount.put(id_genero, 1);
                } else {
                    generoCount.put(id_genero, ++qtd);
                }
            }
        }

        double media = soma / conjuntoFilmes.size();

        int maxCount = 0;
        long maxKey = 0;

        for (Map.Entry<Long, Integer> entry : generoCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        int beginIdx = getResources().getString(R.string.home_genero_favorito_descricao).length() - 2;
        int endIdx = getResources().getString(R.string.home_genero_favorito_descricao).length() - 1;

        //set watched movies
        StringBuilder sb = new StringBuilder(getResources().getString(R.string.home_filmes_assistidos_descricao));
        sb.replace(0, 1, Integer.toString(conjuntoFilmes.size()));
        filmesAssistidos.setText(sb.toString());

        //set average rating
        sb = new StringBuilder(getResources().getString(R.string.home_avaliacao_media_descricao));
        sb.replace(0, 1, String.format("%.1f", media));
        avaliacaoMedia.setText(sb.toString());

        //set favorite gender
        sb = new StringBuilder(getResources().getString(R.string.home_genero_favorito_descricao));
        sb.replace(beginIdx, endIdx, Utils.getGeneroById(maxKey));
        generoFavorito.setText(sb.toString());
    }

    private List<Filme> getConjuntoFilmes(List<Filme> avaliacoes) {
        List<Filme> conjunto = new ArrayList<>();
        for (Filme filme : avaliacoes) {
            boolean ja_adicionado = false;

            for (Filme f : conjunto) {
                if (f.getTitle().equals(filme.getTitle()))
                    ja_adicionado = true;
            }

            if (!ja_adicionado) conjunto.add(filme);
        }

        return conjunto;
    }

    public void buscarLancamentos() {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + Utils.getTmdbApiKey();
        url += "&language=en-US&region=BR&sort_by=popularity.desc&include_adult=false";
        System.out.print("URL de busca: ");
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    retorno = new Gson().fromJson(json, Retorno.class);
                    addPosters();
                }
            }
        });
    }

    private void addPosters() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Filme filme : retorno.getResults()) {
                    ImageView imageView = getPosterImageView();
                    filmesMap.put(imageView, filme);
                    filme.setPosterImage(imageView);
                    posterLinearLayout.addView(imageView);
                }
            }
        });
    }

    private ImageView getPosterImageView() {
        final ImageView imageView = (ImageView) inflater.inflate(R.layout.poster_image_view, null);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popup = new PopupMenu(getContext(), imageView);
                popup.inflate(R.menu.poster_popup_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mostrar_detalhes_popup:
                                ((MainActivity) getActivity()).redirectToDetalhes(filmesMap.get(imageView));
                                popup.dismiss();
                                break;
                            case R.id.avaliar_filme_popup:
                                ((MainActivity) getActivity()).redirectToAvaliarFilme(filmesMap.get(imageView));
                                popup.dismiss();
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        return imageView;
    }

}