package me.nathanpb.Spell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;

public class FlyingDevil implements Spell{
	private int manaCost = 10;
	private Spelling plugin;
	public FlyingDevil(Spelling plugin){
		this.plugin = plugin;
	}
	
	public void setManaCost(int i){
		this.manaCost = i;
	}
	@Override
	public int getManaCost() {
		return this.manaCost;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Flying Devil";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.BONE, getSpellName());
	}
	@Override
	public String getSpellDescription() {
		return "Let your enemies to fly... And fall like a shit";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Defense;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("GFS","CBC","SNG");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('F', Material.FEATHER);
		recipe.setIngredient('S', Material.SHULKER_SHELL);
		recipe.setIngredient('C', Material.CHORUS_FRUIT);
		recipe.setIngredient('N', Material.NETHER_STAR);
		recipe.setIngredient('B', Material.BONE);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEntityEvent){
			PlayerInteractEntityEvent e = (PlayerInteractEntityEvent)rawEvent;
			e.getRightClicked().setVelocity(new Vector(0,50,0));
		}
	}
}
