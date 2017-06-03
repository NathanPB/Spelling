package me.nathanpb.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.apache.commons.lang.StringUtils;

public class Mana implements CommandExecutor{
	 private final me.nathanpb.Spelling.Spelling plugin;
	    public Mana(me.nathanpb.Spelling.Spelling plugin){
	        this.plugin = plugin;}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mana")){
			if(args.length == 0){
				if(!sender.hasPermission("op")){
					sender.sendMessage(ChatColor.DARK_RED+"Você não tem permissão para executar este comando!");
					return true;
				}
				int mana = me.nathanpb.EventHandler.ManaMananger.GetMana(Bukkit.getPlayerExact(sender.getName()));
				sender.sendMessage(ChatColor.BLUE+"Atualmente você tem "+
				ChatColor.GOLD+mana+ChatColor.BLUE+" pontos de mana.");
				return true;
			}
			if(args.length == 3){
				if(!sender.hasPermission("op")){
					sender.sendMessage(ChatColor.DARK_RED+"Você não tem permissão para executar este comando!");
					return true;
				}
				if(!args[0].equalsIgnoreCase("definir")){
					return false;
				}
				if(Bukkit.getPlayerExact(args[1]) == null){
					sender.sendMessage(ChatColor.RED+"O jogador "+ChatColor.GOLD+args[1]+ChatColor.RED+" não está online");
					return true;
				}
				if(!StringUtils.isNumeric(args[2])){
					sender.sendMessage(ChatColor.RED+"O terceiro argumento precisa ser um número");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[1]);
				//GAMBIARRA LVL 1000
				int value = Integer.parseInt(String.valueOf(Math.round(Double.parseDouble(args[2]))));
				me.nathanpb.Spelling.Mana.mana.put(target, value);
				sender.sendMessage(ChatColor.BLUE+"O valor de mana de "+
						ChatColor.GOLD+args[1]+ChatColor.BLUE+" foi definido para "+ChatColor.GOLD+value);
				return true;
			}
			if(args.length == 2){
				if(!sender.hasPermission("op")){
					sender.sendMessage(ChatColor.DARK_RED+"Você não tem permissão para executar este comando!");
					return true;
				}
				if(!args[0].equalsIgnoreCase("ver")){
					return false;
				}
				if(Bukkit.getPlayerExact(args[1]) == null){
					sender.sendMessage(ChatColor.RED+"O jogador "+ChatColor.GOLD+args[1]+ChatColor.RED+" não está online");
					return true;
				}
				int value = me.nathanpb.EventHandler.ManaMananger.GetMana(Bukkit.getPlayerExact(args[1]));
				sender.sendMessage(ChatColor.BLUE+"O valor de mana de "+
						ChatColor.GOLD+args[1]+ChatColor.BLUE+" é de "+ChatColor.GOLD+value+ChatColor.BLUE+" pontos");
				return true;
			}
			
		}
		return false;
	}
}
