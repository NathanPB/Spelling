package me.nathanpb.Spelling;

import me.nathanpb.Spell.AmphibiousBreath;
import me.nathanpb.Spell.AuraShockwave;
import me.nathanpb.Spell.AwakenedTNT;
import me.nathanpb.Spell.BatEyes;
import me.nathanpb.Spell.Binding;
import me.nathanpb.Spell.Blink;
import me.nathanpb.Spell.FlyingDevil;
import me.nathanpb.Spell.HandAssembler;
import me.nathanpb.Spell.Levitator;
import me.nathanpb.Spell.MagicBreaker;
import me.nathanpb.Spell.ManaChecker;
import me.nathanpb.Spell.ManaCookie;
import me.nathanpb.Spell.MeteorCaller;
import me.nathanpb.Spell.PrimordialStick;
import me.nathanpb.Spell.QuicksilverLimbs;
import me.nathanpb.Spell.RabbitLegs;
import me.nathanpb.Spell.Sanitatum;
import me.nathanpb.Spell.Spell;
import me.nathanpb.Spell.SpellTrigger;
import me.nathanpb.Spell.VacuumGenerator;
import me.nathanpb.SpellBook.SpellBook;

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
        this.getCommand("Mana").setExecutor(new me.nathanpb.Commands.Mana(this));
        getServer().getPluginManager().registerEvents(new me.nathanpb.EventHandler.ThingsToListen(null), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.EventHandler.ManaMananger(null), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.Selfs.SelfBook(null), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.SpellBook.Handler(), this);
        getServer().getPluginManager().registerEvents(new me.nathanpb.Spell.SpellTrigger(null), this);
        
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
        
        me.nathanpb.Spelling.ConfigMananger.GenConfigFile();
        me.nathanpb.EventHandler.ManaMananger.LoadManaOnEnable();
        me.nathanpb.Selfs.Selfs.ActiveSelfs();
    }

    @Override
    public void onDisable() {
    	HandlerList.unregisterAll();
    	me.nathanpb.EventHandler.ManaMananger.SaveManaOnDisable();
    	me.nathanpb.Spelling.Mana.mana.clear();
    	me.nathanpb.Spelling.Mana.UsedMana.clear();
    	me.nathanpb.Spelling.Mana.Burnout.clear();
    	me.nathanpb.Spelling.Mana.BurnoutDelay.clear();
    	me.nathanpb.Selfs.SelfMananger.Selfs.clear();
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
