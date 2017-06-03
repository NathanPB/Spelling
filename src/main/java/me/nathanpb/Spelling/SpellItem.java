package me.nathanpb.Spelling;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by nathanpb on 1/31/17.
 */
public class SpellItem {
	static boolean PaperMode = me.nathanpb.Spelling.ConfigMananger.GetConfigBooleans("PaperMode");
    public static ItemStack Binding(){
        ItemStack spell = new ItemStack(Material.PAPER);
        ItemMeta spellMeta = spell.getItemMeta();
        spellMeta.setDisplayName(ChatColor.GOLD+"Binding");
        spell.setItemMeta(spellMeta);
        return spell;
    }
    public static ItemStack Levitator(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.FEATHER);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Levitator");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack MeteorCaller(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.FIREBALL);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Meteor Caller");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack AwakenedTNT(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.BLAZE_ROD);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Awakened TNT");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack ManaChecker(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.WATCH);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Mana Checker");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack PrimordialStick(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.STICK);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Primordial Stick");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack FlyingDevil(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.BONE);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Flying Devil");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack HandAssembler(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.CLAY_BRICK);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Hand Assembler");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack AuraShockwave(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.GLOWSTONE_DUST);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Aura Shockwave");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack ToolFixer(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.EXP_BOTTLE);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Tool Fixer");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack SpellBook(){
    	ItemStack spell = new ItemStack(Material.ENCHANTED_BOOK);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Spell Book");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack MagicBreaker(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.IRON_PICKAXE);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Magic Breaker");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack ManaCookie(){
    	ItemStack spell = new ItemStack(Material.COOKIE);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Mana Cookie");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack BatEyes(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.FERMENTED_SPIDER_EYE);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Bat Eyes");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack SelfBook(){
    	ItemStack spell = new ItemStack(Material.BOOK);
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Self Book");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack AmphibiousBreath(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.RAW_FISH);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Amphibious Breath");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack QuicksilverLimbs(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.SUGAR);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Quicksilver Limbs");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack RabbitLegs(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.RABBIT_FOOT);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Rabbit Legs");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack Blink(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.EMERALD);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Blink");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack VacuumGenerator(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.GLASS_BOTTLE);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Vacuum Generator");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
    public static ItemStack Sanitatum(){
    	ItemStack spell;
    	if(PaperMode){
    		spell = new ItemStack(Material.PAPER);
    	}else{
    		spell = new ItemStack(Material.SPECKLED_MELON);
    	}
    	ItemMeta spellMeta = spell.getItemMeta();
    	spellMeta.setDisplayName(ChatColor.GOLD+"Sanitatum");
    	spell.setItemMeta(spellMeta);
    	return spell;
    }
}
