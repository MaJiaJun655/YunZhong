package minecraft.yunzhong.api;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * wangweihong
 * 字符串常用方法工具类
 * 字符串常用方法工具类
 */
public final class StrUtil {

    /**
     * 此类不需要实例化
     */
    private StrUtil() {
    }

    /**
     * 判断一个字符串是否为空，null也会返回true
     *
     * @param str 需要判断的字符串
     * @return 是否为空，null也会返回true
     */
    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim()) || "null".equals(str.trim());
    }

    /**
     * 判断一个字符串是否不为空
     *
     * @param str 需要判断的字符串
     * @return 是否为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断一组字符串是否有空值
     *
     * @param strs 需要判断的一组字符串
     * @return 判断结果，只要其中一个字符串为null或者为空，就返回true
     */
    public static boolean hasBlank(String... strs) {
        if (null == strs || 0 == strs.length) {
            return true;
        } else {
            //这种代码如果用java8就会很优雅了
            for (String str : strs) {
                if (isBlank(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String reverse(String str, int length) {
        StringBuffer sb = new StringBuffer(str);
        int a = sb.length();
        if (a < length) {
            for (int i = 0; i < length - a; i++) {
                sb.append("0");
            }
        }

        sb = sb.reverse();

        return sb.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }





    public static String toString(Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof Number) {
            return o + "";
        }
        return o.toString();
    }

    /**
     * 获取状态中文名称
     *
     * @param status
     * @return
     */
    public static String statusName(String status) {
        if ("SEND_FAILURE".equals(status)) {
            status = "发送失败";
        } else if ("SEND_OK".equals(status)) {
            status = "发送成功";
        } else if ("SEND".equals(status)) {
            status = "已发送";
        } else if ("REJECTED".equals(status)) {
            status = "审批驳回";
        } else if ("APPROVED".equals(status)) {
            status = "审批通过";
        } else if ("SUBMITTED".equals(status)) {
            status = "已提交";
        } else if ("ENTERED".equals(status)) {
            status = "未提交";
        } else if ("PROCESSED".equals(status)) {
            status = "处理成功";
        } else if ("CANCELLED".equals(status)) {
            status = "已取消";
        } else if ("CANCELL_SUBMIT".equals(status)) {
            status = "已申请撤销";
        } else if ("CANCELL_RECEIVED".equals(status)) {
            status = "已撤销";
        }
        return status;
    }

    public static BigDecimal getBigDecimal(Object obj) {
        BigDecimal amount = new BigDecimal("0");
        if (obj != null && !"".equals(obj.toString().trim())) {
            String amountStr = obj.toString();
            amountStr = amountStr.replaceAll("\r|\n", "");
            amountStr = amountStr.replaceAll("\r|,", "");
            amount = new BigDecimal(amountStr);
        }
        return amount;
    }

    public static String get12UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1];
    }

    /**
     * 截取时间前10位，异常则返回
     * @return
     */
    public static String dateTimeToString(String v){
        try {
            return v.substring(0,10);
        }catch (Exception e){
            return v;
        }
    }
}