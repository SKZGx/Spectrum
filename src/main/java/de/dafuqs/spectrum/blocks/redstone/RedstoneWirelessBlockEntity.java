package de.dafuqs.spectrum.blocks.redstone;

import de.dafuqs.spectrum.events.*;
import de.dafuqs.spectrum.events.listeners.*;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;
import net.minecraft.world.event.listener.*;
import org.jetbrains.annotations.*;

public class RedstoneWirelessBlockEntity extends BlockEntity implements WirelessRedstoneSignalEventQueue.Callback<WirelessRedstoneSignalEventQueue.EventEntry> {
	
	private static final int RANGE = 16;
	private final WirelessRedstoneSignalEventQueue listener;
	private int cachedSignal;
	private int currentSignal;
	
	public RedstoneWirelessBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SpectrumBlockEntities.REDSTONE_WIRELESS, blockPos, blockState);
		this.listener = new WirelessRedstoneSignalEventQueue(new BlockPositionSource(this.pos), RANGE, this);
	}
	
	private static boolean isSender(World world, BlockPos blockPos) {
		if (world == null) {
			return false;
		}
		return world.getBlockState(blockPos).get(RedstoneWirelessBlock.SENDER);
	}
	
	public static void serverTick(@NotNull World world, BlockPos pos, BlockState state, @NotNull RedstoneWirelessBlockEntity blockEntity) {
		if (isSender(world, pos)) {
			if (blockEntity.currentSignal != blockEntity.cachedSignal) {
				blockEntity.currentSignal = blockEntity.cachedSignal;
				blockEntity.world.emitGameEvent(null, SpectrumGameEvents.WIRELESS_REDSTONE_SIGNALS.get(state.get(RedstoneWirelessBlock.CHANNEL)).get(blockEntity.currentSignal), blockEntity.getPos());
			}
		} else {
			blockEntity.listener.tick(world);
		}
	}
	
	public static DyeColor getChannel(World world, BlockPos pos) {
		if (world == null) {
			return DyeColor.RED;
		}
		return world.getBlockState(pos).get(RedstoneWirelessBlock.CHANNEL);
	}
	
	@Override
	public void writeNbt(NbtCompound tag) {
		super.writeNbt(tag);
		tag.putInt("signal", this.currentSignal);
		tag.putInt("cached_signal", this.cachedSignal);
	}
	
	@Override
	public void readNbt(NbtCompound tag) {
		super.readNbt(tag);
		this.currentSignal = tag.getInt("output_signal");
		this.cachedSignal = tag.getInt("cached_signal");
	}
	
	public @Nullable WirelessRedstoneSignalEventQueue getEventListener() {
		return this.listener;
	}
	
	public int getRange() {
		return RANGE;
	}
	
	@Override
	public boolean canAcceptEvent(World world, GameEventListener listener, GameEvent.Message message, Vec3d sourcePos) {
		return !this.isRemoved()
				&& message.getEvent() instanceof RedstoneTransferGameEvent redstoneTransferGameEvent
				&& !isSender(this.world, this.pos)
				&& redstoneTransferGameEvent.getDyeColor() == getChannel(this.world, this.pos);
	}
	
	@Override
	public void triggerEvent(World world, GameEventListener listener, WirelessRedstoneSignalEventQueue.EventEntry redstoneEvent) {
		if (!isSender(this.world, this.pos) && redstoneEvent.gameEvent.getDyeColor() == getChannel(this.world, this.pos)) {
			int receivedSignal = redstoneEvent.gameEvent.getPower();
			this.currentSignal = receivedSignal;
			// trigger a block update in all cases, even when powered does not change. That way connected blocks
			// can react on the strength change of the block, since we store the power in the block entity, not the block state
			if (receivedSignal == 0) {
				world.setBlockState(pos, world.getBlockState(pos).with(RedstoneWirelessBlock.POWERED, false), Block.NOTIFY_LISTENERS);
			} else {
				world.setBlockState(pos, world.getBlockState(pos).with(RedstoneWirelessBlock.POWERED, true), Block.NOTIFY_LISTENERS);
			}
			world.updateNeighbors(pos, SpectrumBlocks.REDSTONE_WIRELESS);
		}
	}
	
	// since redstone is weird we have to cache a new signal or so
	// if we would start a game event right here it could be triggered
	// multiple times a tick (because neighboring redstone updates > 1/tick)
	// and therefore receivers receiving a wrong (because old) signal
	public void setSignalStrength(int newSignal) {
		if (isSender(this.world, this.pos)) {
			this.cachedSignal = newSignal;
		} else {
			this.currentSignal = newSignal;
		}
	}
	
	public int getCurrentSignal() {
		if (isSender(this.world, this.pos)) {
			return 0;
		}
		return this.currentSignal;
	}
	
	public int getCurrentSignalStrength() {
		return this.currentSignal;
	}
	
}
