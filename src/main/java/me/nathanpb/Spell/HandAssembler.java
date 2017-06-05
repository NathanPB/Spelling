package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

;

public class HandAssembler implements Spell{
	private Spelling plugin;
	public HandAssembler(Spelling pluign){
		this.plugin = plugin;
	}
	@Override
	public int getManaCost() {
		return 5;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Hand Assembler";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public String getSpellDescription() {
		return "Allows you to make craftings 'in your hand'";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.CLAY_BRICK, getSpellName());
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("ACA","CBC","ACA");
		recipe.setIngredient('B', Material.CLAY_BRICK);
		recipe.setIngredient('C', Material.WORKBENCH);
		recipe.setIngredient('A', Material.AIR);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent){
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			e.getPlayer().openWorkbench(null, true);
		}
	}
}
