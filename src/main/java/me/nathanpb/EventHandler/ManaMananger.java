package me.nathanpb.EventHandler;

import me.nathanpb.ProjectMetadata.ProjectMetadataObject;
import me.nathanpb.Spell.Spell;
import me.nathanpb.Spell.SpellTrigger;
import me.nathanpb.SpellBook.Utils;
import me.nathanpb.Spelling.Spelling;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManaMananger implements Listener{
	private static int levelMultiplier = 1000;

	public static List<UUID> burnoutDelay = new ArrayList<>();
	public static boolean CanUseSpell(Player p, int cost){
		UUID uuid = p.getUniqueId();
		if(burnoutDelay.contains(uuid)){
			p.sendTitle(ChatColor.RED+"Burnot too high!", ChatColor.GOLD+""+getBurnout(uuid)+ChatColor.BLUE+"/"+ChatColor.GOLD+getMaxBurnout(uuid));
			return false;
		}
		if(getMana(uuid) >= cost){
			setMana(uuid, getMana(uuid)-cost);
			addExp(uuid, cost);
			addBurnout(uuid, cost);
			return true;
		}
		p.sendTitle(ChatColor.RED+"No Mana!","");
		return false;
	}

	public static void mkProfile(UUID uuid){
		try {
			ProjectMetadataObject o = new ProjectMetadataObject(uuid);
			if(!o.hasKey("mana")){
                o.put("mana", 0);
			}
			if(!o.hasKey("selfs")){
				o.put("selfs", new ArrayList<String>());
			}
			if(!o.hasKey("active_selfs")){
				o.put("active_selfs", new ArrayList<String>());
			}
			if(!o.hasKey("exp")){
				o.put("exp", 0);
			}
			if(!o.hasKey("burnout")){
				o.put("burnout", 0);
			}
			if(!o.hasKey("level")){
				o.put("level", 0);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static int getMana(UUID uuid){
		mkProfile(uuid);
	    try {
			return new ProjectMetadataObject(uuid).getAsInt("mana");
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public static int getExp(UUID uuid){
        mkProfile(uuid);
		try {
			return new ProjectMetadataObject(uuid).getAsInt("exp");
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public static int getBurnout(UUID uuid){
        mkProfile(uuid);
        int i = 0;
		try {
			i = new ProjectMetadataObject(uuid).getAsInt("burnout");
		}catch (Exception e){
			e.printStackTrace();
		}
		if(i > getMaxBurnout(uuid)){
			if(!burnoutDelay.contains(uuid)){
				burnoutDelay.add(uuid);
			}
		}
		return i;
	}
	public static int getLevel(UUID uuid){
		mkProfile(uuid);
		try {
			return new ProjectMetadataObject(uuid).getAsInt("level");
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public static List<Spell> getSelfs(UUID uuid){
        mkProfile(uuid);
		List<Spell> spells = new ArrayList<>();
		List<String> raw = new ArrayList<>();
		try {
			raw = new ProjectMetadataObject(uuid).getAsList("selfs");
		}catch (Exception e){
			e.printStackTrace();
		}
		for(Object s : raw){
			for (Spell spell : SpellTrigger.getRegisteredList()){
				if(s.toString().contains(spell.getClass().getName())){
					spells.add(spell);
				}
			}
		}
		return spells;
	}
	public static List<Spell> getActiveSelfs(UUID uuid){
        mkProfile(uuid);
		List<Spell> spells = new ArrayList<>();
		List<String> raw = new ArrayList<>();
		try {
			raw = new ProjectMetadataObject(uuid).getAsList("active_selfs");
		}catch (Exception e){
			e.printStackTrace();
		}
		for(Object s : raw){
			for (Spell spell : SpellTrigger.getRegisteredList()){
				if(s.toString().contains(spell.getClass().getName())){
					spells.add(spell);
				}
			}
		}
		return spells;
	}

	public static void setMana(UUID uuid, int mana){
		try {
			new ProjectMetadataObject(uuid).put("mana", mana);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void setEXP(UUID uuid, int exp){
		new ProjectMetadataObject(uuid).put("exp", exp);
	}
	public static void setBurnout(UUID uuid, int burnout){
		try {
			new ProjectMetadataObject(uuid).put("burnout", burnout);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void setSelfs(UUID uuid, List<Spell> selfs){
		List<Spell> toadd = new ArrayList<>();
		for(Spell s : selfs){
			if(s.getSpellArea().equals(Utils.SpellArea.Selfs)){
				toadd.add(s);
			}
		}
		try {
			new ProjectMetadataObject(uuid).put("selfs", toadd);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void setActiveSelfs(UUID uuid, List<Spell> selfs){
		List<Spell> toadd = new ArrayList<>();
		for(Spell s : selfs){
			if(s.getSpellArea().equals(Utils.SpellArea.Selfs)){
				toadd.add(s);
			}
		}
		try {
			new ProjectMetadataObject(uuid).put("active_selfs", toadd);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void setLevel(UUID uuid, int level){
		new ProjectMetadataObject(uuid).put("level", level);
	}

	public static boolean hasSelf(UUID uuid, Spell self){
		if(getSelfs(uuid).contains(self)){
			return true;
		}
		return false;
	}
	public static boolean hasActiveSelf(UUID uuid, Spell self){
		if(getActiveSelfs(uuid).contains(self)){
			return true;
		}
		return false;
	}
	public static void setActivatedSelf(UUID uuid, Spell self, boolean b){
		if(b){
			addActiveSelf(uuid, self);
		}else{
			removeActiveSelf(uuid, self);
		}
	}

	public static void addSelf(UUID uuid, Spell self){
		if(!hasSelf(uuid, self)){
			List<Spell> selfs = getSelfs(uuid);
			selfs.add(self);
			setSelfs(uuid, selfs);
		}
	}
	public static void addActiveSelf(UUID uuid, Spell self){
		if(!hasActiveSelf(uuid, self)){
			List<Spell> selfs = getActiveSelfs(uuid);
			selfs.add(self);
			setActiveSelfs(uuid, selfs);
		}
	}
	public static void removeSelf(UUID uuid, Spell self){
		if(hasSelf(uuid, self)){
			List<Spell> selfs = getSelfs(uuid);
			selfs.remove(self);
			setSelfs(uuid, selfs);
		}
	}
	public static void removeActiveSelf(UUID uuid, Spell self){
		if(hasActiveSelf(uuid, self)){
			List<Spell> selfs = getActiveSelfs(uuid);
			selfs.remove(self);
			setActiveSelfs(uuid, selfs);
		}
	}

	public static void levelUp(UUID uuid){
		Player p = Bukkit.getPlayer(uuid);
		int level = getLevel(uuid);
		int next = (level + 1);
		int exp = getExp(uuid);
		setLevel(uuid, next);
		setEXP(uuid, exp-getExpToLevel(level));
		p.sendTitle(ChatColor.GREEN+"Level Up!", ChatColor.BLUE+"From "+ChatColor.GOLD+
						level+ChatColor.BLUE+" to "+ChatColor.GOLD+next);
	}
	public static int getExpToLevel(int i){
		return (int)Math.round(Math.pow(i, 4));
	}
	public static int getMaxBurnout(UUID uuid){
		if(getLevel(uuid)==0){
			return 25;
		}
		return getLevel(uuid)*50;
	}
	public static int getMaxMana(UUID uuid){
		return getMaxBurnout(uuid)*2;
	}
	public static void addExp(UUID uuid, int amount){
		setEXP(uuid, getExp(uuid)+amount);
		if(getExp(uuid) > getExpToLevel(getLevel(uuid))){
			levelUp(uuid);
		}
	}
	public static void addBurnout(UUID uuid, int amount){
		setBurnout(uuid, getBurnout(uuid)+amount);
		if(getBurnout(uuid)>getMaxBurnout(uuid)){
			burnoutDelay.add(uuid);
		}
	}
	public static void removeBurnout(UUID uuid, int amount){
		setBurnout(uuid, getBurnout(uuid)-amount);
	}
	public static void addMana(UUID uuid, int amount){
		if(getMana(uuid)+amount > getMaxMana(uuid)){
			setMana(uuid, getMaxMana(uuid));
		}else{
			setMana(uuid, getMaxMana(uuid)+amount);
		}
	}

	public static String getInfo(UUID uuid){

		String mana = ChatColor.BLUE+"Mana: "+ChatColor.GOLD+""+getMana(uuid)+""+ChatColor.BLUE+"/"+ChatColor.GOLD+""+getMaxMana(uuid)+ChatColor.DARK_PURPLE+" - "+ChatColor.GOLD+getPercent(getMana(uuid), getMaxMana(uuid))+"%";
		String burnout = ChatColor.BLUE+"Burnout: "+ChatColor.GOLD+""+getBurnout(uuid)+""+ChatColor.BLUE+"/"+ChatColor.GOLD+""+getMaxBurnout(uuid)+ChatColor.DARK_PURPLE+" - "+ChatColor.GOLD+getPercent(getBurnout(uuid), getMaxBurnout(uuid))+"%";
		String level = ChatColor.BLUE+"Level: "+ChatColor.GOLD+""+getLevel(uuid)+"                          ";
		int atual = (int)getExp(uuid);
		int target = (int)getExpToLevel(getLevel(uuid));
		int percent = getPercent(atual, target);
		String exp = ChatColor.BLUE+"EXP: "+ChatColor.GOLD+""+getExp(uuid)+""+ChatColor.BLUE+"/"+ChatColor.GOLD+target+ChatColor.DARK_PURPLE+" - "+ChatColor.GOLD+percent+"%";
		String splitter = "\n";
		String start = ChatColor.DARK_PURPLE+"\n======================\n";
		String s = start+mana+splitter+burnout+splitter+exp+splitter+level+start;
		return s;
	}
	public static String getActionBar(UUID uuid){
		String mana = ChatColor.BLUE+"Mana: "+ChatColor.GOLD+getPercent(getMana(uuid), getMaxMana(uuid))+"%";
		String burnout = ChatColor.BLUE+"Burnout: "+ChatColor.GOLD+getPercent(getBurnout(uuid), getMaxBurnout(uuid))+"%";
		String level = ChatColor.BLUE+"Level: "+ChatColor.GOLD+""+getLevel(uuid);
		int atual = (int)getExp(uuid);
		int target = (int)getExpToLevel(getLevel(uuid));
		int percent = getPercent(atual, target);
		String exp = ChatColor.BLUE+"EXP: "+ChatColor.GOLD+percent+"%";
		String splitter = ChatColor.DARK_PURPLE+" | ";
		String s = mana+splitter+burnout+splitter+level+splitter+exp;
		return s;
	}
	public static int getPercent(int x, int y){
		int i = 0;
		try {
			i = x * (100) / y;
		}catch (ArithmeticException e){
			return 0;
		}
		return i;
	}
	public static void sendActionbar(Player p, String message) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
				ChatColor.translateAlternateColorCodes('&', message) + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
	}
	public static void startRunnables(){
		new BukkitRunnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					sendActionbar(p, getActionBar(p.getUniqueId()));

					if(getBurnout(p.getUniqueId()) == 0) {
						if (burnoutDelay.contains(p.getUniqueId())) {
							burnoutDelay.remove(p.getUniqueId());
						}
					}
					if(getBurnout(p.getUniqueId()) > 0) {
						if(getBurnout(p.getUniqueId()) - getLevel(p.getUniqueId()) >= 0) {
							removeBurnout(p.getUniqueId(), getLevel(p.getUniqueId()));
						}else{
							setBurnout(p.getUniqueId(), 0);
						}
					}
				}
			}
		}.runTaskTimer(Spelling.getSpelling(), 20, 20);
	}
}
