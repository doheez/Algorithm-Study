package BOJ.구현.톱니바퀴;

import java.util.*;
import java.io.*;

class Gear {
//    Deque<Integer> dq = new ArrayDeque<>();
    List<Integer> dq = new LinkedList<>();
}

class HowToRotate {
    int target;
    int dir;

    HowToRotate(int target, int dir) {
        this.target = target;
        this.dir = dir;
    }
}

public class Main {
    static int toothNum = 8;
    static int gearNum = 4;

    static List<Gear> gears;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new ArrayList<>();

        for (int i = 1; i <= gearNum; i++) {
            String temp = br.readLine();
            Gear gear = new Gear();

            for (int j = 0; j < toothNum; j++) {
                gear.dq.add(Character.getNumericValue(temp.charAt(j)));
            }

            gears.add(gear);
        }

        int rotateNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < rotateNum; i++) {
            String[] temp = br.readLine().split(" ");

            int target = Integer.parseInt(temp[0]) - 1;
            int dir = Integer.parseInt(temp[1]);

            int tempDir = dir;
            List<HowToRotate> howToRotates = new LinkedList<>();

            howToRotates.add(new HowToRotate(target, dir));

            for (int j = target + 1; j < gearNum; j++) {
                Gear prevGear = gears.get(j-1);
                Gear currGear = gears.get(j);

                if (prevGear.dq.get(2) != currGear.dq.get(6)) {
                    tempDir *= -1;
                    howToRotates.add(new HowToRotate(j, tempDir));
                } else break;
            }

            tempDir = dir;

            for (int j = target - 1; j >= 0; j--) {
                Gear prevGear = gears.get(j+1);
                Gear currGear = gears.get(j);

                if (prevGear.dq.get(6) != currGear.dq.get(2)) {
                    tempDir *= -1;
                    howToRotates.add(new HowToRotate(j, tempDir));
                } else break;
            }

            for (HowToRotate how : howToRotates) {
                rotateGear(how.target, how.dir);
            }
        }

        int answer = 0;
        int plus = 1;

        for (Gear gear : gears) {
            if (gear.dq.get(0) == 1) answer += plus;

            plus *= 2;
        }

        System.out.println(answer);
    }

    private static void rotateGear(int target, int rotateDir) {
        Gear gear = gears.get(target);

        if (rotateDir == 1) {
            Integer removed = gear.dq.remove(toothNum-1);
            gear.dq.add(0, removed);
        } else if (rotateDir == -1) {
            Integer removed = gear.dq.remove(0);
            gear.dq.add(removed);
        }
    }
}
