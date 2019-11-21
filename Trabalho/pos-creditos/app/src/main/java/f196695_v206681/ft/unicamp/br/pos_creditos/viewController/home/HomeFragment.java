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

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    View view;
    LinearLayout posterLinearLayout;
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
        }

        buscarLancamentos();

        return view;
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
            public void onFailure(Call call, IOException e) { }
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
                    filme.getSetPoster(imageView);
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
