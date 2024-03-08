package tiatech;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.util.ResourceLocation;

import gregtech.GTInternalTags;

import tiatech.api.utils.TTLog;
import tiatech.common.metatileentities.TiaTechMetaTileEntities;

import org.jetbrains.annotations.NotNull;

@Mod(modid = TiaTech.MODID,
        name = TiaTech.NAME,
        version = TiaTech.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING)
public class TiaTech {

    public static final String MODID = "tiatech";
    public static final String NAME = "TiaTech";
    public static final String VERSION = "0.0.1";

    public static @NotNull ResourceLocation ID(@NotNull String path) {
        return new ResourceLocation(TiaTech.MODID, path);
    }
    @Mod.EventHandler
    public void onPreInit(@NotNull FMLPreInitializationEvent event) {
        TTLog.init(event.getModLog());
        TiaTechMetaTileEntities.init();
    }
}
