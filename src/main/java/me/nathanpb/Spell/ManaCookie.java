package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ManaCookie implements Spell{
	private int cost;
	public void setManaCost(int i){
		this.cost = i;
	}
	public int getManaCost() {
		return this.cost;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Mana Cookie";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.COOKIE, getSpellName());
	}
	@Override
	public String getSpellDescription() {
		return "Can fill your hungry, saturation and give you some special effects =)";
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("SAG","ACA","GAS");
		recipe.setIngredient('A', Material.GOLDEN_APPLE);
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('S', Material.DIAMOND);
		recipe.setIngredient('C', Material.COOKIE);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerItemConsumeEvent){
			PlayerItemConsumeEvent e = (PlayerItemConsumeEvent) rawEvent;
			e.getPlayer().setFoodLevel(20);
			e.getPlayer().setSaturation(20);
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 2));
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*30, 1));
		}
	}
}
