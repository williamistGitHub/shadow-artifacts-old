package tech.williamist.shadowartifacts;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import tech.williamist.shadowartifacts.emlem.EmblemEvents;
import tech.williamist.shadowartifacts.setup.RegistryHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("shadowartifacts")
public class ShadowArtifacts {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "shadowartifacts";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("shadowArtifactsTab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(RegistryHandler.URKOLOWS_JOURNAL.get());
        }
    };

    public ShadowArtifacts() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(EmblemEvents.class);
    }

    private void setup(final FMLCommonSetupEvent event) { }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

}
