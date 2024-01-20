import java.io.*;
import java.util.*;

class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        int rows = land.length;
        int cols = land[0].length;
        int currentId = 2; // 0: 오일 없음, 1: 오일 존재, >1 : 현재 땅 id(오일 영역)
        
        // 각 오일 영역에 대해 석유량 저장 => <오일 영역 id, id에 매장된 석유량>
        Map<Integer, Integer> oilMap = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (land[i][j] == 1) {
                    int area = dfs(land, i, j, currentId);
                    
                    oilMap.put(currentId, area);
                    currentId++;
                }
            }
        }
    
        // debug
        // for(int[] arr : land) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println(oilMap.get(2));
        
        // 각 열에서 최대 석유량 확인    
        int maxOil = 0;
        Set<Integer> visited = new HashSet<>();   // 열이미 방문한 오일 영역 id를 체크       
        for (int col = 0; col < cols; col++) {
            visited.clear(); // 열 기준 방문한 오일영역 초기화
            int currentOil = 0;
            for (int row = 0; row < rows; row++) {
                if (land[row][col] > 1 && !visited.contains(land[row][col])) { // 방문하지 않은 오일 영역에 속하는 곳만 체크
                    currentOil += oilMap.get(land[row][col]);
                    visited.add(land[row][col]);
                }
            }

            maxOil = Math.max(maxOil, currentOil);
        }
        
        return maxOil;
    }

    // dfs
    private static int dfs(int[][] land, int r, int c, int currentId) {
        // 범위체크 + 오일이 묻혀있지 않는 땅 체크
        if (r < 0 || r >= land.length || c < 0 || c >= land[0].length || land[r][c] != 1) {
            return 0;
        }

        // 재귀탄다
        land[r][c] = currentId; // 오일 영역으로 id 갱신
        int area = 1; // 면적 

        for (int i = 0; i < 4; i++) {
            area += dfs(land, r + dr[i], c + dc[i], currentId);
        }

        return area;
    }
}
