package jdbc;

public class Obstacle {
    private String obstacleId;

    public Obstacle(String obstacleId) {
        this.obstacleId = obstacleId;
    }

    public Obstacle() {
    }

    public String getObstacleId() {
        return obstacleId;
    }

    public void setObstacleId(String obstacleId) {
        this.obstacleId = obstacleId;
    }

    @Override
    public String toString() {
        return "Obstacle [obstacleId=" + obstacleId + "]";
    }
}
