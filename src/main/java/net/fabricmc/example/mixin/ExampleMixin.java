package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.RtizieScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class ExampleMixin extends Screen {

	protected ExampleMixin(Text title) {
		super(title);
	}

	@Inject(at = @At("HEAD"), method = "initWidgets")
	private void initWidgets(CallbackInfo ci){
		this.addDrawableChild(new ButtonWidget(10,10,90,10, Text.literal("Rtizie"), (menu) ->{
			this.client.setScreen(new RtizieScreen(this,this.client.options));
		}));
	}
}
