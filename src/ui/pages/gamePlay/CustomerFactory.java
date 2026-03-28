package ui.pages.gamePlay;

import java.util.ArrayList;
import java.util.List;

public class CustomerFactory {
    public static List<CustomerData> getCustomer(int levelID){

        switch (levelID){
            case 1:
                return new CustomerSetting().Setting(16,levelID);

            case 2:
                return new CustomerSetting().Setting(22,levelID);

            case 3:
                return new CustomerSetting().Setting(27,levelID);

            case 4:
                return new CustomerSetting().Setting(32,levelID);

            case 5:
                return new CustomerSetting().Setting(35,levelID);

            default:
                return new ArrayList<>(); // กัน error
        }
    }
}
