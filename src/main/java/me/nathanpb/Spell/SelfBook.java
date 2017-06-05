package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class SelfBook implements Spell{
	@Override
	public int getManaCost() {
		return 0;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Self Book";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.BOOK, getSpellName());
	}
	@Override
	public String getSpellDescription() {
		return "It isn't a spell, only a book ¯\\_(ツ)_/¯";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Books;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("GAG","ABA","GAG");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('A', Material.GOLDEN_APPLE);
		recipe.setIngredient('B', Material.BOOK);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			e.getPlayer().openInventory(me.nathanpb.Selfs.SelfBook.getInventoryFor(e.getPlayer().getUniqueId()));
		}
	}
	
}
