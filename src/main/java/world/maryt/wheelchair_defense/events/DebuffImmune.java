package world.maryt.wheelchair_defense.events;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import world.maryt.wheelchair_defense.util.DebuffUtils;

import java.time.LocalDateTime;

public class DebuffImmune {

    private static final String[] message = new String[]{
            "message.1",
            "message.2",
            "message.3"
    };

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerTicking(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.side == LogicalSide.SERVER) {
            for (MobEffect effect: DebuffUtils.DEBUFF) {
                event.player.removeEffect(effect);
            }
            sendMessage(event.player);
        }
    }

    private static void sendMessage(Player player) {
        if (LocalDateTime.now().getHour() == 23 && LocalDateTime.now().getMinute() == 0 && LocalDateTime.now().getNano() == 0)
            player.sendSystemMessage(Component.translatable(message[0]));
        if (LocalDateTime.now().getHour() == 0 && LocalDateTime.now().getMinute() == 0 && LocalDateTime.now().getNano() == 0)
            player.sendSystemMessage(Component.translatable(message[1]));
        if (LocalDateTime.now().getHour() == 1 && LocalDateTime.now().getMinute() == 0 && LocalDateTime.now().getNano() == 0)
            player.sendSystemMessage(Component.translatable(message[2]));
    }
}
