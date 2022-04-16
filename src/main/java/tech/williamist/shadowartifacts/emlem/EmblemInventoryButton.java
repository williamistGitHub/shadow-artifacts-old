package tech.williamist.shadowartifacts.emlem;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EmblemInventoryButton extends Button {

    private final ResourceLocation CUSTOM_BUTTON_TEX = new ResourceLocation("shadowartifacts", "textures/gui/invbutton.png");

    public EmblemInventoryButton(int p_93728_, int p_93729_, int p_93730_, int p_93731_, Component p_93732_, OnPress p_93733_, OnTooltip p_93734_) {
        super(p_93728_, p_93729_, p_93730_, p_93731_, p_93732_, p_93733_, p_93734_);
    }

    @Override
    public void renderButton(@NotNull PoseStack poseStack, int x, int y, float literallyUselessLmao) { // why does this float never do anything. at all. like even in mojangs code it doesnt do anything. WHY IS IT HERE???

        // WIDGETS_LOCATION is final in AbstractWidget, so I have to copy+paste the entire code to change the texture.
        // (or i could do reflection, but im not dealing with that, no way.)

        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, CUSTOM_BUTTON_TEX);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHoveredOrFocused());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(poseStack, this.x, this.y, 0, i * 20, this.width, this.height);
        //this.blit(poseStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(poseStack, minecraft, x, y);
        int j = getFGColor();

        //drawCenteredString(poseStack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);

        if (this.isHoveredOrFocused()) {
            this.renderToolTip(poseStack, x, y);
        }
    }
}
