package tech.williamist.shadowartifacts.items.urkolowsjournal;

import net.minecraft.ChatFormatting;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import tech.williamist.shadowartifacts.setup.RegistryHandler;
import tech.williamist.shadowartifacts.util.MessageUtils;

public class UrkolowsJournalEvents {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        if (event.getTarget().getLevel().isClientSide()) return;

        if (event.getPlayer().isHolding(RegistryHandler.URKOLOWS_JOURNAL.get())) {
            if (event.getPlayer().getCooldowns().isOnCooldown(RegistryHandler.URKOLOWS_JOURNAL.get())) {
                MessageUtils.sendClientMessage(event.getPlayer(), ChatFormatting.RED + "You can't use this while it's on cooldown!", true);
                return;
            }
            EmblemType mobEmblem = JournalEntityUtils.getEmblemTypeFromEntityAndForm(event.getTarget(), 1); // Hardcoded form 1 for now.
            //MessageUtils.sendClientMessage(event.getPlayer(), "EMBLEM: " + mobEmblem, false);
            if (mobEmblem != null && JournalEntityUtils.isSuccessfulHit(event.getTarget().getType())) {
                event.getTarget().hurt(DamageSource.GENERIC, (float) JournalEntityUtils.getDamageFromEntityType(event.getTarget().getType()));
                event.getPlayer().getCooldowns().addCooldown(RegistryHandler.URKOLOWS_JOURNAL.get(), 20 * 9); // TODO: stop hardcoding things for multiple forms
                /*MessageUtils.sendClientMessage(event.getPlayer(),
                        "Hurt a '" +
                                event.getTarget().getType() +
                                "' for " +
                                JournalEntityUtils.getDamageFromEntityType(event.getTarget().getType()) +
                                " damage!",
                        false);*/
            }
        }
    }

}
