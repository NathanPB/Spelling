package me.nathanpb.Spelling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;


public class ConfigMananger {
	public static HashMap<String, Integer> ManaInUpdatingProcess = new HashMap<String, Integer>();
	public static HashMap<String, Integer> UsedManaInUpdatingProcess = new HashMap<String, Integer>();
	
	public static File ConfigFile = new File(me.nathanpb.Spelling.Mana.path+"//config.npb");
	public static void GenConfigFile(){
		try{
			if(!(new File(me.nathanpb.Spelling.Mana.path).exists())){
				new File(me.nathanpb.Spelling.Mana.path).mkdir();
			}
			if(!ConfigFile.exists()){
				ConfigFile.createNewFile();
				Formatter format;
				format = new Formatter(ConfigFile);
				format.format(
						"PaperMode=false\n"
						+ "ShowManaOnUse=true\n"
						+ "NeedsSpellBook=true\n"
						+ "AllowBurnout=true\n"
						+ "AllowMana=true\n"
						);
				format.close();
			}
		}catch(IOException e){
			Bukkit.getLogger().info(e.getMessage());
		}
	}
	public static boolean GetConfigBooleans(String key){
		Boolean value = false;
		try{
			FileInputStream file = new FileInputStream(ConfigFile);
			Properties config = new Properties();
			config.load(file);
			value = Boolean.valueOf(config.getProperty(key));
			return value;
		}catch(IOException e){
			Bukkit.getLogger().info(e.getMessage());
		}
		return value;
	}
	public static Integer GetConfigInteger(String key){
		int value = 0;
		try{
			FileInputStream file = new FileInputStream(ConfigFile);
			Properties config = new Properties();
			config.load(file);
			value = Integer.parseInt(config.getProperty(key));
			return value;
		}catch(IOException e){
			Bukkit.getLogger().info(e.getMessage());
		}
		return value;
	}
	public static void ConfigUpdater(Player p){
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+"§lSpelling>>>"+ChatColor.BLUE+" Starting update process...");
		for(Map.Entry<Player, Integer> pair : me.nathanpb.Spelling.Mana.mana.entrySet()){
			Player key = pair.getKey();;
			me.nathanpb.Spelling.Mana.WriteFile(key.getName(), "Mana", String.valueOf(pair.getValue()));
			me.nathanpb.Spelling.Mana.WriteFile(key.getName(), "UsedMana", String.valueOf(me.nathanpb.EventHandler.ManaMananger.GetUsedMana(pair.getKey())));
			Bukkit.getServer().getLogger().info("Saved mana from "+key.getName()+"("+pair.getValue()+", level +"+me.nathanpb.EventHandler.ManaMananger.GetLevel(key)+")");
		}
		HashMap<String, Set<Object>> PropsLoaded = new HashMap<String, Set<Object>>();
		/*
		List<File> UsersFile = new ArrayList<>();
		File[] listOfFiles = me.nathanpb.Spelling.Mana.users.listFiles();
	    
		for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        UsersFile.add(listOfFiles[i]);
	      }
	    }
	    for(File f : UsersFile){
	    	Set<Object> keys;
			try{
				FileInputStream file = new FileInputStream(f);
				Properties props = new Properties();
				props.load(file);
				keys = props.keySet();
				String name = f.getName();
				name = name.replaceAll(".npb", "");
				PropsLoaded.put(name, keys);
			}catch(IOException e){
				System.out.println(e);
			}
	    }
	    for (Entry<String, Set<Object>> pair : PropsLoaded.entrySet()){
	    	for(Object o : pair.getValue()){
	    		String value = "";
	    		try{
	    			FileInputStream file = new FileInputStream(me.nathanpb.Spelling.Mana.users+"//"+pair.getKey()+".npb");
	    			Properties props = new Properties();
	    			props.load(file);
	    			value = props.getProperty(o.toString());
	    		}catch(IOException e){
	    			System.out.println(e);
	    		}
	    		if(o.toString().equals("Mana")){
	    			Bukkit.getServer().broadcastMessage(pair.getKey());
	    			Bukkit.getServer().broadcastMessage(pair.getValue()+"");
	    			Bukkit.getServer().broadcastMessage(value);
	    			if(value.equals("")){
	    				ManaInUpdatingProcess.put(pair.getKey(), 0);
	    			}else{
	    				ManaInUpdatingProcess.put(pair.getKey(), Integer.valueOf(value));
	    			}
	    			Bukkit.getServer().broadcastMessage(ChatColor.BLUE+"Mana updated for "+
	    			ChatColor.GOLD+pair.getKey()+ChatColor.BLUE+" ("+ChatColor.GOLD+value+ChatColor.BLUE+")");
	    		}
	    		if(o.toString().equals("UsedMana")){
	    			if(value.equals("")){
	    				UsedManaInUpdatingProcess.put(pair.getKey(), 0);
	    			}else{
	    				UsedManaInUpdatingProcess.put(pair.getKey(), Integer.valueOf(value));
	    			}
	    			Bukkit.getServer().broadcastMessage(ChatColor.BLUE+"Used Mana updated for "+
	    			ChatColor.GOLD+pair.getKey()+ChatColor.BLUE+" ("+ChatColor.GOLD+value+ChatColor.BLUE+")");
	    		}
	    	}
	    	try{
	    		FileUtils.deleteDirectory(me.nathanpb.Spelling.Mana.users);
	    		me.nathanpb.Spelling.Mana.GeneratePlayerFiles(pair.getKey());
	    		FileInputStream fis = new FileInputStream(me.nathanpb.Spelling.Mana.users+"//"+pair.getKey()+".npb");
				Properties props = new Properties();
				props.load(fis);
				props.setProperty("Mana", String.valueOf(ManaInUpdatingProcess.get(pair.getKey())));
				props.setProperty("UsedMana", String.valueOf(UsedManaInUpdatingProcess.get(pair.getKey())));
				props.store(new FileOutputStream(me.nathanpb.Spelling.Mana.users+"//"+pair.getKey()+".npb"), null);
				fis.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    for(Player player : Bukkit.getOnlinePlayers()){
	    	int mana = 	UsedManaInUpdatingProcess.get(player.getName());
	    	int usedmana = 	UsedManaInUpdatingProcess.get(player.getName());
	    	me.nathanpb.EventHandler.ManaMananger.SetUsedMana(player, usedmana);
	    	me.nathanpb.EventHandler.ManaMananger.SetMana(player, mana);
	    }
	    Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+"§lSpelling>>>"+ChatColor.GREEN+" All player data saved, wait for next steps...");
	    */
	    Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+"§lSpelling>>>"+ChatColor.BLUE+" Updating configs files...");
	    HashMap<String, String> ConfigValues = new HashMap<String, String>();
	    try{
	    	Properties props = new Properties();
	    	FileInputStream fis = new FileInputStream(ConfigFile);
	    	props.load(fis);
	    	for(Object o : props.keySet()){
	    		ConfigValues.put(o.toString(), props.getProperty(o.toString()));
	    	}
	    	props.store(new FileOutputStream(ConfigFile), null);
	    	fis.close();
	    	 Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+"§lSpelling>>>"+ChatColor.BLUE+" Generating new configs file");
	    	ConfigFile.delete();
	    	GenConfigFile();
	    	FileInputStream fis2 = new FileInputStream(ConfigFile);
	    	Properties props2 = new Properties();
	    	props2.load(fis2);
	    	for(Object no : props2.keySet()){
	    		for(Entry<String, String> pair : ConfigValues.entrySet()){
	    			if(pair.getKey().equals(no.toString())){
	    				props2.setProperty(no.toString(), pair.getValue());
	    			}
	    		}
	    	}
	    	props2.store(new FileOutputStream(ConfigFile), null);
	    	fis2.close();
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+"§lSpelling>>>"+ChatColor.GREEN+" All server data was updated.");
	    Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED+"§lSpelling>>>"+ChatColor.GREEN+" Please restart the server to refresh database.");
	}
}
