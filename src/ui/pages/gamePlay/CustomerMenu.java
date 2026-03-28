package ui.pages.gamePlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerMenu {

    private static void addNoodlePaths(List<String> folders, String broth, String noodle, boolean hasMeatball, boolean hasPorkSlices, boolean hasPorkRind) {
        String base = "resources/images/gamePlay/ingredients/noodles/finishedNoodles/" + broth + "/" + noodle + "/";

        folders.add(base + "no_addon.png");

        if (hasMeatball) folders.add(base + "meatball");
        if (hasPorkSlices) folders.add(base + "porkSlices");
        if (hasPorkRind) folders.add(base + "porkRind");

        if (hasMeatball && hasPorkSlices) folders.add(base + "meatball&porkSlices");
        if (hasMeatball && hasPorkRind) folders.add(base + "meatball&porkRind");
        if (hasPorkSlices && hasPorkRind) folders.add(base + "porkSlices&porkRind");

        if (hasMeatball && hasPorkSlices && hasPorkRind) folders.add(base + "meatball&porkRind&porkSlices");
    }

    public static List<String> CustomerMenu(int levelID) {
        List<String> folders = new ArrayList<>();

        if (levelID >= 1) folders.add("resources/images/gamePlay/ingredients/drinks/cola");
        if (levelID >= 2) folders.add("resources/images/gamePlay/ingredients/drinks/sprite");
        if (levelID >= 3) folders.add("resources/images/gamePlay/ingredients/drinks/orange");
        if (levelID >= 4) folders.add("resources/images/gamePlay/ingredients/kanomTuay/picked.png");

        boolean m = levelID >= 1;
        boolean s = levelID >= 2;
        boolean r = levelID >= 3;

        addNoodlePaths(folders, "namTok", "riceThinWideVermicelli", m, s, r);
        addNoodlePaths(folders, "namTok", "yellow", m, s, r);
        addNoodlePaths(folders, "clearBroth", "riceThinWideVermicelli", m, s, r);
        addNoodlePaths(folders, "clearBroth", "yellow", m, s, r);

        if (levelID >= 2) {
            addNoodlePaths(folders, "yenTaFo", "riceThinWideVermicelli", m, s, r);
            addNoodlePaths(folders, "yenTaFo", "yellow", m, s, r);
        }

        if (levelID >= 3) {
            addNoodlePaths(folders, "tomYum", "riceThinWideVermicelli", m, s, r);
            addNoodlePaths(folders, "tomYum", "yellow", m, s, r);
        }

        if (levelID >= 4) {
            addNoodlePaths(folders, "namTok", "greenEgg", m, s, r);
            addNoodlePaths(folders, "clearBroth", "greenEgg", m, s, r);
            addNoodlePaths(folders, "yenTaFo", "greenEgg", m, s, r);
            addNoodlePaths(folders, "tomYum", "greenEgg", m, s, r);
        }

        if (levelID >= 5) {
            addNoodlePaths(folders, "braisedPork", "riceThinWideVermicelli", m, s, r);
            addNoodlePaths(folders, "braisedPork", "yellow", m, s, r);
            addNoodlePaths(folders, "braisedPork", "greenEgg", m, s, r);
        }

        return folders;
    }

    // method for request food and drink from customer
    public static String getRandomFoodImage(int levelIO){
        List<String> folders = CustomerMenu(levelIO);
        List<File> allFiles = new ArrayList<>();

        for(String path : folders){
            File fileOrFolder = new File(path);

            if (fileOrFolder.isFile()) {
                allFiles.add(fileOrFolder);
            } else if (fileOrFolder.isDirectory()) {
                File[] files = fileOrFolder.listFiles();
                if (files != null){
                    for(File f : files){
                        // Ignore idle/picked mix-ups if a whole folder is scanned
                        if(f.isFile() && !f.getName().equalsIgnoreCase("idle.png") && !f.getName().equalsIgnoreCase("picked.png")){
                            allFiles.add(f);
                        }
                    }
                }
            }
        }

        if (allFiles.isEmpty()) return "";

        int i = (int)(Math.random() * allFiles.size());
        return allFiles.get(i).getPath();
    }
}