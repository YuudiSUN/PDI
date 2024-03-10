package utils;

public enum MapElement {
    GRASS(1), FOREST(2), BRIDGE(3), RIVER(4), MOUNTAIN(5), MARSHLAND(6), TREASURE(7); // 添加更多地图元素

    private final int value;

    MapElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
