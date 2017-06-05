package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import me.nathanpb.Spelling.Spelling;
import org.bukkit.*;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Blink implements Spell{
	private final Spelling plugin;
	
	public Blink(Spelling plugin){
		this.plugin = plugin;
	}
	private int manaCost;
	
	public void setManaCost(int i){
		this.manaCost = i;
	}
	@Override
	public int getManaCost() {
		return this.manaCost;
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.EMERALD, getSpellName());
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Blink";
	}
	@Override
	public String getSpellDescription() {
		return "Portable teleporter that can be linked";
	}
	
	public void setLocation(PlayerInteractEvent e){
		List<String> lore = new ArrayList<String>();
		Location loc = e.getPlayer().getLocation();
		lore.add("World: "+loc.getWorld().getName());
		lore.add("X: "+Math.round(loc.getX()));
		lore.add("Y: "+Math.round(loc.getY()));
		lore.add("Z: "+Math.round(loc.getZ()));
		ItemMeta meta = e.getItem().getItemMeta();
		meta.setLore(lore);
		e.getItem().setItemMeta(meta);
		return;
	}
	
	public Location getTeleportLocation(PlayerInteractEvent e){
		
		//PLAYER INTERACT EVENT INSTANCIADO!!!
		List<String> lore = e.getItem().getItemMeta().getLore();
		Location loc = e.getPlayer().getLocation();
		String w="";
		String x="";
		String y="";
		String z="";
		for(String s : lore){
			if(s.contains("World")){
				w = s.replace("World: ", "");
			}
			if(s.contains("X")){
				x = s.replace("X: ", "");
			}
			if(s.contains("Y")){
				y = s.replace("Y: ", "");
			}
			if(s.contains("Z")){
				z = s.replace("Z: ", "");
			}
		}
		loc.setWorld(Bukkit.getWorld(w));
		loc.setX(Double.valueOf(x));
		loc.setY(Double.valueOf(y));
		loc.setZ(Double.valueOf(z));
		return loc;
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Utility;
	}
	@Override
	public ShapedRecipe getRecipe(){
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("GEG","EOE","GEG");
		recipe.setIngredient('G', Material.GHAST_TEAR);
		recipe.setIngredient('E', Material.ENDER_PEARL);
		recipe.setIngredient('O', Material.EMERALD);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent)rawEvent;
			Location loc = getTeleportLocation(e);
			e.getPlayer().teleport(loc);
			e.getPlayer().playSound(loc, Sound.ENTITY_ENDERMEN_TELEPORT, 10, 10);
			loc.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc, 10);
		}
	}
}
