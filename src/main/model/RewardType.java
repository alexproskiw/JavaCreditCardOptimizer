package model;

public class RewardType {

    private String rewardName;
    private double value;

    public RewardType(String name, double value) {
        this.rewardName = name;
        this.value = value;
    }

    public String getRewardName() {
        return this.rewardName;
    }

    public double getRewardValue() {
        return this.value;
    }

    public void setRewardName(String name) {
        this.rewardName = name;
    }

    public void setRewardValue(double value) {
        this.value = value;
    }

}
