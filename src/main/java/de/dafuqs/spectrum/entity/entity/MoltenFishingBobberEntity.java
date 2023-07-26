package de.dafuqs.spectrum.entity.entity;

import de.dafuqs.spectrum.blocks.mob_blocks.*;
import de.dafuqs.spectrum.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class MoltenFishingBobberEntity extends SpectrumFishingBobberEntity {
	
	public MoltenFishingBobberEntity(EntityType<? extends SpectrumFishingBobberEntity> type, World world, int luckOfTheSeaLevel, int lureLevel, int exuberanceLevel, int bigCatchLevel, boolean inventoryInsertion) {
		super(type, world, luckOfTheSeaLevel, lureLevel, exuberanceLevel, bigCatchLevel, inventoryInsertion, true);
	}
	
	public MoltenFishingBobberEntity(EntityType<? extends SpectrumFishingBobberEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!world.isClient && age % 20 == 0 && isOnGround()) {
			FirestarterMobBlock.causeFire((ServerWorld) world, getBlockPos(), Direction.DOWN);
		}
	}
	
	public MoltenFishingBobberEntity(PlayerEntity thrower, World world, int luckOfTheSeaLevel, int lureLevel, int exuberanceLevel, int bigCatchLevel, boolean inventoryInsertion) {
		super(SpectrumEntityTypes.MOLTEN_FISHING_BOBBER, thrower, world, luckOfTheSeaLevel, lureLevel, exuberanceLevel, bigCatchLevel, inventoryInsertion, true);
	}
	
	@Override
	public void hookedEntityTick(Entity hookedEntity) {
		hookedEntity.setOnFireFor(2);
	}
	
}
