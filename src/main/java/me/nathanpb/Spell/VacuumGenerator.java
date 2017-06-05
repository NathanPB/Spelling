package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

public class VacuumGenerator implements Spell{
	
	//https://bukkit.org/threads/tutorial-how-to-calculate-vectors.138849/
	
	private Spelling plugin;
	public VacuumGenerator(Spelling plugin){
		this.plugin = plugin;
	}
	private int manaCost = 2;
	
	public void setManaCost(int i){
		manaCost = i;
	}
	@Override
	public int getManaCost() {
		return this.manaCost;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Vacuum Generator";
	}
	@Override
	public String getSpellDescription() {
		return "Sucks all nearby drops to your position. Sneak to suck living entities";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.GLASS_BOTTLE, getSpellName());
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("CDC","GBG","CDC");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('C', Material.CHORUS_FRUIT);
		recipe.setIngredient('D', Material.DIAMOND);
		recipe.setIngredient('B', Material.GLASS_BOTTLE);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			Player p = e.getPlayer();
			for(Entity en : p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 10.0D, 10.0D, 10.0D)){
				if(en instanceof Item){
					//https://bukkit.org/threads/tutorial-how-to-calculate-vectors.138849/
					double dX = p.getLocation().getX() - en.getLocation().getX();
					double dY = p.getLocation().getY() - en.getLocation().getY();
					double dZ = p.getLocation().getZ() - en.getLocation().getZ();
					double yaw = Math.atan2(dZ, dX);
					double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
					double X = Math.sin(pitch) * Math.cos(yaw);
					double Y = Math.sin(pitch) * Math.sin(yaw);
					double Z = Math.cos(pitch);
					Vector vector = new Vector(X, Z, Y);
					vector = en.getLocation().toVector().subtract(p.getLocation().toVector());
					Vector from = new Vector(p.getLocation().getX(), p.getEyeLocation().getY(), p.getLocation().getZ());
					Vector to  = new Vector(en.getLocation().getX(), en.getLocation().getY(), en.getLocation().getZ());
					vector = from.subtract(to);
					vector.multiply(0.1);
					en.setVelocity(vector);
					en.getWorld().spawnParticle(Particle.TOWN_AURA, en.getLocation(),100);
				}
			}
		}
	}
	
	public void triggerIfShifted(PlayerInteractEvent e){
		Player p = e.getPlayer();
		for(Entity en : p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 10.0D, 10.0D, 10.0D)){
			if(!(en instanceof Player)){
				//https://bukkit.org/threads/tutorial-how-to-calculate-vectors.138849/
				double dX = p.getLocation().getX() - en.getLocation().getX();
				double dY = p.getLocation().getY() - en.getLocation().getY();
				double dZ = p.getLocation().getZ() - en.getLocation().getZ();
				double yaw = Math.atan2(dZ, dX);
				double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
				double X = Math.sin(pitch) * Math.cos(yaw);
				double Y = Math.sin(pitch) * Math.sin(yaw);
				double Z = Math.cos(pitch);
				Vector vector = new Vector(X, Z, Y);
				vector = en.getLocation().toVector().subtract(p.getLocation().toVector());
				Vector from = new Vector(p.getLocation().getX(), p.getEyeLocation().getY(), p.getLocation().getZ());
				Vector to  = new Vector(en.getLocation().getX(), en.getLocation().getY(), en.getLocation().getZ());
				vector = from.subtract(to);
				vector.multiply(0.1);
				en.setVelocity(vector);
				en.getWorld().spawnParticle(Particle.TOWN_AURA, en.getLocation(),100);
			}
		}
	}
}
