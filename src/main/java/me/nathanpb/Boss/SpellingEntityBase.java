package me.nathanpb.Boss;

import net.minecraft.server.v1_11_R1.EntityInsentient;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
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
    private BukkitRunnable AI;

    private LivingEntity entity;
    private double health;
    private boolean AIActivated;

    public SpellingEntityBase(String name, double maxHealth, double maxSpeed, EntityType type){
        this.name = name;
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.type = type;
        setupAI();
    }
    public void spawn(Location spawnLocation){
        this.spawnLocation = spawnLocation;
        this.entity = (LivingEntity)spawnLocation.getWorld().spawnEntity(spawnLocation, type);
        this.entity.setAI(false);
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
    public double getMaxSpeed(){
        return this.maxSpeed;
    }
    public LivingEntity getEntity(){
        return this.entity;
    }
    public BukkitRunnable getAI(){
        return this.AI;
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
        this.maxSpeed = maxSpeed;
    }
    public void setAI(BukkitRunnable ai){
        this.AI = ai;
    }

    //UTIL
    public void goTo(Location loc){
        ((EntityInsentient) ((CraftEntity) this.entity).getHandle()).getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), getMaxSpeed());
    }

    private void setupAI(){
        if(this.AI != null){
            if(isAITrue()){
                AI.run();
            }
        }
    }
}
