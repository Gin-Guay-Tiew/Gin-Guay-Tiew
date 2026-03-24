package ui.pages.gamePlay;

import java.util.ArrayList;
import java.util.List;

public class CustomerFactory {
    public static List<CustomerData> getCustomer(int levelID){

        List<CustomerData> list = new ArrayList<>();
        switch (levelID){
            case 1:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_male02 2.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/Oldman_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_female01_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        591,
                        80
                ));
                break;
            case 2:

                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_female01_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/working/WorkingWoman_happy 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/hungry/HungryMan_happy 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        591,
                        80
                ));
                break;

            case 3:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/VIP/VIPman_happy 1.png",
                        "resources/images/gamePlay/customer/Time/hungry-vip.gif",
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/JarnBank/JarnBank1 2.png",
                        "resources/images/gamePlay/customer/Time/jarnBank.gif",
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/Oldwoman_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        591,
                        80
                ));
                break;

            case 4:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_male02 2.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        211,
                        78
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/Oldman_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/general/CollegeStudent_female01_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        591,
                        80
                ));
                break;

            case 5:
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/mars/Alien_angry 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        211,
                        60
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/JarnBank/JarnBank3 2.png",
                        "resources/images/gamePlay/customer/Time/jarnBank.gif",
                        397,
                        59
                ));
                list.add(new CustomerData(
                        "resources/images/gamePlay/customer/mars/Astronaut_bored 1.png",
                        "resources/images/gamePlay/customer/Time/general.gif",
                        591,
                        60
                ));
                break;
        }

        return list;
    }
}
