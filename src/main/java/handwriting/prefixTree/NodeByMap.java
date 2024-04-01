package handwriting.prefixTree;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NodeByMap {

    //经过节点的数量
    int pass;

    //以当前节点结尾的数量
    int end;

    //子节点的信息
    Map<Integer, NodeByMap> child;

    public NodeByMap() {
        this.pass = 0;
        this.end = 0;
        this.child = new HashMap<>();
    }

    public int insert(String s) {

        if (StringUtils.isBlank(s)) {
            return 0;
        }

        //获取到根节点，这里默认为下级节点不为空才存在路径
        NodeByMap node = this;
        node.pass++;

        //将字符串转换为字符数组
        char[] charArray = s.toCharArray();

        for (int j = 0; j < charArray.length; j++) {

            //计算数组偏移位置
            int path = charArray[j];

            //当目标位置上面没有值时，是首次出现此路径，进行新建添加
            if (!node.child.containsKey(path)) {
                node.child.put(path, new NodeByMap());
            }
            //节点后移并路径计数累加
            node = node.child.get(path);
            node.pass++;

        }
        node.end++;
        return node.end;
    }

    public int search(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }

        //获取根节点
        NodeByMap node = this;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int path = charArray[i];
            if (!node.child.containsKey(path)) {
                return 0;
            }
            node = node.child.get(path);
        }
        return node.end;
    }

    //根据前缀查询满足条件的字符串个数
    public int preSearch(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }
        NodeByMap node = this;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int path = charArray[i] ;
            if (!node.child.containsKey(path)) {
                return 0;
            }
            node = node.child.get(path);
        }

        //此处返回是的经过路径的数量，而不是结尾的数量
        return node.pass;
    }

    //删除指定字符串
    public boolean delete(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }

        if (search(s) <= 0) {
            return false;
        }
        NodeByMap node = this;
        node.pass--;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int path = charArray[i] ;
            //如果子节点为空了，证明后面全部为0，直接把后续节点置空
            if ((--node.child.get(path).pass) == 0) {
                node.child.remove(path);
                return true;
            }
            //移动到下一个节点
            node = node.child.get(path);
        }
        //结束节点减1
        node.end--;

        return true;
    }
}
