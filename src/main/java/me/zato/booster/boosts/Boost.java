package me.zato.booster.boosts;

import me.zato.booster.Booster;
import me.zato.booster.events.BoosterEndEvent;
import me.zato.booster.events.BoosterStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Boost {

    String id;
    int duration;
    int remainingTime;
    Player boostAuthor;
    Type type = Type.NONE;
    Cause cause = Cause.NONE;
    Reason reason = Reason.NONE;
    boolean hasStarted;
    boolean hasStopped;
    boolean hasEnded;

    InterestBoost interestBoost;
    MarketBoost marketBoost;
    MobDropBoost mobDropBoost;

    List<Player> benefitedPlayers = new ArrayList<>();

    public Boost(InterestBoost interestBoost, Player boostAuthor, int duration, Type type, Cause cause){
        this.id = UUID.randomUUID().toString();
        this.boostAuthor = boostAuthor;
        this.duration = this.remainingTime = duration;
        this.type = type;
        this.cause = cause;
        this.interestBoost = interestBoost;
    }

    public Boost(MarketBoost marketBoost, Player boostAuthor, int duration, Type type, Cause cause){
        this.id = UUID.randomUUID().toString();
        this.boostAuthor = boostAuthor;
        this.duration = this.remainingTime = duration;
        this.type = type;
        this.cause = cause;
        this.marketBoost = marketBoost;
    }

    public Boost(MobDropBoost mobDropBoost, Player boostAuthor, int duration, Type type, Cause cause){
        this.id = UUID.randomUUID().toString();
        this.boostAuthor = boostAuthor;
        this.duration = this.remainingTime = duration;
        this.type = type;
        this.cause = cause;
        this.mobDropBoost = mobDropBoost;
    }

    public void Initiate(){
        Bukkit.getServer().getPluginManager().callEvent(new BoosterStartEvent(this));
    }

    public void Start(){
        //start duration
        hasStarted = true;
        Boosts.addBoost(this);
        new BukkitRunnable() {
            @Override
            public void run() {

                if(hasStopped){
                    this.cancel();
                    Bukkit.getServer().getPluginManager().callEvent(new BoosterEndEvent(getBoost()));
                }

                Bukkit.broadcastMessage("Time left: " + remainingTime);
                remainingTime--;

                if(remainingTime < 1) {
                    this.cancel();
                    End();
                }

            }
        }.runTaskTimer(Booster.getPlugin(), 20,20);
    }

    public void End(){
        End(Reason.ENDED);
    }

    public void End(Reason reason){
        remainingTime = 0;
        hasEnded = true;
        this.reason = reason;
        Bukkit.getServer().getPluginManager().callEvent(new BoosterEndEvent(getBoost()));
    }

    public void Stop(){
        Stop(Reason.UNKNOWN);
    }

    public void Stop(Reason reason){
        hasStopped = true;
        this.reason = reason;
        Bukkit.getServer().getPluginManager().callEvent(new BoosterEndEvent(getBoost()));
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Cause getCause() {
        return cause;
    }

    public Reason getReason() {
        return reason;
    }

    public Player getAuthor() {
        return boostAuthor;
    }

    public int getDuration() {
        return duration;
    }

    public InterestBoost getInterestBoost() {
        return interestBoost;
    }

    public MarketBoost getMarketBoost() {
        return marketBoost;
    }

    public MobDropBoost getMobDropBoost() {
        return mobDropBoost;
    }

    public boolean isInterestBoost(){
        return (interestBoost != null);
    }

    public boolean isMarketBoost(){
        return (marketBoost != null);
    }

    public boolean isMobDropBoost(){
        return (mobDropBoost != null);
    }

    public boolean hasAuthor(){
        return (boostAuthor != null);
    }

    public Boost getBoost(){
        return this;
    }









    public enum Type{
        NONE,
        MARKET_BOOSTER,
        MOB_DROP_BOOSTER,
        INTEREST_BOOSTER
    }

    public enum Cause{
        NONE,
        CONSOLE_COMMAND,
        PLAYER_COMMAND,
        PLUGIN,
        UNKNOWN
    }

    public enum Reason{
        NONE,
        ENDED,
        INVALID_BOOST,
        SERVER_STOP,
        SUSPICIOUS_VALUES,
        UNKNOWN
    }
}
