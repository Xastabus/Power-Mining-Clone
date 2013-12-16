/*
 * This piece of software is part of the PowerMining Bukkit Plugin
 * Author: BloodyShade (dev.bukkit.org/profiles/bloodyshade)
 *
 * Licensed under the LGPL v3
 * Further information please refer to the included lgpl-3.0.txt or the gnu website (http://www.gnu.org/licenses/lgpl)
 */

/*
 * This class is responsible for cancelling the crafting in case the user does not have permission
 */

package org.bitbucket.bloodyshade.listeners;

import java.util.List;

import org.bitbucket.bloodyshade.PowerMining;
import org.bitbucket.bloodyshade.crafting.CraftItemExcavator;
import org.bitbucket.bloodyshade.crafting.CraftItemHammer;
import org.bitbucket.bloodyshade.lib.Reference;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItemListener implements Listener {
	PowerMining plugin;

	public CraftItemListener(PowerMining plugin) {
		this.plugin = plugin;

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void canCraft(CraftItemEvent event) {
		boolean canCraft = false;

		HumanEntity player = event.getWhoClicked();
		ItemStack resultItem = event.getRecipe().getResult();
		List<String> lore = resultItem.getItemMeta().getLore();

		// If the item has no lore, it can't be one of the power tools
		if (lore == null)
			return;

		if (Reference.PICKAXES.contains(resultItem.getType()) || Reference.SPADES.contains(resultItem.getType())) {
			if (lore.contains(CraftItemHammer.loreString) || lore.contains(CraftItemExcavator.loreString)) {
				switch (resultItem.getType()) {
					case WOOD_PICKAXE:
						if (player.hasPermission("powermining.craft.hammer.wood"))
							canCraft = true;

						break;
					case STONE_PICKAXE:
						if (player.hasPermission("powermining.craft.hammer.stone"))
							canCraft = true;

						break;
					case IRON_PICKAXE:
						if (player.hasPermission("powermining.craft.hammer.iron"))
							canCraft = true;

						break;
					case GOLD_PICKAXE:
						if (player.hasPermission("powermining.craft.hammer.gold"))
							canCraft = true;

						break;
					case DIAMOND_PICKAXE:
						if (player.hasPermission("powermining.craft.hammer.diamond"))
							canCraft = true;

						break;
					case WOOD_SPADE:
						if (player.hasPermission("powermining.craft.excavator.wood"))
							canCraft = true;

						break;
					case STONE_SPADE:
						if (player.hasPermission("powermining.craft.excavator.stone"))
							canCraft = true;

						break;
					case IRON_SPADE:
						if (player.hasPermission("powermining.craft.excavator.iron"))
							canCraft = true;

						break;
					case GOLD_SPADE:
						if (player.hasPermission("powermining.craft.excavator.gold"))
							canCraft = true;

						break;
					case DIAMOND_SPADE:
						if (player.hasPermission("powermining.craft.excavator.diamond"))
							canCraft = true;

						break;
					default:
						break;
				}
			}
		}

		if (!canCraft)
			event.setCancelled(true);
	}
}
