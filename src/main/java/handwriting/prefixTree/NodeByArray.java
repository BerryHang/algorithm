package handwriting.prefixTree;

import org.apache.commons.lang3.StringUtils;

public class NodeByArray {

    //经过节点的数量
    int pass;

    //以当前节点结尾的数量
    int end;

    //子节点的信息
    NodeByArray[] child;

    public NodeByArray() {
        this.pass = 0;
        this.end = 0;
        this.child = new NodeByArray[26];
    }

    //插入一个字符串信息
    public int insert(String s) {

        if (StringUtils.isBlank(s)) {
            return 0;
        }

        //获取到根节点，这里默认为下级节点不为空才存在路径
        NodeByArray node = this;
        node.pass++;

        //将字符串转换为字符数组
        char[] charArray = s.toCharArray();

        for (int j = 0; j < charArray.length; j++) {

            //计算数组偏移位置因为默认全部默认使用小写作为用例，字符可以转换为数字，这里减去字符 a 可以当做偏移量
            int path = charArray[j] - 'a';

            //当目标位置上面没有值时，是首次出现此路径，进行新建添加
            if (node.child[path] == null) {
                node.child[path] = new NodeByArray();
            }
            //节点后移并路径计数累加
            node = node.child[path];
            node.pass++;

        }
        node.end++;
        return node.end;
    }

    //查询指定字符串存在的数量
    public int search(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }

        //获取根节点
        NodeByArray node = this;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int path = charArray[i] - 'a';
            if (node.child[path] == null) {
                return 0;
            }
            node = node.child[path];
        }
        return node.end;
    }

    //根据前缀查询满足条件的字符串个数
    public int preSearch(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }
        NodeByArray node = this;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int path = charArray[i] - 'a';
            if (node.child[path] == null) {
                return 0;
            }
            node = node.child[path];
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
        NodeByArray node = this;
        node.pass--;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int path = charArray[i] - 'a';
            //如果子节点为空了，证明后面全部为0，直接把后续节点置空
            if ((--node.child[path].pass) == 0) {
                node.child[path] = null;
                return true;
            }
            //移动到下一个节点
            node = node.child[path];
        }
        //结束节点减1
        node.end--;

        return true;
    }

}
