package f196695_v206681.ft.unicamp.br.pos_creditos.viewController.consultar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.FilmeAssistido;

public class ConsultarAdapter extends ArrayAdapter<String> implements SectionIndexer {
    private HashMap<String,Integer> mapIndex;
    private String[] sections;
    private List<String> titulos;
    private List<FilmeAssistido> filmes;

    public void setFilmes(List<FilmeAssistido> filmes) { this.filmes = filmes; }

    public ConsultarAdapter(@NonNull Context context, List<String> titulos) {
        super(context, android.R.layout.simple_list_item_1, titulos);
        this.titulos = titulos;
        this.mapIndex = new HashMap<>();
        indexarRolagem();
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int i) {
        return mapIndex.get(sections[i]);
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    private void indexarRolagem() {
        for (int i = 0; i < titulos.size(); i++){
            String item = titulos.get(i);
            String key = item.substring(0,1);
            key = key.toUpperCase();
            if(!mapIndex.containsKey(key)){
                this.mapIndex.put(key,i);
            }
            Set<String> sectionLetters = mapIndex.keySet();
            ArrayList<String> sectionList = new ArrayList<>(sectionLetters);
            Collections.sort(sectionList);
            sections = new String[sectionList.size()];
            sectionList.toArray(sections);
        }
    }
}
