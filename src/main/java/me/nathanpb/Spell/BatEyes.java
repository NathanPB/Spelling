package me.nathanpb.Spell;


import me.nathanpb.Selfs.SelfMananger;
import me.nathanpb.Selfs.SelfMananger.Self;
import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BatEyes implements Spell{
	
	public int getManaCost() {
		return 0;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Bat Eyes";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.SPIDER_EYE, getSpellName());
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Selfs;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("GCD","MEM","DCG");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('C', Material.GOLDEN_CARROT);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('M', Material.SPECKLED_MELON);
		recipe.setIngredient('E', Material.FERMENTED_SPIDER_EYE);
		return recipe;
	}
	@Override
	public String getSpellDescription() {
		return "Can make your eyes see in the dark";
	}
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			if(SelfMananger.GetSelfs(e.getPlayer()).contains(Self.BatEyes)){
				return;
			}
			e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
			SelfMananger.AddSelf(e.getPlayer(), Self.BatEyes);
			e.getPlayer().sendMessage(ChatColor.BLUE+"Your eyes are ashing, something strange just happens with your body");
		}
	}
}
