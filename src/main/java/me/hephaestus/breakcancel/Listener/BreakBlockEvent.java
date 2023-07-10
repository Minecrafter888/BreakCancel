package me.hephaestus.breakcancel.Listener;

import me.hephaestus.breakcancel.BreakCancel;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class BreakBlockEvent implements Listener {
    Plugin plugin = BreakCancel.getPlugin(BreakCancel.class);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        String blockName = event.getBlock().getType().toString();
        List<String> protectedBlocks = plugin.getConfig().getStringList("InvincibleBlocks");

        if (protectedBlocks.contains(blockName)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        List<Block> blockList = event.blockList();
        List<String> protectedBlocks = plugin.getConfig().getStringList("InvincibleBlocks");

        blockList.removeIf(block -> protectedBlocks.contains(block.getType().toString()));

        for (Block block : blockList) {
            if (protectedBlocks.contains(block.getType().toString())) {
                event.setYield(0); // Prevent block drops
                block.setType(Material.AIR); // Reset block to air to prevent destruction
            }
        }
    }
}
