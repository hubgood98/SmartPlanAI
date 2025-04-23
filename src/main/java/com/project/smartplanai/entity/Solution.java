package com.project.smartplanai.entity;

public class Solution {
    public int[] solution(String[] park, String[] routes){

        int rows = park.length;
        int cols = park[0].length();

        char[][] parkMap = new char[rows][cols];

        int x = 0,y = 0;

        for(int i=0;i<parkMap.length;i++){
            for(int j=0;j<rows;j++){
                parkMap[i][j] = park[i].charAt(j);

                //Start position init
                if(parkMap[i][j] == 'S'){
                    x = i;
                    y = j;
                }
            }
        }//for end

        for(String route : routes){
            String[] parts = route.split(" ");
            String dir = parts[0]; //방향
            int dist = Integer.parseInt(parts[1]); //distance

            int dx=0 ,dy=0;

            if (dir.equals("N")) dx = -1; //북
            else if (dir.equals("S")) dx = 1; //남
            else if (dir.equals("W")) dy = -1; //서
            else if (dir.equals("E")) dy = 1; //동

            int nx = x, ny = y;
            boolean canMove = true; //init

            for (int i = 0; i < dist; i++) {
                nx += dx;
                ny += dy;

                // 공원 벗어나거나 장애물 만나면 이동 실패구현
                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || parkMap[nx][ny] == 'X') {
                    canMove = false;
                    continue;
                }
            }

            if (canMove) {
                x = nx;
                y = ny;
            }
        }

        return new int[]{x, y};
    }



    public static void main(String[] args) {

    }
}
