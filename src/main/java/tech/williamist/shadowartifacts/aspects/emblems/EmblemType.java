package tech.williamist.shadowartifacts.aspects.emblems;

public enum EmblemType {

    FRIGHT_EMBLEM (0, 16),
    TORTURE_EMBLEM (0, 32),
    BETRAYAL_EMBLEM (16, 16),
    DOMINANCE_EMBLEM (32, 16),
    REBIRTH_EMBLEM (48, 16),
    SCREAMS_EMBLEM (64, 16),
    SACRIFICE_EMBLEM (16, 32),
    CRUELTY_EMBLEM (32, 32),
    SHADOWS_EMBLEM (80, 16),
    RAGING_EMBLEM (48, 32),
    COLLAPSE_EMBLEM (48, 48),
    VIABILITY_EMBLEM (16, 48),
    FLAMES_EMBLEM (64, 32),
    PERIL_EMBLEM (64, 48),
    ANXIETY_EMBLEM (96, 16),
    LOOMING_FATE_EMBLEM (128, 32),
    OMNIPRESENCE_EMBLEM (96, 48),
    SWARMS_EMBLEM (176, 32),
    NIGHTMARES_EMBLEM (196, 32),
    OBSERVANCE_EMBLEM (208, 32),
    WANDERING_EMBLEM (0, 48),
    DESPISE_EMBLEM (144, 32),
    BLOODLUST_EMBLEM (160, 32),
    DESTRUCTION_EMBLEM (80, 48),
    COWARDICE_EMBLEM (32, 48),
    ABSORPTION_EMBLEM (80, 32),
    SUMMONING_EMBLEM (96, 32),
    ISOLATION_EMBLEM (122, 32),
    CURSES_EMBLEM (122, 16),
    DEATH_EMBLEM (0, 64),
    CHAOS_EMBLEM (16, 64),
    OBLITERATION_EMBLEM (0, 80),
    MANIPULATION_EMBLEM (16, 80),
    PARANOIA_EMBLEM (32, 80),
    DISASTER_EMBLEM (42, 80),
    DISORDER_EMBLEM (64, 80),
    CORRUPTION_EMBLEM (80, 80),
    UNKNOWING_EMBLEM (96, 80),
    DESIRE_EMBLEM (112, 80),
    PRIMITIVE_FUTURE_EMBLEM (128, 80),
    TREACHERY_EMBLEM (144, 80),
    DESPERATION_EMBLEM (160, 80),
    WRATH_EMBLEM,
    GREED_EMBLEM,
    PRIDE_EMBLEM,
    ENVY_EMBLEM,
    LUST_EMBLEM,
    GLUTTONY_EMBLEM,
    SLOTH_EMBLEM,

    SINS_EMBLEM

    ;

    private final int texOffsetX;
    private final int texOffsetY;

    EmblemType() {
        this(0, 0);
    }

    EmblemType(int texOffsetX, int texOffsetY) {
        this.texOffsetX = texOffsetX;
        this.texOffsetY = texOffsetY;
    }

    public int getTexOffsetX() {
        return this.texOffsetX;
    }

    public int getTexOffsetY() {
        return this.texOffsetY;
    }

}
