package jdbc;

public class Player {
    private String playerId;
    private String playerStatus;
    private String playerName;
    private int playerEnergyPoint;
    private int playerActionPoint;
    private int playerWeightbearing;
    private double playerMoney;
    private String professionId;

    public Player(String playerId, String playerStatus, String playerName, int playerEnergyPoint, int playerActionPoint,
            int playerWeightbearing, double playerMoney, String professionId) {
        this.playerId = playerId;
        this.playerStatus = playerStatus;
        this.playerName = playerName;
        this.playerEnergyPoint = playerEnergyPoint;
        this.playerActionPoint = playerActionPoint;
        this.playerWeightbearing = playerWeightbearing;
        this.playerMoney = playerMoney;
        this.professionId = professionId;
    }

    public Player() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerEnergyPoint() {
        return playerEnergyPoint;
    }

    public void setPlayerEnergyPoint(int playerEnergyPoint) {
        this.playerEnergyPoint = playerEnergyPoint;
    }

    public int getPlayerActionPoint() {
        return playerActionPoint;
    }

    public void setPlayerActionPoint(int playerActionPoint) {
        this.playerActionPoint = playerActionPoint;
    }

    public int getPlayerWeightbearing() {
        return playerWeightbearing;
    }

    public void setPlayerWeightbearing(int playerWeightbearing) {
        this.playerWeightbearing = playerWeightbearing;
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(double playerMoney) {
        this.playerMoney = playerMoney;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @Override
    public String toString() {
        return "Player [playerId=" + playerId + ", playerStatus=" + playerStatus + ", playerName=" + playerName
                + ", playerEnergyPoint=" + playerEnergyPoint + ", playerActionPoint=" + playerActionPoint
                + ", playerWeightbearing=" + playerWeightbearing + ", playerMoney=" + playerMoney + ", professionId="
                + professionId + "]";
    }
}
