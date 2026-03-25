package ui.pages.gamePlay;

import java.util.List;

public class LevelUIconfig {
    public String bgPath;
    public List<SlotSpec> slots;

    public LevelUIconfig(String bgPath,List<SlotSpec> slots){
        this.bgPath = bgPath;
        this.slots = slots;
    }
}
