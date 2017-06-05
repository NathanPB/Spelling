package me.nathanpb.Selfs;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.Spell.Spell;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created by nathanpb on 6/4/17.
 */
public class SelfMananger {
    public static void turn(UUID uuid, Spell self, boolean onoroff){
        if(onoroff){
            ManaMananger.addActiveSelf(uuid, self);
        }else{
            ManaMananger.removeActiveSelf(uuid, self);
        }
    }
    public static void startRunnable(){
        new BukkitRunnable(){
            public void run(){
                for(Player p : Bukkit.getOnlinePlayers()){
                    for(Spell s : ManaMananger.getActiveSelfs(p.getUniqueId())){
                        if(s instanceof Self){
                            if(ManaMananger.CanUseSpell(p, 1)) {
                                PotionEffectType type = ((Self) s).getEffect();
                                int level = ManaMananger.getLevel(p.getUniqueId());
                                level = level/10;
                                while (level > ((Self) s).getMaxLevel()){
                                    level--;
                                }
                                if (p.hasPotionEffect(type)) {
                                    p.removePotionEffect(type);
                                }
                                p.addPotionEffect(new PotionEffect(type, (20 * 20) + 1, level));
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Spelling.getSpelling(), 0, 20);
    }
}
