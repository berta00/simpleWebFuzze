package Main;

import WebDirCrowler.WebDirCrowler;

class Main {
    public static void main(String[] args){
        String banner = "\n                __          ____                         \n _      _____  / /_        / __/_  __________  ___  _____\n| | /| / / _ \\/ __ \\______/ /_/ / / /_  /_  / / _ \\/ ___/\n| |/ |/ /  __/ /_/ /_____/ __/ /_/ / / /_/ /_/  __/ /    \n|__/|__/\\___/_.___/     /_/  \\__,_/ /___/___/\\___/_/     \n\n";

        // parse the args
        for(int element = 0; element < args.length; element++){
            System.out.println(element);
        }

        // program startup
        System.out.print(banner);

        // make a test request
        WebDirCrowler testReq = new WebDirCrowler();
        testReq.url = "https://www.google.it/";
        double requestFreq = testReq.getRequestTime("s");

        System.out.println("\nSingle request time: " + requestFreq + " s");
    }
}
