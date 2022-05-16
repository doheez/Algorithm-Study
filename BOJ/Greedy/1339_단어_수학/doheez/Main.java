import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        HashMap<Character, Integer> alphabet = new HashMap<>(); // <알파벳, 자릿수> Map

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] term = new String[N];
        for (int i = 0; i < N; i++) {
            term[i] = br.readLine();

            // 단어의 각 알파벳이 가지는 자릿수 합을 저장
            for (int j = 0; j < term[i].length(); j++) {
                char key = term[i].charAt(j);
                int digit = (int) Math.pow(10, (term[i].length() - 1 - j));

                if (alphabet.containsKey(key)) { // 이미 알파벳이 존재하면 기존 자릿수에 새로운 자릿수 더하고
                    alphabet.replace(key, (alphabet.get(key) + digit));
                } else { // 알파벳이 없으면 새로운 <알파벳, 자릿수> 쌍 추가
                    alphabet.put(key, digit);
                }
            }
        }

        // 자릿수 합을 기준으로 내림차순 정렬
        ArrayList<Map.Entry<Character, Integer>> alphaList = new ArrayList<>(alphabet.entrySet());
        alphaList.sort(Map.Entry.comparingByValue(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2)
                    return -1;
                else if (o1.equals(o2))
                    return 0;
                else
                    return 0;
            }
        }));

        // 자릿수 합이 큰 알파벳부터 큰 숫자 부여
        int count = 0;
        int number = 9;
        for (Map.Entry<Character, Integer> e : alphaList) {
            count += number * e.getValue();
            number--;
        }

        // 출력
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
