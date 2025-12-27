package world.maryt.wheelchair_defense;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = WheelChairDefense.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue DEBUG = BUILDER.comment("Debug mode.").define("DEBUG", false);
    public static final ForgeConfigSpec.IntValue PLAYER_DEFENSE_METHOD = BUILDER.comment("0 (Default): Keep player's health between 0% and this proportion. PROTECT_PLAYERS_AT_THIS_HP serves as proportion (0.5 for 50% for example).\n1: Keep player's health between 0 and this value. PROTECT_PLAYERS_AT_THIS_HP serves as health points.").defineInRange("playerDamageCalcMethod", 0, 0, 1);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int playerDefenseMethod;
    public static boolean debug;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        debug = DEBUG.get();
        playerDefenseMethod = PLAYER_DEFENSE_METHOD.get();
    }
}
