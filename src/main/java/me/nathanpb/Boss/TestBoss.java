package me.nathanpb.Boss;

import net.minecraft.server.v1_11_R1.*;
import net.minecraft.server.v1_11_R1.Entity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nathanpb on 6/5/17.
 */
public class TestBoss extends SpellingEntityBase{
    private static String name = ChatColor.DARK_PURPLE+"Test Boss";
    private static double maxHealth = 100;
    private static double speed = 1f;
    private static EntityType type = EntityType.ZOMBIE;
    private static EntityType mainTarget = EntityType.VINDICATOR;

    public TestBoss(Location spawnLocation){
        super(name, maxHealth, speed, type);
        spawn(spawnLocation);
        clearGoals();
        addGoal(5, new PathfinderGoalMoveTowardsRestriction((EntityCreature) getAsNMSEntity(), 1.0D));
        addGoal(7, new PathfinderGoalRandomStroll((EntityCreature) getAsNMSEntity(), 1.0D));
        addGoal(8, new PathfinderGoalLookAtPlayer(getAsNMSEntity(), EntityHuman.class, 0.0F));
        addGoal(8, new PathfinderGoalRandomLookaround(getAsNMSEntity()));
        BukkitRunnable AI = new BukkitRunnable(){
            public void run(){
                if(getEntity().isDead()){
                    this.cancel();
                }
                if(getNextTarget(mainTarget) != null){
                    Vindicator target = (Vindicator) getNextTarget(mainTarget);
                    double distanceFromTarget = getLocation().distance(target.getLocation());
                    if(distanceFromTarget <= 8){
                        if(distanceFromTarget < 2){
                            attack(AttackType.MELEE, target);
                        }
                        goTo(target.getLocation());
                    }
                    if(distanceFromTarget > 8 && distanceFromTarget < 20){

                    }
                }
            }
        };
        setAI(AI);
    }
}
