package day_0113_2024;

import java.io.*;
import java.util.*;

public class BJ_14439_자원캐기 {
	static int R, C;
    static int[][] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new int[R + 1][C + 1];
        dp = new int[R + 1][C + 1];
        
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                // 오른쪽 또는 아래쪽으로 한 칸 이동 => 현 위치 기준 위쪽, 왼쪽만 검사
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        System.out.println(dp[R][C]);
    }
}
