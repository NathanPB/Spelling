package me.nathanpb.Boss;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nathanpb on 6/5/17.
 */
public class SpellingEntityBase {
    private String name;
    private double maxHealth;
    private double maxSpeed;
    private EntityType type;
    private Location spawnLocation;

    private LivingEntity entity;
    private double health;
    private boolean AIActivated;

    public SpellingEntityBase(String name, double maxHealth, double maxSpeed, EntityType type){
        this.name = name;
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.type = type;
    }
    public void spawn(Location spawnLocation){
        this.spawnLocation = spawnLocation;
        this.entity = (LivingEntity)spawnLocation.getWorld().spawnEntity(spawnLocation, type);
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
}
