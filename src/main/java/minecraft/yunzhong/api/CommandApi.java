package minecraft.yunzhong.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Properties;
import java.util.Random;

public class CommandApi extends JavaPlugin {
	public final static void adminsay(CommandSender sender,String[] args) {
		String Commandinfo = null;
		for(int i =1;i<args.length;i++) {
			if(Commandinfo==null) {
				Commandinfo=args[i]+" ";
			}else {
			Commandinfo = Commandinfo + args[i]+" ";
			}
		}
		sender.sendMessage("����ִ�У�"+Commandinfo);
		if(Commandinfo!=null) {
			sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), Commandinfo);
		}
	}

	//�¸�������������һЩ���
	public final static void moneyGo(CommandSender sender) {
		Player play = Bukkit.getPlayer(sender.getName());
		if(play.hasPermission("yzzm.moneygo")) {
			Random random = new Random();
			int money = random.nextInt(1500);
			sender.getServer().broadcastMessage(ChatColor.YELLOW+"��ϲ"+play.getName()+"ʹ���¸����������"+money+"��ң�");
			play.getServer().dispatchCommand(play.getServer().getConsoleSender(), "eco give "+play.getName()+" "+money);
		}else {
			play.sendMessage("��ʹ�ò˵�������");
		}
	}

	public final static void reload(CommandSender sender,Plugin plugin) {
		sender.sendMessage("[����֮��]�����������");
		Bukkit.getPluginManager().disablePlugin(plugin);
		Bukkit.getPluginManager().enablePlugin(plugin);
	}

}
