package de.dafuqs.spectrum.helpers;

import de.dafuqs.spectrum.particle.*;
import net.minecraft.particle.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class ParticleHelper {
	
	public static void playParticleWithPatternAndVelocityClient(World world, Vec3d position, ParticleEffect particleEffect, @NotNull VectorPattern pattern, double velocity) {
		for (Vec3d vec3d : pattern.getVectors()) {
			world.addParticle(particleEffect, position.getX(), position.getY(), position.getZ(), vec3d.x * velocity, vec3d.y * velocity, vec3d.z * velocity);
		}
	}

	public static void playParticleWithRotation(World world, Vec3d position, double longitude, double latitude, ParticleEffect particleEffect, @NotNull VectorPattern pattern, double velocity) {
		for (Vec3d vec3d : pattern.getVectors()) {
			var length = vec3d.length();
			var orientation = Orientation.getVectorOrientation(vec3d).add(longitude, latitude);
			vec3d = orientation.toVector(length);

			world.addParticle(particleEffect, position.getX(), position.getY(), position.getZ(), vec3d.x * velocity, vec3d.y * velocity, vec3d.z * velocity);
		}
	}
	
}
