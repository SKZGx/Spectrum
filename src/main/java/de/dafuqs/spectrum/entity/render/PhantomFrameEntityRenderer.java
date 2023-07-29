package de.dafuqs.spectrum.entity.render;

import de.dafuqs.spectrum.entity.*;
import de.dafuqs.spectrum.entity.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.item.*;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.*;
import net.minecraft.client.util.*;
import net.minecraft.client.util.math.*;
import net.minecraft.entity.decoration.*;
import net.minecraft.item.*;
import net.minecraft.item.map.*;
import net.minecraft.util.math.*;

public class PhantomFrameEntityRenderer<T extends ItemFrameEntity> extends ItemFrameEntityRenderer<PhantomFrameEntity> {

	public static final ModelIdentifier NORMAL_FRAME_MODEL_IDENTIFIER = new ModelIdentifier("item_frame", "map=false");
	public static final ModelIdentifier MAP_FRAME_MODEL_IDENTIFIER = new ModelIdentifier("item_frame", "map=true");
	public static final ModelIdentifier GLOW_FRAME_MODEL_IDENTIFIER = new ModelIdentifier("glow_item_frame", "map=false");
	public static final ModelIdentifier MAP_GLOW_FRAME_MODEL_IDENTIFIER = new ModelIdentifier("glow_item_frame", "map=true");

	private final MinecraftClient client = MinecraftClient.getInstance();
	private final ItemRenderer itemRenderer;

	public PhantomFrameEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.itemRenderer = context.getItemRenderer();
	}

	@Override
	protected int getBlockLight(PhantomFrameEntity itemFrameEntity, BlockPos blockPos) {
		return itemFrameEntity.getType() == SpectrumEntityTypes.GLOW_PHANTOM_FRAME ? Math.max(5, super.getBlockLight(itemFrameEntity, blockPos)) : super.getBlockLight(itemFrameEntity, blockPos);
	}

	@Override
	public void render(PhantomFrameEntity itemFrameEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		matrixStack.push();
		
		Direction direction = itemFrameEntity.getHorizontalFacing();
		Vec3d vec3d = this.getPositionOffset(itemFrameEntity, g);
		matrixStack.translate(-vec3d.getX(), -vec3d.getY(), -vec3d.getZ());
		double d = 0.46875D;
		matrixStack.translate((double) direction.getOffsetX() * d, (double) direction.getOffsetY() * d, (double) direction.getOffsetZ() * d);
		matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(itemFrameEntity.getPitch()));
		matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - itemFrameEntity.getYaw()));
		boolean isInvisible = itemFrameEntity.isInvisible();
		ItemStack itemStack = itemFrameEntity.getHeldItemStack();
		if (!isInvisible) {
			BlockRenderManager blockRenderManager = this.client.getBlockRenderManager();
			BakedModelManager bakedModelManager = blockRenderManager.getModels().getModelManager();
			ModelIdentifier modelIdentifier = this.getModelId(itemFrameEntity, itemStack);
			matrixStack.push();
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			blockRenderManager.getModelRenderer().render(matrixStack.peek(), vertexConsumerProvider.getBuffer(TexturedRenderLayers.getEntitySolid()), null, bakedModelManager.getModel(modelIdentifier), 1.0F, 1.0F, 1.0F, light, OverlayTexture.DEFAULT_UV);
			matrixStack.pop();
		}
		
		if (!itemStack.isEmpty()) {
			boolean isRenderingMap = itemStack.isOf(Items.FILLED_MAP);
			if (isInvisible) {
				matrixStack.translate(0.0D, 0.0D, 0.5D);
			} else {
				matrixStack.translate(0.0D, 0.0D, 0.4375D);
			}
			
			int renderLight = itemFrameEntity.shouldRenderAtMaxLight() ? LightmapTextureManager.MAX_LIGHT_COORDINATE : light;
			
			int bakedModelManager = isRenderingMap ? itemFrameEntity.getRotation() % 4 * 2 : itemFrameEntity.getRotation();
			matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion((float) bakedModelManager * 360.0F / 8.0F));
			if (isRenderingMap) {
				matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));
				float scale = 0.0078125F;
				matrixStack.scale(scale, scale, scale);
				matrixStack.translate(-64.0D, -64.0D, 0.0D);
				Integer mapId = FilledMapItem.getMapId(itemStack);
				MapState mapState = FilledMapItem.getMapState(mapId, itemFrameEntity.world);
				matrixStack.translate(0.0D, 0.0D, -1.0D);
				if (mapState != null) {
					this.client.gameRenderer.getMapRenderer().draw(matrixStack, vertexConsumerProvider, mapId, mapState, true, renderLight);
				}
			} else {
				float scale = 0.75F;
				matrixStack.scale(scale, scale, scale);
				this.itemRenderer.renderItem(itemStack, ModelTransformation.Mode.FIXED, renderLight, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, itemFrameEntity.getId());
			}
		}

		matrixStack.pop();
	}

	private ModelIdentifier getModelId(PhantomFrameEntity entity, ItemStack stack) {
		boolean bl = entity.getType() == SpectrumEntityTypes.GLOW_PHANTOM_FRAME;
		if (stack.isOf(Items.FILLED_MAP)) {
			return bl ? MAP_GLOW_FRAME_MODEL_IDENTIFIER : MAP_FRAME_MODEL_IDENTIFIER;
		} else {
			return bl ? GLOW_FRAME_MODEL_IDENTIFIER : NORMAL_FRAME_MODEL_IDENTIFIER;
		}
	}


}