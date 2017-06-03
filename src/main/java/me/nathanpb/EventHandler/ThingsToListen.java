package me.nathanpb.EventHandler;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import me.nathanpb.Spell.MagicBreaker;
import me.nathanpb.Spelling.Spelling;
public class ThingsToListen implements Listener{
	private Spelling plugin;
	public ThingsToListen(Spelling plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public static void onFall(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Player p = (Player)event.getEntity();
			if(!p.getInventory().getItemInHand().hasItemMeta()){
				return;
			}
			if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(
					ChatColor.GOLD+"Levitator")){
				if(event.getCause() == DamageCause.FALL){
					int coust = 20;
					if(!me.nathanpb.EventHandler.ManaMananger.CanUseSpell(p, coust, 0, true)){
						return;
					}
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public static void onKicked(PlayerKickEvent event){
		if(event.getReason().contains("Flying")){
			event.setReason(null);
			event.setCancelled(true);
			Bukkit.getServer().getLogger().info("Please enable flying on server configs!");
		}
	}
	@EventHandler
	public static void cancelTAKEALLINFIREfuckingperfectlife(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getPlayer().getItemInHand().equals(new me.nathanpb.Spell.MeteorCaller(null).getSpellItem())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public static void OnFall(EntityChangeBlockEvent event){
		if(event.getEntityType() != EntityType.FALLING_BLOCK){
		return;
		}
		if(event.getEntity().getCustomName() == null){
			return;
		}
		if(event.getEntity().getCustomName().equals("Meteoro")){
			event.getBlock().getWorld().createExplosion(event.getBlock().getLocation(), 30, true);
			Firework fw = event.getBlock().getWorld().spawn(event.getBlock().getLocation(), Firework.class);
			FireworkMeta fwMeta = fw.getFireworkMeta();
			fwMeta.addEffect(FireworkEffect.builder().flicker(false).with(FireworkEffect.Type.BALL).
					trail(false).withColor(Color.ORANGE).build());
			fw.setFireworkMeta(fwMeta);
			event.getBlock().setType(Material.AIR);
		}
	}
	@EventHandler
	public static void interactEvent(PlayerInteractEvent e){
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			if(e.getItem().equals(new MagicBreaker().getSpellItem())){
				if(ManaMananger.CanUseSpell(e.getPlayer(), 3, 0, true)){
					e.getClickedBlock().breakNaturally();
				}
				e.setCancelled(true);
				return;
			}
		}
	}
	@EventHandler
	public static void OnBallBreak(ProjectileHitEvent event){
		if(event.getEntity() instanceof Snowball){
			try{
				if(MagicBreaker.snowball.contains(event.getEntity())){
					if(event.getHitBlock().getType().equals(Material.BEDROCK)){
						return;
					}
					event.getHitBlock().breakNaturally();
				}
			}catch(NullPointerException e){
				return;
			}
			
		}
	}
}
