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

public class AmphibiousBreath implements Spell, Self {
	@Override
	public int getManaCost() {
		return 0;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Amphibious Breath";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.RAW_FISH, getSpellName());
	}
	@Override
	public String getSpellDescription() {
		return "Adapts your lungs to breathe even submerged";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Selfs;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("PGD","AFA","DGP");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('A', Material.PRISMARINE_CRYSTALS);
		recipe.setIngredient('P', Material.PRISMARINE_SHARD);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('F', Material.RAW_FISH);
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
			e.getPlayer().sendMessage(ChatColor.BLUE+"You are felling your skin flacky and your lumbs stronger...");
		}
	}

	@Override
	public PotionEffectType getEffect() {
		return PotionEffectType.WATER_BREATHING;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}
}
