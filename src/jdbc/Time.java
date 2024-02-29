package jdbc;

public class Time {
    private String timeId;
    private String timeSpan;

    public Time(String timeId, String timeSpan) {
        this.timeId = timeId;
        this.timeSpan = timeSpan;
    }

    public Time() {
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    @Override
    public String toString() {
        return "Time [timeId=" + timeId + ", timeSpan=" + timeSpan + "]";
    }
}
