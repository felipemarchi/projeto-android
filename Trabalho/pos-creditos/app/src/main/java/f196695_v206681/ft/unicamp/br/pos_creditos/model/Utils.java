package f196695_v206681.ft.unicamp.br.pos_creditos.model;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static String emailPassword;
    private static String OmdbApiKey;
    private static String TmdbApiKey;
    private static Map<Integer, String> generos;

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

    public static Map<Integer, String> getGenerosMap() {
        if (Utils.generos == null) {
            carregarGeneros();
        }

        return Utils.generos;
    }

    private static void carregarGeneros() {
        generos = new HashMap<>();
        Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> map = new Gson().fromJson(jsonString, stringStringMap);

        for (Map.Entry<String,String> entry : map.entrySet())
            generos.put(Integer.parseInt(entry.getKey()), entry.getValue());
    }

    public static String getGeneroById(long id) {
        for (Map.Entry<Integer, String> entry : getGenerosMap().entrySet()) {
            if (entry.getKey() == id) {
                return entry.getValue();
            }
        }

        return Long.toString(id);
    }

    private static String jsonString = "{\n" +
            "  \"28\": \"Action\",\n" +
            "  \"12\": \"Adventure\",\n" +
            "  \"16\": \"Animation\",\n" +
            "  \"35\": \"Comedy\",\n" +
            "  \"80\": \"Crime\",\n" +
            "  \"99\": \"Documentary\",\n" +
            "  \"18\": \"Drama\",\n" +
            "  \"10751\": \"Family\",\n" +
            "  \"14\": \"Fantasy\",\n" +
            "  \"36\": \"History\",\n" +
            "  \"27\": \"Horror\",\n" +
            "  \"10402\": \"Music\",\n" +
            "  \"9648\": \"Mystery\",\n" +
            "  \"10749\": \"Romance\",\n" +
            "  \"878\": \"Science Fiction\",\n" +
            "  \"10770\": \"TV Movie\",\n" +
            "  \"53\": \"Thriller\",\n" +
            "  \"10752\": \"War\",\n" +
            "  \"37\": \"Western\"\n" +
            "}";

}
