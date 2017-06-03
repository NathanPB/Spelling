package me.nathanpb.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Math;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nathanpb.Spelling.Spelling;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.Items;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;

public class ManaMananger implements Listener{
	private final me.nathanpb.Spelling.Spelling plugin;
	public ManaMananger(me.nathanpb.Spelling.Spelling plugin){this.plugin = plugin;}
	public static boolean CancelAddMana = false;
	static Spelling spelling = me.nathanpb.Spelling.Spelling.getSpelling();
	static List<Player> TaskActivated = new ArrayList<Player>();
	@EventHandler
	public static void OnPlayerJoin(PlayerJoinEvent event){
		int mana = me.nathanpb.Spelling.Mana.ReadFile(event.getPlayer().getName(), "Mana");
		int usedmana = me.nathanpb.Spelling.Mana.ReadFile(event.getPlayer().getName(), "UsedMana");
		me.nathanpb.Spelling.Mana.mana.put(event.getPlayer(), mana);
		me.nathanpb.Spelling.Mana.UsedMana.put(event.getPlayer(), usedmana);
		me.nathanpb.Selfs.SelfMananger.LoadSelfs(event.getPlayer());
		Bukkit.getServer().getLogger().info("Loaded mana from "+event.getPlayer().getName()+
				"("+mana+", level +"+GetLevel(event.getPlayer())+")");
		event.getPlayer().sendMessage(ChatColor.BLUE+"Atualmente você tem "+
		ChatColor.GOLD+me.nathanpb.Spelling.Mana.mana.get(event.getPlayer())+ChatColor.BLUE+
		" pontos de mana e se encontra no nível "+ChatColor.GOLD+GetLevel(event.getPlayer()));
	}
	@EventHandler
	public static void OnPlayerQuit(PlayerQuitEvent event){
		int mana = me.nathanpb.Spelling.Mana.mana.get(event.getPlayer());
		int usedMana = me.nathanpb.Spelling.Mana.UsedMana.get(event.getPlayer());
		me.nathanpb.Spelling.Mana.WriteFile(event.getPlayer().getName(), "UsedMana", String.valueOf(usedMana));
		me.nathanpb.Spelling.Mana.WriteFile(event.getPlayer().getName(), "Mana", String.valueOf(mana));
		me.nathanpb.Selfs.SelfMananger.SaveSelfs(event.getPlayer(), me.nathanpb.Selfs.SelfMananger.GetSelfs(event.getPlayer()));
		Bukkit.getServer().getLogger().info("Saved mana from "+event.getPlayer().getName()+"("+mana+", level +"+GetLevel(event.getPlayer())+")");
	}
	public static void SaveManaOnDisable(){
		
		for(Map.Entry<Player, Integer> pair : me.nathanpb.Spelling.Mana.mana.entrySet()){
			Player key = pair.getKey();;
			me.nathanpb.Spelling.Mana.WriteFile(key.getName(), "Mana", String.valueOf(pair.getValue()));
			me.nathanpb.Spelling.Mana.WriteFile(key.getName(), "UsedMana", String.valueOf(GetUsedMana(pair.getKey())));
			me.nathanpb.Selfs.SelfMananger.SaveSelfs(pair.getKey(), me.nathanpb.Selfs.SelfMananger.GetSelfs(pair.getKey()));
			Bukkit.getServer().getLogger().info("Saved mana from "+key.getName()+"("+pair.getValue()+", level +"+GetLevel(key)+")");
		}
	}
	public static void LoadManaOnEnable(){
		for(Player player : Bukkit.getOnlinePlayers()){
			int mana = me.nathanpb.Spelling.Mana.ReadFile(player.getName(), "Mana");
			me.nathanpb.Spelling.Mana.mana.put(player, mana);
			int UsedMana = me.nathanpb.Spelling.Mana.ReadFile(player.getName(), "UsedMana");
			me.nathanpb.Spelling.Mana.UsedMana.put(player, UsedMana);
			me.nathanpb.Selfs.SelfMananger.LoadSelfs(player);
			Bukkit.getServer().getLogger().info("Loaded mana from "+player.getName()+"("+mana+", level +"+GetLevel(player)+")");
			player.sendMessage(ChatColor.BLUE+"Atualmente você tem "+
			ChatColor.GOLD+me.nathanpb.Spelling.Mana.mana.get(player)+ChatColor.BLUE+" pontos de mana e se encontra no nível "+GetLevel(player));
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void OnDamage(EntityDamageByEntityEvent event){
		if(!(event.getDamager() instanceof Player)){
			return;
		}
		if(!(event.getEntity() instanceof Monster)){
			return;
		}
		if(CancelAddMana){
			CancelAddMana = false;
			return;
		}
		Player damager = (Player)event.getDamager();
		AddMana(damager, (int)Math.round(event.getDamage()));
	}
	public static void AddMana(Player player, int mana){
		int atual = me.nathanpb.Spelling.Mana.mana.get(player);
		me.nathanpb.Spelling.Mana.mana.put(player, atual+mana);
	}
	public static void DecraseMana(Player player, int mana){
		int atual = me.nathanpb.Spelling.Mana.mana.get(player);
		me.nathanpb.Spelling.Mana.mana.put(player, atual-mana);
	}	
	public static int GetMana(Player player){
		return me.nathanpb.Spelling.Mana.mana.get(player);
	}
	public static int GetLevel(Player player){
		int usedMana = me.nathanpb.Spelling.Mana.UsedMana.get(player);
		int level = usedMana/10000;
		return level;
	}
	public static String GetProgression(Player player){
		int next = (GetLevel(player)*10000)*2;
		int current = GetUsedMana(player);
		if(GetLevel(player) == 0){
			next = 10000;
		}
		int percent = (current*100)/next;
		String frase = ChatColor.BLUE+"Sua progressão: "+ChatColor.GOLD+current+
				ChatColor.BLUE+"/"+ChatColor.GOLD+next+ChatColor.BLUE+"("+ChatColor.GOLD+percent+ChatColor.BLUE+"%)";
		return frase;
	}
	public static int GetUsedMana(Player player){
		return me.nathanpb.Spelling.Mana.UsedMana.get(player);
	}
	public static void SetLevel(Player player, int level){
		int value = level*10000;
		me.nathanpb.Spelling.Mana.WriteFile(player.getName(), "UsedMana", String.valueOf(value));
	}
	public static void SetUsedMana(Player player, int mana){
		me.nathanpb.Spelling.Mana.WriteFile(player.getName(), "UsedMana", String.valueOf(mana));
	}
	public static void SetMana(Player player, int mana){
		me.nathanpb.Spelling.Mana.mana.put(player, mana);
	}
	public static void AddUsedMana(Player player, int mana){
			
		int atual = me.nathanpb.Spelling.Mana.UsedMana.get(player);
		me.nathanpb.Spelling.Mana.UsedMana.put(player, atual+mana);
	}
	public static boolean CanUseSpell(Player player, int coust, int cooldown, boolean affectedByCooldown){
		int mana = me.nathanpb.EventHandler.ManaMananger.GetMana(player);
		boolean AllowBurnout = me.nathanpb.Spelling.ConfigMananger.GetConfigBooleans("AllowBurnout");
		boolean AllowMana = me.nathanpb.Spelling.ConfigMananger.GetConfigBooleans("AllowMana");
		boolean ShowManaOnUse = me.nathanpb.Spelling.ConfigMananger.GetConfigBooleans("ShowManaOnUse");
		int level = GetLevel(player);
		
		//BURNOUT CHECK
		if(AllowBurnout){
			if(AffectedByBurnout(player) && affectedByCooldown){
					player.sendTitle(ChatColor.DARK_RED+"Seu burnout está alto!", ChatColor.GOLD+""+GetBurnoutPercent(player)+"%");
					return false;
			}
		}
		
		//MANA CHECK
		if(AllowMana){
			if(mana < coust){
				player.sendMessage(ChatColor.RED+"Você não tem mana o suficiente para usar este spell :c");
				player.sendMessage(ChatColor.BLUE+"Custo: "+ChatColor.GOLD+coust);
				return false;
			}
			me.nathanpb.EventHandler.ManaMananger.DecraseMana(player, coust);
			me.nathanpb.EventHandler.ManaMananger.AddUsedMana(player, coust);
		}

		if(level < GetLevel(player)){
			player.sendTitle(ChatColor.GREEN+"Level Up!", ChatColor.BLUE+"De "+ChatColor.GOLD+
					level+ChatColor.BLUE+" para "+ChatColor.GOLD+GetLevel(player));
		}
		
		if(AllowBurnout){
			AddBurnout(player, coust);
			StartDecrementBurnout(player);
		}
		if(ShowManaOnUse){
			SendManaPacket(player);
		}
		return true;
	}
	public static void SendManaPacket(Player player){
		String progressao = GetProgression(player);
		progressao = progressao.replace("Sua progressão: ", "Progressão: ");
		progressao = progressao.replace(""+GetUsedMana(player), "");
		progressao = progressao.replace(""+(GetLevel(player)*10000)*2, "");
		progressao = progressao.replace("(", "");
		progressao = progressao.replace(")","");
		progressao = progressao.replace("/","");
		progressao = progressao.replace(ChatColor.BLUE+"%","");
		String msg = ChatColor.BLUE+"Mana: "+ChatColor.GOLD+GetMana(player)+ChatColor.BLUE+"   Nível: "+
				ChatColor.GOLD+GetLevel(player)+"   "+progressao+"%"+ChatColor.BLUE+"   Burnout: "+ChatColor.GOLD+GetBurnoutPercent(player)+"%";
		sendActionbar(player, msg);
	}
	public static void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
	public static int GetMaxBurnout(Player player){
		if(GetLevel(player)==0){
			return 100;
		}
		return GetLevel(player)*100;
	}
	public static void AddBurnout(Player player, int burnout){
		me.nathanpb.Spelling.Mana.Burnout.put(player, GetCurrentBurnout(player)+burnout);
	}
	public static int GetCurrentBurnout(Player player){
		int burnout;
		if(me.nathanpb.Spelling.Mana.Burnout.containsKey(player)){
			burnout =  me.nathanpb.Spelling.Mana.Burnout.get(player);
		}else{
			burnout = 0;
		}
		if(burnout > GetMaxBurnout(player)){
				if(!me.nathanpb.Spelling.Mana.BurnoutDelay.contains(player)){
					me.nathanpb.Spelling.Mana.BurnoutDelay.add(player);
				}
		}
		return burnout;
	}
	public static boolean AffectedByBurnout(Player player){
		if(me.nathanpb.Spelling.Mana.BurnoutDelay.contains(player)){
			return true;
		}
		return false;
	}
	public static void StartDecrementBurnout(final Player p){
		if(TaskActivated.contains(p)){
			return;
		}
		TaskActivated.add(p);
		new BukkitRunnable(){	    
			@Override
	            public void run(){
					int decrement;
					if(GetLevel(p)<1){
						decrement = 1;
					}else{
						decrement = GetLevel(p);
					}
					int NewBurnout = me.nathanpb.Spelling.Mana.Burnout.get(p) - decrement;
					if(NewBurnout < 0){
						NewBurnout = 0;
					}
					me.nathanpb.Spelling.Mana.Burnout.replace(p, NewBurnout);
					if(GetCurrentBurnout(p)==0){
						me.nathanpb.Spelling.Mana.BurnoutDelay.remove(p);
						TaskActivated.remove(p);
						this.cancel();
					}
	            }
	        }.runTaskTimer(spelling, 0, 10);
			
	}
	public static int GetBurnoutPercent(Player player){
		int atual = GetCurrentBurnout(player);
		int maximo = GetMaxBurnout(player);
		return (atual*100)/maximo;
	}
}
