package me.nathanpb.SpellBook;


import me.nathanpb.Spell.Spell;
import me.nathanpb.Spell.SpellTrigger;
import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Handler implements Listener{
	private static HashMap<Player, Spell> lastClicked = new HashMap<>();
	@EventHandler
	public static void event(InventoryClickEvent e){
		if(e.getClickedInventory() == null){
			return;
		}
		if(e.getClickedInventory().getName().startsWith(ChatColor.DARK_PURPLE+"Recipe")){
			e.setCancelled(true);
			for(Spell s : SpellTrigger.getRegisteredList()){
				ItemStack target = e.getCurrentItem();
				ItemMeta meta = target.getItemMeta();
				List<String> lore = new ArrayList<>();
				meta.setLore(lore);
				target.setItemMeta(meta);
				if(target.equals(s.getSpellItem())){
					Inventory i = Utils.mkRecipe(s);
					lastClicked.put((Player)e.getWhoClicked(), s);
					Utils.applyCheatButton(i, (Player)e.getWhoClicked());
					e.getWhoClicked().openInventory(i);
					e.setCancelled(true);
				}
			}
			if(e.getCurrentItem().equals(Utils.returnButton)){
				e.getWhoClicked().openInventory(SpellBook.main);
			}
			if(e.getCurrentItem().equals(Utils.cheatButton)){
				e.getWhoClicked().getInventory().addItem(lastClicked.get(e.getWhoClicked()).getSpellItem());
			}
		}
		if(e.getClickedInventory().equals(SpellBook.main)){
			for(SpellArea s : SpellArea.values()){
				if(s.getValue().equals(e.getCurrentItem())){
					e.getWhoClicked().openInventory(SpellBook.areas.get(s));
				}
			}
			e.setCancelled(true);
		}
	}
}
