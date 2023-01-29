package src.Controller;

import java.sql.Date;

public class MyAppContext {
	// définir une variable de classe qui est partagée entre toutes les instances d'une même classe et mémoriser ces valeurs
    public static String workerUsername = "";
    public static String workerPassword = "";
    public static String ClientUsername = "";
    public static String ClientPassword = "";
    public static int selectedIdPersonInSearch=0;
    public static String ClientWhoReserved="";
    public static String acceptOrDecline="";
    private static MyAppContext instance;
    private boolean isListModified = false;

    private MyAppContext() { }

    public static MyAppContext getInstance() {
        if (instance == null) {
            instance = new MyAppContext();
        }
        return instance;
    }

    public void setListModified(boolean isListModified) {
        this.isListModified = isListModified;
    }

    public boolean isListModified() {
        return isListModified;
    }
}

