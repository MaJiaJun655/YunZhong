//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package minecraft.yunzhong.api;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class McLogger {
    private static String file_name = "log";

    public McLogger() {
    }

    private static String getLogName() throws IOException {
        StringBuffer logPath = new StringBuffer();
        System.setProperty("log", "plugins/YunZhong//");
        logPath.append(System.getProperty("log"));
        logPath.append("//" + file_name);
        File file = new File(logPath.toString());
        if (!file.exists()) {
            file.mkdir();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        logPath.append("//" + sdf.format(new Date()) + ".log");
        return logPath.toString();
    }

    public static void setLogingProperties(Logger logger) throws SecurityException, IOException {
        setLogingProperties(logger, Level.ALL);
    }

    public static void setLogingProperties(Logger logger, Level level) {
        try {
            FileHandler fh = new FileHandler(getLogName(), true);
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        } catch (SecurityException var4) {
            logger.log(Level.SEVERE, "安全性错误", var4);
        } catch (IOException var5) {
            logger.log(Level.SEVERE, "读取文件日志错误", var5);
        }

    }

    public static void info(String info) {
        new SimpleDateFormat("yyyy-MM-dd");
        Logger logger = Logger.getLogger("sgg");

        try {
            setLogingProperties(logger);
            logger.log(Level.INFO, info);
            Handler[] var3 = logger.getHandlers();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Handler h = var3[var5];
                h.close();
            }
        } catch (SecurityException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
}
