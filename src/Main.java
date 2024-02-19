import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> L1 = new ArrayList<>();
        ArrayList<String> L2 = new ArrayList<>();
        ArrayList<String> L3 = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String string = sc.nextLine();
        String[] inputs = string.split(" ");
        int m = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        int l = Integer.parseInt(inputs[2]);

        String[] str = line.split(" ");

        Queue<String> queue = new LinkedList<>();
        queue.add("");
        if (!str[0].equals("_")){
            L1.add("_");
            m--;
        }
        if(n == 1 && !str[0].equals("_")){
            while (l > 0){
                L3.add("_");
                l--;
            }
        }
        while (m > 0 || k > 0 || l > 0){
            String previousState = queue.poll();
            for (String s: str){
                String current = previousState + s;
                if (m > 0 && isL1(current)){
                    L1.add(current);
                    m--;
                }
                if (k > 0 && isL2(current, str)){
                    L2.add(current);
                    k--;
                }
                if (l > 0 && isL3(current, str)){
                    L3.add(current);
                    l--;
                }
                queue.add(current);
            }

        }
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        StringBuilder result3 = new StringBuilder();

        for (String s: L1){
            result1.append(s).append(" ");
        }
        for (String s: L2){
            result2.append(s).append(" ");
        }
        for (String s: L3){
            result3.append(s).append(" ");
        }
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
    static boolean isL1(String s){
        Map<String, Integer> table = new HashMap<>();
        String[] symbols = s.split("");
        for (String symbol: symbols){
            if (table.containsKey(symbol)){
                table.put(symbol, table.get(symbol) + 1);
            } else {
                table.put(symbol, 1);
            }
        }
        for (String key: table.keySet()){
            if (table.get(key) % 2 != 0){
                return false;
            }
        }
        return true;
    }
    static boolean isL2(String s, String[] alphabet){
        for (String symbol: alphabet){
            if (!s.contains(symbol)){
                return false;
            }
        }
        return true;

    }
    static boolean isL3(String s, String[] alphabet){
        int counter = 0;
        for (String symbol: alphabet){
            if (!s.contains(symbol)){
                counter++;
            }
            if (counter > 1){
                return false;
            }
        }
        return counter == 1;
    }

}
