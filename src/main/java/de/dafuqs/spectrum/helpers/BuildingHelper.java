package de.dafuqs.spectrum.helpers;

import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tag.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import oshi.util.tuples.*;

import java.util.*;

public class BuildingHelper {
	
	private static final Map<TagKey<Block>, Block> SIMILAR_BLOCKS = new HashMap<>() {{
		put(BlockTags.DIRT, Blocks.GRASS_BLOCK);
		put(BlockTags.NYLIUM, Blocks.NETHERRACK);
	}};
	
	private static final ArrayList<Vec3i> NEIGHBOR_VECTORS_Y = new ArrayList<>() {{
		add(Direction.NORTH.getVector());
		add(Direction.EAST.getVector());
		add(Direction.SOUTH.getVector());
		add(Direction.WEST.getVector());
		add(Direction.WEST.getVector().offset(Direction.NORTH));
		add(Direction.NORTH.getVector().offset(Direction.EAST));
		add(Direction.EAST.getVector().offset(Direction.SOUTH));
		add(Direction.SOUTH.getVector().offset(Direction.WEST));
	}};
	
	public static Triplet<Block, Item, Integer> getBuildingItemCountInInventoryIncludingSimilars(PlayerEntity player, Block block, int maxCount) {
		Item blockItem = block.asItem();
		if (blockItem instanceof AliasedBlockItem aliasedBlockItem) {
			// do not process seeds and similar stuff
			// otherwise players could place fully grown crops
			return new Triplet<>(block, aliasedBlockItem, 0);
		} else {
			int count = player.getInventory().count(block.asItem());
			if (count == 0) {
				if (SIMILAR_BLOCKS.containsKey(block)) {
					Block similarBlock = SIMILAR_BLOCKS.get(block);
					Item similarBlockItem = similarBlock.asItem();
					int similarCount = player.getInventory().count(similarBlockItem);
					if (similarCount > 0) {
						return new Triplet<>(similarBlock, similarBlockItem, Math.min(similarCount, maxCount));
					}
				}
			}
			return new Triplet<>(block, blockItem, Math.min(count, maxCount));
		}
	}
	
	/**
	 * A simple implementation of a breadth first search
	 */
	public static @NotNull List<BlockPos> getConnectedBlocks(@NotNull World world, @NotNull BlockPos blockPos, long maxCount, int maxRange) {
		BlockState originState = world.getBlockState(blockPos);
		Block originBlock = originState.getBlock();
		
		ArrayList<BlockPos> connectedPositions = new ArrayList<>();
		ArrayList<BlockPos> visitedPositions = new ArrayList<>();
		Queue<BlockPos> positionsToVisit = new LinkedList<>();
		
		connectedPositions.add(blockPos);
		visitedPositions.add(blockPos);
		positionsToVisit.add(blockPos);
		while (connectedPositions.size() < maxCount) {
			BlockPos currentPos = positionsToVisit.poll();
			if (currentPos == null) {
				break;
			} else {
				for (Direction direction : Direction.values()) {
					BlockPos offsetPos = currentPos.offset(direction);
					if (!visitedPositions.contains(offsetPos)) {
						visitedPositions.add(offsetPos);
						if (blockPos.isWithinDistance(offsetPos, maxRange)) {
							Block localBlock = world.getBlockState(offsetPos).getBlock();
							if (localBlock.equals(originBlock) || SIMILAR_BLOCKS.containsKey(localBlock) && SIMILAR_BLOCKS.get(localBlock).equals(originBlock)) {
								positionsToVisit.add(offsetPos);
								connectedPositions.add(offsetPos);
								if (connectedPositions.size() >= maxCount) {
									break;
								}
							}
						}
					}
				}
			}
		}
		
		return connectedPositions;
	}
	
	public static @NotNull List<BlockPos> calculateBuildingStaffSelection(@NotNull World world, @NotNull BlockPos originPos, Direction direction, long maxCount, int maxRange, boolean sameBlockOnly) {
		BlockPos offsetPos = originPos.offset(direction);
		BlockState originState = world.getBlockState(originPos);
		
		List<BlockPos> selectedPositions = new ArrayList<>();
		int count = 1;
		
		List<BlockPos> storedNeighbors = new ArrayList<>();
		if (world.canPlace(originState, offsetPos, ShapeContext.absent())) {
			storedNeighbors.add(offsetPos);
		}
		
		while (count < maxCount && !storedNeighbors.isEmpty()) {
			selectedPositions.addAll(storedNeighbors);
			List<BlockPos> newNeighbors = new ArrayList<>();
			
			for (BlockPos neighbor : storedNeighbors) {
				List<BlockPos> facingNeighbors = getValidNeighbors(world, neighbor, direction, originState, sameBlockOnly);
				
				for (BlockPos facingNeighbor : facingNeighbors) {
					if (count < maxCount && originPos.isWithinDistance(facingNeighbor, maxRange)) {
						if (!selectedPositions.contains(facingNeighbor) && !storedNeighbors.contains(facingNeighbor) && !newNeighbors.contains(facingNeighbor)) {
							newNeighbors.add(facingNeighbor);
							count++;
						}
					}
				}
			}
			storedNeighbors.clear();
			storedNeighbors.addAll(newNeighbors);
		}
		selectedPositions.addAll(storedNeighbors);
		return selectedPositions;
	}
	
	private static @NotNull List<BlockPos> getValidNeighbors(World world, BlockPos startPos, Direction facingDirection, BlockState originState, boolean sameBlockOnly) {
		List<BlockPos> foundNeighbors = new ArrayList<>();
		for (Vec3i neighborVectors : getNeighborVectors(facingDirection)) {
			BlockPos targetPos = startPos.add(neighborVectors);
			BlockState targetState = world.getBlockState(targetPos);
			BlockState facingAgainstState = world.getBlockState(targetPos.offset(facingDirection.getOpposite()));
			
			if ((targetState.getMaterial().isReplaceable() || !targetState.getFluidState().isEmpty()) && world.canPlace(originState, targetPos, ShapeContext.absent())) {
				if (sameBlockOnly) {
					if (facingAgainstState.equals(originState) || (SIMILAR_BLOCKS.containsKey(facingAgainstState.getBlock()) && SIMILAR_BLOCKS.get(facingAgainstState.getBlock()) == originState.getBlock())) {
						foundNeighbors.add(targetPos);
					}
				} else {
					if (!facingAgainstState.isAir()) {
						foundNeighbors.add(targetPos);
					}
				}
			}
		}
		
		return foundNeighbors;
	}
	
	private static @NotNull List<Vec3i> getNeighborVectors(@NotNull Direction direction) {
		if (direction.getAxis() == Direction.Axis.Y) {
			return NEIGHBOR_VECTORS_Y;
		} else {
			return new ArrayList<>() {{
				add(direction.rotateYClockwise().getVector());
				add(direction.rotateYCounterclockwise().getVector());
				add(Direction.UP.getVector());
				add(Direction.DOWN.getVector());
			}};
		}
	}
}