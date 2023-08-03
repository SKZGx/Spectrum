package de.dafuqs.spectrum.registries;

import de.dafuqs.spectrum.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.tag.*;
import net.minecraft.util.registry.*;

public class SpectrumAttributeTags {
    
    public static final TagKey<EntityAttribute> INEXORABLE_ARMOR_EFFECTIVE = TagKey.of(Registry.ATTRIBUTE_KEY, SpectrumCommon.locate("inexorable_armor_effective"));
    public static final TagKey<EntityAttribute> INEXORABLE_HANDHELD_EFFECTIVE = TagKey.of(Registry.ATTRIBUTE_KEY, SpectrumCommon.locate("inexorable_handheld_effective"));
    
}
