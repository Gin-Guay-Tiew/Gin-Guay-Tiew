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
    Object[] result;

    public List<CustomerData> Setting(int quantity,int level){
        int Teacher_Bank_wallet = (int)(Math.random() * 251) + 50;
        List<CustomerData> customer = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        setNPC(list, level);

        for (int i = 0; i < quantity; i++) {
            int randomInt = (int)(Math.random() * list.size());
            typeCustomer = list.get(randomInt).get(0);
            skinCustomer = list.get(randomInt).get(1);

            if (level == 1) {
                result = tSet(typeCustomer,50,0,0,Teacher_Bank_wallet,55);
            } else if (level == 2) {
                result = tSet(typeCustomer,55,55,0,Teacher_Bank_wallet,60);
            } else if (level == 3) {
                result = tSet(typeCustomer,60,60,0,Teacher_Bank_wallet,65);
            } else if (level == 4) {
                result = tSet(typeCustomer,65,65,0,Teacher_Bank_wallet,70);
            } else if (level == 5) {
                result = tSet(typeCustomer,70,70,95,Teacher_Bank_wallet,75);
            }


            String timeSet = (String) result[0];
            int patience = (int) result[1];
            int money = (int) result[2];


            customer.add(new CustomerData(
                    level,
                        "resources/images/gamePlay/customer/"+typeCustomer+"/"+skinCustomer+"_happy.png",
                    "resources/images/gamePlay/customer/Time/"+timeSet+".gif",
                    typeCustomer,skinCustomer,CustomerMenu.getRandomFoodImage(level),
                        money,
                        211,
                        78,
                    patience
                ));
            }
        return customer;
    }



    public Object[] tSet(String typeCustomer,int general, int hungry,int vip, int JarnBank, int working){
        if (typeCustomer.equals("general") || typeCustomer.equals("mars")){
            return new Object[]{"general", 46,general};
        } else if (typeCustomer.equals("hungry")){
            return new Object[]{"hungry-vip", 21,hungry};
        } else if (typeCustomer.equals("JarnBank")){
            return new Object[]{"jarnBank", 17,JarnBank};
        } else if (typeCustomer.equals("working")){
            return new Object[]{"working", 32,working};
        } else if (typeCustomer.equals("VIP")) {
            return new Object[]{"hungry-vip", 21,vip};
        }
        System.out.println("Unknown type: " + typeCustomer);
        return new Object[]{"general", 46,general};
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
