package me.zato.booster.events;

import me.zato.booster.boosts.Boost;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BoosterEndEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    Boost boost;
    Boost.Type type = Boost.Type.NONE;
    Boost.Cause cause = Boost.Cause.NONE;

    Boost.Reason reason = Boost.Reason.NONE;
    Player boostAuthor;

    public BoosterEndEvent(Boost boost){
        this.boost = boost;
        this.type = boost.getType();
        this.cause = boost.getCause();
        this.reason = boost.getReason();
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