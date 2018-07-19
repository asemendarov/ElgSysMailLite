import Email.JavaMailService;
import Parser.HTMLParser;
import Tool.Configuration;
import Tool.LoggerProxy;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static {
        try {
            Configuration.init();
        } catch (Exception e) {
            LoggerProxy.warning(e);
            stopApplication();
        }
    }

    public static void main(String[] args) {
        startApplication();
    }

    private static void startApplication(){
        try {
            final String[] html = {Configuration.getMessageProperties()};

            System.out.printf("\nСодержание файла message.html:\n %s\n", html[0]);

            HTMLParser htmlParser = new HTMLParser();
            htmlParser.parsing(html[0]).forEach((v)->{
                html[0] = html[0].replaceAll(v, waitForString(String.format("Введите [%s]\t-> ", v)));
            });

            String from = waitForString("От кого: ");
            String to = waitForString("Кому: ");

            if (!waitForYesOrNo("Пожалуйста, подтвердите отправку сообщения [Y/N]: "))
                return;

            JavaMailService.send(from, Arrays.asList(to), "Какая-то тема сообщения", html[0]);
            System.out.println("Сообщение отправлено!\nНажмите любую ENTER для выхода.");
            waitForEnter();
        } catch (Exception e) {
            LoggerProxy.warning(e);
            stopApplication();
        }
    }

    private static void stopApplication() {
        LoggerProxy.info("Приложение было принудительно остановленно.\nНажмите любую ENTER для выхода.");
        waitForEnter();
    }

    private static void waitForEnter() {
        try {
            System.in.read();
        } catch (Exception e) {
            System.exit(-1);
        }
        System.exit(0);
    }


    private static Scanner scanner = new Scanner(System.in);
    private static String waitForString() { return waitForString(null); }
    private static String waitForString(String outMessage) {
        try {
            String inMessage = "";
            do {
                System.out.print(Objects.requireNonNullElse(outMessage, "-> "));
                inMessage = scanner.next();
            } while (isControlCommand(inMessage));

            return inMessage;
        } catch (Exception e) {
            LoggerProxy.warning(e);
            stopApplication();
        }

        assert true: "Main/waitForString: Где-то совершина логическая ошибка.";

        return "";
    }

    private static boolean isControlCommand(String s){
        if (s.equals("-stop")){
            stopApplication();

            assert true: "Main/isControlCommand: Где-то совершина логическая ошибака";

            return true;
        }
        if (s.equals("-help")){
            System.out.println("Дополнительная информация: https://github.com/oNoComments/ElgSysMailLite");
            return true;
        }

        return false;
    }

    // example: "Пожалуйста, подтвердите отправку сообщения [Y/N]: "
    private static boolean waitForYesOrNo(String s){
        String command = "";
        while (true){
            System.out.print(s);
            command = scanner.next().toUpperCase();
            if (command.equals("Y") || command.equals("YES"))
                return true;
            if (command.equals("N") || command.equals("NO"))
                return false;
        }
    }
}
