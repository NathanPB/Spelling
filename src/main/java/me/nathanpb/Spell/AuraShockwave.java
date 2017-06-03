package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.ConfigMananger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

public class AuraShockwave implements Spell{
	private final me.nathanpb.Spelling.Spelling plugin;
	public AuraShockwave(me.nathanpb.Spelling.Spelling plugin){
		this.plugin = plugin;
	}
	
	@Override
	public int getManaCost(){
		return 500;
	}
	
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Aura Shockwave";
	}
	
	@Override
	public String getSpellDescription() {
		return "Creates an aura os energy, throwing all nearby mobs far away";
	}
	
    public ItemStack getSpellItem(){
    	if(ConfigMananger.GetConfigBooleans("PaperMode")){
    		return Utils.Icon(Material.PAPER, getSpellName());
    	}else{
    		return Utils.Icon(Material.GLOWSTONE_DUST, getSpellName());
    	}
    }
    @Override
    public SpellArea getSpellArea() {
    	return SpellArea.Defense;
    }
    @Override
    public ShapedRecipe getRecipe() {
    	ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("CEC","SGS","TDT");
		recipe.setIngredient('C', Material.END_CRYSTAL);
		recipe.setIngredient('E', Material.END_ROD);
		recipe.setIngredient('S', Material.SHIELD);
		recipe.setIngredient('G', Material.GLOWSTONE_DUST);
		recipe.setIngredient('T', Material.GHAST_TEAR);
		recipe.setIngredient('D', Material.DIAMOND);
		return recipe;
    }
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent event = (PlayerInteractEvent)rawEvent;
			Player p = event.getPlayer();
			for(Entity e : p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 15.0D, 15.0D, 15.0D)){
				if(e instanceof LivingEntity){
					if(e instanceof Player){
						Player ep = (Player)e;
						if(ep.equals(p)){
							return;
						}
					}
					//https://bukkit.org/threads/tutorial-how-to-calculate-vectors.138849/
					double dX = p.getLocation().getX() - e.getLocation().getX();
					double dY = p.getLocation().getY() - e.getLocation().getY();
					double dZ = p.getLocation().getZ() - e.getLocation().getZ();
					double yaw = Math.atan2(dZ, dX);
					double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
					double X = Math.sin(pitch) * Math.cos(yaw);
					double Y = Math.sin(pitch) * Math.sin(yaw);
					double Z = Math.cos(pitch);
					Vector vector = new Vector(X, Z, Y);
					vector = e.getLocation().toVector().subtract(p.getLocation().toVector());
					Vector from = new Vector(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
					Vector to  = new Vector(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ());
					vector = to.subtract(from);
					vector = vector.multiply(2);
					vector = vector.setY(2);
					e.setVelocity(vector);
				}
			}
		}
	}	
}
