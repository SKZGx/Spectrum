package de.dafuqs.spectrum.particle.effect;

import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import de.dafuqs.spectrum.particle.*;
import net.minecraft.network.*;
import net.minecraft.particle.*;
import net.minecraft.util.math.*;
import net.minecraft.world.event.*;

public class ExperienceTransmissionParticleEffect extends SimpleTransmissionParticleEffect {
	
	public static final Codec<ExperienceTransmissionParticleEffect> CODEC = RecordCodecBuilder.create(
		(instance) -> instance.group(
			PositionSource.CODEC.fieldOf("destination").forGetter((effect) -> effect.destination),
			Codec.INT.fieldOf("arrival_in_ticks").forGetter((vibrationParticleEffect) -> vibrationParticleEffect.arrivalInTicks)
		).apply(instance, ExperienceTransmissionParticleEffect::new));
	
	@SuppressWarnings("deprecation")
	public static final Factory<ExperienceTransmissionParticleEffect> FACTORY = new Factory<>() {
		@Override
		public ExperienceTransmissionParticleEffect read(ParticleType<ExperienceTransmissionParticleEffect> particleType, StringReader stringReader) throws CommandSyntaxException {
			stringReader.expect(' ');
			float f = (float) stringReader.readDouble();
			stringReader.expect(' ');
			float g = (float) stringReader.readDouble();
			stringReader.expect(' ');
			float h = (float) stringReader.readDouble();
			stringReader.expect(' ');
			int i = stringReader.readInt();
			BlockPos blockPos = new BlockPos(f, g, h);
			return new ExperienceTransmissionParticleEffect(new BlockPositionSource(blockPos), i);
		}
		
		@Override
		public ExperienceTransmissionParticleEffect read(ParticleType<ExperienceTransmissionParticleEffect> particleType, PacketByteBuf packetByteBuf) {
			PositionSource positionSource = PositionSourceType.read(packetByteBuf);
			int i = packetByteBuf.readVarInt();
			return new ExperienceTransmissionParticleEffect(positionSource, i);
		}
	};
	
	public ExperienceTransmissionParticleEffect(PositionSource positionSource, Integer arrivalInTicks) {
		super(positionSource, arrivalInTicks);
	}
	
	@Override
	public ParticleType<ExperienceTransmissionParticleEffect> getType() {
		return SpectrumParticleTypes.EXPERIENCE_TRANSMISSION;
	}
	
}
