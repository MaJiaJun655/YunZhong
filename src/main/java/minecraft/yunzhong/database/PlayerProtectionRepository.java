package minecraft.yunzhong.database;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static minecraft.yunzhong.database.dataCache.protectedPlayers;

public class PlayerProtectionRepository {

    private final JavaPlugin plugin;
    private Connection connection;



    public PlayerProtectionRepository(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // =========================
    // 初始化入口
    // =========================
    public void init() {
        connect();
        createTable();
        loadCache();
    }

    // =========================
    // 连接 SQLite
    // =========================
    private void connect() {

        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }

            File dbFile = new File(plugin.getDataFolder(), "data.db");

            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + dbFile.getAbsolutePath()
            );

            plugin.getLogger().info("SQLite连接成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 创建表
    // =========================
    private void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS PlayerProtected (
                    PLAYER_NAME TEXT PRIMARY KEY,
                    ON_OFF INTEGER NOT NULL DEFAULT 0
                )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 启动加载缓存
    // =========================
    private void loadCache() {

        String sql = "SELECT PLAYER_NAME FROM PlayerProtected WHERE ON_OFF = 1";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String name = rs.getString("PLAYER_NAME").toLowerCase();
                protectedPlayers.add(name);
            }

            plugin.getLogger().info("加载保护玩家: " + protectedPlayers.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 设置状态（核心方法）
    // =========================
    public void setProtected(Player player, boolean enabled) {

        String name = player.getName().toLowerCase();

        if (enabled) {
            protectedPlayers.add(name);
        } else {
            protectedPlayers.remove(name);
        }

        String sql = """
                INSERT OR REPLACE INTO PlayerProtected
                (PLAYER_NAME, ON_OFF)
                VALUES (?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, enabled ? 1 : 0);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 查询状态（给事件层调用）
    // =========================
    public boolean isProtected(String playerName) {
        return protectedPlayers.contains(playerName.toLowerCase());
    }

    // =========================
    // 是否存在记录
    // =========================
    public boolean exists(String playerName) {

        String sql = """
                SELECT 1 FROM PlayerProtected
                WHERE PLAYER_NAME = ?
                LIMIT 1
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, playerName.toLowerCase());

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // 关闭数据库
    // =========================
    public void close() {

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        protectedPlayers.clear();
    }
}