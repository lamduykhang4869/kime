package org.tensorflow.lite.examples.detection;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalData {

    public List<Integer> currentSign;
    public Map<Integer, List<Integer>> tempSign;
    public long timeStamp;
    public int max_space = 10;
    public int appear_time = 3;
    public int number_sign = 28;
    public Integer[] sign_counter;

    private static GlobalData INSTANCE;

    private GlobalData(){
        currentSign = new ArrayList<Integer>();
        tempSign = new HashMap<Integer, List<Integer>>();
        sign_counter = new Integer[number_sign];
    }
    public static GlobalData getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GlobalData();
        }
        return INSTANCE;
    }

    public void clean_tempSign(int value){
        for (int k: tempSign.keySet()){
            if (tempSign.get(k).contains(value))
                tempSign.get(k).remove(value);
        }
    }
    public void processTempSign(){
        Arrays.fill(sign_counter, 0);
        for (int k: tempSign.keySet()){
            if (timeStamp - k > max_space){
                tempSign.remove(k);
                continue;
            }
            for (Integer i: tempSign.get(k)){
                sign_counter[i] += 1;
                if (sign_counter[i] >= appear_time)
                    clean_tempSign(sign_counter[i]);

            }
        }
        Log.wtf("Hoang", "tempSign: " + timeStamp);
        Log.wtf("Hoang", "current signal: " + currentSign);
    }

}
