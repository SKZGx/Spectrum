package de.dafuqs.spectrum.progression;

import de.dafuqs.spectrum.progression.advancement.*;
import net.fabricmc.fabric.mixin.object.builder.*;

public class SpectrumAdvancementCriteria {

	public static PedestalRecipeCalculatedCriterion PEDESTAL_RECIPE_CALCULATED;
	public static PedestalCraftingCriterion PEDESTAL_CRAFTING;
	public static FusionShrineCraftingCriterion FUSION_SHRINE_CRAFTING;
	public static CompletedMultiblockCriterion COMPLETED_MULTIBLOCK;
	public static BlockBrokenCriterion BLOCK_BROKEN;
	public static TreasureHunterDropCriterion TREASURE_HUNTER_DROP;
	public static NaturesStaffUseCriterion NATURES_STAFF_USE;
	public static EnchanterCraftingCriterion ENCHANTER_CRAFTING;
	public static EnchanterEnchantingCriterion ENCHANTER_ENCHANTING;
	public static EnchantmentUpgradedCriterion ENCHANTER_UPGRADING;
	public static InertiaUsedCriterion INERTIA_USED;
	public static AzureDikeChargeCriterion AZURE_DIKE_CHARGE;
	public static TrinketChangeCriterion TRINKET_CHANGE;
	public static PotionWorkshopBrewingCriterion POTION_WORKSHOP_BREWING;
	public static PotionWorkshopCraftingCriterion POTION_WORKSHOP_CRAFTING;
	public static TakeOffBeltJumpCriterion TAKE_OFF_BELT_JUMP;
	public static InkContainerInteractionCriterion INK_CONTAINER_INTERACTION;
	public static JeopardantKillCriterion JEOPARDANT_KILL;
	public static MemoryManifestingCriterion MEMORY_MANIFESTING;
	public static SpiritInstillerCraftingCriterion SPIRIT_INSTILLER_CRAFTING;
	public static SlimeSizingCriterion SLIME_SIZING;
	public static CrystalApothecaryCollectingCriterion CRYSTAL_APOTHECARY_COLLECTING;
	public static UpgradePlaceCriterion UPGRADE_PLACING;
	public static CrystallarieumGrownCriterion CRYSTALLARIEUM_GROWING;
	public static CinderhearthSmeltingCriterion CINDERHEARTH_SMELTING;
	public static InkProjectileKillingCriterion KILLED_BY_INK_PROJECTILE;
	public static SpectrumFishingRodHookedCriterion FISHING_ROD_HOOKED;
	public static TitrationBarrelTappingCriterion TITRATION_BARREL_TAPPING;
	public static ConfirmationButtonPressedCriterion CONFIRMATION_BUTTON_PRESSED;
	public static BloodOrchidPluckingCriterion BLOOD_ORCHID_PLUCKING;
	public static DivinityTickCriterion DIVINITY_TICK;
	public static ConsumedTeaWithSconeCriterion CONSUMED_TEA_WITH_SCONE;
	public static HummingstoneHymnCriterion CREATE_HUMMINGSTONE_HYMN;
	public static PastelNetworkCreatingCriterion PASTEL_NETWORK_CREATING;
	public static PreservationCheckCriterion PRESERVATION_CHECK;

	public static void register() {
		PEDESTAL_RECIPE_CALCULATED = CriteriaAccessor.callRegister(new PedestalRecipeCalculatedCriterion());
		PEDESTAL_CRAFTING = CriteriaAccessor.callRegister(new PedestalCraftingCriterion());
		FUSION_SHRINE_CRAFTING = CriteriaAccessor.callRegister(new FusionShrineCraftingCriterion());
		COMPLETED_MULTIBLOCK = CriteriaAccessor.callRegister(new CompletedMultiblockCriterion());
		BLOCK_BROKEN = CriteriaAccessor.callRegister(new BlockBrokenCriterion());
		TREASURE_HUNTER_DROP = CriteriaAccessor.callRegister(new TreasureHunterDropCriterion());
		NATURES_STAFF_USE = CriteriaAccessor.callRegister(new NaturesStaffUseCriterion());
		ENCHANTER_CRAFTING = CriteriaAccessor.callRegister(new EnchanterCraftingCriterion());
		ENCHANTER_ENCHANTING = CriteriaAccessor.callRegister(new EnchanterEnchantingCriterion());
		ENCHANTER_UPGRADING = CriteriaAccessor.callRegister(new EnchantmentUpgradedCriterion());
		INERTIA_USED = CriteriaAccessor.callRegister(new InertiaUsedCriterion());
		AZURE_DIKE_CHARGE = CriteriaAccessor.callRegister(new AzureDikeChargeCriterion());
		TRINKET_CHANGE = CriteriaAccessor.callRegister(new TrinketChangeCriterion());
		POTION_WORKSHOP_BREWING = CriteriaAccessor.callRegister(new PotionWorkshopBrewingCriterion());
		POTION_WORKSHOP_CRAFTING = CriteriaAccessor.callRegister(new PotionWorkshopCraftingCriterion());
		TAKE_OFF_BELT_JUMP = CriteriaAccessor.callRegister(new TakeOffBeltJumpCriterion());
		INK_CONTAINER_INTERACTION = CriteriaAccessor.callRegister(new InkContainerInteractionCriterion());
		JEOPARDANT_KILL = CriteriaAccessor.callRegister(new JeopardantKillCriterion());
		MEMORY_MANIFESTING = CriteriaAccessor.callRegister(new MemoryManifestingCriterion());
		SPIRIT_INSTILLER_CRAFTING = CriteriaAccessor.callRegister(new SpiritInstillerCraftingCriterion());
		SLIME_SIZING = CriteriaAccessor.callRegister(new SlimeSizingCriterion());
		CRYSTAL_APOTHECARY_COLLECTING = CriteriaAccessor.callRegister(new CrystalApothecaryCollectingCriterion());
		UPGRADE_PLACING = CriteriaAccessor.callRegister(new UpgradePlaceCriterion());
		CRYSTALLARIEUM_GROWING = CriteriaAccessor.callRegister(new CrystallarieumGrownCriterion());
		CINDERHEARTH_SMELTING = CriteriaAccessor.callRegister(new CinderhearthSmeltingCriterion());
		KILLED_BY_INK_PROJECTILE = CriteriaAccessor.callRegister(new InkProjectileKillingCriterion());
		FISHING_ROD_HOOKED = CriteriaAccessor.callRegister(new SpectrumFishingRodHookedCriterion());
		TITRATION_BARREL_TAPPING = CriteriaAccessor.callRegister(new TitrationBarrelTappingCriterion());
		CONFIRMATION_BUTTON_PRESSED = CriteriaAccessor.callRegister(new ConfirmationButtonPressedCriterion());
		BLOOD_ORCHID_PLUCKING = CriteriaAccessor.callRegister(new BloodOrchidPluckingCriterion());
		DIVINITY_TICK = CriteriaAccessor.callRegister(new DivinityTickCriterion());
		CONSUMED_TEA_WITH_SCONE = CriteriaAccessor.callRegister(new ConsumedTeaWithSconeCriterion());
		CREATE_HUMMINGSTONE_HYMN = CriteriaAccessor.callRegister(new HummingstoneHymnCriterion());
		PASTEL_NETWORK_CREATING = CriteriaAccessor.callRegister(new PastelNetworkCreatingCriterion());
		PRESERVATION_CHECK = CriteriaAccessor.callRegister(new PreservationCheckCriterion());
	}
	
}