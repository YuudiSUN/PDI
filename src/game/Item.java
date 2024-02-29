package game;

public class Item {

    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // 获取物品名称
    public String getName() {
        return name;
    }

    // 获取物品描述
    public String getDescription() {
        return description;
    }

    // 玩家与物品发生交互的方法
    public void interact(Explorer player) {
        // 在这里定义物品与玩家交互的逻辑
        // 例如：增加玩家的健康值、解锁新区域等
    }

    // 其他可能需要的方法和逻辑
}
