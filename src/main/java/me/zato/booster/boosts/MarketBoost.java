package me.zato.booster.boosts;

import me.zato.booster.config.Values;

import java.math.BigDecimal;

public class MarketBoost {

    double multiplier;
    BigDecimal totalProfit;
    boolean isCustom;

    public MarketBoost(){
        init();
    }

    public MarketBoost(double multiplier){
        init();
        this.multiplier = multiplier;
        isCustom = true;
    }

    public void init(){
        this.multiplier = Values.getMarketMultiplier();
    }

    public double getMultiplier() {
        return multiplier;
    }

    public BigDecimal getTotalInterest() {
        return totalProfit;
    }

    public void addTotalProfit(BigDecimal amount){
        totalProfit.add(amount);
    }

    public boolean isCustom() {
        return isCustom;
    }
}
