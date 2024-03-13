package eti.justino.poc.kafka.util;

public class StringUtils {

    private static final String ASPAS = "\"";

    public static String encapulaCampoString(String campo, String valor){
        return ASPAS + campo + ASPAS + ":" + ASPAS + valor + ASPAS;
    }


}
