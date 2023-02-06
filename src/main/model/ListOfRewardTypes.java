package model;

import java.util.ArrayList;

public class ListOfRewardTypes {

    private ArrayList<RewardType> listOfRewardTypes;

    // Create default reward types
    private RewardType aeroplan = new RewardType("Aeroplan", 2.1);
    private RewardType aventura = new RewardType("Aventura", 1.5);
    private RewardType amexRewards = new RewardType("AMEX Rewards", 1);
    private RewardType cashback = new RewardType("Cashback", 1);
    private RewardType sceneRewards = new RewardType("Scene Points", 1);
    private RewardType hsbcRewards = new RewardType("HSBC Points", 0.5);
    private RewardType bmoRewards = new RewardType("BMO Points", 0.7);

    // Effects: constructs a list of RewardTypes, and loads some default types for the user
    public ListOfRewardTypes() {
        this.listOfRewardTypes = new ArrayList<RewardType>();
        loadDefaultRewardTypes();
    }

//    public void listAllRewardTypes() {
//        System.out.println("Your database of reward points and their values in cents per point (cpp) are as follows:");
//        for (RewardType reward: listOfRewardTypes) {
//            System.out.println("- " + reward.getRewardName() + ": " +  reward.getRewardValue() + " cpp");
//        }
//    }

    public boolean addRewardType(RewardType reward) {
        if (containsReward(reward.getRewardName())) {
            return false;
        } else {
            listOfRewardTypes.add(reward);
            return true;
        }
    }

    public boolean removeRewardType(String rewardName) {
        for (int i = 0; i < listOfRewardTypes.size(); i++) {
            if (listOfRewardTypes.get(i).getRewardName().equals(rewardName)) {
                listOfRewardTypes.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return listOfRewardTypes.size();
    }

    public RewardType getRewardType(String rewardName) {
        for (RewardType reward: listOfRewardTypes) {
            if (reward.getRewardName().equals(rewardName)) {
                return reward;
            }
        }
        return null;
    }

    public boolean containsReward(String rewardName) {
        for (int i = 0; i < listOfRewardTypes.size(); i++) {
            if (listOfRewardTypes.get(i).getRewardName().equals(rewardName)) {
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

}