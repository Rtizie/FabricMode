package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin{
	@Shadow private int waitCountdown;
	@Shadow private boolean caughtFish;

	@Inject(at = @At("TAIL"), method = "tick()V")
	public void tick(CallbackInfo info){
		ExampleMod.LOGGER.info("tick" + caughtFish + " " + waitCountdown);
	}

	MinecraftClient client = MinecraftClient.getInstance();

	@Inject(at = @At("TAIL"), method = "onTrackedDataSet")
	public void onTrackedDataSet(CallbackInfo ci){
		if(caughtFish && ExampleMod.isFishingEnabled){
			client.interactionManager.interactItem(client.player,Hand.MAIN_HAND);
		}
	}

}
