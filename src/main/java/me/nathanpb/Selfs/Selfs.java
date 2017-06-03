package me.nathanpb.Selfs;

import me.nathanpb.Selfs.SelfMananger.Self;
import me.nathanpb.Spelling.Spelling;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Selfs {
	static Spelling spelling = me.nathanpb.Spelling.Spelling.getSpelling();
	public static void ActiveSelfs(){
		new BukkitRunnable(){	    
			@Override
	            public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
						for(Self s : me.nathanpb.Selfs.SelfMananger.GetActiveSelfs(p)){
							if(s.equals(Self.BatEyes)){
								if(me.nathanpb.Selfs.SelfMananger.UseSelf(p, 1)){
									if(p.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
										p.removePotionEffect(PotionEffectType.NIGHT_VISION);
									}
									p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 420, 1, false, false));
								}
							}
							if(s.equals(Self.AmphibiousBreath)){
								if(me.nathanpb.Selfs.SelfMananger.UseSelf(p, 1)){
									if(p.hasPotionEffect(PotionEffectType.WATER_BREATHING)){
										p.removePotionEffect(PotionEffectType.WATER_BREATHING);
									}	
									p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 420, 1, false, false));
								}
							}
							if(s.equals(Self.QuicksilverLimbs)){
								if(me.nathanpb.Selfs.SelfMananger.UseSelf(p, 1)){
									if(p.hasPotionEffect(PotionEffectType.SPEED)){
										p.removePotionEffect(PotionEffectType.SPEED);
									}
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 420, 1, false, false));
								}
							}
							if(s.equals(Self.RabbitLegs)){
								if(me.nathanpb.Selfs.SelfMananger.UseSelf(p, 1)){	
									if(p.hasPotionEffect(PotionEffectType.JUMP)){
										p.removePotionEffect(PotionEffectType.JUMP);
									}
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 420, 1, false, false));
								}
							}
						}
					}
					
	            }
	        }.runTaskTimer(spelling, 0, 20);
	}
}
