package xieao.theora.client.render.tile;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import xieao.theora.api.Consts;
import xieao.theora.block.gate.TileGate;
import xieao.theora.client.render.model.ModelGate;

public class RenderGate extends TileEntityRenderer<TileGate> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Consts.MOD_ID, "textures/ter/gate.png");
    private static final ModelGate MODEL_GATE = new ModelGate();

    @Override
    public void render(TileGate gate, double x, double y, double z, float partialTicks, int destroyStage) {
        if (!gate.isGateBase()) return;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translated(x, y, z);
        GlStateManager.scalef(1.0F, -1.0F, -1.0F);
        GlStateManager.translated(0.5D, -1.5D, -0.5D);
        bindTexture(TEXTURE);
        MODEL_GATE.render();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
