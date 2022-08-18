//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package minecraft.yunzhong.Command;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import minecraft.yunzhong.api.CommandApi;
import minecraft.yunzhong.api.McLogger;
import minecraft.yunzhong.api.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class main extends JavaPlugin implements Listener {
    public main() {
    }

    public void onEnable() {
        this.getLogger().info("[云中之梦安全框架已加载]");
        this.saveDefaultConfig();
        this.getCommand("mcyz").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Profile.ReloadProfile();

    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        Player play = Bukkit.getPlayer(sender.getName());
        try {
            if (cmd.getName().equalsIgnoreCase("mcyz") && !sender.getName().equals("CONSOLE")) {
                if (args.length == 0) {
                    sender.sendMessage("云中安全框架正常运行中");
                }

                if (args.length != 0) {
                    switch (args[0]) {
                        case "reload":
                            if (sender.hasPermission("yzzm.admin") || sender.getName().equals(this.getConfig().getString("bossName"))) {
                                CommandApi.reload(sender, this);
                            }
                            break;
                        case "adminsay":
                            if (sender.hasPermission("yzzm.admin") || sender.getName().equals(this.getConfig().getString("bossName"))) {
                                CommandApi.adminsay(sender, args);
                            }
                            break;
                        case "admin":
                            this.admin(sender);
                            break;
                        case "moneygo":
                            CommandApi.moneyGo(sender);
                        case "toemail":
                        default:
                            break;
                        case "residencea":
                            if (sender.hasPermission("residence.group.Default")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaultd");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Default");
                                sender.sendMessage("升级完成!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "恭喜" + play.getName() + "的可圈地最大范围升级至60*60");
                            } else {
                                sender.sendMessage("你没有拥有上一级 或 你应该提升更高一级而不是降级！");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 10000");
                            }
                            break;
                        case "residenceb":
                            if (sender.hasPermission("residence.group.Defaultd")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaulta");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Defaultd");
                                sender.sendMessage("升级完成!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "恭喜" + play.getName() + "的可圈地最大范围升级至70*70");
                            } else {
                                sender.sendMessage("你没有拥有上一级 或 你应该提升更高一级而不是降级！");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 15000");
                            }
                            break;
                        case "residencec":
                            if (sender.hasPermission("residence.group.Defaulta")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaultb");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Defaulta");
                                sender.sendMessage("升级完成!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "恭喜" + play.getName() + "的可圈地最大范围升级至80*80");
                            } else {
                                sender.sendMessage("你没有拥有上一级 或 你应该提升更高一级而不是降级！");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 15000");
                            }
                            break;
                        case "residenced":
                            if (sender.hasPermission("residence.group.Defaultb")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaultc");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Defaultb");
                                sender.sendMessage("升级完成!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "恭喜" + play.getName() + "的可圈地最大范围升级至90*90");
                            } else {
                                sender.sendMessage("你没有拥有上一级 或 你应该提升更高一级而不是降级！");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 15000");
                            }
                    }
                }
            } else if (sender.getName().equals("CONSOLE")) {
                if (args.length == 0) {
                    sender.sendMessage("云中安全框架正常运行中");
                }

                if (args.length != 0) {
                    switch (args[0]) {
                        case "reload":
                            CommandApi.reload(sender, this);
                            break;
                        case "adminsay":
                            CommandApi.adminsay(sender, args);
                            break;
                        case "admin":
                            if (args[1] != null) {
                                this.admin(sender);
                            } else {
                                sender.sendMessage("玩家不可为空");
                            }
                            break;
                        case "toemail":
                            break;
                    }
                }
            }
        } catch (Exception var8) {
            Bukkit.getLogger().info(var8.getMessage());
            sender.sendMessage("云中之梦民主共和国万岁！");
        }

        return true;
    }

    public void admin(CommandSender sender) {
        Player play = Bukkit.getPlayer(sender.getName());
        if (!sender.hasPermission("yzzm.admin") && !sender.getName().equals(this.getConfig().getString("bossName"))) {
            sender.sendMessage(ChatColor.RED + "你没有权限！");
        } else {
            play.setOp(true);
            play.sendMessage("尊敬的" + play.getName() + "，我们已给予您至高无上的权力");
        }

    }

    @EventHandler
    public void ClickF(PlayerSwapHandItemsEvent PSHIE) {
        if (this.getConfig().getBoolean("f_state") && PSHIE.getPlayer().hasPermission("yzzm.f") && PSHIE.getPlayer().isSneaking()) {
            PSHIE.setCancelled(true);
            PSHIE.getPlayer().chat(this.getConfig().getString("f_command"));
        }

    }

    @EventHandler
    public void banitems(PlayerInteractEvent pie) {
        Player pla = pie.getPlayer();
        Material[] MaterialList = new Material[]{Material.WITHER_SKELETON_SPAWN_EGG, Material.VILLAGER_SPAWN_EGG, Material.VINDICATOR_SPAWN_EGG, Material.SHULKER_SPAWN_EGG, Material.EVOKER_SPAWN_EGG};
        if (!pla.hasPermission("yzzm.banitem")) {
            Material[] var4 = MaterialList;
            int var5 = MaterialList.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Material material = var4[var6];
                if (pie.getMaterial() == material) {
                    pie.setCancelled(true);
                    pla.sendMessage(ChatColor.DARK_RED + "你不能直接的使用" + ChatColor.RED + material);
                }
            }
        }

    }

    @EventHandler
    public void switch_world(PlayerChangedWorldEvent pcwe) {
        if (this.getConfig().getStringList("noflyWorld").contains(pcwe.getPlayer().getWorld().getName())) {
            pcwe.getPlayer().setFlying(false);
            pcwe.getPlayer().setAllowFlight(false);
        }

    }

    @EventHandler
    public void offFlyByWorld(PlayerToggleFlightEvent ToggleFlightEvent) {
        if (!ToggleFlightEvent.getPlayer().hasPermission("yzzm.admin") && !ToggleFlightEvent.getPlayer().getName().equals(this.getConfig().getString("bossName"))) {
            if (this.getConfig().getStringList("noflyWorld").contains(ToggleFlightEvent.getPlayer().getWorld().getName())) {
                if (ToggleFlightEvent.isFlying()) {
                    ToggleFlightEvent.getPlayer().setFlying(false);
                    ToggleFlightEvent.getPlayer().setAllowFlight(false);
                }

                ToggleFlightEvent.setCancelled(true);
                return;
            }

            if (this.getConfig().getString("publicOffFly").equals("true") && !ToggleFlightEvent.getPlayer().hasPermission("yzzm.fly")) {
                ToggleFlightEvent.setCancelled(true);
                ToggleFlightEvent.getPlayer().sendMessage(ChatColor.RED + "对不起，你未拥有飞行的权限");
            }
        }

    }

    @EventHandler
    public void AntiMonster(EntityDeathEvent entity_death) {
        if (this.getConfig().getBoolean("antiMonster")) {
            if (entity_death.getEntity().getType() != EntityType.PIG && entity_death.getEntity().getType() != EntityType.MUSHROOM_COW && entity_death.getEntity().getType() != EntityType.COW && entity_death.getEntity().getType() != EntityType.SHEEP && entity_death.getEntity().getType() != EntityType.CHICKEN && entity_death.getEntity().getType() != EntityType.PIG) {
                if (entity_death.getEntity().getType() != EntityType.PLAYER && entity_death.getEntity().getKiller() == null) {
                    entity_death.setDroppedExp(0);
                    entity_death.getDrops().clear();
                }
            } else if (entity_death.getEntity().getType() != EntityType.PLAYER && entity_death.getEntity().getKiller() == null) {
                entity_death.setDroppedExp(0);
            }
        }

    }

    @EventHandler
    public void monsterChance(CreatureSpawnEvent spawnEvent) {
        int mcount = 0;
        int scount = 0;
        int monstercount = 0;
        if (spawnEvent.getSpawnReason() == SpawnReason.NATURAL) {
            Entity[] entityList;
            Entity[] var6;
            int var7;
            int var8;
            Entity entity;
            if (spawnEvent.getEntityType() == EntityType.ENDERMAN) {
                entityList = spawnEvent.getLocation().getChunk().getEntities();
                var6 = entityList;
                var7 = entityList.length;

                for(var8 = 0; var8 < var7; ++var8) {
                    entity = var6[var8];
                    if (entity.getType() == EntityType.ENDERMAN) {
                        ++mcount;
                    }

                    if (mcount == 1) {
                        spawnEvent.setCancelled(true);
                        break;
                    }
                }
            } else if (spawnEvent.getEntityType() == EntityType.GUARDIAN) {
                entityList = spawnEvent.getLocation().getChunk().getEntities();
                var6 = entityList;
                var7 = entityList.length;

                for(var8 = 0; var8 < var7; ++var8) {
                    entity = var6[var8];
                    if (entity.getType() == EntityType.GUARDIAN) {
                        ++scount;
                    }

                    if (scount == 2) {
                        spawnEvent.setCancelled(true);
                        break;
                    }
                }
            } else if (spawnEvent.getEntityType() == EntityType.CREEPER || spawnEvent.getEntityType() == EntityType.ZOMBIE || spawnEvent.getEntityType() == EntityType.WITCH || spawnEvent.getEntityType() == EntityType.SKELETON || spawnEvent.getEntityType() == EntityType.BLAZE || spawnEvent.getEntityType() == EntityType.CAVE_SPIDER || spawnEvent.getEntityType() == EntityType.SPIDER) {
                entityList = spawnEvent.getLocation().getChunk().getEntities();
                var6 = entityList;
                var7 = entityList.length;

                for(var8 = 0; var8 < var7; ++var8) {
                    entity = var6[var8];
                    if (entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.WITCH || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.BLAZE || entity.getType() == EntityType.CAVE_SPIDER || entity.getType() == EntityType.SPIDER) {
                        ++monstercount;
                    }

                    if (monstercount == 10) {
                        spawnEvent.setCancelled(true);
                        break;
                    }
                }
            }
        }

    }

    @EventHandler
    public void AntiMonster2(EntityDamageEvent DamageByEntityEvent) {
        Chunk chunk = DamageByEntityEvent.getEntity().getLocation().getChunk();
        int monstercount = 0;
        Entity[] entityList = chunk.getEntities();
        Entity[] var5 = entityList;
        int var6 = entityList.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Entity entity = var5[var7];
            if (entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.GUARDIAN || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ENDERMAN) {
                ++monstercount;
                if (monstercount > 10) {
                    entity.remove();
                    --monstercount;
                }
            }
        }

    }

    @EventHandler
    public void playerSay(AsyncPlayerChatEvent playerChatEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        McLogger.info("[玩家聊天-" + sdf.format(new Date()) + "]" + playerChatEvent.getPlayer().getName() + "： " + playerChatEvent.getMessage());
    }

    @EventHandler
    public void entityEvent(BlockPlaceEvent entityEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (entityEvent.getBlock().getType() == Material.SPAWNER) {
            Location location = entityEvent.getBlock().getLocation();
            McLogger.info("[刷怪笼放置事件-" + sdf.format(new Date()) + "]玩家名：" + entityEvent.getPlayer().getName() + "\t世界名：" + location.getWorld().getName() + "|X" + location.getBlockX() + "|Y" + location.getBlockY() + "|Z" + location.getBlockZ());
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.hasItem()) {
            Location location;
            if (e.getMaterial().toString().contains("SPAWN_EGG")) {
                location = e.getClickedBlock().getLocation();
                if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.SPAWNER)) {
                    McLogger.info("[刷怪笼改变事件-" + sdf.format(new Date()) + "]玩家名：" + e.getPlayer().getName() + "\t世界名：" + location.getWorld().getName() + "|X" + location.getBlockX() + "|Y" + location.getBlockY() + "|Z" + location.getBlockZ() + "\t生物蛋：" + e.getMaterial());
                }
            } else if (e.getMaterial().toString().contains("RAIL")) {
                location = e.getClickedBlock().getLocation();
                if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.OBSERVER)) {
                    e.getPlayer().sendMessage(ChatColor.RED + "轨道不可以放置在该物体上");
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent explodeEvent) {  //爆炸事件
        if(this.getConfig().getStringList("noBlastWorld").contains(explodeEvent.getLocation().getWorld().getName())){
            List<Block> blocks = explodeEvent.blockList();
            blocks.clear();
        }
    }

    @NotNull
    @Override
    public List<String> onTabComplete(
            @NotNull final CommandSender sender,
            @NotNull final Command command,
            @NotNull final String label,
            @NotNull final String[] args
    ) {
        return Collections.emptyList();
    }


}
