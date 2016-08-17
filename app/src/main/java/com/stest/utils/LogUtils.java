package com.stest.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Limuyang on 2016/8/17.
 * Log辅助类
 */
public class LogUtils {
    //所在的类名
    private static String className;

    //所在的方法名
    private static String methodName;

    //所在行号
    private static String lineNumber;

    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;

    //打印json格式化数据
    public static final int JSON = 0x7;

    //是否打印输出log
    private static boolean IS_SHOW_LOG = true;

    public static final int JSON_INDENT = 4;

    //默认的空字符串打印内容
    public static final String NULL_MSG = "Log with null object";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");


    //log TAG默认设置值
    private static String TAG = "com.liji";


    /**
     * 设置是否显示log
     *
     * @param IS_SHOW_LOG
     */
    public static void setDebug(boolean IS_SHOW_LOG) {
        IS_SHOW_LOG = IS_SHOW_LOG;
    }

    /**
     * Log D级别
     *
     * @param logMsg
     */
    public static void D(String logMsg) {
        if (IS_SHOW_LOG) {
            printLog(D, null, logMsg);
        }
    }

    /**
     * Log D级别 可设置Tag
     *
     * @param Tag
     * @param logMsg
     */
    public static void D(String Tag, String logMsg) {
        if (IS_SHOW_LOG) {
            printLog(D, Tag, logMsg);
        }
    }


    /**
     * Log E级别
     *
     * @param errorMsg
     */
    public static void E(String errorMsg) {
        if (IS_SHOW_LOG) {
            printLog(E, null, errorMsg);
        }
    }

    /**
     * Log E级别
     *
     * @param exception
     */
    public static void E(Exception exception) {
        if (IS_SHOW_LOG) {
            exception.printStackTrace();
        }
    }

    /**
     * Log E级别 可设置Tag
     *
     * @param Tag
     * @param errorMsg
     */
    public static void E(String Tag, String errorMsg) {
        if (IS_SHOW_LOG) {
            printLog(E, Tag, errorMsg);

        }
    }

    /**
     * 格式化打印json
     *
     * @param jsonStr
     */
    public static void Json(String jsonStr) {
        if (IS_SHOW_LOG) {
            printLog(JSON, null, jsonStr);
        }
    }

    /**
     * 格式化打印json，可以设置Tag
     *
     * @param Tag
     * @param jsonStr
     */
    public static void Json(String Tag, String jsonStr) {
        if (IS_SHOW_LOG) {
            printLog(JSON, Tag, jsonStr);

        }
    }

    /**
     * 拼接打印log的头部
     */
    private static String getHeadInfo() {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(className);
        buffer.append("#");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        return buffer.toString();
    }

    /**
     * 获取栈信息
     *
     * @param sElements
     * @return
     */
    private static String[] getStackInfo(StackTraceElement[] sElements) {
        for (int i = 0; i < sElements.length; i++) {
            Log.d(TAG, "className:" + sElements[i].getFileName() + "  methodName:" + sElements[i].getMethodName() + " lineNumber:" + sElements[i].getLineNumber());
        }

        //类名
        className = sElements[2].getFileName();

        //方法名
        methodName = sElements[2].getMethodName();

        //行数
        lineNumber = "" + sElements[2].getLineNumber();

        return new String[]{className, methodName, lineNumber};
    }


    /**
     * 打印日志
     *
     * @param type
     * @param Tag
     * @param logMsg
     */
    private static void printLog(int type, String Tag, String logMsg) {

        //类名
        className = getStackInfo(new Throwable().getStackTrace())[0];

        //方法名
        methodName = getStackInfo(new Throwable().getStackTrace())[1];

        //行号
        lineNumber = getStackInfo(new Throwable().getStackTrace())[2];


        //是否包含Tag信息，不包含则取默认的TAG
        TAG = (Tag == null) ? TAG : Tag;

        String headStr = getHeadInfo() + " ——————> ";

        //拼接打印信息,如果log为空则显示默认的msg，否则显示头部加log
        String msg = (logMsg == null || logMsg.equals("")) ? NULL_MSG : logMsg;

        switch (type) {

            //打印基本的log信息
            case D:
            case A:
            case V:
            case I:
            case E:
            case W:
                printDefaultLog(type, TAG, headStr + msg);
                break;
            //打印json格式化数据
            case JSON:
                printJsonLog(TAG, msg, headStr);
                break;

        }

    }

    /**
     * 打印格式化json字符串
     *
     * @param tag
     * @param msg
     */
    private static void printJsonLog(String tag, String msg, String headString) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLine(tag, false);
    }

    /**
     * 调用系统api，打印log
     *
     * @param type
     * @param tag
     * @param logMsg
     */
    private static void printDefaultLog(int type, String tag, String logMsg) {
        switch (type) {
            case A:
                Log.wtf(tag, logMsg);
                break;
            case I:
                Log.i(tag, logMsg);
                break;
            case V:
                Log.v(tag, logMsg);
                break;
            case D:
                Log.d(tag, logMsg);
                break;
            case E:
                Log.e(tag, logMsg);
                break;
        }

    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}
