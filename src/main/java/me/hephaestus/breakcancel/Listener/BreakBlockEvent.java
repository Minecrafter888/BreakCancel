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
    //gets the config.yml file
    Plugin plugin = BreakCancel.getPlugin(BreakCancel.class);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        /*
        * These following two lines are shortcuts you can call within the class to use
        * These shortcuts can also be expanded upon in most cases
         */
        String blockName = event.getBlock().getType().toString();
        List<String> protectedBlocks = plugin.getConfig().getStringList("InvincibleBlocks");

        // Check if the block being broken is in the list of protected blocks
        if (protectedBlocks.contains(blockName)) {
            event.setCancelled(true); // Cancel the block break event
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        List<Block> blockList = event.blockList();
        List<String> protectedBlocks = plugin.getConfig().getStringList("InvincibleBlocks");

        // Remove the protected blocks from the list of affected blocks by explosion
        blockList.removeIf(block -> protectedBlocks.contains(block.getType().toString()));

        // Iterate over the remaining blocks after removal
        for (Block block : blockList) {
            if (protectedBlocks.contains(block.getType().toString())) {
                event.setYield(0); // Prevent block drops from the explosion
                block.setType(Material.AIR); // Reset the block to air to prevent destruction
            }
        }
    }
}
