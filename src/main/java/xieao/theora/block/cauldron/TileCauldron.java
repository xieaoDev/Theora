package xieao.theora.block.cauldron;

import net.minecraft.item.ItemStack;
import xieao.lib.block.IInv;
import xieao.lib.block.Tile;
import xieao.theora.block.ITiles;

public class TileCauldron extends Tile.Tickable implements IInv {

    public TileCauldron() {
        super(ITiles.CAULDRON);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }
}
