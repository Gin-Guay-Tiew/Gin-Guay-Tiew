package ui.pages.gamePlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerMenu {
    //method for add folder for random in level
    public static List<String> CustomerMenu(int levelID){
        List<String> folders = new ArrayList<>();

        if (levelID >= 1){
            folders.add("resources/images/gamePlay/ingredients/drinks/cola");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/riceThinWideVermicelli/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTokk/riceThinWideVermicelli/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/yellow/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTokk/yellow/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/riceThinWideVermicelli/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/riceThinWideVermicelli/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/yellow/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/yellow/no_addon.png");

        }

        if (levelID >= 2){
            folders.add("resources/images/gamePlay/ingredients/drinks/sprite");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/riceThinWideVermicelli/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/riceThinWideVermicelli/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/riceThinWideVermicelli/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/riceThinWideVermicelli/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/yellow/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/yellow/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/yellow/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/riceThinWideVermicelli/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/riceThinWideVermicelli/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/yellow/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/yellow/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/riceThinWideVermicelli/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/riceThinWideVermicelli/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/yellow/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/yellow/porkSlices");
        }

        if (levelID >= 3){
            folders.add("resources/images/gamePlay/ingredients/drinks/orange");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/riceThinWideVermicelli/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/riceThinWideVermicelli/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/riceThinWideVermicelli/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/yellow/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/yellow/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/yellow/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/yellow/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/riceThinWideVermicelli/no_addon.png");
        }

        if (levelID >= 4){
            folders.add("resources/images/gamePlay/ingredients/kanomTuay/picked.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/greenEgg/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/greenEgg/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/greenEgg/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/greenEgg/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/greenEgg/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/greenEgg/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/greenEgg/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/greenEgg/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/greenEgg/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/greenEgg/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/greenEgg/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/tomYum/greenEgg/porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/greenEgg/no_addon.png");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/greenEgg/meatball");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/greenEgg/meatball&porkSlices");
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles/yenTaFo/greenEgg/porkSlices");

        }

        if (levelID == 5){
            folders.add("resources/images/gamePlay/ingredients/noodles/finishedNoodles");
        }

        return  folders;
    }




    // method for request food and drink from customer
    public static String getRandomFoodImage(int levelIO){
        List<String> folders = CustomerMenu(levelIO);
        List<File> allFiles = new ArrayList<>();

        for(String path : folders){
            File[] files = new File(path).listFiles();
            if (files != null){
                for(File f : files){
                    if(f.isFile()){
                        allFiles.add(f);
                    }
                }
            }
        }

        int i = (int)(Math.random() * allFiles.size());
        return allFiles.get(i).getPath();
    }
}
