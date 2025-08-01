package world.maryt.wheelchair_defense.util;

import net.minecraft.nbt.CompoundTag;

// TODO: Merge this into WheelChair Lib
public class TagUtils {
    public static int getIntNonNull(CompoundTag tag, String key, int defaultValue) {
        if (tag.contains(key)) {
            return tag.getInt(key);
        } else {
            tag.putInt(key, defaultValue);
            return defaultValue;
        }
    }

    public static float getFloatNonNull(CompoundTag tag, String key, float defaultValue) {
        if (tag.contains(key)) {
            return tag.getFloat(key);
        } else {
            tag.putFloat(key, defaultValue);
            return defaultValue;
        }
    }
}
