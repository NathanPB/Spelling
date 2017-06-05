package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Binding implements Spell{
	private Spelling plugin;
	public Binding(Spelling plugin){
		this.plugin = plugin;
	}
	
	@Override
	public int getManaCost() {
		return 100;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Binding";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.PAPER, getSpellName());
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Defense;
	}
	@Override
	public String getSpellDescription() {
		return "Can bind the monsters that you right click";
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("DFD","NPN","DSD");
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('N', Material.NETHER_STAR);
		recipe.setIngredient('P', Material.PAPER);
		recipe.setIngredient('S', Material.SUGAR);
		recipe.setIngredient('F', Material.FERMENTED_SPIDER_EYE);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEntityEvent){
			PlayerInteractEntityEvent e = (PlayerInteractEntityEvent)rawEvent;
			
			LivingEntity target = (LivingEntity)e.getRightClicked();
			final Location location = new Location(target.getWorld(), target.getLocation().getX(),
				target.getLocation().getY()+1, target.getLocation().getZ());
			target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 10));
			target.getWorld().playEffect(location, Effect.SMOKE, 2003);
		}
	}
	
}
