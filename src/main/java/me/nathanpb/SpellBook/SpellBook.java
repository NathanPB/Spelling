package me.nathanpb.SpellBook;

import me.nathanpb.Spell.Spell;
import me.nathanpb.Spell.SpellTrigger;
import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpellBook{
	public static Inventory main = getMain();
	public static HashMap<SpellArea, Inventory> areas = getAreaHolders();
	
	public static Inventory getMain(){
		Inventory i = Utils.generateHolder(SpellArea.values().length, ChatColor.DARK_PURPLE+"Main Menu", false);
		for(SpellArea icon : SpellArea.values()){
			i.addItem(icon.getValue());
		}
		return i;
	}
	public static HashMap<SpellArea, Inventory> getAreaHolders(){
		HashMap<SpellArea, Inventory> hash = new HashMap<>();
		for(SpellArea area : SpellArea.values()){
			List<Spell> spell = new ArrayList<>();
			
			for(Spell s : SpellTrigger.getRegisteredList()){
				if(s.getSpellArea().equals(area)){
					spell.add(s);
				}
			}
			Inventory i = Utils.generateHolder(spell.size(), ChatColor.DARK_PURPLE+"Recipes - "+area, true);
			addSpellsToArea(area, i);
			hash.put(area, i);
		}
		return hash;
	}
	public static void addSpellsToArea(SpellArea area, Inventory i){
		for(Spell s : SpellTrigger.getRegisteredList()){
			if(s.getSpellArea().equals(area)){
				List<String> lore = new ArrayList<>();
				lore.add(s.getSpellDescription());
				
				ItemStack item = s.getSpellItem();
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lore);
				item.setItemMeta(meta);
				
				i.addItem(item);
			}
		}
	}
}
