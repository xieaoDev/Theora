package xieao.theora.core;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import xieao.theora.core.lib.annotation.PreLoad;
import xieao.theora.item.IItem;
import xieao.theora.item.ItemVial;

import java.util.ArrayList;
import java.util.List;

@PreLoad
public class IItems {
    public static final List<Item> ITEMS = new ArrayList<>(IBlocks.ITEM_BLOCKS);
    public static final Item VIAL;

    static {
        VIAL = register("vial", new ItemVial(new Item.Builder().group(IItem.MAIN)));
    }

    static <T extends Item & IItem> T register(String name, T item) {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        ITEMS.add(item);
        return item;
    }
}