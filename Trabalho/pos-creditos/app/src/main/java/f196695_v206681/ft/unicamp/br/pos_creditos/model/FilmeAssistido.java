package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import android.widget.ImageView;

import com.google.firebase.Timestamp;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.DownloadImageTask;

public class FilmeAssistido {
    private double avaliacao;
    private String comentario;
    private Timestamp dataAvaliacao;
    private String imddbId;
    private String poster;
    private String titulo;
    private String tipo;
    private String ano;

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Timestamp dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getImddbId() {
        return imddbId;
    }

    public void setImddbId(String imddbId) {
        this.imddbId = imddbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void getSetPoster(ImageView imagePoster) {
        if (this.getPoster().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else
            new DownloadImageTask(imagePoster).execute(this.getPoster());
    }
}
