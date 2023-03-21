//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package minecraft.yunzhong.Command;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import me.yic.xconomy.api.XConomyAPI;
import minecraft.yunzhong.api.CommandApi;
import minecraft.yunzhong.api.McLogger;
import minecraft.yunzhong.api.Profile;
import minecraft.yunzhong.api.StrUtil;
import net.Zrips.CMILib.ActionBar.CMIActionBar;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static minecraft.yunzhong.api.CommandApi.isHaveResidenceBuildMoney;

public class main extends JavaPlugin implements Listener {
    public main() {
    }
    public static String playerName = "";
    public void onEnable() {
        this.getLogger().info("[����֮�ΰ�ȫ����Ѽ���]");
        this.saveDefaultConfig();
        this.getCommand("mcyz").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Profile.ReloadProfile();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
        sdf.format(new Date());
    }

    public void setName(String name){
        playerName = name;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        Player play = Bukkit.getPlayer(sender.getName());

        try {
            if (cmd.getName().equalsIgnoreCase("mcyz") && !sender.getName().equals("CONSOLE")) {
                if (args.length == 0) {
                    sender.sendMessage("���а�ȫ�������������");
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
                            break;
                        case "rtp":
                            if(!StrUtil.toString(args[1]).equals("")){
                                if (this.getConfig().getStringList("rtp").contains(args[1])) {
                                    CommandApi.rtp(sender,args[1]);
                                }else{
                                    CMIActionBar.send(play,ChatColor.RED+"�������δ�������ͣ�");
                                }
                            }
                            break;
                        case "toemail":
                        default:
                            break;
                        case "residencea":
                            if (sender.hasPermission("residence.group.Default")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaultd");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Default");
                                sender.sendMessage("�������!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "��ϲ" + play.getName() + "�Ŀ�Ȧ�����Χ������60*60");
                            } else {
                                sender.sendMessage("��û��ӵ����һ�� �� ��Ӧ����������һ�������ǽ�����");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 10000");
                            }
                            break;
                        case "residenceb":
                            if (sender.hasPermission("residence.group.Defaultd")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaulta");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Defaultd");
                                sender.sendMessage("�������!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "��ϲ" + play.getName() + "�Ŀ�Ȧ�����Χ������70*70");
                            } else {
                                sender.sendMessage("��û��ӵ����һ�� �� ��Ӧ����������һ�������ǽ�����");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 15000");
                            }
                            break;
                        case "residencec":
                            if (sender.hasPermission("residence.group.Defaulta")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaultb");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Defaulta");
                                sender.sendMessage("�������!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "��ϲ" + play.getName() + "�Ŀ�Ȧ�����Χ������80*80");
                            } else {
                                sender.sendMessage("��û��ӵ����һ�� �� ��Ӧ����������һ�������ǽ�����");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 15000");
                            }
                            break;
                        case "residenced":
                            if (sender.hasPermission("residence.group.Defaultb")) {
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manuaddp " + sender.getName() + " residence.group.Defaultc");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "manudelp " + sender.getName() + " residence.group.Defaultb");
                                sender.sendMessage("�������!");
                                sender.getServer().broadcastMessage(ChatColor.YELLOW + "��ϲ" + play.getName() + "�Ŀ�Ȧ�����Χ������90*90");
                            } else {
                                sender.sendMessage("��û��ӵ����һ�� �� ��Ӧ����������һ�������ǽ�����");
                                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "eco give " + sender.getName() + " 15000");
                            }
                    }
                }
            } else if (sender.getName().equals("CONSOLE")) {
                if (args.length == 0) {
                    sender.sendMessage("���а�ȫ�������������");
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
                                sender.sendMessage("��Ҳ���Ϊ��");
                            }
                            break;
                        case "addFakePlayer":

                            sender.getServer().getOnlinePlayers();
                            break;
                        case "toemail":
                            break;
                    }
                }
            }
        } catch (Exception var8) {
            sender.sendMessage("����֮���������͹����꣡");
        }

        return true;
    }

    public void admin(CommandSender sender) {
        Player play = Bukkit.getPlayer(sender.getName());
        if (!sender.hasPermission("yzzm.admin") && !sender.getName().equals(this.getConfig().getString("bossName"))) {
            sender.sendMessage(ChatColor.RED + "��û��Ȩ�ޣ�");
        } else {
            play.setOp(true);
            play.sendMessage("�𾴵�" + play.getName() + "�������Ѹ������������ϵ�Ȩ��");
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
        Material[] MaterialList = new Material[]{
//                Material.WITHER_SKELETON_SPAWN_EGG,
//                Material.VILLAGER_SPAWN_EGG,
//                Material.VINDICATOR_SPAWN_EGG,
//                Material.SHULKER_SPAWN_EGG,
//                Material.EVOKER_SPAWN_EGG
        };
        if (!pla.hasPermission("yzzm.banitem")) {
            Material[] var4 = MaterialList;
            int var5 = MaterialList.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Material material = var4[var6];
                if (pie.getMaterial() == material) {
                    pie.setCancelled(true);
                    pla.sendMessage(ChatColor.DARK_RED + "�㲻��ֱ�ӵ�ʹ��" + ChatColor.RED + material);
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
                ToggleFlightEvent.getPlayer().sendMessage(ChatColor.RED + "�Բ�����δӵ�з��е�Ȩ��");
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

                for (var8 = 0; var8 < var7; ++var8) {
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

                for (var8 = 0; var8 < var7; ++var8) {
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

                for (var8 = 0; var8 < var7; ++var8) {
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
    public void AntiMonster2(EntityDamageEvent event) {
        Chunk chunk = event.getEntity().getLocation().getChunk();
        int monstercount = 0;
        Entity[] entityList = chunk.getEntities();
        Entity[] var5 = entityList;
        int var6 = entityList.length;

        for (int var7 = 0; var7 < var6; ++var7) {
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
    public void playerSay(AsyncPlayerChatEvent event) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        McLogger.info("[�������-" + sdf.format(new Date()) + "]" + event.getPlayer().getName() + "�� " + event.getMessage());
    }

//    @EventHandler
//    public void entityEvent(BlockPlaceEvent entityEvent) {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        if (entityEvent.getBlock().getType() == Material.SPAWNER) {
//            Location location = entityEvent.getBlock().getLocation();
//            McLogger.info("[ˢ���������¼�-" + sdf.format(new Date()) + "]�������" + entityEvent.getPlayer().getName() + "\t��������" + location.getWorld().getName() + "|X" + location.getBlockX() + "|Y" + location.getBlockY() + "|Z" + location.getBlockZ());
//        }
//
//    }
//
//    @EventHandler
//    public void onInteract(PlayerInteractEvent e) {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.hasItem()) {
//            Location location;
//            if (e.getMaterial().toString().contains("SPAWN_EGG")) {
//                location = e.getClickedBlock().getLocation();
//                if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.SPAWNER)) {
//                    McLogger.info("[ˢ�����ı��¼�-" + sdf.format(new Date()) + "]�������" + e.getPlayer().getName() + "\t��������" + location.getWorld().getName() + "|X" + location.getBlockX() + "|Y" + location.getBlockY() + "|Z" + location.getBlockZ() + "\t���ﵰ��" + e.getMaterial());
//                }
//            } else if (e.getMaterial().toString().contains("RAIL")) {
//                location = e.getClickedBlock().getLocation();
//                if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.OBSERVER)) {
//                    e.getPlayer().sendMessage(ChatColor.RED + "��������Է����ڸ�������");
//                    e.setCancelled(true);
//                }
//            }
//        }
//    }

    /**
     * ��ը�����¼�
     * @param event
     */
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {  //��ը�¼�
        if (this.getConfig().getStringList("noBlastWorld").contains(event.getLocation().getWorld().getName())) {
            List<Block> blocks = event.blockList();
            blocks.clear();
        }
    }

    /**
     * �����ƻ��¼�
     * @param event
     */
    @EventHandler
    public void onBreakEvent(BlockBreakEvent event) {  //�����鱻�ƻ�
        if(!isHaveResidenceBuildMoney(event.getPlayer(),event.getBlock().getLocation())){
            event.setCancelled(true);
        }
    }

    /**
     * ��������¼�
     * @param event
     */
    @EventHandler
    public void onPlaceEvent (BlockPlaceEvent event) {  //�����鱻����
        if(!isHaveResidenceBuildMoney(event.getPlayer(),event.getBlock().getLocation())){
            event.setCancelled(true);
        }
    }

    /**
     * ʹ��ˮͰ�����¼�
     * @param event
     */
    @EventHandler
    public void onPlayerBucketEvent (PlayerBucketEvent event) {
        if(!isHaveResidenceBuildMoney(event.getPlayer(),event.getBlock().getLocation())){
            event.setCancelled(true);
        }
    }


}
