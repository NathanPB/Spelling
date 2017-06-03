package me.nathanpb.Selfs;

import java.util.ArrayList;
import java.util.List;

import me.nathanpb.Selfs.SelfMananger.Self;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelfBook implements Listener{
	private final me.nathanpb.Spelling.Spelling plugin;
	public SelfBook(me.nathanpb.Spelling.Spelling plugin){this.plugin = plugin;}
	
	public static Inventory Main(Player player){
		Inventory inventory = Bukkit.createInventory(null, 27, "SelfBook:");
		me.nathanpb.SpellBook.Utils.PagesDecoration(inventory, false);
		inventory.addItem(BatEyesIcon(player));
		inventory.addItem(AmphibiousBreathIcon(player));
		inventory.addItem(QuicksilverLimbsIcon(player));
		inventory.addItem(RabbitLegsIcon(player));
		return inventory;
	}
	private static ItemStack BatEyesIcon(Player player){
		String desc;
		List<String> lore = new ArrayList<String>();
		if(!me.nathanpb.Selfs.SelfMananger.GetSelfs(player).contains(Self.BatEyes)){
			desc=ChatColor.DARK_RED+"Você não possui este self!";
		}else{
			lore.add(ChatColor.BLUE+"Clique para alternar");
			if(me.nathanpb.Selfs.SelfMananger.GetActiveSelfs(player).contains(Self.BatEyes)){
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.GREEN+"Ativado";
			}else{
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.RED+"Desativado";
			}
		}
		ItemStack spell;
    	spell = new ItemStack(Material.FERMENTED_SPIDER_EYE);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Bat Eyes");
    	
    	lore.add(desc);
    	spellMeta.setLore(lore);
    	spell.setItemMeta(spellMeta);
    	return spell;
	}
	private static ItemStack AmphibiousBreathIcon(Player player){
		String desc;
		List<String> lore = new ArrayList<String>();
		if(!me.nathanpb.Selfs.SelfMananger.GetSelfs(player).contains(Self.AmphibiousBreath)){
			desc=ChatColor.DARK_RED+"Você não possui este self!";
		}else{
			lore.add(ChatColor.BLUE+"Clique para alternar");
			if(me.nathanpb.Selfs.SelfMananger.GetActiveSelfs(player).contains(Self.AmphibiousBreath)){
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.GREEN+"Ativado";
			}else{
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.RED+"Desativado";
			}
		}
		ItemStack spell;
    	spell = new ItemStack(Material.RAW_FISH);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Amphibious Breath");
    	
    	lore.add(desc);
    	spellMeta.setLore(lore);
    	spell.setItemMeta(spellMeta);
    	return spell;
	}
	private static ItemStack QuicksilverLimbsIcon(Player player){
		String desc;
		List<String> lore = new ArrayList<String>();
		if(!me.nathanpb.Selfs.SelfMananger.GetSelfs(player).contains(Self.QuicksilverLimbs)){
			desc=ChatColor.DARK_RED+"Você não possui este self!";
		}else{
			lore.add(ChatColor.BLUE+"Clique para alternar");
			if(me.nathanpb.Selfs.SelfMananger.GetActiveSelfs(player).contains(Self.QuicksilverLimbs)){
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.GREEN+"Ativado";
			}else{
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.RED+"Desativado";
			}
		}
		ItemStack spell;
    	spell = new ItemStack(Material.SUGAR);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Quicksilver Limbs");
    	
    	lore.add(desc);
    	spellMeta.setLore(lore);
    	spell.setItemMeta(spellMeta);
    	return spell;
	}
	private static ItemStack RabbitLegsIcon(Player player){
		String desc;
		List<String> lore = new ArrayList<String>();
		if(!me.nathanpb.Selfs.SelfMananger.GetSelfs(player).contains(Self.RabbitLegs)){
			desc=ChatColor.DARK_RED+"Você não possui este self!";
		}else{
			lore.add(ChatColor.BLUE+"Clique para alternar");
			if(me.nathanpb.Selfs.SelfMananger.GetActiveSelfs(player).contains(Self.RabbitLegs)){
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.GREEN+"Ativado";
			}else{
				desc=ChatColor.BLUE+"Atualemente "+ChatColor.RED+"Desativado";
			}
		}
		ItemStack spell;
    	spell = new ItemStack(Material.RABBIT_FOOT);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Rabbit Legs");
    	
    	lore.add(desc);
    	spellMeta.setLore(lore);
    	spell.setItemMeta(spellMeta);
    	return spell;
	}
	
	@EventHandler
	public static void OnClick(InventoryClickEvent event){
		if(event.getCurrentItem() == null){
			return;
		}
		if(event.getInventory().getName().equals("SelfBook:")){
			if(!event.getCurrentItem().hasItemMeta()){
				event.setCancelled(true);
				return;
			}
			if(!event.getCurrentItem().getItemMeta().hasDisplayName()){
				event.setCancelled(true);
				return;
			}
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Bat Eyes")){
				List<String> lore = event.getCurrentItem().getItemMeta().getLore();
				for(String s : lore){
					if(s.contains("Ativado") || s.contains("Desativado")){
						if(s.contains("Ativado")){
							me.nathanpb.Selfs.SelfMananger.RemoveActiveSelf((Player)event.getWhoClicked(), Self.BatEyes);
						}
						if(s.contains("Desativado")){
							me.nathanpb.Selfs.SelfMananger.AddActiveSelf((Player)event.getWhoClicked(), Self.BatEyes);
						}
					}
				}
			}
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Amphibious Breath")){
				List<String> lore = event.getCurrentItem().getItemMeta().getLore();
				for(String s : lore){
					if(s.contains("Ativado") || s.contains("Desativado")){
						if(s.contains("Ativado")){
							me.nathanpb.Selfs.SelfMananger.RemoveActiveSelf((Player)event.getWhoClicked(), Self.AmphibiousBreath);
						}
						if(s.contains("Desativado")){
							me.nathanpb.Selfs.SelfMananger.AddActiveSelf((Player)event.getWhoClicked(), Self.AmphibiousBreath);
						}
					}
				}
			}
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Quicksilver Limbs")){
				List<String> lore = event.getCurrentItem().getItemMeta().getLore();
				for(String s : lore){
					if(s.contains("Ativado") || s.contains("Desativado")){
						if(s.contains("Ativado")){
							me.nathanpb.Selfs.SelfMananger.RemoveActiveSelf((Player)event.getWhoClicked(), Self.QuicksilverLimbs);
						}
						if(s.contains("Desativado")){
							me.nathanpb.Selfs.SelfMananger.AddActiveSelf((Player)event.getWhoClicked(), Self.QuicksilverLimbs);
						}
					}
				}
			}
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD+"Rabbit Legs")){
				List<String> lore = event.getCurrentItem().getItemMeta().getLore();
				for(String s : lore){
					if(s.contains("Ativado") || s.contains("Desativado")){
						if(s.contains("Ativado")){
							me.nathanpb.Selfs.SelfMananger.RemoveActiveSelf((Player)event.getWhoClicked(), Self.RabbitLegs);
						}
						if(s.contains("Desativado")){
							me.nathanpb.Selfs.SelfMananger.AddActiveSelf((Player)event.getWhoClicked(), Self.RabbitLegs);
						}
					}
				}
			}
			event.getWhoClicked().closeInventory();
			event.getWhoClicked().openInventory(Main((Player)event.getWhoClicked()));
			event.setCancelled(true);
		}
	}
}
