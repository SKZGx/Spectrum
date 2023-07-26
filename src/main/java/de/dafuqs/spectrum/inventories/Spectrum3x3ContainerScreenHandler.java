package de.dafuqs.spectrum.inventories;

import de.dafuqs.spectrum.blocks.redstone.*;
import de.dafuqs.spectrum.enums.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.*;
import org.jetbrains.annotations.*;

public class Spectrum3x3ContainerScreenHandler extends ScreenHandler {
	
	private final ProgressionStage tier;
	private final Inventory inventory;
	
	public Spectrum3x3ContainerScreenHandler(int syncId, PlayerInventory playerInventory, ProgressionStage progressionStage) {
		this(SpectrumScreenHandlerTypes.GENERIC_TIER1_3X3, syncId, playerInventory, new SimpleInventory(9), progressionStage);
	}
	
	public Spectrum3x3ContainerScreenHandler(ScreenHandlerType<Spectrum3x3ContainerScreenHandler> screenHandlerType, int syncId, PlayerInventory playerInventory, Inventory inventory, ProgressionStage progressionStage) {
		super(screenHandlerType, syncId);
		checkSize(inventory, 9);
		this.tier = progressionStage;
		this.inventory = inventory;
		inventory.onOpen(playerInventory.player);
		
		int m;
		int l;
		for (m = 0; m < 3; ++m) {
			for (l = 0; l < 3; ++l) {
				this.addSlot(new Slot(inventory, l + m * 3, 62 + l * 18, 17 + m * 18));
			}
		}
		
		for (m = 0; m < 3; ++m) {
			for (l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
			}
		}
		
		for (m = 0; m < 9; ++m) {
			this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
		}
	}
	
	@Contract("_, _ -> new")
	public static @NotNull Spectrum3x3ContainerScreenHandler createTier1(int syncId, PlayerInventory playerInventory) {
		return new Spectrum3x3ContainerScreenHandler(syncId, playerInventory, ProgressionStage.EARLYGAME);
	}
	
	@Contract("_, _, _ -> new")
	public static @NotNull ScreenHandler createTier1(int syncId, PlayerInventory playerInventory, BlockPlacerBlockEntity blockPlacerBlockEntity) {
		return new Spectrum3x3ContainerScreenHandler(SpectrumScreenHandlerTypes.GENERIC_TIER1_3X3, syncId, playerInventory, blockPlacerBlockEntity, ProgressionStage.EARLYGAME);
	}
	
	@Contract("_, _ -> new")
	public static @NotNull Spectrum3x3ContainerScreenHandler createTier2(int syncId, PlayerInventory playerInventory) {
		return new Spectrum3x3ContainerScreenHandler(syncId, playerInventory, ProgressionStage.MIDGAME);
	}
	
	@Contract("_, _, _ -> new")
	public static @NotNull ScreenHandler createTier2(int syncId, PlayerInventory playerInventory, BlockPlacerBlockEntity blockPlacerBlockEntity) {
		return new Spectrum3x3ContainerScreenHandler(SpectrumScreenHandlerTypes.GENERIC_TIER1_3X3, syncId, playerInventory, blockPlacerBlockEntity, ProgressionStage.MIDGAME);
	}
	
	@Contract("_, _ -> new")
	public static @NotNull Spectrum3x3ContainerScreenHandler createTier3(int syncId, PlayerInventory playerInventory) {
		return new Spectrum3x3ContainerScreenHandler(syncId, playerInventory, ProgressionStage.LATEGAME);
	}
	
	@Contract("_, _, _ -> new")
	public static @NotNull ScreenHandler createTier3(int syncId, PlayerInventory playerInventory, BlockPlacerBlockEntity blockPlacerBlockEntity) {
		return new Spectrum3x3ContainerScreenHandler(SpectrumScreenHandlerTypes.GENERIC_TIER1_3X3, syncId, playerInventory, blockPlacerBlockEntity, ProgressionStage.LATEGAME);
	}
	
	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}
	
	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasStack()) {
			ItemStack itemStack2 = slot.getStack();
			itemStack = itemStack2.copy();
			if (index < 9) {
				if (!this.insertItem(itemStack2, 9, 45, true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(itemStack2, 0, 9, false)) {
				return ItemStack.EMPTY;
			}
			
			if (itemStack2.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
			
			if (itemStack2.getCount() == itemStack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTakeItem(player, itemStack2);
		}
		
		return itemStack;
	}
	
	@Override
	public void close(PlayerEntity player) {
		super.close(player);
		this.inventory.onClose(player);
	}
	
	public ProgressionStage getTier() {
		return this.tier;
	}
	
}
