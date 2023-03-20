package minecraft.yunzhong.api;

import java.math.BigDecimal;
import java.util.Random;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import me.yic.xconomy.api.XConomyAPI;
import net.Zrips.CMILib.ActionBar.CMIActionBar;
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
			CMIActionBar.send(play,"请使用菜单操作！");
		}
	}

	/**
	 * 是否有钱在非领地区域建筑
	 * @param player
	 * @param loc
	 * @return
	 */
	public static boolean isHaveResidenceBuildMoney(Player player,Location loc){
		String worldName = player.getWorld().getName();
		if (!player.isOp()) {
			if (worldName.equals("world")) {
				ClaimedResidence res = ResidenceApi.getResidenceManager().getByLoc(loc);
				if (res != null) {
					ResidencePermissions perms = res.getPermissions();
					boolean hasPermission = perms.playerHas(player.getName(), "build", true);
					if (!hasPermission) {
						XConomyAPI xcapi = new XConomyAPI();
						int t = xcapi.changePlayerBalance(player.getUniqueId(), player.getName(), BigDecimal.valueOf(1), false);
						if (t == 0) {
							CMIActionBar.send(player, ChatColor.YELLOW + "非领地区域，扣除 1 金币");
						} else if (t == 2) {
							CMIActionBar.send(player, ChatColor.BLUE + "金币不足无法在此世界非领地区域进行建造");
							return false;
						}
					}
				} else {
					XConomyAPI xcapi = new XConomyAPI();
					int t = xcapi.changePlayerBalance(player.getUniqueId(), player.getName(), BigDecimal.valueOf(1), false);
					if (t == 0) {
						CMIActionBar.send(player, ChatColor.YELLOW + "非领地区域，扣除 1 金币");
					} else if (t == 2) {
						CMIActionBar.send(player, ChatColor.BLUE + "金币不足无法在此世界非领地区域进行建造");
						return false;
					}
				}
			} else if (worldName.equals("spawn")) {
				return false;
			}
		}
		return true;
	}

	public static final void rtp(CommandSender sender,String worldName) {
		Player play = Bukkit.getPlayer(sender.getName());
		World w = Bukkit.getWorld(worldName);
		if(w!=null) {
			Random random = new Random();
			int n = random.nextInt(10);
			int x = 0;
			int z = 0;
			while (true) {
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
				Location t = new Location(w, x, a, z);
				Block block = w.getBlockAt(t);
				if(
						block.getType()!=Material.WATER
						&&block.getType()!=Material.LAVA
						&&block.getType()!=Material.SNOW
						&&block.getType()!=Material.SNOW_BLOCK
						&&block.getType()!=Material.PACKED_ICE
				) {
					play.teleport(loc);
					break;
				}
				n = random.nextInt(10);
			}
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
