package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashSet;

public class MeteorCaller implements Spell{

	private Spelling plugin;
	public MeteorCaller(Spelling plugin){
		this.plugin = plugin;
	}
	
	@Override
	public int getManaCost() {
		return 1000;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.FIREBALL, getSpellName());
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Meteor Caller";
	}
	@Override
	public String getSpellDescription() {
		return "Calls an meteor where are you looking";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Misc;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("DBD","TFT","DDD");
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('B', Material.BLAZE_POWDER);
		recipe.setIngredient('T', Material.TNT);
		recipe.setIngredient('F', Material.FIREBALL);
		return recipe;
	}
	
	public boolean checkIfIsVoid(PlayerInteractEvent e){
		Location looking = e.getPlayer().getTargetBlock((HashSet<Byte>)null, 200).getLocation();
		looking.setY(255);
		while(looking.getBlock().getType() == Material.AIR){
			looking.setY(looking.getY()-1);
			if(looking.getY() == 0){
				return true;
			}
		}
		return false;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent){
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			Location looking = e.getPlayer().getTargetBlock((HashSet<Byte>)null, 200).getLocation();
			FallingBlock fallingBlock = looking.getWorld().spawnFallingBlock(new Location(looking.getWorld(), looking.getX(), 255, looking.getZ())
			, Material.OBSIDIAN, (byte)0);
			fallingBlock.setCustomName("Meteoro");
			fallingBlock.setGlowing(true);
		}
	}
}
