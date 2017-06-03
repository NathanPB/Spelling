package me.nathanpb.Spell;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;
import org.bukkit.ChatColor;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;

public class Levitator implements Spell{
	private Spelling plugin;
	public Levitator(Spelling plugin){
		this.plugin = plugin;
	}
	
	@Override
	public int getManaCost() {
		return 10;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.FEATHER, getSpellName());
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Levitator";
	}
	@Override
	public String getSpellDescription() {
		return "Invokes an airstream that can levitate you and throws to the direction that you are looking";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("SDS","DFD","SDS");
		recipe.setIngredient('F', Material.FEATHER);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('S', Material.STRING);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			Player p = e.getPlayer();
			p.setAllowFlight(true);
			Vector looking = p.getEyeLocation().getDirection();
			if(p.isGliding()){
				looking = looking.multiply(2);	
			}
			p.setVelocity(looking);
			if(p.getGameMode().equals(GameMode.CREATIVE)||p.getGameMode().equals(GameMode.SPECTATOR)){
				return;
			}
			p.setAllowFlight(false);
		}
	}
}
