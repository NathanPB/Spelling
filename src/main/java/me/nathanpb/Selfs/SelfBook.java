package me.nathanpb.Selfs;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.Spell.Spell;
import me.nathanpb.Spell.SpellTrigger;
import me.nathanpb.SpellBook.Utils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nathanpb on 6/4/17.
 */
public class SelfBook {
    public static ItemStack setLore(UUID uuid, Spell s){
        ItemStack stack = s.getSpellItem();
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(ManaMananger.getActiveSelfs(uuid).contains(s)){
            lore.add(ChatColor.BLUE+"Actually: "+ChatColor.GOLD+"On");
        }else{
            lore.add(ChatColor.BLUE+"Actually: "+ChatColor.GOLD+"Off");
        }
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }
    public static boolean getFromLore(ItemStack s){
        return s.getItemMeta().getLore().contains(ChatColor.BLUE+"Actually: "+ChatColor.GOLD+"On");
    }
    public static Inventory getInventoryFor(UUID uuid){
        int num = 0;
        List<Spell> allSelfs = new ArrayList<>();
        for(Spell s : SpellTrigger.getRegisteredList()){
            if(s.getSpellArea().equals(Utils.SpellArea.Selfs)){
                allSelfs.add(s);
            }
        }
        num = allSelfs.size();

        Inventory i = Utils.generateHolder(num, ChatColor.DARK_PURPLE+"Selfs", false);
        for(Spell s : allSelfs){
            if (ManaMananger.hasSelf(uuid, s)) {
                i.addItem(setLore(uuid, s));
            }
        }
        return i;
    }
}
