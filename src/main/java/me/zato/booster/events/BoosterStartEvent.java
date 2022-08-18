package me.zato.booster.events;

import me.zato.booster.boosts.Boost;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BoosterStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    Boost boost;
    Boost.Type type = Boost.Type.NONE;
    Boost.Cause cause = Boost.Cause.NONE;
    Player boostAuthor;

    public BoosterStartEvent(Boost boost){
        this.boost = boost;
        this.type = boost.getType();
        this.cause = boost.getCause();
        this.boostAuthor = boost.getAuthor();
    }

    public Boost getBoost() {
        return boost;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
