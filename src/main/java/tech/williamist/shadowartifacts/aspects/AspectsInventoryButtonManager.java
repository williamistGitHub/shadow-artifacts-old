package tech.williamist.shadowartifacts.aspects;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class AspectsInventoryButtonManager {

    private static final ResourceLocation buttonTexLoc = new ResourceLocation("shadowartifacts", "textures/gui/invbutton.png");

    private static ImageButton emblemButton = null;

    @SubscribeEvent
    public static void inventoryOpen(ScreenEvent.InitScreenEvent.Post event) {
        //ShadowArtifacts.LOGGER.debug("Screen type: " + event.getScreen().toString());
        if (event.getScreen() instanceof InventoryScreen) {
            event.addListener(emblemButton = new ImageButton(((InventoryScreen) event.getScreen()).getGuiLeft() + 152, ((InventoryScreen) event.getScreen()).getGuiTop() + 4, 20, 20, 0, 0, 20, buttonTexLoc, 20, 40, button -> {
                event.getScreen().getMinecraft().setScreen(new AspectsScreen(new TextComponent("Sussy Screen")));
            }, (button, poseStack, x, y) -> event.getScreen().renderTooltip(poseStack, new TextComponent("Aspects"), x, y), new TextComponent("")));
        }
    }

    @SubscribeEvent
    public static void inventoryTick(ScreenEvent.DrawScreenEvent.Pre event) {
        if (event.getScreen() instanceof InventoryScreen) {
            emblemButton.x = ((InventoryScreen) event.getScreen()).getGuiLeft() + 152;
        }
    }

}
