package me.nathanpb.SpellBook;

import me.nathanpb.Spell.Spell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map.Entry;

public class Utils{
	private final me.nathanpb.Spelling.Spelling plugin;
	public Utils(me.nathanpb.Spelling.Spelling plugin){this.plugin = plugin;}
	
	public static ItemStack returnButton = getReturnButton();
	public static ItemStack whiteButton = getWhiteButton();
	public static ItemStack cheatButton = getCheatButton();
	
	public static void PagesDecoration(Inventory inventory, boolean returnButton){
		
		ItemStack redglass = Utils.returnButton;
		ItemStack whiteglass = Utils.whiteButton;
		
		for(int i = 0; i <= 8; i++){
			inventory.setItem(i, whiteglass);
		}
		for(int i = inventory.getSize()-1; i >= inventory.getSize()-9; i--){
			inventory.setItem(i, whiteglass);
		}
		int i=8;
		while(i<=inventory.getSize()-1){
			inventory.setItem(i, whiteglass);
			i+=9;
		}
		i=9;
		while(i<=inventory.getSize()-1){
			inventory.setItem(i, whiteglass);
			i+=9;
		}
		if(returnButton){
			int last = inventory.getSize()-1;
			inventory.setItem(last, redglass);
			inventory.setItem(last-8, redglass);
		}
	}
	public  enum SpellArea{
		Attack(Icon(Material.IRON_SWORD, ChatColor.DARK_BLUE+"Attack")),
		Defense(Icon(Material.IRON_CHESTPLATE, ChatColor.DARK_BLUE+"Defense")),
		Utility(Icon(Material.BUCKET, ChatColor.DARK_BLUE+"Utility")),
		Misc(Icon(Material.TNT, ChatColor.DARK_BLUE+"Misc")),
		Books(Icon(Material.BOOK_AND_QUILL, ChatColor.DARK_BLUE+"Books")),
		Selfs(Icon(Material.RAW_BEEF, ChatColor.BLUE+"Selfs"));
		
		private ItemStack value;
		SpellArea(ItemStack value){
			this.value = value;
		}
		public ItemStack getValue(){
			return this.value;
		}
	}
	public static ItemStack Icon(Material type, String name){
		ItemStack stack = new ItemStack(type);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(name);
		stack.setItemMeta(meta);
		return stack;
	}
	public static Inventory generateHolder(int size, String name, boolean redButtons){
		int var = 0;
		for(int i = size-1; i >= 0; i--){
			if(i%7 == 0){
				var += 9;
			}
		}
		var = var+18;
		Inventory i = Bukkit.createInventory(null, var, name);
		Utils.PagesDecoration(i, redButtons);
		return i;
	}
	private static ItemStack getReturnButton(){
		ItemStack redglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
		ItemMeta glassmeta = redglass.getItemMeta();
		glassmeta.setDisplayName(ChatColor.RED+"Back");
		redglass.setItemMeta(glassmeta);
		return redglass;
	}
	private static ItemStack getWhiteButton(){
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta glassmeta = glass.getItemMeta();
		glassmeta.setDisplayName(ChatColor.RED+" ");
		glass.setItemMeta(glassmeta);
		return glass;
	}
	private static ItemStack getCheatButton(){
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
		ItemMeta glassmeta = glass.getItemMeta();
		glassmeta.setDisplayName(ChatColor.GREEN+"Cheat this recipe");
		glass.setItemMeta(glassmeta);
		return glass;
	}
	public static Inventory blankRecipeInventory(){
		Inventory recipe = Bukkit.createInventory(null, 45, ChatColor.DARK_PURPLE+"Recipe");
		ItemStack material = whiteButton;
		ItemMeta materialMeta = material.getItemMeta();
		materialMeta.setDisplayName(" ");
		material.setItemMeta(materialMeta);
		ItemStack work = returnButton;
		recipe.setItem(2, material);
		recipe.setItem(3, material);
		recipe.setItem(4, material);
		recipe.setItem(5, material);
		recipe.setItem(6, material);
		recipe.setItem(11, material);
		recipe.setItem(15, material);
		recipe.setItem(18, material);
		recipe.setItem(19, material);
		recipe.setItem(20, material);
		recipe.setItem(24, material);
		recipe.setItem(25, material);
		recipe.setItem(26, material);
		recipe.setItem(29, material);
		recipe.setItem(33, material);
		recipe.setItem(38, material);
		recipe.setItem(39, material);
		recipe.setItem(40, material);
		recipe.setItem(41, material);
		recipe.setItem(42, material);
		recipe.setItem(0, work);
		recipe.setItem(1, work);
		recipe.setItem(7, work);
		recipe.setItem(8, work);
		recipe.setItem(37, work);
		recipe.setItem(36, work);
		recipe.setItem(43, work);
		recipe.setItem(44, work);
		recipe.setItem(28, work);
		recipe.setItem(27, work);
		recipe.setItem(9, work);
		recipe.setItem(10, work);
		recipe.setItem(16, work);
		recipe.setItem(17, work);
		recipe.setItem(34, work);
		recipe.setItem(35, work);
		return recipe;
	}
	public static Inventory mkRecipe(Spell s){
		Inventory i = Utils.blankRecipeInventory();
		ShapedRecipe recipe = null;
		for(Recipe r : Bukkit.getRecipesFor(s.getSpellItem())){
			if(r instanceof ShapedRecipe){
				if(r.getResult().equals(s.getSpellItem())){
					recipe = (ShapedRecipe) r;
				}
			}
		}
		for(Entry<Character, ItemStack> entry : recipe.getIngredientMap().entrySet()){
			if(entry.getKey().equals('a')){
				i.setItem(12, entry.getValue());
			}
			if(entry.getKey().equals('b')){
				i.setItem(13, entry.getValue());
			}
			if(entry.getKey().equals('c')){
				i.setItem(14, entry.getValue());
			}
			if(entry.getKey().equals('d')){
				i.setItem(21, entry.getValue());
			}
			if(entry.getKey().equals('e')){
				i.setItem(22, entry.getValue());
			}
			if(entry.getKey().equals('f')){
				i.setItem(23, entry.getValue());
			}
			if(entry.getKey().equals('g')){
				i.setItem(30, entry.getValue());
			}
			if(entry.getKey().equals('h')){
				i.setItem(31, entry.getValue());
			}
			if(entry.getKey().equals('i')){
				i.setItem(32, entry.getValue());
			}
		}
		return i;
	}
	public static void applyCheatButton(Inventory i, Player p){
		if(p.isOp()){
			i.setItem(40, cheatButton);
		}
	}
}
