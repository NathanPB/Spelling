package me.nathanpb.Spell;

import me.nathanpb.SpellBook.Utils.SpellArea;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public interface Spell {
	public int getManaCost();
	public String getSpellName();
	public ItemStack getSpellItem();
	public String getSpellDescription();
	public SpellArea getSpellArea();
	public ShapedRecipe getRecipe();
	public void triggeredSpellEvent(Event rawEvent);
}
