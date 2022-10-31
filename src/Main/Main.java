package Main;

import WebDirCrowler.WebDirCrowler;

class Main {
    public static String banner  = "\n                __          ____                         \n _      _____  / /_        / __/_  __________  ___  _____\n| | /| / / _ \\/ __ \\______/ /_/ / / /_  /_  / / _ \\/ ___/\n| |/ |/ /  __/ /_/ /_____/ __/ /_/ / / /_/ /_/  __/ /    \n|__/|__/\\___/_.___/     /_/  \\__,_/ /___/___/\\___/_/     \n\n";

    public static String dirList = "./../../defaultList/dirList.txt";

    /*
        Usage:
            simpleWebFuze <target> <options>
        Options:
            -d <optionaly dir list>: start web direcotry fuzzing
            -
     */
    public static void main(String[] args){
        String banner = "\n                __          ____                         \n _      _____  / /_        / __/_  __________  ___  _____\n| | /| / / _ \\/ __ \\______/ /_/ / / /_  /_  / / _ \\/ ___/\n| |/ |/ /  __/ /_/ /_____/ __/ /_/ / / /_/ /_/  __/ /    \n|__/|__/\\___/_.___/     /_/  \\__,_/ /___/___/\\___/_/     \n\n";

        System.out.print(banner);

        // parse the args
        for(int element = 0; element < args.length; element++){
            if(args[element].charAt(0) == '-'){
                // it is a flag
                switch (args[element]){
                    // dir fuze
                    case "-f":
                        if(args[element + 1].charAt(0) != '-'){
                            System.out.println("aaa");
                        } else {
                            System.out.println("aaa");
                        }
                }
            }
        }

        WebDirCrowler req = new WebDirCrowler();
        req.url = "http://localhost:8080";
        req.startDirScrapping("s");

        // if no flag
        defaultStartup("www.example.org");

    }
    private static void defaultStartup(String testUrl){
        // make a test request
        WebDirCrowler testReq = new WebDirCrowler();
        testReq.url = "https://www.google.it/";

        double requestFreq = testReq.getRequestTime("s");

        System.out.println("\nSingle request time: " + requestFreq + " s");
    }
}
