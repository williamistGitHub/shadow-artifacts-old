package tech.williamist.shadowartifacts.items.urkolowsjournal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.Random;
import java.util.Set;

public final class JournalEntityUtils {

    private static final Set<EntityType> passiveEntities = Set.of(

            EntityType.WOLF, // Wolf is included because they drop the same thing.

            EntityType.AXOLOTL,
            EntityType.BAT,
            EntityType.BEE,
            EntityType.CAT,
            EntityType.CHICKEN,
            EntityType.COD,
            EntityType.COW,
            EntityType.DOLPHIN,
            EntityType.DONKEY,
            EntityType.FOX,
            EntityType.GLOW_SQUID,
            EntityType.GOAT,
            EntityType.HORSE,
            EntityType.IRON_GOLEM,
            EntityType.LLAMA,
            EntityType.MULE,
            EntityType.MOOSHROOM,
            EntityType.OCELOT,
            EntityType.PANDA,
            EntityType.PARROT,
            EntityType.PIG,
            EntityType.POLAR_BEAR, // Does this count?
            EntityType.RABBIT,
            EntityType.SALMON,
            EntityType.SHEEP,
            EntityType.SKELETON_HORSE,
            EntityType.SNOW_GOLEM,
            EntityType.SQUID,
            EntityType.STRIDER,
            EntityType.TRADER_LLAMA,
            EntityType.TROPICAL_FISH,
            EntityType.TURTLE,
            EntityType.VILLAGER,
            EntityType.WANDERING_TRADER,
            EntityType.ZOMBIE_HORSE
    );

    private static final Set<EntityType> ignoredEntities = Set.of( // Basically entities that aren't mobs.
            EntityType.AREA_EFFECT_CLOUD,
            EntityType.ARMOR_STAND,
            EntityType.ARROW,
            EntityType.BOAT,
            EntityType.DRAGON_FIREBALL,
            EntityType.END_CRYSTAL,
            EntityType.EVOKER_FANGS,
            EntityType.EXPERIENCE_ORB,
            EntityType.EYE_OF_ENDER,
            EntityType.FALLING_BLOCK,
            EntityType.FIREWORK_ROCKET,
            EntityType.GIANT,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.ITEM,
            EntityType.ITEM_FRAME,
            EntityType.FIREBALL,
            EntityType.LEASH_KNOT,
            EntityType.LIGHTNING_BOLT,
            EntityType.LLAMA_SPIT,
            EntityType.MARKER, // What is this??
            EntityType.MINECART,
            EntityType.CHEST_MINECART,
            EntityType.COMMAND_BLOCK_MINECART,
            EntityType.FURNACE_MINECART,
            EntityType.HOPPER_MINECART,
            EntityType.SPAWNER_MINECART,
            EntityType.TNT_MINECART,
            EntityType.PAINTING,
            EntityType.TNT,
            EntityType.SHULKER_BULLET,
            EntityType.SMALL_FIREBALL,
            EntityType.SNOWBALL,
            EntityType.SPECTRAL_ARROW,
            EntityType.EGG,
            EntityType.ENDER_PEARL,
            EntityType.EXPERIENCE_BOTTLE,
            EntityType.POTION,
            EntityType.TRIDENT,
            EntityType.WITHER_SKULL,
            EntityType.PLAYER,
            EntityType.FISHING_BOBBER
    );

    public static EmblemType getEmblemTypeFromEntityAndForm(Entity entity, int form) {
        EntityType type = entity.getType();

        //ShadowArtifacts.LOGGER.debug("TYPE: " + type);
        //ShadowArtifacts.LOGGER.debug("Ignored: " + ignoredEntities.contains(type));
        //ShadowArtifacts.LOGGER.debug("Passive: " + passiveEntities.contains(type)); // this one should be true

        if (ignoredEntities.contains(type)) return null; // Ignore ignored entities.
        else if (passiveEntities.contains(type)) return EmblemType.BETRAYAL_EMBLEM; // Passive entities (and wolves) always drop betrayal emblems.
        else {
            // Form 1 emblems.
            if (type == EntityType.SPIDER ||
                    type == EntityType.CAVE_SPIDER)
                return EmblemType.FRIGHT_EMBLEM;
            if (type == EntityType.LLAMA ||
                    type == EntityType.TRADER_LLAMA)
                return EmblemType.DOMINANCE_EMBLEM;
            if (type == EntityType.ZOMBIE ||
                    type == EntityType.DROWNED ||
                    type == EntityType.HUSK ||
                    type == EntityType.ZOMBIE_VILLAGER)
                return EmblemType.REBIRTH_EMBLEM;
            if (type == EntityType.SKELETON ||
                    type == EntityType.STRAY)
                return EmblemType.SCREAMS_EMBLEM;
            if (type == EntityType.ENDERMAN)
                return EmblemType.SHADOWS_EMBLEM;
            if (type == EntityType.CREEPER)
                return EmblemType.ANXIETY_EMBLEM;
            if (type == EntityType.WITHER_SKELETON)
                return EmblemType.CURSES_EMBLEM;

            // Form 2+
            if (form > 1) {
                // TODO: Implement this. (not in alpha 0.1)
            }

            return null;
        }
    }

    public static boolean isSuccessfulHit(EntityType type) {

        int percent = new Random().nextInt(100) + 1;

        if (ignoredEntities.contains(type)) return false;
        else if (passiveEntities.contains(type)) return percent < 90;
        else {
            if (type == EntityType.SPIDER ||
                    type == EntityType.CAVE_SPIDER)
                return percent < 20;
            if (type == EntityType.LLAMA ||
                    type == EntityType.TRADER_LLAMA)
                return percent < 30;
            if (type == EntityType.ZOMBIE ||
                    type == EntityType.DROWNED ||
                    type == EntityType.HUSK ||
                    type == EntityType.ZOMBIE_VILLAGER)
                return percent < 45;
            if (type == EntityType.SKELETON ||
                    type == EntityType.STRAY)
                return percent < 40;
            if (type == EntityType.ENDERMAN)
                return percent < 10;
            if (type == EntityType.CREEPER)
                return percent < 15;
            if (type == EntityType.WITHER_SKELETON)
                return percent < 5;

            return false;
        }
    }

    public static int getDamageFromEntityType(EntityType type) {
        if (ignoredEntities.contains(type)) return 0;
        else if (passiveEntities.contains(type)) return 20;
        else {
            if (type == EntityType.SPIDER ||
                    type == EntityType.CAVE_SPIDER)
                return 10;
            if (type == EntityType.LLAMA ||
                    type == EntityType.TRADER_LLAMA)
                return 14;
            if (type == EntityType.ZOMBIE ||
                    type == EntityType.DROWNED ||
                    type == EntityType.HUSK ||
                    type == EntityType.ZOMBIE_VILLAGER)
                return 16;
            if (type == EntityType.SKELETON ||
                    type == EntityType.STRAY)
                return 16;
            if (type == EntityType.ENDERMAN)
                return 18;
            if (type == EntityType.CREEPER)
                return 16;
            if (type == EntityType.WITHER_SKELETON)
                return 10;

            return 0;
        }
    }

}
