package me.nathanpb.Spell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;

public class SpellBook implements Spell{
	@Override
	public int getManaCost() {
		return 0;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Spell Book";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Books;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.ENCHANTED_BOOK, getSpellName());
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("AGA","ABA","AAA");
		recipe.setIngredient('A', Material.AIR);
		recipe.setIngredient('B', Material.BOOK_AND_QUILL);
		recipe.setIngredient('G', Material.GHAST_TEAR);
		return recipe;
	}
	@Override
	public String getSpellDescription() {
		return "It isn't a spell, only a book ¯\\_(ツ)_/¯";
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			e.getPlayer().openInventory(me.nathanpb.SpellBook.SpellBook.main);
		}
		
	}
}
