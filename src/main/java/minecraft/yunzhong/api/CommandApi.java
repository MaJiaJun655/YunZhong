//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package minecraft.yunzhong.api;

import java.util.List;
import java.util.Random;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandApi extends JavaPlugin {
	public CommandApi() {
	}

	public static final void adminsay(CommandSender sender, String[] args) {
		String Commandinfo = null;

		for(int i = 1; i < args.length; ++i) {
			if (Commandinfo == null) {
				Commandinfo = args[i] + " ";
			} else {
				Commandinfo = Commandinfo + args[i] + " ";
			}
		}

		sender.sendMessage("正在执行：" + Commandinfo);
		if (Commandinfo != null) {
			sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), Commandinfo);
		}

	}

	public static final void moneyGo(CommandSender sender) {
		Player play = Bukkit.getPlayer(sender.getName());
		if (play.hasPermission("yzzm.moneygo")) {
			Random random = new Random();
			int money = random.nextInt(1500);
			sender.getServer().broadcastMessage(ChatColor.YELLOW + "恭喜" + play.getName() + "使用致富卡，获得了" + money + "金币！");
			play.getServer().dispatchCommand(play.getServer().getConsoleSender(), "eco give " + play.getName() + " " + money);
		} else {
			play.sendMessage("请使用菜单操作！");
		}
	}

	/**
	 * 生存世界指定随机传送
	 * @param sender
	 */
	public static final void rtp(CommandSender sender) {
		List<Material> BAND_MATERIALES;
		Player play = Bukkit.getPlayer(sender.getName());
		World w = Bukkit.getWorld("world");
		Random random = new Random();
		int n = random.nextInt(10);
		int x =0;
		int z =0;
		if(n==1||n==3||n==5||n==7||n==9){
			x = x-random.nextInt(1000);
			n = random.nextInt(10);
		}else{
			x = random.nextInt(1000);
			n = random.nextInt(10);
		}
		if(n==1||n==3||n==5||n==7||n==9){
			z = z-random.nextInt(1000);
		}else{
			z = random.nextInt(1000);

		}
		int a = w.getHighestBlockYAt(x,z);
		Location loc = new Location(w, x,a+1, z);
		Block block = w.getBlockAt(loc);
		while(block.getType() == Material.WATER ||block.getType() == Material.LAVA ){
				x =(Math.abs(random.nextInt()) % (1000-0 + 1))+ 0;
				z =(Math.abs(random.nextInt()) % (1000-0 + 1))+ 0;
				a = w.getHighestBlockYAt(x,z);
				loc = new Location(w, x,a+1, z);
				block = w.getBlockAt(loc);
		}
		play.teleport(loc);
	}

	public static final void rtp(CommandSender sender,String worldName) {
		List<Material> BAND_MATERIALES;
		Player play = Bukkit.getPlayer(sender.getName());
		World w = Bukkit.getWorld(worldName);
		if(w!=null) {
			Random random = new Random();
			int n = random.nextInt(10);
			int x = 0;
			int z = 0;
			if (n == 1 || n == 3 || n == 5 || n == 7 || n == 9) {
				x = x - random.nextInt(1000);
				n = random.nextInt(10);
			} else {
				x = random.nextInt(1000);
				n = random.nextInt(10);
			}
			if (n == 1 || n == 3 || n == 5 || n == 7 || n == 9) {
				z = z - random.nextInt(1000);
			} else {
				z = random.nextInt(1000);

			}
			int a = w.getHighestBlockYAt(x, z);
			Location loc = new Location(w, x, a + 1, z);
			Block block = w.getBlockAt(loc);
			while (block.getType() == Material.WATER || block.getType() == Material.LAVA) {
				x = (Math.abs(random.nextInt()) % (1000 - 0 + 1)) + 0;
				z = (Math.abs(random.nextInt()) % (1000 - 0 + 1)) + 0;
				a = w.getHighestBlockYAt(x, z);
				loc = new Location(w, x, a + 1, z);
				block = w.getBlockAt(loc);
			}
			play.teleport(loc);
		}else{
			play.sendMessage(ChatColor.RED+"世界不存在！");
		}
	}

	public static final void reload(CommandSender sender, Plugin plugin) {
		sender.sendMessage("[云中之梦]插件正在重载");
		Bukkit.getPluginManager().disablePlugin(plugin);
		Bukkit.getPluginManager().enablePlugin(plugin);
	}
}
