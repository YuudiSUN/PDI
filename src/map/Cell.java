package map;
public class Cell {
	public enum TerrainType {
        GRASS, MOUNTAIN, SWAMP, FOREST, RIVER, TREASURE // 添加了森林地形类型
    }

    private TerrainType terrain;

    public Cell(TerrainType terrain) {
        this.terrain = terrain;
    }
    // Getter 和 Setter
    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

}
