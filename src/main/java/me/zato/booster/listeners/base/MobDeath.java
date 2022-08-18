package me.zato.booster.listeners.base;

import me.zato.booster.Booster;
import me.zato.booster.boosts.Boost;
import me.zato.booster.boosts.Boosts;
import me.zato.booster.config.Values;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobDeath implements Listener {
    public MobDeath(Booster plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if(entity == null)
            return;

        Boost boost = Boosts.getMobDropBoost();
        if(boost == null)
            return;

        e.getDrops().clear();
        for(ItemStack itemStack : e.getDrops()){
            ItemStack item = itemStack;
            item.setAmount(itemStack.getAmount() * boost.getMobDropBoost().getMultiplier());
            e.getEntity().getWorld().dropItemNaturally(entity.getLocation(), item);
        }
        return;
    }
}
