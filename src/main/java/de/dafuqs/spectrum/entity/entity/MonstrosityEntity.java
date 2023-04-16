package de.dafuqs.spectrum.entity.entity;

import de.dafuqs.additionalentityattributes.*;
import de.dafuqs.revelationary.api.advancements.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.sound.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.control.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import net.minecraft.sound.*;
import net.minecraft.tag.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class MonstrosityEntity extends SpectrumBossEntity implements RangedAttackMob {
    
    private static final Identifier ENTERED_DD_ADVANCEMENT_IDENTIFIER = SpectrumCommon.locate("lategame/spectrum_lategame");
    private static final Predicate<LivingEntity> SHOULD_NOT_BE_IN_DD_PLAYER_PREDICATE = (entity) -> {
        if (entity instanceof PlayerEntity player) {
            return !AdvancementHelper.hasAdvancement(player, ENTERED_DD_ADVANCEMENT_IDENTIFIER);
        }
        return false;
    };
    
    private static final float MAX_LIFE_LOST_PER_TICK = 20;
    private static final float GET_STRONGER_EVERY_X_TICKS = 400;
    private float previousHealth;
    
    public MonstrosityEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, false);
        this.experiencePoints = 500;
        this.noClip = true;
        this.ignoreCameraFrustum = true;
        this.previousHealth = getHealth();
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new ProjectileAttackGoal(this, 1.0, 40, 20.0F));
        this.goalSelector.add(5, new FlyGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
        
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, LivingEntity.class, 0, false, false, SHOULD_NOT_BE_IN_DD_PLAYER_PREDICATE));
        this.targetSelector.add(2, new RevengeGoal(this));
    }
    
    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (world.isClient()) {
            MonstrositySoundInstance.startSoundInstance(this);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }
    
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }
    
    @Override
    public void tick() {
        super.tick();
        
        float currentHealth = this.getHealth();
        if (currentHealth < this.previousHealth - MAX_LIFE_LOST_PER_TICK) {
            this.setHealth(this.previousHealth - MAX_LIFE_LOST_PER_TICK);
        }
        this.previousHealth = currentHealth;
        
        if (this.age % GET_STRONGER_EVERY_X_TICKS == 0) {
            this.growStronger(1);
        }
    }
    
    public void growStronger(int amount) {
        //this.getAttributes().addTemporaryModifiers(itemStack2.getAttributeModifiers(equipmentSlot));
    }
    
    @Override
    public void kill() {
        if (this.previousHealth > this.getMaxHealth() / 4) {
            // naha, I do not feel like doing that
            this.setHealth(this.getHealth() + this.getMaxHealth() / 2);
            this.growStronger(8);
            this.playSound(getHurtSound(DamageSource.OUT_OF_WORLD), 2.0F, 1.5F);
        } else {
            this.remove(RemovalReason.KILLED);
            this.emitGameEvent(GameEvent.ENTITY_DIE);
        }
    }
    
    public static DefaultAttributeContainer createMonstrosityAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 800.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0)
                .add(EntityAttributes.GENERIC_ARMOR, 12.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 4.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0)
                .add(AdditionalEntityAttributes.MAGIC_PROTECTION, 2.0)
                .build();
    }
    
    private void damageLivingEntities(List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                entity.damage(DamageSource.mob(this), 10.0F);
                this.applyDamageEffects(this, entity);
            }
        }
    }
    
    private boolean destroyBlocks(Box box) {
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.floor(box.minY);
        int k = MathHelper.floor(box.minZ);
        int l = MathHelper.floor(box.maxX);
        int m = MathHelper.floor(box.maxY);
        int n = MathHelper.floor(box.maxZ);
        boolean bl = false;
        boolean bl2 = false;
        
        for (int o = i; o <= l; ++o) {
            for (int p = j; p <= m; ++p) {
                for (int q = k; q <= n; ++q) {
                    BlockPos blockPos = new BlockPos(o, p, q);
                    BlockState blockState = this.world.getBlockState(blockPos);
                    if (!blockState.isAir() && !blockState.isIn(BlockTags.DRAGON_TRANSPARENT)) {
                        if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) && !blockState.isIn(BlockTags.DRAGON_IMMUNE)) {
                            bl2 = this.world.removeBlock(blockPos, false) || bl2;
                        } else {
                            bl = true;
                        }
                    }
                }
            }
        }
        
        if (bl2) {
            BlockPos blockPos2 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(m - j + 1), k + this.random.nextInt(n - k + 1));
            this.world.syncWorldEvent(2008, blockPos2, 0);
        }
        
        return bl;
    }
    
    @Override
    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }
    
    @Override
    public boolean canHit() {
        return false;
    }
    
    @Override
    protected Text getDefaultName() {
        return Text.literal("§kLivingNightmare");
    }
    
    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ENDER_DRAGON_HURT;
    }
    
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }
    
    @Override
    public void attack(LivingEntity target, float pullProgress) {
    
    }
}
