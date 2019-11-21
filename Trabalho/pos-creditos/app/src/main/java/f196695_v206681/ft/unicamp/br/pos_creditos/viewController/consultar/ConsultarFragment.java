package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.application.MainActivity;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.firebase.FirebaseBuscar;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.FilmeAssistido;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsultarFragment extends Fragment {

    View view;
    MainActivity mainActivity;
    ListView listView;
    ConsultarAdapter adapter;
    private View progressBar;

    public ConsultarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.consultar_titulo);

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_consultar, container, false);
            mainActivity = (MainActivity) getActivity();

            progressBar = view.findViewById(R.id.avaliar_resultados_loading);
            listView = view.findViewById(R.id.consultar_list_view);
        }

        (new FirebaseBuscar() {
            @Override
            public void onSuccess() {
                List<FilmeAssistido> filmes = getFilmes();

                if (filmes.size() > 0) {
                    List<String> titulos = new ArrayList<>();
                    for (int i = 0; i < filmes.size(); i++)
                        if (!titulos.contains(filmes.get(i).getTitle()))
                            titulos.add(filmes.get(i).getTitle());
                    Collections.sort(titulos);

                    progressBar.setVisibility(View.GONE);

                    adapter = new ConsultarAdapter(getActivity(), titulos);
                    adapter.setFilmes(filmes);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String tituloSelecionado = (String) adapterView.getItemAtPosition(i);
                            mainActivity.redirectToConsultarFilme(tituloSelecionado);
                        }
                    });
                }
                else
                {
                    mainActivity.showToast(getString(R.string.sem_filmes_toast_consultar));
                    mainActivity.onBackPressed();
                }
            }
        }).buscarAvaliacoes();

        return view;
    }

}
