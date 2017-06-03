package me.nathanpb.Selfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SelfMananger {
	public static HashMap<Player, List<Self>> Selfs = new HashMap<Player, List<Self>>();
	public static HashMap<Player, List<Self>> ActiveSelfs = new HashMap<Player, List<Self>>();
	
	
	public enum Self{
		Null("Null"),
		BatEyes("BatEyes"),
		AmphibiousBreath("AmphibiousBreath"),
		QuicksilverLimbs("QuicksilverLimbs"),
		RabbitLegs("RabbitLegs");
		
		
		  private String value; 
		  Self(String value){
		   this.value = value;
		  }
		  public String getValue(){
		   return this.value;
		  }
	}
	
	public static void AddSelf(Player player, Self self){
		List<Self> list = new ArrayList<Self>();
		if(!Selfs.containsKey(player)){
			list.add(self);
			Selfs.put(player,list);
		}else{
			list = Selfs.get(player);
			if(list.contains(self)){
				return;
			}
			list.add(self);
			Selfs.put(player, list);
		}
	}
	public static void RemoveSelf(Player player, Self self){
		List<Self> list = new ArrayList<Self>();
		if(self.equals(self.Null)){
			return;
		}
		if(!Selfs.containsKey(player)){
			return;
		}
		list = Selfs.get(player);
		if(!list.contains(self)){
			return;
		}
		list.remove(self);
		Selfs.put(player, list);
		return;
	}
	public static void SaveSelfs(Player player, List<Self> selfs){
		String SelfList = "";
		if(selfs.isEmpty()){
			return;
		}
		for(Self s : selfs){
			SelfList = SelfList+", "+s.getValue();
		}
		me.nathanpb.Spelling.Mana.WriteFile(player.getName(), "Selfs", SelfList);
	}
	public static void LoadSelfs(Player player){
		try{
			String SelfList;
			SelfList = me.nathanpb.Spelling.Mana.ReadFileToString(player.getName(), "Selfs");
			if(SelfList.equals(", Null")){
				return;
			}
			List<String> list = new ArrayList<>();
			list = Arrays.asList(SelfList.split(", "));
			List<Self> SelfEnumList = new ArrayList<>();
			for(String s : list){
				if(!s.equals("")){
					SelfEnumList.add(Self.valueOf(s));
				}
			}
			Selfs.put(player, SelfEnumList);
		}catch(NullPointerException e){
			AddSelf(player, Self.Null);
		}
	}
	public static List<Self> GetSelfs(Player player){
		if(!Selfs.containsKey(player)){
			AddSelf(player, Self.Null);
		}
		return Selfs.get(player);
	}
	public static List<Self> GetActiveSelfs(Player player){
		if(!ActiveSelfs.containsKey(player)){
			AddActiveSelf(player, Self.Null);
		}
		return ActiveSelfs.get(player);
	}
	public static void AddActiveSelf(Player player, Self self){
		List<Self> list = new ArrayList<Self>();
		if(!ActiveSelfs.containsKey(player)){
			list.add(self);
			ActiveSelfs.put(player,list);
		}else{
			list = ActiveSelfs.get(player);
			if(list.contains(self)){
				return;
			}
			list.add(self);
			ActiveSelfs.put(player, list);
		}
	}
	public static void RemoveActiveSelf(Player player, Self self){
		List<Self> list = GetActiveSelfs(player);
		if(!list.contains(self)){
			return;
		}
		list.remove(self);
		ActiveSelfs.put(player, list);
	}
	public static boolean UseSelf(Player player, int coust){
		int mana = me.nathanpb.EventHandler.ManaMananger.GetMana(player);
		boolean AllowMana = me.nathanpb.Spelling.ConfigMananger.GetConfigBooleans("AllowMana");
		int level = me.nathanpb.EventHandler.ManaMananger.GetLevel(player);
		//MANA CHECK
		if(AllowMana){
			if(mana < coust){
				return false;
			}
			me.nathanpb.EventHandler.ManaMananger.DecraseMana(player, coust);
			me.nathanpb.EventHandler.ManaMananger.AddUsedMana(player, coust);
		}

		if(level < me.nathanpb.EventHandler.ManaMananger.GetLevel(player)){
			player.sendTitle(ChatColor.GREEN+"Level Up!", ChatColor.BLUE+"De "+ChatColor.GOLD+
					level+ChatColor.BLUE+" para "+ChatColor.GOLD+me.nathanpb.EventHandler.ManaMananger.GetLevel(player));
		}
		return true;
	}
	public static void ClearAllSelfs(Player player){
		List<Self> list = new ArrayList<>();
		list.add(Self.Null);
		ActiveSelfs.put(player, list);
		Selfs.put(player, list);
		return;
	}
}
