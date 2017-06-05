package me.nathanpb.Selfs;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.Spell.Spell;
import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffectType;

public class QuicksilverLimbs implements Spell, Self {
	@Override
	public int getManaCost() {
		return 0;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Quicksilver Limbs";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.SUGAR, getSpellName());
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Selfs;
	}
	@Override
	public String getSpellDescription() {
		return "Able to improve your limbs and make you faster";
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("DSD","GUG","FDF");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('S', Material.RABBIT_HIDE);
		recipe.setIngredient('U', Material.SUGAR);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('F', Material.RABBIT_FOOT);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			if(ManaMananger.getSelfs(e.getPlayer().getUniqueId()).contains(this)){
				e.getPlayer().sendMessage(ChatColor.RED+"You already have this self!");
				return;
			}
			ManaMananger.addSelf(e.getPlayer().getUniqueId(), this);
			e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
			e.getPlayer().sendMessage(ChatColor.BLUE+"You feel restless, an unusual energy falls on you");
		}
	}

	@Override
	public PotionEffectType getEffect() {
		return PotionEffectType.SPEED;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
