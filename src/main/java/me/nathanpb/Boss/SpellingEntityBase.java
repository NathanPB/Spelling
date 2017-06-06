package me.nathanpb.Boss;

import com.google.common.collect.Sets;
import me.nathanpb.Spelling.Spelling;
import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanpb on 6/5/17.
 */
public class SpellingEntityBase {
    private String name;
    private double maxHealth;
    private double speed;
    private EntityType type;
    private Location spawnLocation;
    private BukkitRunnable AI;

    private LivingEntity entity;
    private double health;
    private boolean AIActivated = true;

    public SpellingEntityBase(String name, double maxHealth, double maxSpeed, EntityType type){
        this.name = name;
        this.maxHealth = maxHealth;
        this.speed = maxSpeed;
        this.type = type;
        setupAI();
    }
    public void spawn(Location spawnLocation){
        this.spawnLocation = spawnLocation;
        this.entity = (LivingEntity)spawnLocation.getWorld().spawnEntity(spawnLocation, type);
        this.entity.setCustomName(this.name);
    }
    public void kill(){
        this.entity.setHealth(0);
    }
    public void remove(){
        this.entity.remove();
    }

    //GETTERS
    public double getHealth(){
        return this.entity.getHealth();
    }
    public double getMaxHealth(){
        return this.entity.getMaxHealth();
    }
    public Location getLocation(){
        return this.entity.getLocation();
    }
    public Location getSpawnLocation(){
        return this.spawnLocation;
    }
    public boolean isAlive(){
        return !this.entity.isDead();
    }
    public boolean isDead(){
        return this.entity.isDead();
    }
    public boolean isAITrue(){
        return this.AIActivated;
    }
    public double getSpeed(){
        return this.speed;
    }
    public LivingEntity getEntity(){
        return this.entity;
    }
    public BukkitRunnable getAI(){
        return this.AI;
    }
    public EntityInsentient getAsNMSEntity(){
        return ((EntityInsentient)((CraftEntity)this.entity).getHandle());
    }

    //SETTERS
    public void setHealth(double health){
        this.entity.setHealth(health);
    }
    public void setMaxHealth(double health){
        this.entity.setMaxHealth(health);
    }
    public void setLocation(Location loc){
        this.entity.teleport(loc);
    }
    public void setAI(boolean ai){
        this.AIActivated = ai;
    }
    public void setMaxSpeed(double maxSpeed){
        this.speed = maxSpeed;
    }
    public void setAI(BukkitRunnable ai){
        this.AI = ai;
        if(this.AI != null){
            if(isAITrue()){
                AI.runTaskTimer(Spelling.getSpelling(), 1, 1);
                Bukkit.broadcastMessage("iniciou AI");
            }
        }
    }

    //UTIL
    public void goTo(Location loc){
        net.minecraft.server.v1_11_R1.Entity bondia = ((CraftEntity)this.entity).getHandle();
        PathEntity path = ((EntityInsentient) bondia).getNavigation().a(
                loc.getX(), loc.getY(), loc.getZ() + 1);
        ((EntityInsentient) bondia).getNavigation().a(path, getSpeed());
    }

    private void setupAI(){
        if(this.AI != null){
            if(isAITrue()){
                AI.runTaskTimer(Spelling.getSpelling(), 1, 1);
            }
        }
    }
    public Entity getNextTarget(EntityType type){
        List<Entity> targets = new ArrayList<>();
        for(Entity e : this.entity.getNearbyEntities(15, 3, 15)){
            if(e.getType().equals(type)){
                targets.add(e);
            }
        }
        Entity lastTarget = null;
        for(Entity e : targets){
            if(lastTarget == null){
                lastTarget = e;
            }
            if(this.entity.getLocation().distance(e.getLocation()) < this.entity.getLocation().distance(lastTarget.getLocation())){
                lastTarget = e;
            }
        }
        return lastTarget;
    }
    public void clearGoals(){
        try {
            EntityInsentient c = getAsNMSEntity();
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            bField.set(c.goalSelector, Sets.newLinkedHashSet());
            bField.set(c.targetSelector, Sets.newLinkedHashSet());
            cField.set(c.goalSelector, Sets.newLinkedHashSet());
            cField.set(c.targetSelector, Sets.newLinkedHashSet());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addGoal(int id, PathfinderGoal goal){
        EntityInsentient c = getAsNMSEntity();
        c.goalSelector.a(0, new PathfinderGoalFloat(c));
        c.goalSelector.a(id, goal);
    }
    public void attack(AttackType type, LivingEntity target){
        if(type.equals(AttackType.MELEE)){
            getAsNMSEntity().B((EntityLiving)((CraftEntity)target).getHandle());
        }
    }
}
