package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import f196695_v206681.ft.unicamp.br.pos_creditos.controllers.omdb.TmdbApi;

public class Utils {
    private static String emailPassword;
    private static String OmdbApiKey;
    private static String TmdbApiKey;

    public static String getEmailPassword() {
        return emailPassword;
    }

    public static void setEmailPassword(String emailPassword) {
        Utils.emailPassword = emailPassword;
    }

    public static String getOmdbApiKey() {
        return OmdbApiKey;
    }

    public static void setOmdbApiKey(String omdbApiKey) {
        OmdbApiKey = omdbApiKey;
    }

    public static String getTmdbApiKey() {
        if (TmdbApiKey == null) {
            return "166e5c2be7dc1189ef89bc84bf04831b";
        } else {
            return TmdbApiKey;
        }
    }

    public static void setTmdbApiKey(String tmdbApiKey) {
        TmdbApiKey = tmdbApiKey;
    }
}
