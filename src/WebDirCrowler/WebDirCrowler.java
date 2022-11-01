package WebDirCrowler;

import java.net.*;
import java.io.*;
import java.util.*;

public class WebDirCrowler {
    // attributes
    public String url;
    public String dirListPath = "/Users/marcobertagnolli/Desktop/Programmazione/simpleWebFuzze/defaultList/dirList.txt";
    public int    timeOut;
    public int    timeStop;

    private String HTTP_USER_AGENT = "Mozilla/5.0";
    private String HTTP_CONNECTION = "close";

    public static void main(String[] args){
        System.out.println("Web dir crowler initializated!");
    }

    // methods
    public double startDirScrapping(String timeFormat){
        String[] parsedList        = this.parseList(this.dirListPath);
        int    requestFreq         = 0;
        int    requestN            = parsedList.length;
        long   reqTimeDeltaEverage = 0;
        long[] reqTimeDeltas       = new long[requestN + 1];

        System.out.printf("\nStarting directory fuzz at: %s \n\n", this.url);

        for(int reqNum = 0; reqNum < requestN; reqNum++){
            String currentRoute = parsedList[reqNum];

            long startTime = System.nanoTime();
            int reqReturnCode = this.sendNewReq(currentRoute);
            long finishTime = System.nanoTime();

            reqTimeDeltas[reqNum] = finishTime - startTime;

            if(reqReturnCode <= 600){
                // normal HTTP status code
                System.out.println("n: " + (reqNum + 1) + ", dir: " + parsedList[reqNum] + ", code: " + reqReturnCode);
                // increment for index
                reqNum++;
                // summ all req time deltas
                reqTimeDeltaEverage += reqTimeDeltas[(reqNum - 1)];
            } else {
                // connection or request exception code
                System.out.println("Get request n." + reqNum + " can't be done code: " + reqReturnCode);
            }
        }
        // calc the everage time
        reqTimeDeltaEverage = reqTimeDeltaEverage / requestN;
        // convert in correct time format
        switch(timeFormat){
            case "ns":
                return (double) reqTimeDeltaEverage;
            case "ms":
                return (double) ((double) reqTimeDeltaEverage / 1000000);
            case "s":
                return (double) ((double) reqTimeDeltaEverage / 1000000000);
            default:
                System.out.println("Invalid time format");
        }

        return 0;
    }

    public void stopFuzzing(){
        System.out.printf("Stopping direcotry fuzz at: %s", this.url);
    }

    public void autoWordList(){
        System.out.print("aaaaa");
    }

    public double getRequestTime(String timeFormat){
        int    requestFreq             = 0;
        int    testReqNum              = 15;
        long   testReqTimeDeltaEverage = 0;
        long[] testReqTimeDeltas       = new long[testReqNum + 1];

        for(int reqNum = 0; reqNum < testReqNum; /*increment in if statement*/){
            long startTime = System.nanoTime();
            int reqReturnCode = this.sendNewReq("/");
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

    private int sendNewReq(String route){
        int statusCode = 200;
        try {
            URL url = new URL(this.url + route);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", HTTP_USER_AGENT);
            con.setRequestProperty("Connection", HTTP_CONNECTION);
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
    private String[] parseList(String path){
        String[] errReturn = {"err"};

        int nLineFile = 0;

        try {
            File listFile      = new File(path);
            Scanner readedList = new Scanner(listFile);

            while(readedList.hasNextLine()){
                String currentLine = readedList.nextLine();
                nLineFile++;
            }
            readedList.close();
        } catch (java.io.FileNotFoundException err){
            System.out.println("Cant open word list: " + err);
            return errReturn;
        }

        String[] wordlist = new String[nLineFile];

        try {
            File listFile      = new File(path);
            Scanner readedList = new Scanner(listFile);

            for(int nWord = 0; readedList.hasNextLine(); nWord++){
                wordlist[nWord] = readedList.nextLine();
            }
            readedList.close();
        } catch (java.io.FileNotFoundException err){
            System.out.println("Cant open word list: " + err);
            return errReturn;
        }

        return wordlist;
    }
}
