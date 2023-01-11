package net.sistr.stweaks.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.sistr.stweaks.STweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTraderSpawner.class)
public class MixinWanderingTraderSpawner {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(ServerLevel serverLevel, boolean bl, boolean bl2, CallbackInfoReturnable<Integer> cir) {
        if (STweaks.config().banWanderingTraderSpawn) cir.setReturnValue(0);
    }

}
