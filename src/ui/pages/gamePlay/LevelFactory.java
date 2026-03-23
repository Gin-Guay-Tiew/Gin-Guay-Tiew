package ui.pages.gamePlay;

import java.util.ArrayList;
import java.util.List;

//หน้านี้สำหรับเก็บข้อมูลในแต่ละเลเวล เราจะเปลี่ยนแค่ของในเลเวล มาเพิ่มเปลี่ยนเอาในนี้ได้เลย case 1,2,3,4,5  แทนเลขในแต่ละเลเวล
public class LevelFactory {
    public static LevelUIconfig getlevel(int levelId) {
        List<SlotSpec> slots = new ArrayList<>();
        String bg = "";

        switch (levelId) {
            case 1:
                bg = "resources/images/gamePlay/bg/LV1.gif";

                //mai
                slots.add(new SlotSpec("mai1",-2,200,120,114,"resources/images/gamePlay/aquiment/mai1.png","DRAG",null));
                slots.add(new SlotSpec("mai2",95,200,120,120,"resources/images/gamePlay/aquiment/mai2.png","DRAG",null));
                // down counter
                slots.add(new SlotSpec("pot",-86,240,387,387,"resources/images/gamePlay/counter/potter.png","STATIC",null));
                slots.add(new SlotSpec("bucket lv3",230,350,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png","STATIC",null));
                slots.add(new SlotSpec("trash",620,400,162,73,"resources/images/gamePlay/binn/empty.png","STATIC",null));

                //on counter



                //soup row 1
                slots.add(new SlotSpec("yenTafo",238,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","STATIC",null));
                slots.add(new SlotSpec("tomYum",308,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","STATIC",null));

                //soup row2
                slots.add(new SlotSpec("namTok",238,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","STATIC",null));
                slots.add(new SlotSpec("Pork",308,280,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png","STATIC",null));

                //towel
                slots.add(new SlotSpec("towel",360,150,240,240,"resources/images/gamePlay/counter/towel.png","STATIC",null));

                //add on
                slots.add(new SlotSpec("vegetable",575,170,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png","SPAWN","resources/images/gamePlay/ingredients/vegetables/picked.png"));
                slots.add(new SlotSpec("porkSlice",570,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png"));
                slots.add(new SlotSpec("meatball",634,260,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/meatball/picked.png"));
                slots.add(new SlotSpec("porkRind",694,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkRind/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkRind/picked.png"));

                //up counter

                slots.add(new SlotSpec("container",2,14,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png","STATIC",null));
                slots.add(new SlotSpec("greenEgg",143,117,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png","SPAWN","resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png"));
                slots.add(new SlotSpec("noodlesBowl",226,120,76,76,"resources/images/gamePlay/bowl/decorate.png","STATIC",null));
                slots.add(new SlotSpec("chopsticks",282,110,86,86,"resources/images/gamePlay/counter/chopsticks.png","STATIC",null));
                slots.add(new SlotSpec("kanomTuay",670,150,113,55,"resources/images/gamePlay/ingredients/kanomTuay/idle.png","SPAWN","resources/images/gamePlay/ingredients/kanomTuay/picked.png"));


                break;

            case 2:
                bg = "resources/images/gamePlay/bg/LV2.gif";
                //mai
                slots.add(new SlotSpec("mai1",-2,200,120,114,"resources/images/gamePlay/aquiment/mai1.png","DRAG",null));
                slots.add(new SlotSpec("mai2",95,200,120,120,"resources/images/gamePlay/aquiment/mai2.png","DRAG",null));
                // down counter
                slots.add(new SlotSpec("pot",-86,240,387,387,"resources/images/gamePlay/counter/potter.png","STATIC",null));
                slots.add(new SlotSpec("bucket lv3",230,350,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png","STATIC",null));
                slots.add(new SlotSpec("trash",620,400,162,73,"resources/images/gamePlay/binn/empty.png","STATIC",null));

                //on counter



                //soup row 1
                slots.add(new SlotSpec("yenTafo",238,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","STATIC",null));
                slots.add(new SlotSpec("tomYum",308,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","STATIC",null));

                //soup row2
                slots.add(new SlotSpec("namTok",238,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","STATIC",null));
                slots.add(new SlotSpec("Pork",308,280,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png","STATIC",null));

                //towel
                slots.add(new SlotSpec("towel",360,150,240,240,"resources/images/gamePlay/counter/towel.png","STATIC",null));

                //add on
                slots.add(new SlotSpec("vegetable",575,170,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png","SPAWN","resources/images/gamePlay/ingredients/vegetables/picked.png"));
                slots.add(new SlotSpec("porkSlice",570,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png"));
                slots.add(new SlotSpec("meatball",634,260,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/meatball/picked.png"));
                slots.add(new SlotSpec("porkRind",694,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkRind/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkRind/picked.png"));

                //up counter

                slots.add(new SlotSpec("container",2,14,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png","STATIC",null));
                slots.add(new SlotSpec("greenEgg",143,117,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png","SPAWN","resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png"));
                slots.add(new SlotSpec("noodlesBowl",226,120,76,76,"resources/images/gamePlay/bowl/decorate.png","STATIC",null));
                slots.add(new SlotSpec("chopsticks",282,110,86,86,"resources/images/gamePlay/counter/chopsticks.png","STATIC",null));
                slots.add(new SlotSpec("kanomTuay",670,150,113,55,"resources/images/gamePlay/ingredients/kanomTuay/idle.png","SPAWN","resources/images/gamePlay/ingredients/kanomTuay/picked.png"));


                break;

            case 3:
                bg = "resources/images/gamePlay/bg/LV3.gif";

                //mai
                slots.add(new SlotSpec("mai1",-2,200,120,114,"resources/images/gamePlay/aquiment/mai1.png","DRAG",null));
                slots.add(new SlotSpec("mai2",95,200,120,120,"resources/images/gamePlay/aquiment/mai2.png","DRAG",null));
                // down counter
                slots.add(new SlotSpec("pot",-86,240,387,387,"resources/images/gamePlay/counter/potter.png","STATIC",null));
                slots.add(new SlotSpec("bucket lv3",230,350,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png","STATIC",null));
                slots.add(new SlotSpec("trash",620,400,162,73,"resources/images/gamePlay/binn/empty.png","STATIC",null));

                //on counter



                //soup row 1
                slots.add(new SlotSpec("yenTafo",238,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","STATIC",null));
                slots.add(new SlotSpec("tomYum",308,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","STATIC",null));

                //soup row2
                slots.add(new SlotSpec("namTok",238,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","STATIC",null));
                slots.add(new SlotSpec("Pork",308,280,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png","STATIC",null));

                //towel
                slots.add(new SlotSpec("towel",360,150,240,240,"resources/images/gamePlay/counter/towel.png","STATIC",null));

                //add on
                slots.add(new SlotSpec("vegetable",575,170,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png","SPAWN","resources/images/gamePlay/ingredients/vegetables/picked.png"));
                slots.add(new SlotSpec("porkSlice",570,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png"));
                slots.add(new SlotSpec("meatball",634,260,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/meatball/picked.png"));
                slots.add(new SlotSpec("emptyBox",694,260,102,102,"resources/images/gamePlay/aquiment/emptyBox.png","STATIC",null));

                //up counter

                slots.add(new SlotSpec("container",2,14,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png","STATIC",null));
                slots.add(new SlotSpec("noodlesBowl",153,117,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png","SPAWN","resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png"));
                slots.add(new SlotSpec("chopsticks",226,120,76,76,"resources/images/gamePlay/counter/chopsticks.png","STATIC",null));

                break;

            case 4:
                bg = "resources/images/gamePlay/bg/LV4.gif";

                //mai
                slots.add(new SlotSpec("mai1",-2,200,120,114,"resources/images/gamePlay/aquiment/mai1.png","DRAG",null));
                slots.add(new SlotSpec("mai2",95,200,120,120,"resources/images/gamePlay/aquiment/mai2.png","DRAG",null));
                // down counter
                slots.add(new SlotSpec("pot",-86,240,387,387,"resources/images/gamePlay/counter/potter.png","STATIC",null));
                slots.add(new SlotSpec("bucket lv3",230,350,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png","STATIC",null));
                slots.add(new SlotSpec("trash",620,400,162,73,"resources/images/gamePlay/binn/empty.png","STATIC",null));

                //on counter



                //soup row 1
                slots.add(new SlotSpec("yenTafo",238,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","STATIC",null));
                slots.add(new SlotSpec("tomYum",308,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","STATIC",null));

                //soup row2
                slots.add(new SlotSpec("namTok",238,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","STATIC",null));
                slots.add(new SlotSpec("Pork",308,280,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png","STATIC",null));

                //towel
                slots.add(new SlotSpec("towel",360,150,240,240,"resources/images/gamePlay/counter/towel.png","STATIC",null));

                //add on
                slots.add(new SlotSpec("vegetable",575,170,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png","SPAWN","resources/images/gamePlay/ingredients/vegetables/picked.png"));
                slots.add(new SlotSpec("porkSlice",570,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png"));
                slots.add(new SlotSpec("meatball",634,260,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/meatball/picked.png"));
                slots.add(new SlotSpec("porkRind",694,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkRind/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkRind/picked.png"));

                //up counter

                slots.add(new SlotSpec("container",2,14,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png","STATIC",null));
                slots.add(new SlotSpec("greenEgg",143,117,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png","SPAWN","resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png"));
                slots.add(new SlotSpec("noodlesBowl",226,120,76,76,"resources/images/gamePlay/bowl/decorate.png","STATIC",null));
                slots.add(new SlotSpec("chopsticks",282,110,86,86,"resources/images/gamePlay/counter/chopsticks.png","STATIC",null));
                slots.add(new SlotSpec("kanomTuay",670,150,113,55,"resources/images/gamePlay/ingredients/kanomTuay/idle.png","SPAWN","resources/images/gamePlay/ingredients/kanomTuay/picked.png"));

                break;

            case 5:
                bg = "resources/images/gamePlay/bg/LV5.gif";

                //mai
                slots.add(new SlotSpec("mai1",-2,200,120,114,"resources/images/gamePlay/aquiment/mai1.png","DRAG",null));
                slots.add(new SlotSpec("mai2",95,200,120,120,"resources/images/gamePlay/aquiment/mai2.png","DRAG",null));
                // down counter
                slots.add(new SlotSpec("pot",-86,240,387,387,"resources/images/gamePlay/counter/potter.png","STATIC",null));
                slots.add(new SlotSpec("bucket lv3",230,350,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png","STATIC",null));
                slots.add(new SlotSpec("trash",620,400,162,73,"resources/images/gamePlay/binn/empty.png","STATIC",null));

                //on counter



                //soup row 1
                slots.add(new SlotSpec("yenTafo",238,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","STATIC",null));
                slots.add(new SlotSpec("tomYum",308,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","STATIC",null));

                //soup row2
                slots.add(new SlotSpec("namTok",238,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","STATIC",null));
                slots.add(new SlotSpec("Pork",308,280,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png","STATIC",null));

                //towel
                slots.add(new SlotSpec("towel",360,150,240,240,"resources/images/gamePlay/counter/towel.png","STATIC",null));

                //add on
                slots.add(new SlotSpec("vegetable",575,170,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png","SPAWN","resources/images/gamePlay/ingredients/vegetables/picked.png"));
                slots.add(new SlotSpec("porkSlice",570,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png"));
                slots.add(new SlotSpec("meatball",634,260,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/meatball/picked.png"));
                slots.add(new SlotSpec("porkRind",694,260,102,102,"resources/images/gamePlay/ingredients/addOn/porkRind/idle.png","SPAWN","resources/images/gamePlay/ingredients/addOn/porkRind/picked.png"));

                //up counter

                slots.add(new SlotSpec("container",2,14,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png","STATIC",null));
                slots.add(new SlotSpec("greenEgg",143,117,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png","SPAWN","resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png"));
                slots.add(new SlotSpec("noodlesBowl",226,120,76,76,"resources/images/gamePlay/bowl/decorate.png","STATIC",null));
                slots.add(new SlotSpec("chopsticks",282,110,86,86,"resources/images/gamePlay/counter/chopsticks.png","STATIC",null));
                slots.add(new SlotSpec("kanomTuay",670,150,113,55,"resources/images/gamePlay/ingredients/kanomTuay/idle.png","SPAWN","resources/images/gamePlay/ingredients/kanomTuay/picked.png"));

                break;
        }
        return new LevelUIconfig(bg, slots);
    }

}
