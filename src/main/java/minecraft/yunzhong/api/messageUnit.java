package minecraft.yunzhong.api;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class messageUnit {
    public static void sendMessage(Player player, String message,String message2) {
        String locale = player.getLocale();
        if(locale.equals("zh_cn")){
            player.sendMessage(message);
        }else{
            player.sendMessage(message2);
        }
    }

    public static void sendMessage(CommandSender sender, String message, String message2) {
       Player player = Bukkit.getPlayer(sender.getName());
        if (player != null) {
            sendMessage(player,message,message2);
        }else{
            sender.sendMessage(message);
        }
    }
}
