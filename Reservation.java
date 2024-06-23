package com.example.padel;

public class Reservation {

    public String type;
    public String player1,player2,player3,player4;
    public float skillPlayer1,skillPlayer2,skillPlayer3,skillPlayer4;
    public float softSkillPlayer1,softSkillPlayer2,softSkillPlayer3,softSkillPlayer4;
    public int numVote;

    public Reservation(){

    }
    public Reservation(String type, String player1, String player2, String player3, String player4,float skillPlayer1,float skillPlayer2,float  skillPlayer3,float skillPlayer4,int numVote,
                       float softSkillPlayer1,float softSkillPlayer2,float softSkillPlayer3, float softSkillPlayer4){
        this.type = type;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.skillPlayer1 = skillPlayer1;
        this.skillPlayer2 = skillPlayer2;
        this.skillPlayer3 = skillPlayer3;
        this.skillPlayer4 = skillPlayer4;
        this.numVote = numVote;
        this.softSkillPlayer1 = softSkillPlayer1;
        this.softSkillPlayer2 = softSkillPlayer2;
        this.softSkillPlayer3 = softSkillPlayer3;
        this.softSkillPlayer4 = softSkillPlayer4;

    }
    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public String getPlayer4() {
        return player4;
    }

    public void setPlayer4(String player4) {
        this.player4 = player4;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSkillPlayer1() {
        return skillPlayer1;
    }

    public void setSkillPlayer1(int skillPlayer1) {
        this.skillPlayer1 = ((skillPlayer1*numVote)+skillPlayer1)/(numVote+1);
    }

    public float getSkillPlayer2() {
        return skillPlayer2;
    }

    public void setSkillPlayer2(int skillPlayer2) {
        this.skillPlayer2 = ((skillPlayer2*numVote)+skillPlayer2)/(numVote+1);
    }

    public float getSkillPlayer3() {
        return skillPlayer3;
    }

    public void setSkillPlayer3(int skillPlayer3) {
        this.skillPlayer3 = ((skillPlayer3*numVote)+skillPlayer3)/(numVote+1);
    }

    public float getSkillPlayer4() {
        return skillPlayer4;
    }

    public void setSkillPlayer4(int skillPlayer4) {
        this.skillPlayer4 = ((skillPlayer4*numVote)+skillPlayer4)/(numVote+1);
    }

    public int getNumVote() {
        return numVote;
    }

    public void setNumVote(int numVote) {
        this.numVote = numVote;
    }

    public float getSoftSkillPlayer1() {
        return softSkillPlayer1;
    }

    public float getSoftSkillPlayer2() {
        return softSkillPlayer2;
    }

    public void setSoftSkillPlayer2(float softSkillPlayer2) {
        this.softSkillPlayer2 = softSkillPlayer2;
    }

    public float getSoftSkillPlayer3() {
        return softSkillPlayer3;
    }

    public void setSoftSkillPlayer3(float softSkillPlayer3) {
        this.softSkillPlayer3 = softSkillPlayer3;
    }

    public float getSoftSkillPlayer4() {
        return softSkillPlayer4;
    }

    public void setSoftSkillPlayer4(float softSkillPlayer4) {
        this.softSkillPlayer4 = softSkillPlayer4;
    }

    public void setSoftSkillPlayer1(float softSkillPlayer1) {
        this.softSkillPlayer1 = softSkillPlayer1;
    }
}
