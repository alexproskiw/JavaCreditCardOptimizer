package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a reward type having a reward name and reward value in cents per point (cpp)
public class RewardType implements Writable {

    private String rewardName;  // name of the reward
    private double value;       // value of 1 reward in cents per point (cpp)

    // Requires: rewardName has a non-zero length, and reward value must be >= 0
    // Effects: constructs a reward type with the given reward name and value in cents per point (cpp)
    public RewardType(String name, double value) {
        this.rewardName = name;
        this.value = value;
    }

    // Getters

    public String getRewardName() {
        return this.rewardName;
    }

    public double getRewardValue() {
        return this.value;
    }

    // Setters

    public void setRewardName(String name) {
        this.rewardName = name;
    }

    public void setRewardValue(double value) {
        this.value = value;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("reward name", rewardName);
        json.put("reward value (cpp)", value);
        return json;
    }

}
