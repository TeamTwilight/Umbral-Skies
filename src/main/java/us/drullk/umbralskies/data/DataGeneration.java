package us.drullk.umbralskies.data;

import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import us.drullk.umbralskies.UmbralSkies;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class DataGeneration {
    public static void gatherData(GatherDataEvent event) {
        boolean isServer = event.includeServer();
        boolean isClient = event.includeClient();

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(isClient, new UmbralLocaleData(output));
        generator.addProvider(isClient, new BlockStateModels(output, existingFileHelper));
        generator.addProvider(isClient, new ItemModels(output, existingFileHelper));
        generator.addProvider(isServer, new UmbralTags.UmbralBlockTags(output, provider, existingFileHelper));
        generator.addProvider(isServer, new DatapackBuiltinEntriesProvider(output, provider, UmbralDataPack.DATA_BUILDER, Collections.singleton(UmbralSkies.MODID)));
        generator.addProvider(isServer, new UmbralTags.UmbralPlacedFeatureTags(output, provider.thenApply(p -> UmbralDataPack.DATA_BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), p)), existingFileHelper));
    }
}
