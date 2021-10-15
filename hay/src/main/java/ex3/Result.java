package ex3;

import java.util.HashMap;
import java.util.List;

public class Result {

    /*
     * Complete the 'decode' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY codes
     *  2. STRING encoded
     */

    public static String decode(List<String> codes, String encoded) {
        var codeMap = new HashMap<String, Character>();
        for (var code : codes) {
            String[] split = code.split("\t");
            char value = split[0].equals("[newline]") ? '\n' : split[0].charAt(0);
            codeMap.put(split[1], value);
        }
        var decoded = new StringBuilder();
        var codeBuilder = new StringBuilder();
        for (var c : encoded.toCharArray()) {
            codeBuilder.append(c);
            String code = codeBuilder.toString();
            if (codeMap.containsKey(code)) {
                decoded.append(codeMap.get(code));
                codeBuilder.setLength(0);  // reset(erase) StringBuilder
            }
        }
        return decoded.toString();
    }

    public static void main(String[] args) {
        String result = decode(List.of("a\t100100", "b\t100101", "[newline]\t111111"), "100100111111100101");
        System.out.println(result);
    }
}
