package srdqrk.maria.listeners;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import srdqrk.maria.Maria;

public class GlobalListeners implements Listener {

  private final Maria instance;

  GlobalListeners(Maria instance) {
    this.instance = instance;
  }

  String buidlderPermission = "kiwistudios.builder";


  @EventHandler
  public void onBreakItemFrame(HangingBreakEvent e) {
    if (e.getEntity() instanceof ItemFrame) {
      if (
              !(HangingBreakEvent.RemoveCause.ENTITY.name().equalsIgnoreCase(e.getCause().name()))

      ) {
        e.setCancelled(true);
      }

    }
  }


  @EventHandler
  public void onBreakItemFrameByPlayer(HangingBreakByEntityEvent e) {
    if (e.getEntity() instanceof ItemFrame) {
      if (e.getRemover() instanceof Player p) {
        if (p.hasPermission(buidlderPermission)) {
          e.getEntity().remove();
        } else {
          e.setCancelled(true);
        }

      }

    }
  }

  @EventHandler
  public void onAttackItemFrame(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player p && p.hasPermission(buidlderPermission))
      return;
    if (e.getEntity() instanceof ItemFrame) {
      e.setCancelled(true);
    }
  }


  /**
   * Can be optimized closing all in one if with &&.
   * With more time I would test it
   *
   * @param event
   */
  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    if (event.getRightClicked() instanceof ItemFrame) {
      if (!event.getPlayer().hasPermission(buidlderPermission)) {
        event.setCancelled(true);
      }
    }
  }
}
