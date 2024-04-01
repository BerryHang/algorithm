package handwriting.prefixTree;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Standard {
    Map<String, Integer> map =  new HashMap<>();

    public int insert(String s) {

        if (map.containsKey(s)) {
            map.put(s, map.get(s) + 1);
        } else {
            map.put(s, 1);
        }

        return map.get(s);
    }

    public int search(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        return 0;
    }

    public int preSearch(String s) {

        int count = 0;

        for (Map.Entry<String, Integer> entry:map.entrySet()){

            if (entry.getKey().startsWith(s)){
                count+=entry.getValue();
            }

        }
        return count;
    }

    public boolean delete(String s){

        if (StringUtils.isBlank(s)){
            return false;
        }

        if (!map.containsKey(s)){
            return false;
        }

        if (map.get(s)==1){
            map.remove(s);
        }else {
            map.put(s,map.get(s)-1);
        }

        return true;
    }

}

