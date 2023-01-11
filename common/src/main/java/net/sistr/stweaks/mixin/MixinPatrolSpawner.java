package net.sistr.stweaks.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.sistr.stweaks.STweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PatrolSpawner.class)
public class MixinPatrolSpawner {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(ServerLevel serverLevel, boolean bl, boolean bl2, CallbackInfoReturnable<Integer> cir) {
        if (STweaks.config().banPatrolSpawn) cir.setReturnValue(0);
    }

}
