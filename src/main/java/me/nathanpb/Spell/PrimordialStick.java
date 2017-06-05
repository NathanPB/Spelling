package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils;
import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class PrimordialStick implements Spell{
	public static List<Snowball> snowball = new ArrayList<Snowball>();
	@Override
	public int getManaCost() {
		return 10;
	}
	@Override
	public String getSpellName() {
		return ChatColor.GOLD+"Primordial Stick";
	}
	@Override
	public ItemStack getSpellItem() {
		return Utils.Icon(Material.STICK, getSpellName());
	}
	@Override
	public String getSpellDescription() {
		return "A weapon that can hurt things and glow them";
	}
	@Override
	public SpellArea getSpellArea() {
		return SpellArea.Attack;
	}
	@Override
	public ShapedRecipe getRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(getSpellItem());
		recipe.shape("NDN","CSC","NAN");
		recipe.setIngredient('N', Material.NETHER_STAR);
		recipe.setIngredient('D', Material.DIAMOND_SWORD);
		recipe.setIngredient('S', Material.STICK);
		recipe.setIngredient('C', Material.END_CRYSTAL);
		recipe.setIngredient('A', Material.SPECTRAL_ARROW);
		return recipe;
	}
	@Override
	public void triggeredSpellEvent(Event rawEvent) {
		if(rawEvent instanceof PlayerInteractEvent){
			PlayerInteractEvent e = (PlayerInteractEvent) rawEvent;
			Player p = e.getPlayer();
			
			Vector looking = p.getEyeLocation().getDirection();
			Snowball ball = p.getWorld().spawn(p.getEyeLocation().add(looking.getX(),
					looking.getY(), looking.getZ()), Snowball.class);
			ball.setVelocity(looking);
			ball.setGravity(false);
			snowball.add(ball);
		}
		if(rawEvent instanceof EntityDamageByEntityEvent){
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) rawEvent;
			if(e.getDamager() instanceof Snowball){
				if(snowball.contains(e.getDamager())){
					if(!(e.getEntity() instanceof LivingEntity)){
						return;
					}
					((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 1));
				}
			}
			if(e.getDamager() instanceof Player){
				e.setCancelled(true);
				Player p = (Player)e.getDamager();
				LivingEntity entity = (LivingEntity) e.getEntity();
				entity.damage(25, p);
			}
		}
	}
}
