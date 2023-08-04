package de.dafuqs.spectrum.entity.models;

import com.google.common.collect.*;
import de.dafuqs.spectrum.entity.entity.*;
import net.fabricmc.api.*;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.util.math.*;

@Environment(EnvType.CLIENT)
public class LizardEntityModel extends AnimalModel<LizardEntity> {
	
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart tail;
	private final ModelPart lower_tail;
	private final ModelPart maw;
	
	public LizardEntityModel(ModelPart root) {
		super(true, 8.0F, 3.35F);
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.head = root.getChild(EntityModelPartNames.HEAD);
		this.rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
		this.tail = root.getChild(EntityModelPartNames.TAIL);
		this.lower_tail = tail.getChild("lower_tail");
		this.maw = head.getChild("maw");
	}
	
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(35, 9).cuboid(-3.0F, -2.5F, -5.0F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F))
				.uv(28, 35).cuboid(-3.0F, -1.5F, -9.0F, 6.0F, 2.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 14).cuboid(3.0F, 0.5F, -9.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(24, 31).cuboid(-3.0F, 0.5F, -9.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
				.uv(11, 2).cuboid(-3.0F, 0.5F, -9.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.5F, -8.0F));
		
		ModelPartData horns_r1 = head.addChild("horns_r1", ModelPartBuilder.create().uv(0, 35).cuboid(-7.0F, -6.0F, 0.0F, 14.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 0.0F, -0.8727F, 0.0F, 0.0F));
		
		ModelPartData head_frills_left_r1 = head.addChild("head_frills_left_r1", ModelPartBuilder.create().uv(10, 11).cuboid(1.0F, -2.5F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -1.0F, -1.0F, 0.0F, 0.5236F, 0.0F));
		
		ModelPartData head_frills_right_r1 = head.addChild("head_frills_right_r1", ModelPartBuilder.create().uv(10, 7).cuboid(-1.0F, -2.5F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, -1.0F, 0.0F, -0.5236F, 0.0F));
		
		ModelPartData maw = head.addChild("maw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		
		ModelPartData lower_teeth_left_r1 = maw.addChild("lower_teeth_left_r1", ModelPartBuilder.create().uv(0, 1).cuboid(2.75F, -0.5F, -8.75F, 0.0F, 1.0F, 9.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.75F, -0.5F, -8.75F, 0.0F, 1.0F, 9.0F, new Dilation(0.0F))
				.uv(24, 32).cuboid(-2.75F, -0.5F, -8.75F, 5.5F, 1.0F, 0.0F, new Dilation(0.0F))
				.uv(27, 25).cuboid(-3.0F, 0.5F, -9.0F, 6.0F, 1.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -9.0F, -8.0F, 8.0F, 6.0F, 19.0F, new Dilation(0.0F))
				.uv(0, 7).cuboid(0.0F, -13.0F, -7.0F, 0.0F, 6.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 25.0F, 0.0F));
		
		ModelPartData left_front_leg = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(0, 11).cuboid(4.0F, -5.0F, -6.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 33).cuboid(4.0F, -1.0F, -7.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 25.0F, 0.0F));
		
		ModelPartData left_front_leg_frills_r1 = left_front_leg.addChild("left_front_leg_frills_r1", ModelPartBuilder.create().uv(10, 42).cuboid(0.5F, -2.5F, -1.5F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -2.5F, -3.5F, 0.0F, 0.3491F, 0.0F));
		
		ModelPartData right_front_leg = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(28, 41).cuboid(-6.0F, -5.0F, -6.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-6.0F, -1.0F, -7.0F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 25.0F, 0.0F));
		
		ModelPartData right_front_leg_frills_r1 = right_front_leg.addChild("right_front_leg_frills_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.75F, -2.5F, -1.5F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-5.75F, -2.5F, -3.5F, 0.0F, -0.3491F, 0.0F));
		
		ModelPartData left_hind_leg = modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 2).cuboid(-2.0F, 1.5F, -3.5F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 45).cuboid(-2.0F, -2.5F, -2.5F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 22.5F, 7.5F));
		
		ModelPartData left_front_leg_frills_r2 = left_hind_leg.addChild("left_front_leg_frills_r2", ModelPartBuilder.create().uv(16, 42).cuboid(0.5F, -2.5F, -1.5F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));
		
		ModelPartData right_hind_leg = modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 1).cuboid(-12.0F, 1.5F, -3.5F, 2.0F, 0.0F, 1.0F, new Dilation(0.0F))
				.uv(38, 41).cuboid(-12.0F, -2.5F, -2.5F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, 22.5F, 7.5F));
		
		ModelPartData right_back_leg_frills_r1 = right_hind_leg.addChild("right_back_leg_frills_r1", ModelPartBuilder.create().uv(35, 0).cuboid(-11.75F, -2.5F, 2.5F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));
		
		ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(34, -1).cuboid(-2.0F, -0.5F, -1.0F, 4.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.5F, 11.0F));
		
		ModelPartData upper_tail_frills_left_r1 = tail.addChild("upper_tail_frills_left_r1", ModelPartBuilder.create().uv(16, 27).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.5F, 5.0F, 0.0F, 0.5236F, 0.0F));
		
		ModelPartData upper_tail_frills_up_r1 = tail.addChild("upper_tail_frills_up_r1", ModelPartBuilder.create().uv(7, 0).cuboid(-1.0F, -3.0F, -6.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 13.0F, 0.5236F, 0.0F, 0.0F));
		
		ModelPartData upper_tail_frills_right_r1 = tail.addChild("upper_tail_frills_right_r1", ModelPartBuilder.create().uv(8, 27).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.5F, 5.0F, 0.0F, -0.5236F, 0.0F));
		
		ModelPartData lower_tail = tail.addChild("lower_tail", ModelPartBuilder.create().uv(-1, -1).cuboid(-1.0F, -0.99F, -1.0F, 2.0F, 1.98F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 7.0F));
		
		ModelPartData lower_tail_frills_right_r1 = lower_tail.addChild("lower_tail_frills_right_r1", ModelPartBuilder.create().uv(0, 27).cuboid(5.0F, -1.0F, 6.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, -2.0F, 0.0F, -0.5236F, 0.0F));
		
		ModelPartData lower_tail_frills_up_r1 = lower_tail.addChild("lower_tail_frills_up_r1", ModelPartBuilder.create().uv(11, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 6.0F, 0.5236F, 0.0F, 0.0F));
		
		ModelPartData lower_tail_frills_left_r1 = lower_tail.addChild("lower_tail_frills_left_r1", ModelPartBuilder.create().uv(11, 0).cuboid(-5.0F, -1.0F, 6.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -2.0F, 0.0F, 0.5236F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	
	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}
	
	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg, this.tail);
	}
	
	@Override
	public void setAngles(LizardEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;
		this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		
		this.tail.yaw = this.lerpAngleDegrees(this.tail.yaw, 0.3F * MathHelper.cos(animationProgress * 0.1F));
		this.lower_tail.yaw = this.lerpAngleDegrees(this.tail.yaw, 0.3F * MathHelper.cos(animationProgress * 0.1F));
		
		this.maw.pitch = 0.3F * this.lerpAngleDegrees(this.tail.yaw, 0.05F * MathHelper.cos(animationProgress * 0.1F));
	}
	
	private float lerpAngleDegrees(float start, float end) {
		return MathHelper.lerpAngleDegrees(0.05F, start, end);
	}
	
}