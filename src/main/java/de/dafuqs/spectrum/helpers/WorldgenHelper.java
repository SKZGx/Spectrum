package de.dafuqs.spectrum.helpers;

import de.dafuqs.spectrum.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.*;

public class WorldgenHelper {
	
	public static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
		return Registry.register(Registry.FEATURE, SpectrumCommon.locate(name), feature);
	}
	
	public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<?, ?>> registerConfiguredFeature(Identifier identifier, F feature, FC featureConfig) {
		return registerConfiguredFeature(BuiltinRegistries.CONFIGURED_FEATURE, identifier, new ConfiguredFeature<>(feature, featureConfig));
	}
	
	public static <T> RegistryEntry<T> registerConfiguredFeature(Registry<T> registry, Identifier identifier, T value) {
		return BuiltinRegistries.add(registry, identifier, value);
	}
	
	public static RegistryEntry<PlacedFeature> registerPlacedFeature(Identifier identifier, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, identifier, new PlacedFeature(RegistryEntry.upcast(feature), List.of(modifiers)));
	}
	
	public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<PlacedFeature> registerConfiguredAndPlacedFeature(Identifier identifier, F feature, FC featureConfig, PlacementModifier... placementModifiers) {
		RegistryEntry<ConfiguredFeature<?, ?>> configuredFeature = registerConfiguredFeature(identifier, feature, featureConfig);
		return registerPlacedFeature(identifier, configuredFeature, placementModifiers);
	}
	
	public static RegistryEntry<PlacedFeature> registerConfiguredAndPlacedFeature(Identifier identifier, ConfiguredFeature<?, ?> configuredFeature, PlacementModifier... placementModifiers) {
		RegistryEntry<ConfiguredFeature<?, ?>> id = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configuredFeature);
		return registerPlacedFeature(identifier, id, placementModifiers);
	}
	
}
