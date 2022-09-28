package net.fabricmc.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;


public class AutoFish{
    public int recastRod = -1;

    public MinecraftClient client;

    public void tick(){
        ExampleMod.LOGGER.info(String.valueOf(recastRod));
        if(recastRod > 0)
            recastRod--;

        if(recastRod == 0 && ExampleMod.isFishingEnabled){
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            recastRod = -1;
        }
    }

    public void setRecastRod(int num){
        recastRod = num;
    }
}
