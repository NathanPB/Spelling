package me.nathanpb.Spell;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.ChatColor;;

public class ManaChecker implements Spell{
	private Spelling plugin;
	public ManaChecker(Spelling plugin){
		this.plugin = plugin;
	}
	@Override
	public int getManaCost() {
		return 1;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Mana Checker";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.WATCH, getSpellName());
	}
	@Override
	public String getSpellDescription() {
		return "Allows you to see your mana, level , progression, etc.";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("RDR","SCS","RGR");
		recipe.setIngredient('R', Material.REDSTONE);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('S', Material.SUGAR);
		recipe.setIngredient('C', Material.WATCH);
		recipe.setIngredient('G', Material.GOLD_NUGGET);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			ManaMananger.SendManaPacket(e.getPlayer());
		}
	}
}