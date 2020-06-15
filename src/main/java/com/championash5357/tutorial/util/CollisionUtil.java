package com.championash5357.tutorial.util;

import java.util.Collection;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class CollisionUtil {

	private static final double CENTER = 0.5;
	private static final double NINETY_DEGREES = Math.toRadians(90),
								ONE_HUNDRED_EIGHTY_DEGREES = Math.toRadians(180),
								TWO_HUNDRED_SEVENTY_DEGREES = Math.toRadians(270);
	
	public static VoxelShape orAll(VoxelShape... shapes) {
		return orAll(Lists.newArrayList(shapes));
	}
	
	public static VoxelShape orAll(Collection<VoxelShape> shapes) {
		VoxelShape collision = VoxelShapes.empty();
		for(VoxelShape shape : shapes) collision = VoxelShapes.or(shape, collision);
		return collision;
	}
	
	public static VoxelShape rotate90(@Nullable Axis axis, VoxelShape... shapes) {
		return rotate(axis, NINETY_DEGREES, shapes);
	}
	
	public static VoxelShape rotate90(@Nullable Axis axis, Collection<VoxelShape> shapes) {
		return rotate(axis, NINETY_DEGREES, shapes);
	}
	
	public static VoxelShape rotate180(@Nullable Axis axis, VoxelShape... shapes) {
		return rotate(axis, ONE_HUNDRED_EIGHTY_DEGREES, shapes);
	}
	
	public static VoxelShape rotate180(@Nullable Axis axis, Collection<VoxelShape> shapes) {
		return rotate(axis, ONE_HUNDRED_EIGHTY_DEGREES, shapes);
	}
	
	public static VoxelShape rotate270(@Nullable Axis axis, VoxelShape... shapes) {
		return rotate(axis, TWO_HUNDRED_SEVENTY_DEGREES, shapes);
	}
	
	public static VoxelShape rotate270(@Nullable Axis axis, Collection<VoxelShape> shapes) {
		return rotate(axis, TWO_HUNDRED_SEVENTY_DEGREES, shapes);
	}
	
	public static VoxelShape rotate(@Nullable Axis axis, double radians, VoxelShape... shapes) {
		return rotate(axis, radians, Lists.newArrayList(shapes));
	}
	
	public static VoxelShape rotate(@Nullable Axis axis, double radians, Collection<VoxelShape> shapes) {
		VoxelShape collision = VoxelShapes.empty();
		for(VoxelShape shape : shapes) collision = VoxelShapes.or(collision, rotate(axis, radians, shape));
		return collision;
	}
	
	public static VoxelShape rotate(@Nullable Axis axis, double radians, VoxelShape shape) {
		VoxelShape collision = VoxelShapes.empty();
		for(AxisAlignedBB box : shape.toBoundingBoxList()) {
			Pair<Double, Double> min = axis == Axis.X ? rotatePoint(box.minY, box.minZ, radians) : (axis == Axis.Z ? rotatePoint(box.minX, box.minY, radians) : rotatePoint(box.minX, box.minZ, radians));
			Pair<Double, Double> max = axis == Axis.X ? rotatePoint(box.maxY, box.maxZ, radians) : (axis == Axis.Z ? rotatePoint(box.maxX, box.maxY, radians) : rotatePoint(box.maxX, box.maxZ, radians));
			collision = VoxelShapes.or(collision, axis == Axis.X ? VoxelShapes.create(box.minX, min.getLeft(), min.getRight(), box.maxX, max.getLeft(), max.getRight()) : (
												  axis == Axis.Z ? VoxelShapes.create(min.getLeft(), min.getRight(), box.minZ, max.getLeft(), max.getRight(), box.maxZ) :
													  			   VoxelShapes.create(min.getLeft(), box.minY, min.getRight(), max.getLeft(), box.maxY, max.getRight())));
		}
		return collision;
	}
	
	private static Pair<Double, Double> rotatePoint(double p1, double p2, double rotation) {
		return rotatePoint(p1, p2, rotation, CENTER);
	}
	
	private static Pair<Double, Double> rotatePoint(double p1, double p2, double rotation, double center) {
		return Pair.of(((p1 - center) * Math.cos(rotation) - ((p2 - center) * Math.sin(rotation))) + center, ((p1 - center) * Math.sin(rotation)) + ((p2 - center) * Math.cos(rotation)) + center);
	}
}
