package de.dafuqs.spectrum.entity.entity;

import de.dafuqs.spectrum.entity.*;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.entity.*;
import net.minecraft.entity.decoration.*;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class PhantomGlowFrameEntity extends PhantomFrameEntity {
	
	public PhantomGlowFrameEntity(EntityType<? extends ItemFrameEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public PhantomGlowFrameEntity(World world, BlockPos pos, Direction facing) {
		this(SpectrumEntityTypes.GLOW_PHANTOM_FRAME, world, pos, facing);
	}
	
	public PhantomGlowFrameEntity(EntityType<? extends ItemFrameEntity> type, World world, BlockPos pos, Direction facing) {
		super(type, world, pos, facing);
	}
	
	@Override
	protected ItemStack getAsItemStack() {
		return new ItemStack(SpectrumItems.GLOW_PHANTOM_FRAME);
	}
	
	@Override
	public SoundEvent getRemoveItemSound() {
		return SoundEvents.ENTITY_GLOW_ITEM_FRAME_REMOVE_ITEM;
	}
	
	@Override
	public SoundEvent getBreakSound() {
		return SoundEvents.ENTITY_GLOW_ITEM_FRAME_BREAK;
	}
	
	@Override
	public SoundEvent getPlaceSound() {
		return SoundEvents.ENTITY_GLOW_ITEM_FRAME_PLACE;
	}
	
	@Override
	public SoundEvent getAddItemSound() {
		return SoundEvents.ENTITY_GLOW_ITEM_FRAME_ADD_ITEM;
	}
	
	@Override
	public SoundEvent getRotateItemSound() {
		return SoundEvents.ENTITY_GLOW_ITEM_FRAME_ROTATE_ITEM;
	}
	
	@Override
	public boolean shouldRenderAtMaxLight() {
		return !isRedstonePowered();
	}
	
}
