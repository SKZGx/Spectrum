package de.dafuqs.spectrum.entity.entity;

import de.dafuqs.spectrum.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

public class BedrockFishingBobberEntity extends SpectrumFishingBobberEntity {
	
	public BedrockFishingBobberEntity(EntityType<? extends SpectrumFishingBobberEntity> type, World world, int luckOfTheSeaLevel, int lureLevel, int exuberanceLevel, int bigCatchLevel, boolean inventoryInsertion, boolean foundry) {
		super(type, world, luckOfTheSeaLevel, lureLevel, exuberanceLevel, bigCatchLevel, inventoryInsertion, foundry);
	}
	
	public BedrockFishingBobberEntity(EntityType<? extends SpectrumFishingBobberEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public BedrockFishingBobberEntity(PlayerEntity thrower, World world, int luckOfTheSeaLevel, int lureLevel, int exuberanceLevel, int bigCatchLevel, boolean inventoryInsertion, boolean foundry) {
		super(SpectrumEntityTypes.BEDROCK_FISHING_BOBBER, thrower, world, luckOfTheSeaLevel, lureLevel, exuberanceLevel, bigCatchLevel, inventoryInsertion, foundry);
	}
	
	
}
