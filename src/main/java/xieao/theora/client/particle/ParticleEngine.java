package xieao.theora.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ParticleEngine {

    public static final ParticleEngine INSTANCE = new ParticleEngine();
    private static final Minecraft mc = Minecraft.getMinecraft();
    private final List<ParticleBase> particles = new ArrayList<>();
    private final Random rand = new Random();

    @SubscribeEvent
    public static void tickParticles(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START) {
            if (mc.currentScreen == null || !mc.currentScreen.doesGuiPauseGame()) {
                Iterator<ParticleBase> iterator = INSTANCE.particles.iterator();
                while (iterator.hasNext()) {
                    ParticleBase particle = iterator.next();
                    particle.onUpdate();
                    if (!particle.isAlive()) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void renderParticles(RenderWorldLastEvent event) {
        float f0 = ActiveRenderInfo.getRotationX();
        float f1 = ActiveRenderInfo.getRotationZ();
        float f2 = ActiveRenderInfo.getRotationYZ();
        float f3 = ActiveRenderInfo.getRotationXY();
        float f4 = ActiveRenderInfo.getRotationXZ();
        EntityPlayerSP player = mc.player;
        float partialTicks = event.getPartialTicks();
        Particle.interpPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTicks;
        Particle.interpPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTicks;
        Particle.interpPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTicks;
        Particle.cameraViewDir = player.getLook(partialTicks);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        for (int i = 0; i < INSTANCE.particles.size(); i++) {
            ParticleBase particle = INSTANCE.particles.get(i);
            GlStateManager.depthMask(!particle.shouldDisableDepth());
            particle.renderParticle(partialTicks, f0, f4, f1, f2, f3);
        }
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);
    }

    public void addEffect(ParticleBase... effects) {
        if (mc.currentScreen == null || !mc.currentScreen.doesGuiPauseGame()) {
            this.particles.addAll(Arrays.asList(effects));
        }
    }
}