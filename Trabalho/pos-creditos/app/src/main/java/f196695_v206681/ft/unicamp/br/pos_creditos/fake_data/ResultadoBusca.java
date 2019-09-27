package f196695_v206681.ft.unicamp.br.pos_creditos.fake_data;

import f196695_v206681.ft.unicamp.br.pos_creditos.model.Filme;
import f196695_v206681.ft.unicamp.br.pos_creditos.R;

public class ResultadoBusca {
    public static Filme[] buscaFilmesResultado = {
            new Filme("Iron Man", "2008", R.drawable.filme_iron_man),
            new Filme("Iron Man 2", "2010", R.drawable.filme_iron_man_2),
            new Filme("Iron Man 3","2013", R.drawable.filme_iron_man_3)
    };

    public static Filme[] ultimosLancamentos = {
            new Filme("Joker", "2019", R.drawable.filme_joker),
            new Filme("Rambo", "2019", R.drawable.filme_rambo),
            new Filme("The Lion King", "2019", R.drawable.filme_the_lion_king),
            new Filme("Yesterday", "2019", R.drawable.filme_yesterday)
    };
}
