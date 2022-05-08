package minecraft.yunzhong.api;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class McLogger {
    private static String file_name = "log";

    private static String getLogName() throws IOException {
        StringBuffer logPath = new StringBuffer();
        System.setProperty("log", "plugins/YunZhong//");// 这里设置一个自定义的key作为存日志的目录
        logPath.append(System.getProperty("log")); // 现将自定义的目录取出来
        logPath.append("//"+file_name);
        File file = new File(logPath.toString());
        if (!file.exists()) {
            file.mkdir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        logPath.append("//"+sdf.format(new Date())+".log");
        return logPath.toString();

    }


    /**
     * 配置Logger对象输出日志文件路径
     * @param logger
     * @throws SecurityException
     * @throws IOException
     */
    public static void setLogingProperties(Logger logger) throws SecurityException, IOException {
        setLogingProperties(logger,Level.ALL);
    }



    /**
     * 配置Logger对象输出日志文件路径
     * @param logger
     * @param level 在日志文件中输出level级别以上的信息
     * @throws SecurityException
     * @throws IOException
     */
    public static void setLogingProperties(Logger logger,Level level) {
        FileHandler fh;
        try {
            fh = new FileHandler(getLogName(),true);
            logger.addHandler(fh);//日志输出文件
            //logger.setLevel(level);
            fh.setFormatter(new SimpleFormatter());//输出格式
            //logger.addHandler(new ConsoleHandler());//输出到控制台
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "安全性错误", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"读取文件日志错误", e);
        }

    }


    public static void info(String info) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Logger logger = Logger.getLogger("sgg");
        try {
            McLogger.setLogingProperties(logger);
            logger.log(Level.INFO, info);
            for (Handler h:logger.getHandlers()){
                h.close();
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//    public static void main(String [] args) {
//        Logger logger = Logger.getLogger("sgg");
//        try {
//            FileHandler fh = null;
//            McLogger.setLogingProperties(fh,logger);
//            logger.log(Level.INFO, "某某玩家防止了一个刷怪笼");
//            logger.log(Level.INFO, "[Jetren]说：大家还好吗？");
//
//
//        } catch (SecurityException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
