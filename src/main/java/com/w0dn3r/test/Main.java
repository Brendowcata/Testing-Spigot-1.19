package com.w0dn3r.test;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Main extends JavaPlugin  implements Listener {

    private BossBar bossBar;
    @Override
    public void onEnable() {



//        Player player = null;
//        player.getWorld().setTime(6000);

        bossBar = Bukkit.createBossBar("§c§lw0dn3r's craft!", BarColor.PINK, BarStyle.SEGMENTED_6);
        Bukkit.getPluginManager().registerEvents(this, this);

        bossBar.setProgress(1);


//        Bukkit.getWorld("world").getBlockAt(1, 1, 1).setType(Material.LAVA);


        getConfig().options().copyDefaults();
        saveDefaultConfig();

//        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("config").setExecutor(new ConfigCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("book").setExecutor(new BookCommand());
        getCommand("punish").setExecutor(new PunishCommand());
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3));

//        for(PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
//            e.getPlayer().removePotionEffect(effect.getType());
//        }

        bossBar.addPlayer(e.getPlayer());

        e.getPlayer().setPlayerListHeaderFooter("§cw0dn3r's craft!", "First Line\nSecond Line");

//        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
//                TextComponent.fromLegacyText("§4§lHELLO MY FRIEND!"));

        e.getPlayer().sendTitle("§cBem Vindo!",
                "§aAproveite o servidor!",
                20, 100, 20); //Titulo apresentado ao entrar no jogo

//        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
//        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
//        helmetMeta.setColor(Color.RED);
//        helmet.setItemMeta(helmetMeta);
//
//        e.getPlayer().getInventory().addItem(helmet);
//
//        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
//        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
//        chestplateMeta.setColor(Color.RED);
//        chestplate.setItemMeta(chestplateMeta);
//
//        e.getPlayer().getInventory().addItem(chestplate);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        Firework firework = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
        FireworkMeta meta = (FireworkMeta) firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.RED)
                .withColor(Color.BLUE).with(FireworkEffect.Type.CREEPER)
                .withFlicker().build());
        meta.setPower(1);
        firework.setFireworkMeta(meta);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.hasItem()) {
            Player player = e.getPlayer();
            if (e.getItem().getType().equals(Material.DIAMOND_HOE)) {
                player.launchProjectile(Snowball.class);
                player.sendMessage("§cYou have shot the snowball!");
            } else if (e.getItem().getType().equals(Material.IRON_HOE)) {
                player.launchProjectile(Egg.class);
                player.sendMessage("§cYou have shot the egg!");
            }
        }

    }



}
