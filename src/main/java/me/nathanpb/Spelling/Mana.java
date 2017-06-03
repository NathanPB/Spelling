package me.nathanpb.Spelling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Mana {
    public static String path = "Spelling";
	public static File users = new File(path+"//users");
	
	public static HashMap<Player, Integer> mana = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> UsedMana = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Burnout = new HashMap<Player, Integer>();
	public static List<Player> BurnoutDelay = new ArrayList<Player>();
	
	
	public static void GenerateDirectory(){
		if(!users.exists()){
			users.mkdirs();
			Bukkit.getLogger().info("Creating "+users);
		}
	}
	public static void GeneratePlayerFiles(String PlayerName) throws IOException{
		GenerateDirectory();
		File file = new File(users+"//"+PlayerName+".npb");
		if(!file.exists()){
			file.createNewFile();
			Formatter format;
			try{
				format = new Formatter(file);
			}catch(IOException NFE){
				return;
			}
			format.format(
					"Mana=0\n"
					+ "UsedMana=0\n"
					+ "Selfs="
					);
			format.close();
		}
	}
	public static int ReadFile(String PlayerName, String key){
		try{
			GeneratePlayerFiles(PlayerName);
		}catch(IOException e){
			Bukkit.getServer().getLogger().info("Failed to generate "+PlayerName+" files: "+e);
		}
		int value = 0;
		try{
			FileInputStream file = new FileInputStream(users+"//"+PlayerName+".npb");
			Properties props = new Properties();
			props.load(file);
			value = Integer.valueOf(props.getProperty(key));
		}catch(IOException e){
			System.out.println(e);
		}
		return value;
	}
	public static void WriteFile(String PlayerName, String key, String value){
		try{
			FileInputStream file = new FileInputStream(users+"//"+PlayerName+".npb");
			Properties props = new Properties();
			props.load(file);
			props.setProperty(key, String.valueOf(value));
			props.store(new FileOutputStream(users+"//"+PlayerName+".npb"), null);
			file.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	public static String ReadFileToString(String PlayerName, String key){
		try{
			GeneratePlayerFiles(PlayerName);
		}catch(IOException e){
			Bukkit.getServer().getLogger().info("Failed to generate "+PlayerName+" files: "+e);
		}
		String value = "";
		try{
			FileInputStream file = new FileInputStream(users+"//"+PlayerName+".npb");
			Properties props = new Properties();
			props.load(file);
			value = props.getProperty(key);
		}catch(IOException e){
			System.out.println(e);
		}
		return value;
	}
}
