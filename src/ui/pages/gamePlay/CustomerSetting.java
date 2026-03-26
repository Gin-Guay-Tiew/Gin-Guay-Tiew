package ui.pages.gamePlay;

import java.util.ArrayList;
import java.util.List;

public class CustomerSetting {
    List<List<String>> general = List.of(
            List.of("general", "CollegeStudent_female01"),
            List.of("general", "CollegeStudent_male01"),
            List.of("general", "CollegeStudent_male02"),
            List.of("general", "Oldman"),
            List.of("general", "Oldwoman")
    );

    List<List<String>> hungry = List.of(
            List.of("hungry", "HungryMan"),
            List.of("hungry", "HungryWoman")
    );

    List<List<String>> working = List.of(
            List.of("working", "WorkingMan"),
            List.of("working", "WorkingWoman")
    );

    List<List<String>> teacher = List.of(
            List.of("JarnBank", "JarnBank")
    );

    List<List<String>> mars = List.of(
            List.of("mars", "Alien"),
            List.of("mars", "Astronaut")
    );

    List<List<String>> vip = List.of(
            List.of("VIP", "VIPman"),
            List.of("VIP", "VIPwoman")
    );


    String typeCustomer;
    String skinCustomer;

    public List<CustomerData> Setting(int quantity,int level){
        List<CustomerData> customer = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        setNPC(list, level);

        for (int i = 0; i < quantity; i++) {
            int randomInt = (int)(Math.random() * list.size());
            typeCustomer = list.get(randomInt).get(0);
            skinCustomer = list.get(randomInt).get(1);

            Object[] result = tSet(typeCustomer);

            String timeSet = (String) result[0];
            int patience = (int) result[1];


            customer.add(new CustomerData(
                        "resources/images/gamePlay/customer/"+typeCustomer+"/"+skinCustomer+"_happy.png",
                    "resources/images/gamePlay/customer/Time/"+timeSet+".gif",
                    typeCustomer,skinCustomer,CustomerMenu.getRandomFoodImage(level),
                        211,
                        78,
                    patience
                ));
            }
        return customer;
    }



    public Object[] tSet(String typeCustomer){
        if (typeCustomer.equals("general") || typeCustomer.equals("mars")){
            return new Object[]{"general", 46};
        } else if (typeCustomer.equals("hungry") || typeCustomer.equals("VIP")){
            return new Object[]{"hungry-vip", 21};
        } else if (typeCustomer.equals("JarnBank")){
            return new Object[]{"jarnBank", 17};
        } else if (typeCustomer.equals("working")){
            return new Object[]{"working", 32};
        }
        return new Object[]{"general", 46};
    }

    public void setNPC(List<List<String>> list, int level){
        list.addAll(general);

        if (level >= 1) {
            list.addAll(working);
            list.addAll(teacher);
        }
        if (level >= 2) {
            list.addAll(hungry);
        }
        if (level >= 3) {
            list.addAll(vip);
        }
        if (level >= 5) {
            list.addAll(mars);
        }
    }
}
