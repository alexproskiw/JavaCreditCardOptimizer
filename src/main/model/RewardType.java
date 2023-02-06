package model;

public class RewardType {

    private String name;
    private double value;

    public RewardType(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getRewardName() {
        return this.name;
    }

    public double getRewardValue() {
        return this.value;
    }

    public void setRewardName(String name) {
        this.name = name;
    }

    public void setRewardValue(double value) {
        this.value = value;
    }

}
