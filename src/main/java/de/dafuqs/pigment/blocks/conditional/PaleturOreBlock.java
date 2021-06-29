package de.dafuqs.pigment.blocks.conditional;

import de.dafuqs.pigment.misc.PigmentBlockCloaker;
import de.dafuqs.pigment.PigmentCommon;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.NumberRange;
import net.minecraft.util.Identifier;

import java.util.List;

public class PaleturOreBlock extends ConditionallyVisibleOreBlock {

    public PaleturOreBlock(Settings settings, NumberRange.IntRange intRange) {
        super(settings, intRange);
        setupCloak();
    }

    @Override
    public Identifier getCloakAdvancementIdentifier() {
        return new Identifier(PigmentCommon.MOD_ID, "craft_colored_sapling"); // TODO
    }

    @Override
    public void setCloaked() {
        PigmentBlockCloaker.swapModel(this.getDefaultState(), Blocks.END_STONE.getDefaultState()); // block
        PigmentBlockCloaker.swapModel(this.asItem(), Items.END_STONE); // item
    }

    @Deprecated
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        return getCloakedDroppedStacks(state, builder);
    }


}
