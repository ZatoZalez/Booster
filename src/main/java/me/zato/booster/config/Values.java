package me.zato.booster.config;

public class Values {
    public static boolean isMarketBoosterEnabled(){
        return Utility.getData("EnableMarketBooster").getBoolean();
    }

    public static boolean isMobDropBoosterEnabled(){
        return Utility.getData("EnableMobDropBooster").getBoolean();
    }

    public static boolean isInterestBoosterEnabled(){
        return Utility.getData("EnableInterestBooster").getBoolean();
    }

    public static int getDefaultEventDuration(){
        return Utility.getData("DefaultEventDuration").getInt();
    }

    public static int getDefaultTipAmount(){
        return Utility.getData("DefaultTipAmount").getInt();
    }

    public static int getInterestDelay(){
        return Utility.getData("InterestDelay").getInt();
    }

    public static double getInterestAmount(){
        return Utility.getData("InterestAmount").getDouble();
    }

    public static int getMobDropMultiplier(){
        return Utility.getData("MobDropMultiplier").getInt();
    }

    public static double getMarketMultiplier(){
        return Utility.getData("MarketMultiplier").getDouble();
    }
}
