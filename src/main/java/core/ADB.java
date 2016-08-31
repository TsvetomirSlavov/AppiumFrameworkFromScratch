package core;

import core.managers.ServerManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * FIX ADB CONFLICT OF VERSION BY DELETING ALL DUPLICATES IN TOOLS IN SDK MANAGER!!!!!!!!!!!!!!!
 * utility methods
 */
public class ADB {

    private String ID;

    //constructor
    public ADB(String deviceID) {
        ID = deviceID;
    }

    public static String command(String command) {
        //USE LOGGER CLASS FOR ALL METHODS, BUT HOW, SIMPLE, THEY ALL CALL THIS METHOD command()
        MyLogger.log.debug("Formatting ADB Command: "+command);
        if (command.startsWith("adb"))
            command = command.replace("adb ", ServerManager.getAndroidHome() + "/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run adb commands only");
        MyLogger.log.debug("Formatted ADB Command: "+command);
        String output = ServerManager.runCommand(command);
        MyLogger.log.debug("Output of the  ADB Command: "+output);
        if (output == null) return "";
        else return output;
    }

    public static void killServer() {
        command("adb kill-server");
    }

    public static void startServer() {
        command("adb start-server");
    }

    public static ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        //DECLARING AN ARRAY IN THE LOOP DEFINITION: output.split("\n")
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        return devices;

    }

    public String getForegroundActivity() {
        //executes the comman in android like linux on the device not like windows that is why we say grep linux commands
        return command("adb -s " + ID + " shell dumpsys windows windows | grep mCurrentFocus");
    }

    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        //just to be consistent in case it returns 6.0 three characters not 6.0.1 five characters, so we can be consistent in usig this otput variable later
        if (output.length() == 3) output += ".0";
        return output;
    }

    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));

    }

    public ArrayList getInstalledPackages() {
        ArrayList packages = new ArrayList();
        //declare String[] array to hold the packages
        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String packageID : output) packages.add(packageID.replace("package:", "").trim());
        return packages;
    }

    //Open specific application
    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    //Clear Application Data     pm - means package manager command
    public void clearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    //Force stop specific application
    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " install " + packageID);
    }

    public void clearLogBuffer(String packageID) {
        command("adb -s " + ID + " shell -c");
    }

    //target: the location to storr the file
    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    public void deleteFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    public void takeScreenShot(String target) {
        command("adb -s " + ID + " shell screencap " + target);
    }

    public void rebootDevice() {
        command("adb -s " + ID + " reboot");
    }

    public String getDeviceModel() {
        return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    public String getDeviceSerialNumber() {
        return command("adb -s " + ID + " dhell getprop ro.serailno");
    }

    public String getDeviceCarrier() {
        return command("adb -s " + ID + " dhell getprop gsm.operator.alpha");
    }

    public ArrayList getLogcatProcesses() {
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList processes = new ArrayList();
        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    //ability to filter the log grep
    public Object startLogcat( final String logID, final String grep) {
        ArrayList pidBefore = getLogcatProcesses();

        Thread logcat = new Thread(new Runnable() {
            @Override
            public void run() {
                if(grep == null) {
                    command("adb -s " + ID + " shell logcat -v threadtime > /sdcard/" + logID + ".txt");
                }
                else command("adb -s "+ID+" shell logcat -v threadtime | grep -i '"+grep+"'> /sdcard/" +logID+ ".txt");
            }
        });
        logcat.setName(logID);
        logcat.start();
        logcat.interrupt();

        ArrayList pidAfter = getLogcatProcesses();
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(15)){
            if (pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
            if (pidAfter.size() > 0) break;
            pidAfter = getLogcatProcesses();
        }

        if (pidAfter.size() == 1) return pidAfter.get(0);
        else if (pidAfter.size() > 1)
            throw new RuntimeException("Multiple logcat processes were started when only one was expected!");
        else throw new RuntimeException("Failed to start logcat process!");
    }

    public void stopLogcat(Object PID){
        command("adb -s "+ID+" shell kill "+PID);
    }

    @Test
    public void test(){
        ID = "0715f7c98061163a";
        System.out.println("Processes Prior to starting new logcat: "+getLogcatProcesses());
        Object PID = startLogcat("1", null);
        System.out.println("Started logcat on PID: "+PID);
        System.out.println("Processes sfter starting new logcat: "+ getLogcatProcesses());
        stopLogcat(PID);
        System.out.println("Processes after stopping new logcat: "+getLogcatProcesses());
    }




}
