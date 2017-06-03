package me.nathanpb.Spell;

import me.nathanpb.Selfs.SelfMananger;
import me.nathanpb.Selfs.SelfMananger.Self;
import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class RabbitLegs implements Spell{
	@Override
	public int getManaCost() {
		return 0;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Rabbit Legs";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.RABBIT_FOOT, getSpellName());
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Selfs;
	}
	@Override
	public String getSpellDescription() {
		return "Makes you jump like a rabbit";
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("SGS","DFD","SGS");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('S', Material.RABBIT_HIDE);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('F', Material.RABBIT_FOOT);
		return recipe;
	}
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			if(SelfMananger.GetSelfs(e.getPlayer()).contains(Self.RabbitLegs)){
				return;
			}
			e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
			SelfMananger.AddSelf(e.getPlayer(), Self.RabbitLegs);
			e.getPlayer().sendMessage(ChatColor.BLUE+"Your legs are more agile ... Maybe mutated? Do they really belong to you?");
		}
	}
}
