import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class Log {
    public Logger logger;
    FileHandler fileHandler;
    public Log(String fileName) throws SecurityException, IOException {
        File file = new File(fileName);
        fileHandler = new FileHandler(fileName, true);
        logger = Logger.getLogger("logger");
        logger.addHandler(fileHandler);
        logger.setLevel(Level.INFO);
        SimpleFormatter formatter = new SimpleFormatter() {
            //[Дата в формате 2024-05-24 13:27:49] [Уровень лога, выделено 7 символов] Сообщение Переход на новую строку
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";
            @Override
            public synchronized String format(LogRecord lr){
                return String.format(format,
                new Date(lr.getMillis()),
                lr.getLevel().getName(),
                lr.getMessage());
            }
        };
        fileHandler.setFormatter(formatter);
        fileHandler.setEncoding("UTF-8");
        logger.setUseParentHandlers(false);
    }
}
