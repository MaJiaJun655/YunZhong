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
        System.setProperty("log", "plugins/YunZhong//");// ��������һ���Զ����key��Ϊ����־��Ŀ¼
        logPath.append(System.getProperty("log")); // �ֽ��Զ����Ŀ¼ȡ����
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
     * ����Logger���������־�ļ�·��
     * @param logger
     * @throws SecurityException
     * @throws IOException
     */
    public static void setLogingProperties(Logger logger) throws SecurityException, IOException {
        setLogingProperties(logger,Level.ALL);
    }



    /**
     * ����Logger���������־�ļ�·��
     * @param logger
     * @param level ����־�ļ������level�������ϵ���Ϣ
     * @throws SecurityException
     * @throws IOException
     */
    public static void setLogingProperties(Logger logger,Level level) {
        FileHandler fh;
        try {
            fh = new FileHandler(getLogName(),true);
            logger.addHandler(fh);//��־����ļ�
            //logger.setLevel(level);
            fh.setFormatter(new SimpleFormatter());//�����ʽ
            //logger.addHandler(new ConsoleHandler());//���������̨
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "��ȫ�Դ���", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"��ȡ�ļ���־����", e);
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
//            logger.log(Level.INFO, "ĳĳ��ҷ�ֹ��һ��ˢ����");
//            logger.log(Level.INFO, "[Jetren]˵����һ�����");
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
