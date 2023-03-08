package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// A list of reward types
public class ListOfRewardTypes implements Writable {

    private ArrayList<RewardType> listOfRewardTypes;    // a list of reward types

    // Default reward types
    private RewardType aeroplan = new RewardType("Aeroplan", 2.1);
    private RewardType aventura = new RewardType("Aventura", 1.5);
    private RewardType amexRewards = new RewardType("AMEX Rewards", 1);
    private RewardType cashback = new RewardType("Cashback", 1);
    private RewardType sceneRewards = new RewardType("Scene Points", 1);
    private RewardType hsbcRewards = new RewardType("HSBC Points", 0.5);
    private RewardType bmoRewards = new RewardType("BMO Points", 0.7);

    // Effects: constructs a list of RewardTypes, and adds the default ones
    public ListOfRewardTypes(Boolean loadDefaults) {
        this.listOfRewardTypes = new ArrayList<RewardType>();
        if (loadDefaults) {
            loadDefaultRewardTypes();
        }
    }

    // Getter
    public ArrayList<RewardType> getListOfRewardTypes() {
        return listOfRewardTypes;
    }

    // Effects: Returns the reward type that has the given reward name, or returns null if the reward does not exist
    public RewardType getRewardType(String rewardName) {
        for (RewardType reward: listOfRewardTypes) {
            if (reward.getRewardName().equals(rewardName)) {
                return reward;
            }
        }
        return null;
    }

    // Effects: Returns true if the reward type that has the given reward name is in the list of reward names,
    //              otherwise returns false
    public boolean containsReward(String rewardName) {
        for (RewardType reward: listOfRewardTypes) {
            if (reward.getRewardName().equals(rewardName)) {
                return true;
            }
        }
        return false;
    }

    // Requires: reward must not be null
    // Modifies: this
    // Effects: if the given reward is not already in the list of rewards, it is added and the method returns true,
    //              but if the reward is already in the list it is not added and the method returns false
    public boolean addRewardType(RewardType reward) {
        if (containsReward(reward.getRewardName())) {
            return false;
        } else {
            listOfRewardTypes.add(reward);
            return true;
        }
    }

    // Requires: rewardName has a non-zero length
    // Modifies: this
    // Effects: if the reward with the given reward name is in the list of rewards, it is removed from the list
    //              and the method returns true. Returns false if there is no reward matching the given reward name.
    public boolean removeRewardType(String rewardName) {
        for (RewardType reward: listOfRewardTypes) {
            if (reward.getRewardName().equals(rewardName)) {
                listOfRewardTypes.remove(reward);
                return true;
            }
        }
        return false;
    }

    // Modifies: this
    // Effects: Adds the default reward types to the listOfRewardTypes
    private void loadDefaultRewardTypes() {
        listOfRewardTypes.add(aeroplan);
        listOfRewardTypes.add(aventura);
        listOfRewardTypes.add(amexRewards);
        listOfRewardTypes.add(cashback);
        listOfRewardTypes.add(sceneRewards);
        listOfRewardTypes.add(hsbcRewards);
        listOfRewardTypes.add(bmoRewards);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("reward types", rewardTypesToJson());
        return json;
    }

    // EFFECTS: returns reward types in this list of reward types as a JSON array
    private JSONArray rewardTypesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (RewardType reward : listOfRewardTypes) {
            jsonArray.put(reward.toJson());
        }

        return jsonArray;
    }

}
