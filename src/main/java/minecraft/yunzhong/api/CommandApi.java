//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package minecraft.yunzhong.api;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

	public static final void reload(CommandSender sender, Plugin plugin) {
		sender.sendMessage("[云中之梦]插件正在重载");
		Bukkit.getPluginManager().disablePlugin(plugin);
		Bukkit.getPluginManager().enablePlugin(plugin);
	}
}
