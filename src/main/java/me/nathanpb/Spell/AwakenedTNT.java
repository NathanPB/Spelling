package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

public class AwakenedTNT implements Spell{

	private final Spelling plugin;
	public AwakenedTNT(Spelling plugin){
		this.plugin = plugin;
	}
	
	@Override
	public int getManaCost() {
		return 250;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.BLAZE_ROD, getSpellName());
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Awakened TNT";
	}
	@Override
	public String getSpellDescription() {
		return "Throws an TNT where are you looking";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Misc;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("DND","GTG","DGD");
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('N', Material.NETHER_STAR);
		recipe.setIngredient('G', Material.SULPHUR);
		recipe.setIngredient('T', Material.TNT);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			Player p = e.getPlayer();
			Vector looking = p.getEyeLocation().getDirection().multiply(2);
			TNTPrimed tnt = p.getWorld().spawn(p.getEyeLocation().add(looking.getX(),
					looking.getY(), looking.getZ()), TNTPrimed.class);
			tnt.setVelocity(looking);
		}
	}
}
