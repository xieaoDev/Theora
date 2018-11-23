package xieao.theora.common.item;

import net.minecraft.item.ItemStack;
import xieao.theora.api.item.slate.ILootingSlate;

public class ItemSlateLooting extends ItemBase implements ILootingSlate {

    @Override
    public Enum<?>[] getSubTypeValues() {
        return Level.values();
    }

    @Override
    public int getFortuneLevel(ItemStack stack) {
        return Level.values()[stack.getMetadata()].ordinal() + 1;
    }

    @Override
    public float getLiquidCost(ItemStack stack) {
        return (Level.values()[stack.getMetadata()].ordinal() + 1) * 8.0F;
    }

    public enum Level {
        LEVEL_1, LEVEL_2, LEVEL_3
    }
}