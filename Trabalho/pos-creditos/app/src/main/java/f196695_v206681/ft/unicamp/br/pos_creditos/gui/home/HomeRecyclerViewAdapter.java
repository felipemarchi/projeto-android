package f196695_v206681.ft.unicamp.br.pos_creditos.gui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter  {
    private ArrayList<Filme> filmes;

    HomeRecyclerViewAdapter(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.busca_filmes_adapter_layout, parent,false);
        final HomeViewHolder viewHolder = new HomeRecyclerViewAdapter.HomeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HomeViewHolder) holder).onBind(filmes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    private class HomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagePoster;
        private TextView textTitulo;
        private TextView textAno;
        public int position;

        public HomeViewHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.poster_resultado_busca);
            textTitulo = itemView.findViewById(R.id.titulo_resultado_busca);
            textAno = itemView.findViewById(R.id.ano_resultado_busca);
        }

        public void onBind(final Filme filme, final int position) {
            this.position = position;
            imagePoster.setImageResource(filme.getPoster());
            textTitulo.setText(filme.getTitulo());
            textAno.setText(filme.getAno());
        }

    }
}
