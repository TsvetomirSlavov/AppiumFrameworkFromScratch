package core;

import java.util.Date;

/**
 * Created by ceko on 08/29/2016.
 */
public class Timer {

    public long startStamp;

    public void start(){
        startStamp = getTimeStamp();
    }

    public static long getTimeStamp(){
        return new Date().getTime();
    }

    public boolean expired(int seconds){
        //CASTING
        int difference = (int) ((getTimeStamp() - startStamp) / 1000);
        return difference > seconds;
    }




}
