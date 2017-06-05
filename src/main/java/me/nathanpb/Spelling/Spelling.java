package me.nathanpb.Spelling;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.Selfs.*;
import me.nathanpb.Spell.*;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
public class Spelling extends JavaPlugin{
	
	public static Spelling getSpelling(){
		return (Spelling) Bukkit.getServer().getPluginManager().getPlugin("Spelling");
	}
	
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting Spelling "+ this.getDescription().getVersion());
        this.getCommand("Spelling").setExecutor(new me.nathanpb.Commands.Spelling(this));
        getServer().getPluginManager().registerEvents(new me.nathanpb.EventHandler.ThingsToListen(null), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.EventHandler.ManaMananger(), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.SpellBook.Handler(), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.Spell.SpellTrigger(null), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.Selfs.Handler(), this);
        
        SpellTrigger.registerSpell(new AuraShockwave(null));
        SpellTrigger.registerSpell(new AwakenedTNT(null));
        SpellTrigger.registerSpell(new Blink(null));
        SpellTrigger.registerSpell(new Levitator(null));
        SpellTrigger.registerSpell(new ManaChecker(null));
        SpellTrigger.registerSpell(new MeteorCaller(null));
        SpellTrigger.registerSpell(new VacuumGenerator(null));
        SpellTrigger.registerSpell(new Binding(null));
        SpellTrigger.registerSpell(new FlyingDevil(null));
        SpellTrigger.registerSpell(new HandAssembler(null));
        SpellTrigger.registerSpell(new Sanitatum(null));
        SpellTrigger.registerSpell(new MagicBreaker());
        SpellTrigger.registerSpell(new ManaCookie());
        SpellTrigger.registerSpell(new PrimordialStick());
        SpellTrigger.registerSpell(new me.nathanpb.Spell.SpellBook());
        SpellTrigger.registerSpell(new BatEyes());
        SpellTrigger.registerSpell(new AmphibiousBreath());
        SpellTrigger.registerSpell(new QuicksilverLimbs());
        SpellTrigger.registerSpell(new me.nathanpb.Spell.SelfBook());
        SpellTrigger.registerSpell(new RabbitLegs());

        SelfMananger.startRunnable();
        ManaMananger.startRunnables();
    }

    @Override
    public void onDisable() {
    	HandlerList.unregisterAll();

    }
    public void flying(){
        if(Bukkit.getServer().getAllowFlight()){
        	Bukkit.getServer().getLogger().info("Spelling require flying enabled, actually, it's enabled");
        }else{
        	Bukkit.getServer().getLogger().info("Spelling require flying enabled, actually, it's disabled");
        	Bukkit.getServer().getLogger().info("PLEASE ENABLE IT OR YOU WILL GET FLOODED!");
        }
    }
}
