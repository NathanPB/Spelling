package me.nathanpb.Spell;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;

public class Sanitatum implements Spell{
	private List<PotionEffectType> pet = new ArrayList<>();
	private int manaCost = 10;
	private Spelling plugin;
	public Sanitatum(Spelling plugin){
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
		return ChatColor.GOLD+"Sanitatum";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.SPECKLED_MELON, getSpellName());
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Defense;
	}
	@Override
	public String getSpellDescription() {
		return "This spell can heal you and purify your body from negative effects, like wither effect or poison";
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("DAD","GMG","DGD");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('A', Material.GOLDEN_APPLE,1);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('M', Material.SPECKLED_MELON);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			Player p = e.getPlayer();
			p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));
			for(PotionEffectType effect : pet){
				p.removePotionEffect(effect);
				p.sendMessage(org.bukkit.ChatColor.GOLD+effect.getName()+ChatColor.BLUE+" removed!");
			}
		}
	}
	public int calculateManaCost(Player p){
		int cost = 20;
		if(p.hasPotionEffect(PotionEffectType.WITHER)){
			cost += 60;
			pet.add(PotionEffectType.WITHER);
		}
		if(p.hasPotionEffect(PotionEffectType.POISON)){
			cost +=40;
			pet.add(PotionEffectType.POISON);
		}
		return cost;
	}
	
}
