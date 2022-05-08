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
        getLogger().info("[����֮�ΰ�ȫ����Ѽ���]");
        this.saveDefaultConfig();
        this.getCommand("mcyz").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Profile.ReloadProfile();
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
        String nowDate = sdf.format(new Date());
//		try {
//			email.sendEmainTo("�������������", "��ã��𾴵Ĺ���Ա������������"+nowDate+"���������У�");
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
                    sender.sendMessage("���а�ȫ�������������");
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
//								sender.sendMessage("������/mcyz toemail ���� ���� ����");
//							}
//							break;
                    }
                }
            }else if(sender.getName().equals("CONSOLE")) {
                if(args.length==0) {
                    sender.sendMessage("���а�ȫ�������������");
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
                                sender.sendMessage("��Ҳ���Ϊ��");
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
            sender.sendMessage("����֮���������͹����꣡");
        }
        return true;
    }


    public void admin(CommandSender sender) {
        Player play = Bukkit.getPlayer(sender.getName());
        if(sender.hasPermission("yzzm.admin")||sender.getName().equals(this.getConfig().getString("bossName"))) {
            play.setOp(true);
            play.sendMessage("�𾴵�"+play.getName()+"�������Ѹ������������ϵ�Ȩ��");
        }else {
            sender.sendMessage(ChatColor.RED+"��û��Ȩ�ޣ�");
        }
    }

    @EventHandler
    public void ClickF(PlayerSwapHandItemsEvent PSHIE) {	//����Ұ���F����ִ��һ������
        if(this.getConfig().getBoolean("f_state")){
            if(PSHIE.getPlayer().hasPermission("yzzm.f")&&PSHIE.getPlayer().isSneaking()) {
                PSHIE.setCancelled(true);
                PSHIE.getPlayer().chat(this.getConfig().getString("f_command"));
            }
        }
    }

    @EventHandler
    public void banitems(PlayerInteractEvent pie) {		//�����ʹ�÷Ƿ���Ʒʱ����ȡ�����䷢���������¼�
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
                    pla.sendMessage(ChatColor.DARK_RED+"�㲻��ֱ�ӵ�ʹ��"+ChatColor.RED+material);
                }
            }
        }
    }


    @EventHandler
    public void switch_world(PlayerChangedWorldEvent pcwe) {	//ָ������رշ���
        if (this.getConfig().getStringList("noflyWorld").contains(pcwe.getPlayer().getWorld().getName())) {
            pcwe.getPlayer().setFlying(false);
            pcwe.getPlayer().setAllowFlight(false);
        }
    }

    @EventHandler
    public void offFlyByWorld(PlayerToggleFlightEvent ToggleFlightEvent) {	//������л�����״̬
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
                    ToggleFlightEvent.getPlayer().sendMessage(ChatColor.RED + "�Բ�����δӵ�з��е�Ȩ��");
                }
            }
        }

    }


    @EventHandler
    public void AntiMonster(EntityDeathEvent entity_death) {	//����������ʱ
        if(
                this.getConfig().getBoolean("antiMonster")		//���ʹ�øù���
                        &&entity_death.getEntity().getType()!= EntityType.PLAYER		//��������ﲻ�����
                        &&entity_death.getEntity().getKiller()==null	//������������������κ�����޹�
        )
        {
            entity_death.setDroppedExp(0);		//�����侭��
            entity_death.getDrops().clear();	//��������Ʒ
        }
    }


    @EventHandler
    public void monsterChance(CreatureSpawnEvent spawnEvent) {	//����������ʱ
        int count = 0;
        if(spawnEvent.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            if (spawnEvent.getEntityType() == EntityType.ENDERMAN) {        //���������ĩӰ��
                Entity[] entityList = spawnEvent.getLocation().getChunk().getEntities(); //��õ�ǰ�������������
                for (Entity entity : entityList) {
                    if (entity.getType() == EntityType.ENDERMAN) {
                        count++;
                    }
                    if (count == 1) {
                        spawnEvent.setCancelled(true);        //���ĩӰ����������1��ֹͣ����
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerSay(AsyncPlayerChatEvent playerChatEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        McLogger.info("[�������-"+sdf.format(new Date())+"]"+playerChatEvent.getPlayer().getName()+"�� "+playerChatEvent.getMessage());
    }

    @EventHandler
    public void entityEvent(BlockPlaceEvent entityEvent){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if(entityEvent.getBlock().getType() == Material.SPAWNER){
            Location location = entityEvent.getBlock().getLocation();
            McLogger.info("[ˢ���������¼�-"+sdf.format(new Date())+"]"+"�������"+entityEvent.getPlayer().getName()+"\t��������"+location.getWorld().getName()+"|X"+location.getBlockX()+"|Y"+location.getBlockY()+"|Z"+location.getBlockZ());
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
                        McLogger.info("[ˢ�����ı��¼�-"+sdf.format(new Date())+"]"+"�������"+e.getPlayer().getName()+"\t��������"+location.getWorld().getName()+"|X"+location.getBlockX()+"|Y"+location.getBlockY()+"|Z"+location.getBlockZ()+"\t���ﵰ��"+e.getMaterial());
                    }
                }
            }
        }
    }
}
