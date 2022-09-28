package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin{

	@Shadow private boolean caughtFish;



	@Inject(at = @At("TAIL"), method = "tick()V")
	public void tick(CallbackInfo info){
		MinecraftClient client = MinecraftClient.getInstance();
		ExampleMod.getInstance().autoFish.tick();
	}

	MinecraftClient client = MinecraftClient.getInstance();
	@Inject(at = @At("TAIL"), method = "onTrackedDataSet")
	public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci){
		if(caughtFish && ExampleMod.isFishingEnabled){
			ExampleMod.getInstance().autoFish.setRecastRod(30);
			client.interactionManager.interactItem(client.player,Hand.MAIN_HAND);
		}
	}

}
