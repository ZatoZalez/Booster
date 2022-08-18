package me.zato.booster.boosts;

import me.zato.booster.config.Values;
import org.bukkit.Bukkit;

import java.math.BigDecimal;

public class InterestBoost {

    double defaultInterestAmount;
    int defaultInterestDelay;
    double interestAmount;
    int interestDelay;
    BigDecimal totalInterest;
    boolean isCustom;

    public InterestBoost(){
        init();
    }

    public InterestBoost(double interestAmount, int interestDelay){
        init();
        this.interestAmount = interestAmount;
        this.interestDelay = interestDelay;
        isCustom = true;
    }

    public void init(){
        this.interestAmount = Values.getInterestAmount();
        this.interestDelay = Values.getInterestDelay();
        this.defaultInterestAmount = me.pulsi_.bankplus.values.Values.CONFIG.getInterestMoneyGiven();
        this.defaultInterestDelay = me.pulsi_.bankplus.values.Values.CONFIG.getInterestDelay();
    }
    public double getInterestAmount() {
        return interestAmount;
    }

    public int getInterestDelay() {
        return interestDelay;
    }

    public double getDefaultInterestAmount() {
        return defaultInterestAmount;
    }

    public int getDefaultInterestDelay() {
        return defaultInterestDelay;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void addTotalInterest(BigDecimal amount){
        totalInterest.add(amount);
    }

    public boolean isCustom() {
        return isCustom;
    }
}
