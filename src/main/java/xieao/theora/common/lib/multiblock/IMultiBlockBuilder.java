package xieao.theora.common.lib.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import xieao.theora.common.block.TileBase;

public interface IMultiBlockBuilder<T extends TileBase> {

    IMultiBlock getMultiBlock();

    @SuppressWarnings("unchecked")
    default void tryBuild(T builder) {
        if (getMultiBlock().isAllInPlace(builder)) {
            for (IMultiBlock.Set set : getMultiBlock().getSets()) {
                int[][] offsets = set.offsets;
                for (int[] offset : offsets) {
                    BlockPos pos = builder.getPos().add(offset[0], offset[1], offset[2]);
                    IBlockState state = builder.getBlockState(pos);
                    if (state.equals(set.state)) {
                        TileEntity tileEntity = builder.getTileEntity(pos);
                        if (tileEntity instanceof IMultiBlockPart) {
                            IMultiBlockPart part = (IMultiBlockPart) tileEntity;
                            part.setBuilderPos(builder.getPos());
                        }
                    }
                }
            }
            setBuilt(true);
            builder.syncNBTData();
        }
    }

    default void dimolish(T builder) {
        for (IMultiBlock.Set set : getMultiBlock().getSets()) {
            int[][] offsets = set.offsets;
            for (int[] offset : offsets) {
                BlockPos pos = builder.getPos().add(offset[0], offset[1], offset[2]);
                IBlockState state = builder.getBlockState(pos);
                if (state.equals(set.state)) {
                    TileEntity tileEntity = builder.getTileEntity(pos);
                    if (tileEntity instanceof IMultiBlockPart) {
                        IMultiBlockPart part = (IMultiBlockPart) tileEntity;
                        part.setBuilderPos(null);
                    }
                }
            }
        }
        setBuilt(false);
        builder.syncNBTData();
    }

    boolean built();

    void setBuilt(boolean built);

}