package Tool;

import Tool.Configuration;

import java.util.logging.*;

public class LoggerProxy {

    private final static Logger log = Logger.getLogger(Configuration.NAME_PROGRAM);

    static {
        log.setLevel(Level.ALL);
    }

    public static void config(String msg){
        log.config(messageFormat(new Exception(msg)));
    }

    public static void config(Exception ex){
        log.config(messageFormat(ex));
    }

    public static void fine(String msg){
        log.fine(messageFormat(new Exception(msg)));
    }

    public static void fine(Exception ex){
        log.fine(messageFormat(ex));
    }

    public static void finer(String msg){
        log.finer(messageFormat(new Exception(msg)));
    }

    public static void finer(Exception ex){
        log.finer(messageFormat(ex));
    }

    public static void finest(String msg){
        log.finest(messageFormat(new Exception(msg)));
    }

    public static void finest(Exception ex){
        log.finest(messageFormat(ex));
    }

    public static void info(String msg){
        log.info(messageFormat(new Exception(msg)));
    }

    public static void info(Exception ex){
        log.info(messageFormat(ex));
    }

    public static void severe(String msg){
        log.severe(messageFormat(new Exception(msg)));
    }

    public static void severe(Exception ex){
        log.severe(messageFormat(ex));
    }

    public static void warning(String msg){
        log.warning(messageFormat(new Exception(msg)));
    }

    public static void warning(Exception ex){
        log.warning(messageFormat(ex));
    }

    private static String messageFormat(Exception ex){
        StackTraceElement[] stackTraceElement = ex.getStackTrace();

        int index = 1; // Стоит внимательно относиться к этому параметру

        return String.format(
                "[%s/%s.%s()]: %s",
                stackTraceElement[index].hashCode(),
                stackTraceElement[index].getClassName(),
                stackTraceElement[index].getMethodName(),
                ex.getMessage() // OR -> ex.getLocalizedMessage()
        );
    }

    public static void addHandler(Handler handler){
        log.addHandler(handler);
    }

    public static void removeHandler(Handler handler){
        log.removeHandler(handler);
    }
}
