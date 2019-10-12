package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import android.widget.ImageView;
import java.util.Date;

import f196695_v206681.ft.unicamp.br.pos_creditos.R;
import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.DownloadImageTask;

public class Filme {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;
    private double Avaliacao;
    private Date DataAvaliacao;
    private String Comentario;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbId() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

    public double getAvaliacao() {
        return Avaliacao;
    }

    public Date getDataAvaliacao() {
        return DataAvaliacao;
    }

    public String getComentario() {
        return Comentario;
    }


    public void getSetPoster(ImageView imagePoster) {
        if (this.getPoster().equals("N/A"))
            imagePoster.setImageResource(R.drawable.default_poster);
        else
            new DownloadImageTask(imagePoster).execute(this.getPoster());
    }

    public void setAvaliacao(double estrelas, Date data, String comentario) {
        this.Avaliacao = estrelas;
        this.DataAvaliacao = data;
        this.Comentario = comentario;
    }

}
