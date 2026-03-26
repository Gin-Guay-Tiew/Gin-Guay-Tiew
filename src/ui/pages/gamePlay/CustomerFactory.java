package ui.pages.gamePlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerFactory {

    //method for add folder for random in level
    public static  List<String> getFoldersByLevel(int levelID){
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
        List<String> folders = getFoldersByLevel(levelIO);
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

    public static List<CustomerData> getCustomer(int levelID){

        List<CustomerData> list = new ArrayList<>();
        switch (levelID){
            case 1:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_male02 2.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        211,
                        65
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/Oldman_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_female01_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        591,
                        50
                ));
                break;
            case 2:

                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_female01_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/working/WorkingWoman_happy 1.png",
                        "resources/images/gamePlay/customer/Time/working.gif",
                        getRandomFoodImage(levelID),
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/hungry/HungryMan_happy 1.png",
                        "resources/images/gamePlay/customer/Time/hungry-vip.gif",
                        getRandomFoodImage(levelID),
                        591,
                        80
                ));
                break;

            case 3:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/VIP/VIPman_happy 1.png",
                        "resources/images/gamePlay/customer/Time/hungry-vip.gif",
                        getRandomFoodImage(levelID),
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/JarnBank/JarnBank1 2.png",
                        "resources/images/gamePlay/customer/Time/jarnBank.gif",
                        getRandomFoodImage(levelID),
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/Oldwoman_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        591,
                        80
                ));
                break;

            case 4:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/hungry/HungryMan_angry 1.png",
                        "resources/images/gamePlay/customer/Time/hungry-vip.gif",
                        getRandomFoodImage(levelID),
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/working/WorkingMan_bored 1.png",
                        "resources/images/gamePlay/customer/Time/working.gif",
                        getRandomFoodImage(levelID),
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/VIP/VIPwoman_happy 1.png",
                        "resources/images/gamePlay/customer/Time/hungry-vip.gif",
                        getRandomFoodImage(levelID),
                        591,
                        80
                ));
                break;

            case 5:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/mars/Alien_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        211,
                        60
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/JarnBank/JarnBank3 2.png",
                        "resources/images/gamePlay/customer/Time/jarnBank.gif",
                        getRandomFoodImage(levelID),
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/mars/Astronaut_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        getRandomFoodImage(levelID),
                        591,
                        60
                ));
                break;
        }

        return list;
    }
}
