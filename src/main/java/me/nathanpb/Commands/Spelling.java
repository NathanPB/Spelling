package me.nathanpb.Commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.nathanpb.Selfs.SelfMananger.Self;
import me.nathanpb.SpellBook.SpellBook;
import me.nathanpb.SpellBook.Utils;

import java.util.ConcurrentModificationException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
				if (args[0].equals("nível")) {
					sender.sendMessage(me.nathanpb.EventHandler.ManaMananger
							.GetProgression(Bukkit.getPlayerExact(sender
									.getName())));
					return true;
				}
				if (args[0].equalsIgnoreCase("hand")) {
					sender.sendMessage(Bukkit.getPlayerExact(sender.getName())
							.getItemInHand().getType()
							+ "");
					return true;
				}
				if (args[0].equalsIgnoreCase("update")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					me.nathanpb.Spelling.ConfigMananger.ConfigUpdater(Bukkit
							.getPlayerExact(sender.getName()));
					return true;
				}
				if (args[0].equalsIgnoreCase("selfs")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					List<Self> selfs = new ArrayList<>();
					selfs = me.nathanpb.Selfs.SelfMananger.GetSelfs(Bukkit
							.getPlayerExact(sender.getName()));
					String self = "";
					if (selfs.isEmpty()) {
						sender.sendMessage(ChatColor.RED
								+ "Você ainda não tem nenhum Self!");
					}
					for (Self s : selfs) {
						self = self + s + ", ";
					}
					if (self.contains("Null, ")) {
						self = self.replaceAll("Null, ", "");
					}
					if (self.endsWith(", ")) {
						self = self.substring(0, self.length() - 2);
					}
					sender.sendMessage(ChatColor.BLUE + "Seus Selfs: "
							+ ChatColor.GOLD + self);
					return true;
				}
				
				if (args[0].equalsIgnoreCase("selfsativos")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					List<Self> selfs = new ArrayList<>();
					selfs = me.nathanpb.Selfs.SelfMananger
							.GetActiveSelfs(Bukkit.getPlayerExact(sender
									.getName()));
					String self = "";
					if (selfs.isEmpty()) {
						sender.sendMessage(ChatColor.RED
								+ "Você não tem nenhum Self ativo!");
					}
					for (Self s : selfs) {
						self = self + s + ", ";
					}
					if (self.contains("Null, ")) {
						self = self.replaceAll("Null, ", "");
					}
					if (self.endsWith(", ")) {
						self = self.substring(0, self.length() - 2);
					}
					sender.sendMessage(ChatColor.BLUE + "Seus Selfs: "
							+ ChatColor.GOLD + self);
					return true;
				}
			}
			if (args.length == 2) {
				if (Bukkit.getPlayerExact(args[1]) == null) {
					sender.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED
							+ " não está online");
				}
				if (args[0].equalsIgnoreCase("selfs")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					List<Self> selfs = new ArrayList<>();
					selfs = me.nathanpb.Selfs.SelfMananger.GetSelfs(Bukkit
							.getPlayerExact(args[1]));
					String self = "";
					if (selfs.isEmpty()) {
						sender.sendMessage(ChatColor.RED
								+ "O jogador não tem nenhum Self!");
					}
					for (Self s : selfs) {
						self = self + s + ", ";
					}
					if (self.contains("Null, ")) {
						self = self.replaceAll("Null, ", "");
					}
					if (self.endsWith(", ")) {
						self = self.substring(0, self.length() - 2);
					}
					sender.sendMessage(ChatColor.BLUE + "Selfs: "
							+ ChatColor.GOLD + self);
					return true;
				}
				if (args[0].equalsIgnoreCase("selfsativos")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					List<Self> selfs = new ArrayList<>();
					selfs = me.nathanpb.Selfs.SelfMananger
							.GetActiveSelfs(Bukkit.getPlayerExact(args[1]));
					String self = "";
					if (selfs.isEmpty()) {
						sender.sendMessage(ChatColor.RED
								+ "O jogador não tem nenhum Self ativo!");
						return true;
					}
					for (Self s : selfs) {
						self = self + s + ", ";
					}
					if (self.contains("Null, ")) {
						self = self.replaceAll("Null, ", "");
					}
					if (self.endsWith(", ")) {
						self = self.substring(0, self.length() - 2);
					}
					sender.sendMessage(ChatColor.BLUE + "Selfs Ativos: "
							+ ChatColor.GOLD + self);
					return true;
				} /*
				if (args[0].equalsIgnoreCase("limparselfs")) {
					if (!sender.hasPermission("op")) {
						sender.sendMessage(ChatColor.DARK_RED
								+ "Você não tem permissão para executar este comando!");
						return true;
					}
					List<Self> selfs = new ArrayList<>();
					selfs = me.nathanpb.Selfs.SelfMananger.GetSelfs(Bukkit
							.getPlayerExact(args[1]));
					if (selfs.isEmpty()) {
						sender.sendMessage(ChatColor.RED
								+ "O jogador não tem nenhum Self!");
						return true;
					}
					me.nathanpb.Selfs.SelfMananger.ClearAllSelfs(Bukkit.getPlayerExact(args[1]));
					sender.sendMessage(ChatColor.BLUE + "Todas as Selfs de "
							+ ChatColor.GOLD + args[1] + ChatColor.BLUE
							+ " foram removidas!");
					return true;
				} */
			}
		}
		return false;
	}
}
