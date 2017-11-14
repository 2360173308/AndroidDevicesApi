package com.sy.ondemo.utils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
/**
 * 创建时间： 2017/11/14 0014.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：通过超级用户权限修改一下参数
 */
public class SuUtils {
    private static Process process;
    private static  String TAG = SuUtils.class.getSimpleName();

    public static void initProcess(){
        if (process ==null){
            try {
                process = Runtime.getRuntime().exec("su");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据包名关闭app
     * @param packageName
     */
    public static void killProcess(String packageName){
        initProcess();
        OutputStream outputStream = process.getOutputStream();
        String cmd = "am force-stop " + packageName + " \n";
        try {
            outputStream.write(cmd.getBytes());
            outputStream.flush();
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 截图
     */
    public static void screenShot(String path){
        initProcess();
        OutputStream outputStream = process.getOutputStream();
        String cmd = "/system/bin/screencap -p /"+ path;
        try {
            outputStream.write(cmd.getBytes());
            outputStream.flush();
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启的指令
     */
    public static void roobt() {
        initProcess();
        OutputStream outputStream = process.getOutputStream();
//        String cmd = "am force-stop " + name + " \n";
        String cmd = "reboot";
        try {
            outputStream.write(cmd.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 端口重定向
     */
    public static void portReset(){
        initProcess();
        OutputStream outputStream = process.getOutputStream();
        String cmd = "iptables -t nat -A PREROUTING -p tcp -m tcp --dport 80 -j REDIRECT --to-ports 8080 ";
        try {
            outputStream.write(cmd.getBytes());
            outputStream.flush();
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //命令行修改系统时间
    public static void testDate(){
        try {
            Process process = Runtime.getRuntime().exec("su");
            String datetime="20131023.112800"; //测试的设置的时间【时间格式 yyyyMMdd.HHmmss】
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT\n");
            os.writeBytes("/system/bin/date -s "+datetime+"\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输出流
     */
    private static void close() {
        if (process != null)
            try {
                process.getOutputStream().close();
                process = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    //静默安装应用，静默卸载应用
    public  static  void StartCommand(String command,String path){
        String commande = command;
        String pathApk =path;
        if (commande.equals("install")){//静默安装
            try {
//                   pm install -r/mnt/sdcard/test.apk
                exeCommand("pm su");
                exeCommand("pm install -r"+pathApk);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (commande.equals("uninstall")){//静默卸载
            String cmd = "pm uninstall "+ pathApk;
            Log.d(TAG, "StartCommand: -----------"+cmd);

            try {
//                   pm install -r/mnt/sdcard/test.apk
                exeCommand("pm su");
                exeCommand(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {

        }

    }
    private static void exeCommand(String command) throws IOException{
        Runtime runtime = Runtime.getRuntime();
        Process pro = runtime.exec(command);
        try {
            if (pro.waitFor()!=0){
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line =null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+"");
            }
            Log.d(TAG, "exeCommand: 123"+stringBuffer.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d(TAG, "exeCommand: 456"+e);
        } finally {

            pro.destroy();
        }
    }
}
