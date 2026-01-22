package world.maryt.wheelchair_defense.util;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import static world.maryt.wheelchair_defense.WheelChairDefense.LOGGER;

public class DebuffUtils {
    public static MobEffect[] DEBUFF = null;

    public static void initBuffCache() {
        if (DEBUFF != null) return;

        List<MobEffect> buff = new ArrayList<>(512);
        for (MobEffect effect: ForgeRegistries.MOB_EFFECTS) {
            if (!effect.isBeneficial()) {
                buff.add(effect);
            }
        }

        DEBUFF = buff.toArray(MobEffect[]::new);
        LOGGER.info("Debuff table initialized. {} debuff recorded.", DEBUFF.length);
    }

}
