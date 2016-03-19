package ru.gremal.cs.common.tools;

import ru.gremal.cs.common.interfaces.CoreSide;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by Пользователь on 07.03.2016.
 */
public class CommonTools {
    // интервал sleep в случае ожидания какого-либо события
    // Во внешних модулях вызовы производятся исключительно с префиксом в виде имени класса
    public static final int SLEEP_INTERVAL = 50; // миллисекунд
    /* функция чтения в Map данных из ini-файла */
    public  static Map<String, String> readFromIniFile(String fileName) throws FileNotFoundException, IOException {
        Map<String, String> map = new HashMap<String, String>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String str = "";
        while((str = reader.readLine()) != null){
            String[] temp = str.split("=");
            if(temp.length == 0){ continue; } // строку не удалось разбить на две части.
            map.put(temp[0].trim(), temp[1].trim());
        }
        reader.close();

        return map;
    }

    /* Почему-то внешние модули не видят поле public SLEEP_INTERVAL, поэтому, используем эту функцию доступа к константе */
/*
    public static int getSleepInterval(){
        return SLEEP_INTERVAL;
    }
    */

    public void publishToLogFile(CoreSide core, String message) throws ClassNotFoundException {
        FileHandler fh = core.getLogFileHandler();
        Level l = core.getLogLevel();
        StackTraceElement ste = Thread.currentThread().getStackTrace()[1];
        StringBuilder builder = new StringBuilder(Class.forName(ste.getClassName()).getPackage().toString()).append(message).append(System.getProperty("line.separator"));
        fh.publish(new LogRecord(l, builder.toString()));
    };

}
