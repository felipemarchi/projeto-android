package f196695_v206681.ft.unicamp.br.pos_creditos.model;

public class Filme {
    private String titulo;
    private String ano;
    private int poster;

    public Filme(String titulo, String ano, int poster) {
        this.titulo = titulo;
        this.ano = ano;
        this.poster = poster;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
