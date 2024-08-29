package com.sixtyfy.skyblock.mixin;

import com.sixtyfy.skyblock.config.SixtyFyConfig;
import com.sixtyfy.skyblock.config.SixtyFyConfigManager;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class CustomHeldItemRenderer {

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void onRenderItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        SixtyFyConfig config = SixtyFyConfigManager.getConfig();
        if (config.enableViewmodelTransformation) {
            if (hand == Hand.MAIN_HAND) {
                matrices.scale(config.scale, config.scale, config.scale);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(config.rotateX));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(config.rotateY));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(config.rotateZ));
                matrices.translate(config.translateX, config.translateY, config.translateZ);
            } else {
                matrices.scale(config.scale, config.scale, config.scale);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(config.rotateX));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-config.rotateY));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-config.rotateZ));
                matrices.translate(-config.translateX, config.translateY, config.translateZ);
            }
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderArmHoldingItem(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IFFLnet/minecraft/util/Arm;)V"))
    private void onRenderArm(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        SixtyFyConfig config = SixtyFyConfigManager.getConfig();
        if (config.enableArmTransformation) {
            if (hand == Hand.MAIN_HAND) {
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(config.armRotateX));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(config.armRotateY));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(config.armRotateZ));
                matrices.translate(config.armTranslateX, config.armTranslateY, config.armTranslateZ);
            } else {
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(config.armRotateX));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-config.armRotateY));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-config.armRotateZ));
                matrices.translate(-config.armTranslateX, config.armTranslateY, config.armTranslateZ);
            }
        }
    }
}