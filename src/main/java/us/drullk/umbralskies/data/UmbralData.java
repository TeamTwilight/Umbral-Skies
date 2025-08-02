package us.drullk.umbralskies.data;

import net.minecraft.core.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import us.drullk.umbralskies.UmbralSkies;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class UmbralData {
	public static void generateData(GatherDataEvent event) {
		boolean isServer = event.includeServer();
		boolean isClient = event.includeClient();

		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		generator.addProvider(isClient, new UmbralLocaleData(output));
		generator.addProvider(isClient, new UmbralBlockStateModels(output, existingFileHelper));
		generator.addProvider(isClient, new UmbralItemModels(output, existingFileHelper));
		UmbralTags.UmbralBlockTags blockTags = new UmbralTags.UmbralBlockTags(output, provider, existingFileHelper);
		generator.addProvider(isServer, blockTags);
		generator.addProvider(isServer, new UmbralTags.UmbralItemTags(output, provider, blockTags.contentsGetter(), existingFileHelper));
		generator.addProvider(isServer, new DatapackBuiltinEntriesProvider(output, provider, UmbralDataPack.DATA_BUILDER, Collections.singleton(UmbralSkies.MODID)));
		generator.addProvider(isServer, new UmbralRecipes(output, provider));
		generator.addProvider(isServer, new UmbralLootModifiers(output, provider));
		generator.addProvider(isServer, new UmbralLoot(output, provider));

		generator.addProvider(isServer, new UmbralTags.UmbralPlacedFeatureTags(output, RegistryPatchGenerator.createLookup(provider, UmbralDataPack.DATA_BUILDER).thenApply(RegistrySetBuilder.PatchedRegistries::full), existingFileHelper));
	}
}
