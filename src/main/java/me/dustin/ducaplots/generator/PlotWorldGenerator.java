package me.dustin.ducaplots.generator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class PlotWorldGenerator extends ChunkGenerator {

    private static final int PLOT_SIZE = 64;
    private static final int ROAD_WIDTH = 5;
    private static final int GROUND_HEIGHT = 64;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        int blockX = chunkX << 4;
        int blockZ = chunkZ << 4;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = blockX + x;
                int worldZ = blockZ + z;

                boolean isRoadX = (worldX % (PLOT_SIZE + ROAD_WIDTH)) < ROAD_WIDTH;
                boolean isRoadZ = (worldZ % (PLOT_SIZE + ROAD_WIDTH)) < ROAD_WIDTH;
                boolean isRoad = isRoadX || isRoadZ;

                for (int y = 0; y < GROUND_HEIGHT - 1; y++) {
                    chunk.setBlock(x, y, z, Material.STONE);
                }

                if (isRoad) {
                    chunk.setBlock(x, GROUND_HEIGHT - 1, z, Material.STONE);
                } else {
                    chunk.setBlock(x, GROUND_HEIGHT - 1, z, Material.GRASS_BLOCK);
                    int localX = worldX % (PLOT_SIZE + ROAD_WIDTH);
                    int localZ = worldZ % (PLOT_SIZE + ROAD_WIDTH);
                    if (localX == ROAD_WIDTH || localX == PLOT_SIZE + ROAD_WIDTH - 1 ||
                        localZ == ROAD_WIDTH || localZ == PLOT_SIZE + ROAD_WIDTH - 1) {
                        chunk.setBlock(x, GROUND_HEIGHT, z, Material.QUARTZ_SLAB);
                    }
                }
            }
        }

        return chunk;
    }
}
