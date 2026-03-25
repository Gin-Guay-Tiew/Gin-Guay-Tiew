package ui.pages.gamePlay;

import java.util.List;

public class LevelUIConfig {
    public String bgPath;
    public List<SlotSpec> slots;

    public LevelUIConfig(String bgPath, List<SlotSpec> slots){
        this.bgPath = bgPath;
        this.slots = slots;
    }
}
