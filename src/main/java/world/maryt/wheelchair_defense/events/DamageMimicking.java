package world.maryt.wheelchair_defense.events;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import static world.maryt.wheelchair.Config.protectPlayersAtThisHP;
import static world.maryt.wheelchair_defense.Config.playerDefenseMethod;

public class DamageMimicking {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDamaged(LivingDamageEvent event) {
        LivingEntity target = event.getEntity();
        // Only apply for players
        if (!(target instanceof Player player)) return;

        // So /kill and void damage is not prevented
        if (event.getSource().is(DamageTypes.GENERIC_KILL) && event.getAmount() == Float.MAX_VALUE) return;
        if (player.getY() < -64) return;

        // Backup the damage
        float rawDamageAmount = event.getAmount();
        // If no affective damage, do nothing
        if (rawDamageAmount <= 0) return;

        // Maybe there will be more modes
        float targetHealth =  switch (playerDefenseMethod) {
            case 0 -> player.getMaxHealth() * protectPlayersAtThisHP;
            case 1 -> protectPlayersAtThisHP;
            default -> player.getMaxHealth();
        };
        if (player.getHealth() - rawDamageAmount < targetHealth) {
            // Cancel the damage
            event.setAmount(0);

            float mimicHealth = 2 + Math.round(player.getRandom().nextFloat() * targetHealth);
            player.setHealth(mimicHealth);

            // Debug info
            player.sendSystemMessage(Component.nullToEmpty("Raw Damage: " + rawDamageAmount));
            player.sendSystemMessage(Component.nullToEmpty("Mimic Health: " + mimicHealth));
        }
    }
}
