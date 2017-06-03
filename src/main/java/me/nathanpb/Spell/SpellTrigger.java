package me.nathanpb.Spell;

import java.util.ArrayList;
import java.util.List;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.Spelling.Spelling;
import net.minecraft.server.v1_11_R1.Material;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class SpellTrigger implements Listener{

	private final me.nathanpb.Spelling.Spelling plugin;
	public SpellTrigger(Spelling plugin){this.plugin = plugin;}
	
	private static List<Spell> registered = new ArrayList<>();
	public static void registerSpell(Spell s){
		if(!registered.contains(s)){
			
			registered.add(s);
	    	Bukkit.addRecipe(s.getRecipe());
			Bukkit.getServer().getLogger().info("Listener and recipe for '"+s.getClass().getName()+"' registered");
		}
	}
	public static List<Spell> getRegisteredList(){
		return registered;
	}
	private static void trigger(Spell s, Event e){
		for(Spell list : registered){
			if(list.getClass() == s.getClass()){
				s.triggeredSpellEvent(e);
			}
		}
	}
	
	@EventHandler
	public static void triggerPIEvent(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_AIR){
			if(e.getAction() != Action.RIGHT_CLICK_BLOCK){
				return;
			}
		}
		for(Spell s : registered){
			//TOOL FIXER PRECISA AJUSTAR O CUSTO DE EXP E DE MANA
			if(s instanceof ToolFixer){
				if(s.getSpellItem().equals(e.getItem())){
					ToolFixer tf = new ToolFixer(null);
					int TotalDurability = tf.calculateCost(e);
					int EXP = ToolFixer.getTotalExperience(e.getPlayer());
					tf.setManaCost(TotalDurability*2);
					if (EXP * 2 < TotalDurability) {
						e.getPlayer().sendMessage(ChatColor.RED+ "You doesn't have enought XP!");
						e.getPlayer().sendMessage(ChatColor.BLUE + "Needed: " + ChatColor.GOLD+ TotalDurability * 2);
						e.getPlayer().sendMessage(ChatColor.BLUE + "Available: " + ChatColor.GOLD+ EXP);
						return;
					}
					if(ManaMananger.CanUseSpell(e.getPlayer(), tf.getManaCost(), 0, true)){
						tf.triggeredSpellEvent(e);
						return;
					}
				}
			}
			//SANITATUM PRECISA CALCULAR O CUSTO DE MANA E CHECAR A HP
			if(s instanceof Sanitatum){
				if(s.getSpellItem().equals(e.getItem())){
					if(e.getPlayer().getHealth() >= 20){
						return;
					}
					Sanitatum sanitatum = new Sanitatum(null);
					sanitatum.setManaCost(sanitatum.calculateManaCost(e.getPlayer()));
					if(ManaMananger.CanUseSpell(e.getPlayer(), sanitatum.getManaCost(), 0, true)){
						trigger(sanitatum, e);
					}
					return;
				}
			}
			
			//VACUUM GENERATOR PRECISA SUGAR LIVING ENTITYES CASO ESTAJA COM SHIFT
			if(s instanceof VacuumGenerator){
				if(s.getSpellItem().equals(e.getItem())){
					if(e.getPlayer().isSneaking()){
						VacuumGenerator vacuum = new VacuumGenerator(null);
						vacuum.setManaCost(50);
						if(ManaMananger.CanUseSpell(e.getPlayer(), vacuum.getManaCost(), 0, true)){
							vacuum.triggerIfShifted(e);
							return;
						}
					}
				}
			}
			
			//O CUSTO DE MANA DO BLINK É IRREGULAR, ENTÃO TEM TRATAMENTO ESPECIAL
			if(s instanceof Blink){
				if(s.getSpellItem().hasItemMeta()){
					if(e.getPlayer().getItemInHand().equals(Material.AIR)){
						return;
					}
					if(!e.getPlayer().getItemInHand().hasItemMeta()){
						return;
					}
					if(!e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()){
						return;
					}

					Blink blink = new Blink(null);
					if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(blink.getSpellName())){
					if(e.getPlayer().isSneaking()){
						blink.setLocation(e);
						return;
					}
					if(e.getPlayer().getItemInHand().getItemMeta().hasLore()){
						Location loc = blink.getTeleportLocation(e);
						int distance;
						if(loc.getWorld() == e.getPlayer().getWorld()){
							distance = (int)e.getPlayer().getLocation().distance(loc);
							distance = distance / 5;
						}else{
							distance = 1000;
						}
						blink.setManaCost(distance);
						if(ManaMananger.CanUseSpell(e.getPlayer(), blink.getManaCost(), 0, true)){
							trigger(blink, e);
						}
					}
					}
				}
			}
			//METEOR CALLER PRECISA CHECAR SE A POSIÇÃO QUE O METEORO DEVE CAIR É VOID
			if(s instanceof MeteorCaller){
				if(s.getSpellItem().equals(e.getItem())){
					MeteorCaller meteor = new MeteorCaller(null);
					if(meteor.checkIfIsVoid(e)){
						e.getPlayer().sendMessage(ChatColor.RED+"You can't call meteors on void!");
						return;
					}
					if(ManaMananger.CanUseSpell(e.getPlayer(), s.getManaCost(), 0, true)){
						trigger(meteor, e);
					}
				}
			}
			//REGULARES
			if(s.getSpellItem().equals(e.getItem())){
				if(ManaMananger.CanUseSpell(e.getPlayer(), s.getManaCost(), 0, true)){
					trigger(s, e);
				}
			}
		}
	}

	@EventHandler
	public static void triggerPIEEvent(PlayerInteractEntityEvent e){
		for(Spell s : registered){
			if(e.getPlayer().getItemInHand().equals(Material.AIR)){
				return;
			}
			if(!e.getPlayer().getItemInHand().hasItemMeta()){
				return;
			}
			//FLYING DEVIL PRECISA DE AJUSTES NO CUSTO DE MANA
			if(s instanceof FlyingDevil){
				if(s.getSpellItem().equals(e.getPlayer().getItemInHand())){
					FlyingDevil spell = new FlyingDevil(null);
					if(e.getRightClicked() instanceof LivingEntity){
						spell.setManaCost(100);
						if(e.getRightClicked() instanceof Player){
							spell.setManaCost(1000);
						}
						if(ManaMananger.CanUseSpell(e.getPlayer(), spell.getManaCost(), 0, true)){
							s.triggeredSpellEvent(e);
							return;
						}
					}
				}
			}
			
			
			//REGULARES
			if(s.getSpellItem().equals(e.getPlayer().getItemInHand())){
				if(ManaMananger.CanUseSpell(e.getPlayer(), s.getManaCost(), 0, true)){
					s.triggeredSpellEvent(e);
					return;
				}
			}
		}
	}
	@EventHandler
	public static void triggerPICE(PlayerItemConsumeEvent e){
		for(Spell s : registered){
			//MANA COOKIE PRECIA ALTERAR O CUSTO DE MANA
			if(s instanceof ManaCookie){
				if(s.getSpellItem().equals(e.getPlayer().getItemInHand())){
					ManaCookie spell = new ManaCookie();
					spell.setManaCost((20 - e.getPlayer().getFoodLevel())*100);
					if(ManaMananger.CanUseSpell(e.getPlayer(), spell.getManaCost(), 0, false)){
						spell.triggeredSpellEvent(e);
					}
				}
			}
		}
	}
	@EventHandler
	public static void triggetEDBEE(EntityDamageByEntityEvent e){
		if(e.getDamage() == 25){
			if(e.getDamager() instanceof Player){
				Player p = (Player) e.getDamager();
				if(p.getItemInHand().equals(new PrimordialStick().getSpellItem())){
					return;
				}
			}
		}
		for(Spell s : registered){
			if(s instanceof PrimordialStick){
				if(e.getEntity() instanceof LivingEntity){
					if(e.getDamager() instanceof Player){
						Player p = (Player) e.getDamager();
						if(p.getItemInHand().equals(s.getSpellItem())){
							if(ManaMananger.CanUseSpell(p, s.getManaCost(), 0, true)){
								s.triggeredSpellEvent(e);
								return;
							}
						}
					}
					if(e.getDamager() instanceof Snowball){
						s.triggeredSpellEvent(e);
					}
				}
			}
		}
	}
}