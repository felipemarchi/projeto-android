package f196695_v206681.ft.unicamp.br.pos_creditos.gui.consultar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.model.FilmeAssistido;

public class ConsultarRecyclerViewAdapter extends RecyclerView.Adapter {
        private ArrayList<FilmeAssistido> filmes;

        ConsultarRecyclerViewAdapter(ArrayList<FilmeAssistido> filmes) {
            this.filmes = filmes;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultar_adapter_layout, parent,false);
            final ConsultarViewHolder viewHolder = new ConsultarRecyclerViewAdapter.ConsultarViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((ConsultarRecyclerViewAdapter.ConsultarViewHolder) holder).onBind(filmes.get(position), position);
        }

        @Override
        public int getItemCount() {
            return filmes.size();
        }

        private class ConsultarViewHolder extends RecyclerView.ViewHolder {
            private ImageView imagePoster;
            private TextView textTitulo;
            private TextView textAno;
            private TextView textAvaliacao;
            public int position;

            public ConsultarViewHolder(View itemView) {
                super(itemView);
                imagePoster = itemView.findViewById(R.id.poster_consultar);
                textTitulo = itemView.findViewById(R.id.titulo_consultar);
                textAno = itemView.findViewById(R.id.ano_consultar);
                textAvaliacao = itemView.findViewById(R.id.avaliacao_consultar);
            }

            public void onBind(final FilmeAssistido filme, final int position) {
                this.position = position;
                imagePoster.setImageResource(filme.getPoster());
                textTitulo.setText(filme.getTitulo());
                textAno.setText(filme.getAno());
                textAvaliacao.setText(Double.toString(filme.getAvaliacao()));
            }

        }
    }
