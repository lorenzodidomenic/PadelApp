package com.example.padel;

public class Match {

    public String player1;


    public String player2;
    public String player3;
    public String player4;

    public Match(){

    }
    public Match(String player1,String player2, String player3, String player4){
        player1 = player1;
        player2 = player2;
        player3 = player3;
        player4 = player4;
    }
    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
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
}
