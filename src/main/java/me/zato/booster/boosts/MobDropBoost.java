package me.zato.booster.boosts;

import me.zato.booster.config.Values;
public class MobDropBoost {

    int multiplier;
    boolean isCustom;

    public MobDropBoost(){
        init();
    }

    public MobDropBoost(int multiplier){
        init();
        this.multiplier = multiplier;
        isCustom = true;
    }

    public void init(){
        this.multiplier = Values.getMobDropMultiplier();
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean isCustom() {
        return isCustom;
    }
}
