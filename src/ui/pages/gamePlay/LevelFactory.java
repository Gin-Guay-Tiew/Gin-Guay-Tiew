package ui.pages.gamePlay;

import logic.GamePlay.PlayerData;
import java.util.ArrayList;
import java.util.List;

//หน้านี้สำหรับเก็บข้อมูลในแต่ละเลเวล เราจะเปลี่ยนแค่ของในเลเวล มาเพิ่มเปลี่ยนเอาในนี้ได้เลย case 1,2,3,4,5  แทนเลขในแต่ละเลเวล
public class LevelFactory {

    public static int getReqMoney(int levelId) {
        switch (levelId) {
            case 1: return 350;
            case 2: return 550;
            case 3: return 850;
            case 4: return 1100;
            case 5: return 1300;
            default: return 0;
        }
    }

    public static int getLives(int levelId) {
        switch (levelId) {
            case 1: return 5;
            case 2: return 7;
            case 3: return 7;
            case 4: return 8;
            case 5: return 10;
            default: return 10;
        }
    }

    public static LevelUIConfig getLevel(int levelId, PlayerData data) {
        List<SlotSpec> slots = new ArrayList<>();
        String bg = "";

        // Determine Background per Level
        switch (levelId) {
            case 1: bg = "resources/images/gamePlay/bg/LV1.gif"; break;
            case 2: bg = "resources/images/gamePlay/bg/LV2.gif"; break;
            case 3: bg = "resources/images/gamePlay/bg/LV3.gif"; break;
            case 4: bg = "resources/images/gamePlay/bg/LV4.gif"; break;
            case 5: bg = "resources/images/gamePlay/bg/LV5.gif"; break;
            default: bg = "resources/images/gamePlay/bg/LV1.gif"; break;
        }

        // Dynamic Image Logic

        // Container Logic
        boolean hasVermicelli = data.isItemUnlocked("Rice vermicelli noodles");
        boolean hasWide = data.isItemUnlocked("Wide rice noodles");
        String containerImg = "resources/images/gamePlay/ingredients/noodles/container/yellow_thin.png";

        if (hasVermicelli && hasWide) {
            containerImg = "resources/images/gamePlay/ingredients/noodles/container/yellow_thin_rice_wide.png";
        } else if (hasVermicelli) {
            containerImg = "resources/images/gamePlay/ingredients/noodles/container/yelllow_thin_rice.png";
        } else if (hasWide) {
            containerImg = "resources/images/gamePlay/ingredients/noodles/container/yellow_thin_wide.png";
        }

        // Drink Bucket Logic
        boolean hasCola = data.isItemUnlocked("Cola");
        boolean hasSprite = data.isItemUnlocked("Sprite");
        boolean hasOrange = data.isItemUnlocked("Orange");
        String bucketImg = "resources/images/gamePlay/ingredients/drinks/bucket.png";

        if (hasCola && hasSprite && hasOrange) {
            bucketImg = "resources/images/gamePlay/ingredients/drinks/cola_sprite_orange.png";
        } else if (hasCola && hasSprite) {
            bucketImg = "resources/images/gamePlay/ingredients/drinks/cola_sprite.png";
        } else if (hasCola && hasOrange) {
            bucketImg = "resources/images/gamePlay/ingredients/drinks/cola_orange.png";
        } else if (hasCola) {
            bucketImg = "resources/images/gamePlay/ingredients/drinks/cola.png";
        }


        // Build Counter (Shared across ALL levels)

        // Drinks
        if (hasCola) slots.add(new SlotSpec("cola", 238, 355, 40, 120, null, "SPAWN", "resources/images/gamePlay/ingredients/drinks/cola/cola.png"));
        if (hasSprite) slots.add(new SlotSpec("sprite", 267, 355, 40, 120, null, "SPAWN", "resources/images/gamePlay/ingredients/drinks/sprite/sprite.png"));
        if (hasOrange) slots.add(new SlotSpec("orange", 301, 355, 40, 120, null, "SPAWN", "resources/images/gamePlay/ingredients/drinks/orange/orange.png"));

        // Soups
        switch (levelId) {
            case 1:
                slots.add(new SlotSpec("emptyPot",230,210,70,70,"resources/images/gamePlay/aquiment/emptyPot.png","STATIC",null));
                slots.add(new SlotSpec("emptyPot",300,210,70,70,"resources/images/gamePlay/aquiment/emptyPot.png","STATIC",null));
                slots.add(new SlotSpec("namTok",230,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/namTok/laped.png"));
                slots.add(new SlotSpec("emptyPot",300,280,70,70,"resources/images/gamePlay/aquiment/emptyPot.png","STATIC",null));
                break;
            case 2:
                slots.add(new SlotSpec("yenTafo",230,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/yenTaFo/laped.png"));
                slots.add(new SlotSpec("emptyPot",300,210,70,70,"resources/images/gamePlay/aquiment/emptyPot.png","STATIC",null));
                slots.add(new SlotSpec("namTok",230,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/namTok/laped.png"));
                slots.add(new SlotSpec("emptyPot",300,280,70,70,"resources/images/gamePlay/aquiment/emptyPot.png","STATIC",null));
                break;
            case 3:
            case 4:
                slots.add(new SlotSpec("yenTafo",230,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/yenTaFo/laped.png"));
                slots.add(new SlotSpec("tomYum",300,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/tomYum/laped.png"));
                slots.add(new SlotSpec("namTok",230,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/namTok/laped.png"));
                slots.add(new SlotSpec("emptyPot",300,280,70,70,"resources/images/gamePlay/aquiment/emptyPot.png","STATIC",null));
                break;
            case 5:
            default:
                slots.add(new SlotSpec("yenTafo",230,210,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/yenTaFo/laped.png"));
                slots.add(new SlotSpec("tomYum",300,210,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/tomYum/laped.png"));
                slots.add(new SlotSpec("namTok",230,280,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/namTok/laped.png"));
                slots.add(new SlotSpec("braisedPork",300,280,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png","SPAWN","resources/images/gamePlay/ingredients/soups/category/braisedPork/laped.png"));
                break;
        }

        // Add-ons
        if (data.isItemUnlocked("Vegetable")) {
            slots.add(new SlotSpec("vegetable", 575, 190, 98, 98, "resources/images/gamePlay/ingredients/vegetables/idle.png", "SPAWN", "resources/images/gamePlay/ingredients/vegetables/picked.png"));
        }

        if (data.isItemUnlocked("Pork slices")) {
            slots.add(new SlotSpec("porkSlice", 558, 260, 102, 102, "resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png", "SPAWN", "resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png"));
        } else {
            slots.add(new SlotSpec("emptyBox",558,260,102,102,"resources/images/gamePlay/aquiment/emptyBox.png","STATIC",null));
        }

        if (data.isItemUnlocked("Meatball")) {
            slots.add(new SlotSpec("meatball", 632, 260, 102, 102, "resources/images/gamePlay/ingredients/addOn/meatball/idle.png", "SPAWN", "resources/images/gamePlay/ingredients/addOn/meatball/picked.png"));
        } else {
            slots.add(new SlotSpec("emptyBox",632,260,102,102,"resources/images/gamePlay/aquiment/emptyBox.png","STATIC",null));
        }

        if (data.isItemUnlocked("Pork rind")) {
            slots.add(new SlotSpec("porkRind", 694, 260, 102, 102, "resources/images/gamePlay/ingredients/addOn/porkRind/idle.png", "SPAWN", "resources/images/gamePlay/ingredients/addOn/porkRind/picked.png"));
        } else {
            slots.add(new SlotSpec("emptyBox",694,260,102,102,"resources/images/gamePlay/aquiment/emptyBox.png","STATIC",null));
        }

        // Base Equipment
        slots.add(new SlotSpec("takronoodle",-2,200,120,114,"resources/images/gamePlay/aquiment/takronoodle.png","DRAG",null));
        slots.add(new SlotSpec("ladle",95,200,120,120,"resources/images/gamePlay/aquiment/ladle.png","DRAG",null));
        slots.add(new SlotSpec("placemat",355,150,240,240,"resources/images/gamePlay/ingredients/noodles/placemat.png","STATIC",null));
        slots.add(new SlotSpec("noodlesBowl",226,122,76,76,"resources/images/gamePlay/bowl/decorate.png","SPAWN","resources/images/gamePlay/bowl/picked.png"));
        slots.add(new SlotSpec("chopsticks",282,110,86,86,"resources/images/gamePlay/counter/chopsticks.png","STATIC",null));
        slots.add(new SlotSpec("pot",-86,240,380,380,"resources/images/gamePlay/ingredients/noodles/boilingPot/not_boiling.png","STATIC",null));
        slots.add(new SlotSpec("trash",620,405,153,73,"resources/images/gamePlay/binn/empty.png","STATIC",null));
        slots.add(new SlotSpec("bucket",230,355,120,120, bucketImg,"STATIC",null));

        // Noodles
        if (hasVermicelli) slots.add(new SlotSpec("riceVermicelli", 32, 40, 60, 60, null, "SPAWN", "resources/images/gamePlay/ingredients/noodles/category/riceVermicelli/picked.png"));
        if (hasWide) slots.add(new SlotSpec("wideRice", 94, 40, 60, 60, null, "SPAWN", "resources/images/gamePlay/ingredients/noodles/category/wideRice/picked.png"));
        if (data.isItemUnlocked("Yellow egg noodles")) slots.add(new SlotSpec("yellowEgg", 30, 120, 60, 60, null, "SPAWN", "resources/images/gamePlay/ingredients/noodles/category/yellowEgg/picked.png"));
        if (data.isItemUnlocked("Thin rice noodles")) slots.add(new SlotSpec("thinRice", 100, 120, 60, 60, null, "SPAWN", "resources/images/gamePlay/ingredients/noodles/category/thinRice/picked.png"));
        if (data.isItemUnlocked("Green egg noodles")) slots.add(new SlotSpec("greenEgg", 143, 117, 100, 92, "resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png", "SPAWN", "resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png"));

        slots.add(new SlotSpec("container",2,14,182,182, containerImg ,"STATIC",null));

        // Extras
        if (data.isItemUnlocked("Kanom tuay")) {
            slots.add(new SlotSpec("kanomTuay", 670, 150, 113, 55, "resources/images/gamePlay/ingredients/kanomTuay/idle.png", "SPAWN", "resources/images/gamePlay/ingredients/kanomTuay/picked.png"));
        }

        return new LevelUIConfig(bg, slots);
    }
}