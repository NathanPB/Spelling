package me.nathanpb.Commands;

import me.nathanpb.EventHandler.ManaMananger;
import me.nathanpb.ProjectMetadata.ProjectMetadataObject;
import me.nathanpb.Spell.Spell;
import me.nathanpb.SpellBook.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

/**
 * Created by nathanpb on 1/31/17.
 */
public class Spelling implements CommandExecutor {
	private final me.nathanpb.Spelling.Spelling plugin;

	public Spelling(me.nathanpb.Spelling.Spelling plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("Spelling")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("cheat")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					Bukkit.getPlayerExact(sender.getName()).getInventory().addItem(new me.nathanpb.Spell.SpellBook().getSpellItem());
					return true;
				}
				if (args[0].equalsIgnoreCase("recipes")) {
					if(sender instanceof ConsoleCommandSender){
						sender.sendMessage(ChatColor.RED+"You must be a player!");
						return true;
					}
					Player p = Bukkit.getPlayer(sender.getName());
					Inventory i = Utils.mkRecipe(new me.nathanpb.Spell.SpellBook());
					while(i.contains(Utils.returnButton)){
						i.remove(Utils.returnButton);
						i.setItem(0, Utils.whiteButton);
						i.setItem(1, Utils.whiteButton);
						i.setItem(7, Utils.whiteButton);
						i.setItem(8, Utils.whiteButton);
						
						i.setItem(0+9, Utils.whiteButton);
						i.setItem(1+9, Utils.whiteButton);
						i.setItem(7+9, Utils.whiteButton);
						i.setItem(8+9, Utils.whiteButton);
						
						i.setItem(27, Utils.whiteButton);
						i.setItem(28, Utils.whiteButton);
						i.setItem(34, Utils.whiteButton);
						i.setItem(35, Utils.whiteButton);
						
						i.setItem(36, Utils.whiteButton);
						i.setItem(37, Utils.whiteButton);
						i.setItem(43, Utils.whiteButton);
						i.setItem(44, Utils.whiteButton);
					}
					p.openInventory(i);
					return true;
				}

				if (args[0].equalsIgnoreCase("hand")) {
					sender.sendMessage(Bukkit.getPlayerExact(sender.getName())
							.getItemInHand().getType()
							+ "");
					return true;
				}
			}
			if(args.length == 3){
				if(!sender.isOp()){
					sender.sendMessage(ChatColor.RED+"You have no permission!");
					return true;
				}
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
				if(args[0].equalsIgnoreCase("mana")){
					if(args[1].equalsIgnoreCase("see")){
						int value = ManaMananger.getMana(target.getUniqueId());
						sender.sendMessage(ChatColor.BLUE+"Mana for "+ChatColor.GOLD+args[2]+ChatColor.BLUE+": "+
						ChatColor.GOLD+value);
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("burnout")){
					if(args[1].equalsIgnoreCase("see")){
						int value = ManaMananger.getBurnout(target.getUniqueId());
						sender.sendMessage(ChatColor.BLUE+"Burnout for "+ChatColor.GOLD+args[2]+ChatColor.BLUE+": "+
								ChatColor.GOLD+value);
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("exp")){
					if(args[1].equalsIgnoreCase("see")){
						int value = ManaMananger.getExp(target.getUniqueId());
						sender.sendMessage(ChatColor.BLUE+"EXP for "+ChatColor.GOLD+args[2]+ChatColor.BLUE+": "+
								ChatColor.GOLD+value);
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("level")){
					if(args[1].equalsIgnoreCase("see")){
						int value = ManaMananger.getLevel(target.getUniqueId());
						sender.sendMessage(ChatColor.BLUE+"Level for "+ChatColor.GOLD+args[2]+ChatColor.BLUE+": "+
								ChatColor.GOLD+value);
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("selfs")){
					if(args[1].equalsIgnoreCase("see")){
						String selfs = "";
						String actives = "";
						for(Spell s : ManaMananger.getSelfs(target.getUniqueId())){
							selfs += s.getSpellName()+ChatColor.BLUE+", ";
						}
						for(Spell s : ManaMananger.getActiveSelfs(target.getUniqueId())){
							actives += s.getSpellName()+ChatColor.BLUE+", ";
						}
						sender.sendMessage(ChatColor.BLUE+"All Selfs: "+selfs);
						sender.sendMessage(ChatColor.BLUE+"Actives: "+actives);
						return true;
					}
				}
			}
			if(args.length == 4){
				if(!sender.isOp()){
					sender.sendMessage(ChatColor.RED+"You have no permission!");
					return true;
				}
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
				if(args[0].equalsIgnoreCase("mana")){
					if(args[1].equalsIgnoreCase("set")){
						if(StringUtils.isNumeric(args[3])){
							ManaMananger.setMana(target.getUniqueId(), Integer.valueOf(args[3]));
							sender.sendMessage(ChatColor.GREEN+"Sucessfull!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("burnout")){
					if(args[1].equalsIgnoreCase("set")){
						if(StringUtils.isNumeric(args[3])){
							ManaMananger.setBurnout(target.getUniqueId(), Integer.valueOf(args[3]));
							sender.sendMessage(ChatColor.GREEN+"Sucessfull!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("level")){
					if(args[1].equalsIgnoreCase("set")){
						if(StringUtils.isNumeric(args[3])){
							ManaMananger.setLevel(target.getUniqueId(), Integer.valueOf(args[3]));
							sender.sendMessage(ChatColor.GREEN+"Sucessfull!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("exp")){
					if(args[1].equalsIgnoreCase("set")){
						if(StringUtils.isNumeric(args[3])){
							ManaMananger.setEXP(target.getUniqueId(), Integer.valueOf(args[3]));
							sender.sendMessage(ChatColor.GREEN+"Sucessfull!");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
