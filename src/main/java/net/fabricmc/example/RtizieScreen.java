package net.fabricmc.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class RtizieScreen extends Screen {
    private final Screen parent;
    private final GameOptions options;

    public RtizieScreen(Screen parent, GameOptions options) {
        super(Text.literal("Rtizie"));
        this.parent = parent;
        this.options = options;
    }

    Text flyingText(){
        return Text.literal("Flying");
    }

    protected void init(){
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 100, 20, flyingText(),(flying) ->{
            ExampleMod.LOGGER.info("FLYING");
            if(ExampleMod.isFishingEnabled)
                ExampleMod.isFishingEnabled = false;
            else
                ExampleMod.isFishingEnabled = true;
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 120, 100, 20, ScreenTexts.BACK,(flying) ->{
            this.client.setScreen(parent);
        }));
    }
}
