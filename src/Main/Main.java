package Main;

import WebDirCrowler.WebDirCrowler;

class Main {
    public String banner = "\n                __          ____                         \n _      _____  / /_        / __/_  __________  ___  _____\n| | /| / / _ \\/ __ \\______/ /_/ / / /_  /_  / / _ \\/ ___/\n| |/ |/ /  __/ /_/ /_____/ __/ /_/ / / /_/ /_/  __/ /    \n|__/|__/\\___/_.___/     /_/  \\__,_/ /___/___/\\___/_/     \n\n";

    public static void main(String[] args){
        String banner = "\n                __          ____                         \n _      _____  / /_        / __/_  __________  ___  _____\n| | /| / / _ \\/ __ \\______/ /_/ / / /_  /_  / / _ \\/ ___/\n| |/ |/ /  __/ /_/ /_____/ __/ /_/ / / /_/ /_/  __/ /    \n|__/|__/\\___/_.___/     /_/  \\__,_/ /___/___/\\___/_/     \n\n";

        // program startup
        System.out.print(banner);

        WebDirCrowler prova1 = new WebDirCrowler();
        // set some values
        prova1.url = "https://www.google.it/";

        int requestFreq = prova1.getRequestFrequence();
        System.out.println("\n" + requestFreq);
    }
}
