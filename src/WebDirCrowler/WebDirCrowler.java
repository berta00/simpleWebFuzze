package WebDirCrowler;

import java.net.*;
import java.io.*;

public class WebDirCrowler {
    // attributes
    public String url;
    public String wordListPath;
    public int    timeOut;
    public int    timeStop;

    private String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args){
        System.out.println("Web dir crowler initializated!");
    }

    // methods
    public void startFuzzing(){
        System.out.printf("Starting direcotry fuzz at: %s", this.url);

        
    }

    public void stopFuzzing(){
        System.out.printf("Stopping direcotry fuzz at: %s", this.url);
    }

    public void autoWordList(){
        System.out.print("aaaaa");
    }

    public double getRequestTime(String timeFormat){
        int requestFreq = 0;
        int testReqNum = 15;
        long testReqTimeDeltaEverage = 0;
        long[] testReqTimeDeltas = new long[(testReqNum)+1];

        for(int reqNum = 0; reqNum < testReqNum; /*increment in if statement*/){
            long startTime = System.nanoTime();
            // make the test request
            int reqReturnCode = this.sendNewReq();
            long finishTime = System.nanoTime();

            testReqTimeDeltas[reqNum] = finishTime - startTime;

            if(reqReturnCode <= 600){
                // normal HTTP status code
                System.out.println("Get request n." + (reqNum + 1) + " code: " + reqReturnCode);
                // increment for index
                reqNum++;
                // summ all req time deltas
                testReqTimeDeltaEverage += testReqTimeDeltas[(reqNum - 1)];
            } else{
                // connection or request exception code
                System.out.println("Get request n." + reqNum + " can't be done code: " + reqReturnCode);
            }
        }
        // calc the everage time
        testReqTimeDeltaEverage = testReqTimeDeltaEverage / testReqNum;
        // convert in correct time format
        switch(timeFormat){
            case "ns":
                return (double) testReqTimeDeltaEverage;
            case "ms":
                return (double) ((double) testReqTimeDeltaEverage / 1000000);
            case "s":
                return (double) ((double) testReqTimeDeltaEverage / 1000000000);
            default:
                System.out.println("Invalid time format");
        }

        return 0;
    }

    private int sendNewReq(){
        int statusCode = 200;
        try {
            URL url = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            statusCode = con.getResponseCode();

        } catch (ProtocolException err){
            System.out.println("Protocol error: " + err + "\n");
            return 601;

        } catch (IOException err){
            System.out.println("IO error: " + err + "\n");
            return 602;
        }

        return statusCode;
    }
}
