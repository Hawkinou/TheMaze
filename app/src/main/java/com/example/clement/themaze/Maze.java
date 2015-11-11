package com.example.clement.themaze;

import android.util.Log;

/**
 * Created by clement on 13/10/2015.
 */
public class Maze {
    // x et y sont la tailles de la grille
    private int x;
    private int y;
    private int[][] grille;
    private int nbMaze;
    public Maze(int x, int y){
        this.x=x;
        this.y=y;
        grille=new int[x][y];
    }
    public Maze(int typeOfMaze){

        Log.e("TAGSAMERE typeofMaze:", ""+typeOfMaze);
        if(typeOfMaze==1){
            Log.e("TAGSAMERE:","Jerentre");
            createFirstMaze();
        }
        else if(typeOfMaze==2){
            createSecondMaze();
        }
    }

    private void createFirstMaze(){
        x=16;
        y=16;
        grille=new int[16][16];
        //0 noir, 1 blanc 2 entré 3 sortie
        for (int i =0;i<16;i++){
            if (i==7||i==8){
                grille[0][i]=1;
            }
            else
                grille[0][i]=0;
        }
        for (int i =0;i<16;i++){
            if (i==6||i==15||i==12){
                grille[1][i]=0;
                grille[2][i]=0;

            }
            else{
                grille[1][i]=1;
                grille[2][i]=1;
            }
        }
        for (int i =0;i<16;i++){
            if (i==4||i==5||i==10||i==11||i==13||i==14){
                grille[3][i]=1;
            }
            else
                grille[3][i]=0;
        }
        for (int i =0;i<16;i++){
            if (i==0||i==6||i==12||i==15){
                grille[4][i]=0;
                grille[5][i]=0;

            }
            else{
                grille[4][i]=1;
                grille[5][i]=1;
            }
        }
        for (int i =0;i<16;i++){
            if (i==1||i==2||i==8||i==7||i==13||i==14){
                grille[6][i]=1;
            }
            else
                grille[6][i]=0;
        }
        for (int i =0;i<16;i++){
            if (i==0||i==6||i==9||i==15){
                grille[7][i]=0;
                grille[8][i]=0;

            }
            else{
                grille[7][i]=1;
                grille[8][i]=1;
            }
        }
        for (int i =0;i<16;i++){
            if (i==0||i==3||i==6||i==9||i==12||i==15){
                grille[9][i]=0;
                grille[10][i]=0;
                grille[11][i]=0;
                grille[12][i]=0;
            }
            else{
                grille[9][i]=1;
                grille[10][i]=1;
                grille[11][i]=1;
                grille[12][i]=1;
            }
        }
        for (int i =0;i<16;i++){
            if (i==0||i==3||i==12||i==15){
                grille[13][i]=0;
                grille[14][i]=0;

            }
            else{
                grille[13][i]=1;
                grille[14][i]=1;
            }
        }
        for (int i =0;i<16;i++){
            grille[15][i]=0;
        }
        grille[1][0]= 2;
        grille[2][0]= 2;
        grille[0][7]= 3;
        grille[0][8]= 3;
    }

    private void createSecondMaze(){
        x=16;
        y=16;
        grille=new int[16][16];
        //0 noir, 1 blanc 2 entré 3 sortie
        for (int i =0;i<16;i++){
            if (i==7||i==8){
                grille[0][i]=1;
            }
            else{
                grille[0][i]=0;
                grille[i][0]=0;
                grille[7][i]=0;
                grille[i][7]=0;
            }
        }
        for(int i=1;i<7;i++){
            for(int j=1;j<7;j++){
                grille[i][j]=0;
            }
        }
        grille[8][0]=0;
        grille[9][0]=0;
        grille[1][2]=1;
        grille[2][2]=1;
        grille[3][1]=1;
        grille[3][3]=1;
        grille[4][4]=1;
        grille[5][3]=1;
        grille[6][4]=1;
        grille[6][5]=1;
        grille[6][6]=1;
        grille[5][7]=1;
        grille[4][6]=1;
        grille[3][7]=1;
        grille[3][8]=1;
        grille[4][9]=1;
        grille[4][10]=1;
        grille[3][11]=1;
        grille[4][12]=1;
        grille[5][12]=1;
        grille[6][11]=1;
        grille[7][10]=1;
        grille[8][11]=1;
        grille[9][10]=1;
        grille[10][11]=1;
        grille[11][11]=1;
        grille[12][10]=1;
        grille[11][9]=1;
        grille[12][8]=1;
        grille[11][7]=1;
        grille[12][6]=1;
        grille[12][5]=1;
        grille[12][4]=1;
        grille[11][3]=1;
        grille[12][2]=1;
        grille[11][1]=1;
        grille[10][1]=1;
        grille[9][1]=1;

        /*
        for (int i =0;i<8;i++){
            if (i==6||i==15||i==12){
                grille[1][i]=0;
                grille[2][i]=0;

            }
            else{
                grille[1][i]=1;
                grille[2][i]=1;
            }
        }*/
        grille[8][0]=3;
        grille[7][0]=3;
        grille[1][0]= 2;
        grille[2][0]= 2;
    }

    public void setNbMaze(int number){
        this.nbMaze=number;
        if(number==1){
            createFirstMaze();
        }
        else if(number==2){
            createSecondMaze();
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int[][] getGrille() {
        return grille;
    }

    public void setGrille(int[][] grille) {
        this.grille = grille;
    }

}
