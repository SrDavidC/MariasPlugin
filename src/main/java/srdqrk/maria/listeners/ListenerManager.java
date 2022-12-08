package srdqrk.maria.listeners;
import  srdqrk.maria.Maria;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class ListenerManager  {

    private Maria instance;

    public ListenerManager(Maria instance){
        this.instance = instance;
        registerListener(new GlobalListeners(instance));
    }

    public void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, instance);

    }

}
