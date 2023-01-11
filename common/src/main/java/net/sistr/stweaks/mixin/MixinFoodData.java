package net.sistr.stweaks.mixin;

import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import net.sistr.stweaks.STweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class MixinFoodData {

    @Shadow
    private float saturationLevel;//隠し満腹度

    @Shadow
    private int foodLevel;//満腹度

    @Shadow
    private int tickTimer;//タイマー

    @Shadow
    public abstract void addExhaustion(float f);

    @Shadow private float exhaustionLevel;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(Player player, CallbackInfo ci) {
        if (!STweaks.config().overhaulHealSystem) {
            return;
        }
        ci.cancel();

        boolean naturalRegen = player.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
        Difficulty difficulty = player.level.getDifficulty();
        if (naturalRegen
                && this.saturationLevel > 0.0f
                && player.isHurt()
                && this.foodLevel >= 20) {
            ++this.tickTimer;
            if (this.tickTimer >= 10) {
                float f = Math.min(this.saturationLevel, 6.0f);
                player.heal(f / 6.0f);
                this.addExhaustion(f);
                this.tickTimer = 0;
            }
        } else if (naturalRegen
                && this.foodLevel >= 10
                && player.isHurt()) {
            ++this.tickTimer;
            if (this.tickTimer >= 20) {
                player.heal(1.0f);
                this.addExhaustion(6.0f);
                this.tickTimer = 0;
            }
        } else if (this.foodLevel <= 0) {
            ++this.tickTimer;
            if (this.tickTimer >= 80) {
                if (player.getHealth() > 10.0f
                        || difficulty == Difficulty.HARD
                        || player.getHealth() > 1.0f
                        && difficulty == Difficulty.NORMAL) {
                    player.hurt(DamageSource.STARVE, 1.0f);
                }
                this.tickTimer = 0;
            }
        } else {
            this.tickTimer = 0;
        }
    }

}
