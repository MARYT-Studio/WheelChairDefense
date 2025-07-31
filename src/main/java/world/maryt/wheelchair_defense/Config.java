package world.maryt.wheelchair_defense;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = WheelChairDefense.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue PLAYER_DEFENSE_METHOD = BUILDER.comment("0 (Default): Keep player's health between 0 and this value. The following PROTECT_VALUE serves as health points.\n1: Keep player's health between 0% and this proportion. The following PROTECT_VALUE serves as proportion (0.5 for 50% for example).").defineInRange("playerDamageCalcMethod", 1, 0, 1);

    public static final ForgeConfigSpec.DoubleValue PROTECT_VALUE = BUILDER.comment("0 (Default): Keep player's health between 0 and this value. The following PROTECT_VALUE serves as health points.\n1: Keep player's health between 0% and this proportion. The following PROTECT_VALUE serves as proportion (0.5 for 50% for example).").defineInRange("protectValue", 0.6, 0.0, Double.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int playerDefenseMethod;
    public static float protectValue;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        playerDefenseMethod = PLAYER_DEFENSE_METHOD.get();
        protectValue = PROTECT_VALUE.get().floatValue();
    }
}
