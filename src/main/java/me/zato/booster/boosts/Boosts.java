package me.zato.booster.boosts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Boosts {

    private static LinkedHashMap<String, Boost> boosts = new LinkedHashMap<String, Boost>();

    public static Boost getBoost(String id){
        return boosts.get(id);
    }

    public static Boost addBoost(Boost boost){
        return boosts.put(boost.getId(), boost);
    }

    public static Boost removeBoost(Boost boost){
        return boosts.remove(boost.getId());
    }

    public static List<Boost> getAllBoosts(){
        return new ArrayList<Boost>(boosts.values());
    }

    public static List<Boost> getAllInterestBoosts(){
        List<Boost> l = new ArrayList<>();
        for(Boost boost : boosts.values())
            if(boost.getType().equals(Boost.Type.INTEREST_BOOSTER))
                l.add(boost);
        return l;
    }

    public static List<Boost> getAllMarketBoosts(){
        List<Boost> l = new ArrayList<>();
        for(Boost boost : boosts.values())
            if(boost.getType().equals(Boost.Type.MARKET_BOOSTER))
                l.add(boost);
        return l;
    }

    public static List<Boost> getAllMobDropBoosts(){
        List<Boost> l = new ArrayList<>();
        for(Boost boost : boosts.values())
            if(boost.getType().equals(Boost.Type.MOB_DROP_BOOSTER))
                l.add(boost);
        return l;
    }

    public static boolean containsInterestBoost(){
        return !getAllInterestBoosts().isEmpty();
    }

    public static boolean containsMarketBoost(){
        return !getAllMarketBoosts().isEmpty();
    }

    public static boolean containsMobDropBoost(){
        return !getAllMobDropBoosts().isEmpty();
    }

    public static Boost getInterestBoost(){
        if(!containsInterestBoost())
            return null;
        return boosts.get(0);
    }

    public static Boost getMarketBoost(){
        if(!containsMarketBoost())
            return null;
        return boosts.getOrDefault(0, null);
    }

    public static Boost getMobDropBoost(){
        if(!containsMobDropBoost())
            return null;
        return boosts.get(0);
    }
}
