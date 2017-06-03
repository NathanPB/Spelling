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

public class QuicksilverLimbs implements Spell{
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
			if(SelfMananger.GetSelfs(e.getPlayer()).contains(Self.AmphibiousBreath)){
				return;
			}
			e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
			SelfMananger.AddSelf(e.getPlayer(), Self.AmphibiousBreath);
			e.getPlayer().sendMessage(ChatColor.BLUE+"You feel restless, an unusual energy falls on you");
		}
	}
}
