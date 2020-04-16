package com.nexgrid_fsp.myapplication.log;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.DocumentsContract;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LogService
{
    // Logcat output/search tag name
    private final static String SERVICE = "SERVICE_LOG";

    // file name output length limit
    final static int FILE_NAME_LENGTH_LIMIT = 20;

    // method name output length limit
    final static int METHOD_NAME_LENGTH_LIMIT = 20;

    // line number output length limit
    final static int LINE_NUMBER_LENGTH_LIMIT = 6;

    // log flow check method(LogService.check)
    private static long beforeTime = 0;

    // default output log level
    private static level logLevel = level.error;

    // For get log file path
    private static Context context = null;

    // save file in internal storage
    private static Boolean internalMod = false;

    // save file in external storage
    private static Boolean externalMod = false;

    // log level kinds
    public enum level
    {
        debug,
        info,
        warn,
        error
    }

    /**
     * set default output log level
     * @param level
     */
    public static synchronized void setLogLevel(level level)
    {
        logLevel = level;
    }

    public static synchronized void fileRWMode(Context conTxt)
    {
        if(context == null)
        {
            context = conTxt;
        }
    }

    /**
     * if use internal file storage
     * @param conTxt
     */
    public static synchronized void saveInternalFileMod(Context conTxt)
    {
        if(context == null)
        {
            context = conTxt;
        }

        internalMod = true;
    }

    /**
     * if use external file storage
     * @param conTxt
     */
    public static synchronized void saveExternalFileMod(Context conTxt)
    {
        if(context == null)
        {
            context = conTxt;
        }

        externalMod = true;
    }

    private static void writeLog(level level, String msg, Throwable e)
    {
        if(internalMod == true)
        {
            writeLogInternal(level, msg, e);
        }

        if(externalMod == true)
        {
            writeLogExternal(level, msg, e);
        }
    }

    /**
     * file write log in internal storage
     * @param level
     * @param msg
     */
    private static void writeLogInternal(level level, String msg, Throwable e)
    {
        if(context == null)
        {
            Log.w(SERVICE, "fileOutputMode isn't active.");
            return;
        }

        FileOutputStream os = null;

        PrintWriter pw = null;

        try
        {
            File file = new File(context.getCacheDir(), new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".log");

            os = new FileOutputStream(file, true);

            os.write((level.name().substring(0, 1) + " : " + msg  + "\n").getBytes());

            if(e != null)
            {
                pw = new PrintWriter(file);
                e.printStackTrace(pw);
            }
        }
        catch (Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
        finally
        {
            try
            {
                if(os != null)
                {
                    os.close();
                }
                if(pw != null)
                {
                    pw.close();
                }
            }
            catch (IOException ioEx)
            {
                // Occured Unexpected Error
                Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ioEx);
            }
        }
    }

    /**
     * file write log in External storage
     * @param level
     * @param msg
     */
    private static void writeLogExternal(level level, String msg, Throwable e)
    {
        if(context == null)
        {
            Log.w(SERVICE, "fileOutputMode isn't active.");
            return;
        }

        FileOutputStream os = null;

        PrintWriter pw = null;

        try
        {
            File file = new File(context.getExternalFilesDir("log"), new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".log");

            os = new FileOutputStream(file, true);

            os.write((level.name().substring(0, 1) + " : " + msg  + "\n").getBytes());

            if(e != null)
            {
                pw = new PrintWriter(file);
                e.printStackTrace(pw);
            }
        }
        catch (Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
        finally
        {
            try
            {
                if(os != null)
                {
                    os.close();
                }
                if(pw != null)
                {
                    pw.close();
                }
            }
            catch (IOException ioEx)
            {
                // Occured Unexpected Error
                Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ioEx);
            }
        }
    }

    public static void writeStorageLog(Context context)
    {
        if(context == null)
        {
            Log.w(SERVICE, "fileOutputMode isn't active.");
            return;
        }

        FileOutputStream os = null;
        PrintWriter pw = null;


        try
        {
//            File file = new File(Environment.getRootDirectory(), new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".log");
//            Log.i(SERVICE, "path : " + Environment.getExternalStorageDirectory().getAbsolutePath());
//            String state = Environment.getExternalStorageState();

//            Log.i(SERVICE, "state : " + state);

            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            {
                Log.i(SERVICE, "mount");
            }
            else
            {
                Log.i(SERVICE, "unmount");
            }




//            File file = Environment.getExternalStorageDirectory();
//            File file = Environment.getRootDirectory();
//            File file = new File(Environment.getRootDirectory().getAbsolutePath() + "/usr");
//            File[] files = ContextCompat.getExternalFilesDirs(context, null);
//            File file = new File("/storage/emulated/0/Android/data/");


            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if(!dirPath.endsWith("/"))
            {
                dirPath = dirPath+"/";
            }
            File file = new File(dirPath);

            File[] files = file.listFiles();

            Log.i(SERVICE, "file : " + dirPath);
//
//            if(files == null)
//            {
//                Log.i(SERVICE, "files is null");
//                return;
//            }

            for(int i = 0; i < files.length; i++)
            {
                Log.i(SERVICE, "file[" + i + "] : " + files[i].getAbsolutePath());
            }

        }
        catch (Exception ex)
        {
            String msg = ex.getMessage();
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
    }

    /**
     * print All File Log
     */
    public static void printAllFileLog()
    {
        printInternalFileLog();

        printExternalFileLog();
    }

    /**
     * print Internal File Log
     */
    public static void printInternalFileLog()
    {
        if(context == null)
        {
            Log.w(SERVICE, "fileOutputMode isn't active.");
            return;
        }

        try
        {
            Log.i(SERVICE, " ==================== Internal File Log Start ==================== ");

            File[] files = context.getCacheDir().listFiles();

            String line = null;
            BufferedReader tmpBuf = null;

            for(int i = 0; i < files.length; i++)
            {
                tmpBuf = new BufferedReader(new FileReader(files[i]));
                while((line=tmpBuf.readLine())!=null)
                {
                    if(line.substring(0, 1).equals(level.debug.name().substring(0, 1)))
                    {
                        Log.d(SERVICE, line.substring(4, line.length()));
                    }
                    else if(line.substring(0, 1).equals(level.warn.name().substring(0, 1)))
                    {
                        Log.w(SERVICE, line.substring(4, line.length()));
                    }
                    else if(line.substring(0, 1).equals(level.error.name().substring(0, 1)))
                    {
                        Log.e(SERVICE, line.substring(4, line.length()));
                    }
                    else
                    {
                        Log.i(SERVICE, line.substring(4, line.length()));
                    }
                }
                tmpBuf.close();
            }
        }
        catch (Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error", ex);
        }
        finally
        {
            Log.i(SERVICE, " ====================  Internal File Log End  ==================== ");
        }
    }

    /**
     * print External File Log
     */
    public static void printExternalFileLog()
    {
        if(context == null)
        {
            Log.w(SERVICE, "fileOutputMode isn't active.");
            return;
        }

        try
        {
            Log.i(SERVICE, " ==================== Internal File Log Start ==================== ");

            File[] files = context.getExternalFilesDir("log").listFiles();

            String line = null;
            BufferedReader tmpBuf = null;

            for(int i = 0; i < files.length; i++)
            {
                tmpBuf = new BufferedReader(new FileReader(files[i]));
                while((line=tmpBuf.readLine())!=null)
                {
                    if(line.substring(0, 1).equals(level.debug.name().substring(0, 1)))
                    {
                        Log.d(SERVICE, line.substring(4, line.length()));
                    }
                    else if(line.substring(0, 1).equals(level.warn.name().substring(0, 1)))
                    {
                        Log.w(SERVICE, line.substring(4, line.length()));
                    }
                    else if(line.substring(0, 1).equals(level.error.name().substring(0, 1)))
                    {
                        Log.e(SERVICE, line.substring(4, line.length()));
                    }
                    else
                    {
                        Log.i(SERVICE, line.substring(4, line.length()));
                    }
                }
                tmpBuf.close();
            }
        }
        catch (Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error", ex);
        }
        finally
        {
            Log.i(SERVICE, " ====================  Internal File Log End  ==================== ");
        }
    }

    /**
     * debug level log output
     * @param msg output log messeage
     */
    public static synchronized void debug(String msg)
    {
        try
        {
            // if setting log level low than info
            if(level.debug.compareTo(logLevel) > 0)
            {
                return;
            }

            // create output log buffer
            StringBuffer logMsg = new StringBuffer();

            // make log to readability
            makeDevelopInfo(new Throwable().getStackTrace()[1], logMsg);

            // add log message
            logMsg.append(msg);

            // info log output
            Log.i(SERVICE, logMsg.toString());

            writeLog(level.debug, logMsg.toString(), null);
        }
        catch(Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
    }

    /**
     * info level log output
     * @param msg output log messeage
     */
    public static synchronized void info(String msg)
    {
        try
        {
            // if setting log level low than info
            if(level.info.compareTo(logLevel) > 0)
            {
                return;
            }

            // create output log buffer
            StringBuffer logMsg = new StringBuffer();

            // make log to readability
            makeDevelopInfo(new Throwable().getStackTrace()[1], logMsg);

            // add log message
            logMsg.append(msg);

            // info log output
            Log.i(SERVICE, logMsg.toString());

            writeLog(level.info, logMsg.toString(), null);
        }
        catch(Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
    }

    /**
     * warn level log output
     * @param msg output log messeage
     * @param e   exception
     */
    public static synchronized void warn(String msg, Throwable e)
    {
        try
        {
            // create output log buffer
            StringBuffer logMsg = new StringBuffer();

            // make log to readability
            makeDevelopInfo(new Throwable().getStackTrace()[1], logMsg);

            // add log message
            logMsg.append(msg);

            // error log output
            Log.e(SERVICE, logMsg.toString(), e);

            writeLog(level.warn, logMsg.toString(), e);
        }
        catch(Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
    }

    /**
     * error level log output
     * @param msg output log messeage
     * @param e   exception
     */
    public static synchronized void error(String msg, Throwable e)
    {
        try
        {
            // create output log buffer
            StringBuffer logMsg = new StringBuffer();

            // make log to readability
            makeDevelopInfo(new Throwable().getStackTrace()[1], logMsg);

            // add log message
            logMsg.append(msg);

            // error log output
            Log.e(SERVICE, logMsg.toString(), e);

            writeLog(level.error, logMsg.toString(), e);
        }
        catch(Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error / Msg : " + msg, ex);
        }
    }

    /**
     * log flow check method
     * ex).
     * [ Main.java < onCreate : 21 > ]
     * == {21:15} ==> [ Main.java < onCreate : 21 > ]
     * == {22:25} ==> [ Test.java < onCreate : 25 > ]
     */
    public static synchronized void check()
    {
        try
        {
            // get current time
            long currentTime = System.currentTimeMillis();

            // get class, metthod, line number
            StackTraceElement ste = new Throwable().getStackTrace()[1];

            // output time check message
            StringBuffer checkMsg = new StringBuffer();

            // output (current - before) time
            if(beforeTime != 0)
            {
                checkMsg.append(" == ");
                checkMsg.append("{");

                // 시
                checkMsg.append((((currentTime - beforeTime) / 1000) / 60) / 60);
                checkMsg.append(":");

                // 분
                checkMsg.append((((currentTime - beforeTime) / 1000) / 60) % 60);
                checkMsg.append(":");

                // 초
                checkMsg.append(((currentTime - beforeTime) / 1000) % 60);
                checkMsg.append(".");

                // 밀리초
                checkMsg.append((currentTime - beforeTime) % 1000);
                checkMsg.append("}");
                checkMsg.append(" ==> ");
            }

            checkMsg.append("[ ");

            // get current class name
            checkMsg.append(ste.getFileName());

            checkMsg.append(" < ");

            // get current method name
            checkMsg.append(ste.getMethodName());

            checkMsg.append(" : ");

            // get current line number
            checkMsg.append(ste.getLineNumber());

            checkMsg.append(" > ");
            checkMsg.append(" ]");

            // save current time
            beforeTime = currentTime;

            // output log
            Log.i(SERVICE, checkMsg.toString());
        }
        catch (Exception ex)
        {
            // Occured Unexpected Error
            Log.e(SERVICE, "Occured Unexpected Error", ex);
        }
    }

    /**
     * make log to readability
     *
     * ex).
     * [ Main.java ==============> onCreateName :   211 ] Heloo
     * [ Maistere.java ==========>        onReq :    21 ] HelooAs
     *
     * @param ste
     * @param logMsg
     */
    private static synchronized void makeDevelopInfo(StackTraceElement ste, StringBuffer logMsg)
    {
        logMsg.append("[ ");

        logMsg.append(ste.getFileName() + " ");

        logMsg.append(rightPad("", '=', FILE_NAME_LENGTH_LIMIT - ste.getFileName().length()));

        logMsg.append(">");

        logMsg.append(leftPad("", ' ', METHOD_NAME_LENGTH_LIMIT - ste.getMethodName().length()));

        logMsg.append(ste.getMethodName());

        logMsg.append(" : ");

        logMsg.append(leftPad("", ' ', LINE_NUMBER_LENGTH_LIMIT - String.valueOf(ste.getLineNumber()).length()));

        logMsg.append(ste.getLineNumber());

        logMsg.append(" ] ");
    }

    /**
     * message
     *
     * @param source
     * @param fill
     * @param length
     * @return
     */
    private static synchronized String rightPad(String source, char fill, int length)
    {
        if(source.length() > length)
        {
            return source;
        }
        char[] out = new char[length];
        System.arraycopy(source.toCharArray(), 0, out, 0, source.length());
        Arrays.fill(out, source.length(), length, fill);

        return new String(out);
    }

    /**
     * message
     *
     * @param source
     * @param fill
     * @param length
     * @return
     */
    private static synchronized String leftPad(String source, char fill, int length)
    {
        if(source.length() > length)
        {
            return source;
        }
        char[] out = new char[length];
        int sourceOffset = length - source.length();
        System.arraycopy(source.toCharArray(), 0, out, sourceOffset, source.length());
        Arrays.fill(out, 0, sourceOffset, fill);

        return new String(out);
    }
}