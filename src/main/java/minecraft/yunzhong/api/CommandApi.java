
package minecraft.yunzhong.api;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import static minecraft.yunzhong.api.messageUnit.sendMessage;
import static minecraft.yunzhong.database.dataCache.protectedPlayers;
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

	public static Integer setProtected(String PlayerName){
		Player player = Bukkit.getPlayer(PlayerName);
		if (true) {
			if (PlayerName != null) {
				if (!protectedPlayers.contains(player.getName())) {
					protectedPlayers.add(player.getName());
					sendMessage(player,ChatColor.YELLOW + "保护模式已开启！", ChatColor.YELLOW + "Protected Is True!");
					return 1;
				} else {
					protectedPlayers.remove(player.getName());
					sendMessage(player, ChatColor.RED + "保护模式已关闭！", ChatColor.RED + "Protected Is Close!");
					return 0;
				}
			} else {
				sendMessage(player,ChatColor.RED+PlayerName+"玩家不存在！",ChatColor.RED+"Player "+PlayerName+" Not Found");
			}
		}else{
			sendMessage(player, ChatColor.RED + "你没有权限！", ChatColor.RED + "Insufficient Permissions");
		}
		return -1;
	}

	public static void setProtected(CommandSender sender, String[] args){
		if(args.length>1){
			Player play = Bukkit.getPlayer(args[1]);
			if (play != null){
				int i = setProtected(args[1]);
				if (i==1){
					sendMessage(sender,ChatColor.YELLOW+args[1]+"保护模式已开启！", ChatColor.YELLOW+args[1]+" Protected Is True!");
				}else if (i==0){
					sendMessage(sender,ChatColor.RED+args[1]+"保护模式已关闭！", ChatColor.RED+args[1]+" Protected Is Close!");
				}else {
					sendMessage(sender,ChatColor.RED+args[1]+"玩家不存在！",ChatColor.RED+"Player "+args[1]+" Not Found");
				}
			}
		}else{
			sendMessage(sender,ChatColor.RED+"没有指定玩家！",ChatColor.RED+"Insufficient Permissions");
		}
	}
}
