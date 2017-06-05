package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MagicBreaker implements Spell{
	public static List<Snowball> snowball = new ArrayList<Snowball>();
	@Override
	public int getManaCost() {
		return 3;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Magic Breaker";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.IRON_PICKAXE, getSpellName());
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("DAD","GPG","SDS");
		recipe.setIngredient('A', Material.IRON_AXE);
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('P', Material.IRON_PICKAXE);
		recipe.setIngredient('S', Material.IRON_SPADE);
		recipe.setIngredient('D', Material.DIAMOND);
		return recipe;
	}
	@Override
	public String getSpellDescription() {
		return "Can break block at long range";
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				if(e.getClickedBlock().getType().equals(Material.BEDROCK)){
					return;
				}
				e.getClickedBlock().breakNaturally();
				return;
			}
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
				Vector looking = e.getPlayer().getEyeLocation().getDirection();
				Snowball ball = e.getPlayer().getWorld().spawn(e.getPlayer().
						getEyeLocation().add(looking.getX(),
						looking.getY(), looking.getZ()), Snowball.class);
				ball.setVelocity(looking);
				ball.setGravity(false);
				snowball.add(ball);
			}
		}
		
	}
}
