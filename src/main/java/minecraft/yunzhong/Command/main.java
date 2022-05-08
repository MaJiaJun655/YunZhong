package minecraft.yunzhong.Command;

import minecraft.yunzhong.api.CommandApi;
import minecraft.yunzhong.api.McLogger;
import minecraft.yunzhong.api.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("[云中之梦安全框架已加载]");
        this.saveDefaultConfig();
        this.getCommand("mcyz").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Profile.ReloadProfile();
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String nowDate = sdf.format(new Date());
//		try {
//			email.sendEmainTo("服务器启动完成", "你好，尊敬的管理员，服务器已于"+nowDate+"正常运行中！");
//		} catch (AddressException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (MessagingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    }
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        try {
            if(cmd.getName().equalsIgnoreCase("mcyz")&&!sender.getName().equals("CONSOLE")){
                if(args.length==0) {
                    sender.sendMessage("云中安全框架正常运行中");
                }
                if(args.length!=0) {
                    switch(args[0]) {
                        case "reload":
                            if(sender.hasPermission("yzzm.admin")||sender.getName().equals(this.getConfig().getString("bossName"))) {
                                CommandApi.reload(sender, this);
                            }
                            break;
                        case "adminsay":
                            if(sender.hasPermission("yzzm.admin")||sender.getName().equals(this.getConfig().getString("bossName"))) {
                                CommandApi.adminsay(sender, args);
                            }
                            break;
                        case "admin":
                            admin(sender);
                            break;
                        case "moneygo":
                            CommandApi.moneyGo(sender);
                            break;
                        case "toemail":
//							if(args[1]!=null&&args[2]!=null&&args[3]!=null) {
//								email.sendEmainTo(args[1], args[2],args[3],sender.getName());
//							}else if(args[1]!=null&&args[2]!=null) {
//							email.sendEmainTo(args[1], args[2]);
//							}else {
//								sender.sendMessage("请输入/mcyz toemail 标题 内容 邮箱");
//							}
//							break;
                    }
                }
            }else if(sender.getName().equals("CONSOLE")) {
                if(args.length==0) {
                    sender.sendMessage("云中安全框架正常运行中");
                }
                if(args.length!=0) {
                    switch (args[0]) {
                        case "reload":
                            CommandApi.reload(sender, this);
                            break;
                        case "adminsay":
                            CommandApi.adminsay(sender, args);
                            break;
                        case "admin":
                            if (args[1] != null) {
                                admin(sender);
                            } else {
                                sender.sendMessage("玩家不可为空");
                            }
                            break;
                        case "toemail":
//						if(args[1]!=null&&args[2]!=null&&args[3]!=null) {
//							email.sendEmainTo(args[1], args[2],args[3],sender.getName());
//						}else if(args[1]!=null&&args[2]!=null) {
//							email.sendEmainTo(args[1], args[2]);
//						}
//						break;
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            sender.sendMessage("云中之梦民主共和国万岁！");
        }
        return true;
    }


    public void admin(CommandSender sender) {
        Player play = Bukkit.getPlayer(sender.getName());
        if(sender.hasPermission("yzzm.admin")||sender.getName().equals(this.getConfig().getString("bossName"))) {
            play.setOp(true);
            play.sendMessage("尊敬的"+play.getName()+"，我们已给予您至高无上的权力");
        }else {
            sender.sendMessage(ChatColor.RED+"你没有权限！");
        }
    }

    @EventHandler
    public void ClickF(PlayerSwapHandItemsEvent PSHIE) {	//当玩家按下F键可执行一段命令
        if(this.getConfig().getBoolean("f_state")){
            if(PSHIE.getPlayer().hasPermission("yzzm.f")&&PSHIE.getPlayer().isSneaking()) {
                PSHIE.setCancelled(true);
                PSHIE.getPlayer().chat(this.getConfig().getString("f_command"));
            }
        }
    }

    @EventHandler
    public void banitems(PlayerInteractEvent pie) {		//当玩家使用非法物品时，将取消用其发生的所有事件
        Player pla = pie.getPlayer();
        Material[] MaterialList = new Material[]{
                Material.WITHER_SKELETON_SPAWN_EGG,
                Material.VILLAGER_SPAWN_EGG,
                Material.SHULKER_SPAWN_EGG,
                Material.EVOKER_SPAWN_EGG
        };
        if(!pla.hasPermission("yzzm.banitem")) {
            for (Material material : MaterialList) {
                if(pie.getMaterial() == material) {
                    pie.setCancelled(true);
                    pla.sendMessage(ChatColor.DARK_RED+"你不能直接的使用"+ChatColor.RED+material);
                }
            }
        }
    }


    @EventHandler
    public void switch_world(PlayerChangedWorldEvent pcwe) {	//指定世界关闭飞行
        if (this.getConfig().getStringList("noflyWorld").contains(pcwe.getPlayer().getWorld().getName())) {
            pcwe.getPlayer().setFlying(false);
            pcwe.getPlayer().setAllowFlight(false);
        }
    }

    @EventHandler
    public void offFlyByWorld(PlayerToggleFlightEvent ToggleFlightEvent) {	//当玩家切换飞行状态
        if(!ToggleFlightEvent.getPlayer().hasPermission("yzzm.admin")&&!ToggleFlightEvent.getPlayer().getName().equals(this.getConfig().getString("bossName"))) {
            if (this.getConfig().getStringList("noflyWorld").contains(ToggleFlightEvent.getPlayer().getWorld().getName())) {
                if (ToggleFlightEvent.isFlying()) {
                    ToggleFlightEvent.getPlayer().setFlying(false);
                    ToggleFlightEvent.getPlayer().setAllowFlight(false);
                }
                ToggleFlightEvent.setCancelled(true);
                return;
            }
            if (this.getConfig().getString("publicOffFly").equals("true")) {
                if (!ToggleFlightEvent.getPlayer().hasPermission("yzzm.fly")) {
                    ToggleFlightEvent.setCancelled(true);
                    ToggleFlightEvent.getPlayer().sendMessage(ChatColor.RED + "对不起，你未拥有飞行的权限");
                }
            }
        }

    }


    @EventHandler
    public void AntiMonster(EntityDeathEvent entity_death) {	//当生物死亡时
        if(
                this.getConfig().getBoolean("antiMonster")		//如果使用该功能
                        &&entity_death.getEntity().getType()!= EntityType.PLAYER		//如果改生物不是玩家
                        &&entity_death.getEntity().getKiller()==null	//如果这个生物的死亡与任何玩家无关
        )
        {
            entity_death.setDroppedExp(0);		//不掉落经验
            entity_death.getDrops().clear();	//不掉落物品
        }
    }


    @EventHandler
    public void monsterChance(CreatureSpawnEvent spawnEvent) {	//当生物生成时
        int count = 0;
        if(spawnEvent.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnEvent.getEntityType() == EntityType.ENDERMAN) {        //如果生物是末影人
                Entity[] entityList = spawnEvent.getLocation().getChunk().getEntities(); //获得当前区块的所有生物
                for (Entity entity : entityList) {
                    if (entity.getType() == EntityType.ENDERMAN) {
                        count++;
                    }
                    if (count == 1) {
                        spawnEvent.setCancelled(true);        //如果末影人数量大于1则停止生成
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerSay(AsyncPlayerChatEvent playerChatEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        McLogger.info("[玩家聊天-"+sdf.format(new Date())+"]"+playerChatEvent.getPlayer().getName()+"： "+playerChatEvent.getMessage());
    }

    @EventHandler
    public void entityEvent(BlockPlaceEvent entityEvent){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if(entityEvent.getBlock().getType() == Material.SPAWNER){
            Location location = entityEvent.getBlock().getLocation();
            McLogger.info("[刷怪笼放置事件-"+sdf.format(new Date())+"]"+"玩家名："+entityEvent.getPlayer().getName()+"\t世界名："+location.getWorld().getName()+"|X"+location.getBlockX()+"|Y"+location.getBlockY()+"|Z"+location.getBlockZ());
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.getMaterial().toString().contains("SPAWN_EGG")) {
                    Location location = e.getClickedBlock().getLocation();
                    if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.SPAWNER)) {
                        McLogger.info("[刷怪笼改变事件-"+sdf.format(new Date())+"]"+"玩家名："+e.getPlayer().getName()+"\t世界名："+location.getWorld().getName()+"|X"+location.getBlockX()+"|Y"+location.getBlockY()+"|Z"+location.getBlockZ()+"\t生物蛋："+e.getMaterial());
                    }
                }
            }
        }
    }
}
