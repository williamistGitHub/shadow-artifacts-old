package tech.williamist.shadowartifacts.items.urkolowsjournal;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import tech.williamist.shadowartifacts.ShadowArtifacts;

public class UrkolowsJournalItem extends Item {

    public UrkolowsJournalItem() {
        super(new Item.Properties()
                .tab(ShadowArtifacts.ITEM_GROUP)
                .stacksTo(1)
        );
        MinecraftForge.EVENT_BUS.register(UrkolowsJournalEvents.class);
    }



}
