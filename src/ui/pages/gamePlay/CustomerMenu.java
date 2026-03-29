package ui.pages.gamePlay;

import java.util.ArrayList;
import java.util.List;

public class CustomerMenu {

    private static void addNoodlePaths(List<String> folders, String broth, String noodle, boolean hasMeatball, boolean hasPorkSlices, boolean hasPorkRind) {
        String base = "/images/gamePlay/ingredients/noodles/finishedNoodles/" + broth + "/" + noodle + "/";

        folders.add(base + "no_addon.png");

        if (hasMeatball) {
            folders.add(base + "meatball/idle.png");
            folders.add(base + "meatball/haveVegetable.png");
        }
        if (hasPorkSlices) {
            folders.add(base + "porkSlices/idle.png");
            folders.add(base + "porkSlices/haveVegetable.png");
        }
        if (hasPorkRind) {
            folders.add(base + "porkRind/idle.png");
            folders.add(base + "porkRind/haveVegetable.png");
        }

        if (hasMeatball && hasPorkSlices) {
            folders.add(base + "meatball&porkSlices/idle.png");
            folders.add(base + "meatball&porkSlices/haveVegetable.png");
        }
        if (hasMeatball && hasPorkRind) {
            folders.add(base + "meatball&porkRind/idle.png");
            folders.add(base + "meatball&porkRind/haveVegetable.png");
        }
        if (hasPorkSlices && hasPorkRind) {
            folders.add(base + "porkSlices&porkRind/idle.png");
            folders.add(base + "porkSlices&porkRind/haveVegetable.png");
        }

        if (hasMeatball && hasPorkSlices && hasPorkRind) {
            folders.add(base + "meatball&porkRind&porkSlices/idle.png");
            folders.add(base + "meatball&porkRind&porkSlices/haveVegetable.png");
        }
    }

    public static List<String> CustomerMenu(int levelID) {
        List<String> folders = new ArrayList<>();

        if (levelID >= 1) folders.add("/images/gamePlay/ingredients/drinks/cola/cola.png");
        if (levelID >= 2) folders.add("/images/gamePlay/ingredients/drinks/sprite/sprite.png");
        if (levelID >= 3) folders.add("/images/gamePlay/ingredients/drinks/orange/orange.png");
        if (levelID >= 4) folders.add("/images/gamePlay/ingredients/kanomTuay/picked.png");

        boolean m = levelID >= 1;
        boolean s = levelID >= 2;
        boolean r = levelID >= 5;

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
        List<String> allPaths = CustomerMenu(levelIO);

        if (allPaths.isEmpty()) return "";

        int i = (int)(Math.random() * allPaths.size());
        String selectedPath = allPaths.get(i);

        return selectedPath;
    }
}