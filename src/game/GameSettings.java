package game;
public class GameSettings {
    private static GameSettings instance;
    private String currentStrategy;

    private GameSettings() {
        // 初始化默认策略
        currentStrategy = "Radical"; // 默认策略
    }

    public static synchronized GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    public String getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentStrategy(String currentStrategy) {
        this.currentStrategy = currentStrategy;
    }
}
