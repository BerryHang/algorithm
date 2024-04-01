package handwriting.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T> {

    //使用集合实现堆，底层也是数组
    private ArrayList<T> heap;

    //使用map存储逆向数据 key 是元素   Value 是元素在 heap 中的位置
    private HashMap<T, Integer> indexMap;

    //当前堆的大小
    private int heapSize;

    //构建堆时的比较掐
    private Comparator<? super T> comp;

    //加强堆的构造方法
    public HeapGreater(Comparator<? super T> comp) {
        heap = new ArrayList<>();
        heapSize = 0;
        indexMap = new HashMap<>();
        this.comp = comp;
    }

    //判断堆是否为空
    public boolean isEmpty() {
        return heapSize == 0;
    }

    //获取堆元素数量
    public int size() {
        return heapSize;
    }

    //判断堆中是否存在某个元素
    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    //查看堆顶元素
    public T peek() {
        return heap.get(0);
    }

    //弹出堆顶元素
    public T pop() {

        T t = heap.get(0);
        swap(0, --heapSize);
        heapify(0);
        indexMap.remove(t);
        heap.remove(heapSize);
        return t;
    }

    //向堆中添加元素
    public void push(T t) {
        heap.add(t);
        indexMap.put(t, heapSize);
        heapInsert(heapSize++);
    }

    //对指定元素重新拍
    public void resign(T t) {
        heapInsert(indexMap.get(t));
        heapify(indexMap.get(t));
    }

    //从堆中移除指定元素
    public void remove(T t) {

        //从map中移除元素
        Integer index = indexMap.remove(t);

        //获取最后一个元素
        T replace = heap.get(--heapSize);
        //在堆中移除最后一个元素
        heap.remove(heapSize);

        //判断需要移除的元素是否为最后一个，如果是不需要任何操作
        if (t != replace) {
            //将最后一个元素覆盖到要移除元素的位置上
            heap.set(index, replace);

            //修改覆盖元素的位置
            indexMap.put(replace, index);

            //重新调整堆的排序
            resign(replace);
        }
    }

    //自下而上进行调整堆
    public void heapInsert(int index) {

        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    //自上而下进行调整堆
    public void heapify(int index) {

        int leftIndex = index * 2 + 1;

        while (leftIndex < heapSize) {
            int rightIndex = leftIndex + 1;

            int best = rightIndex < heapSize && comp.compare(heap.get(leftIndex + 1), heap.get(leftIndex)) < 0 ? leftIndex + 1 : leftIndex;

            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;

            if (best == index) {
                break;
            }
            swap(index, best);
            index = best;
            leftIndex = index * 2 + 1;
        }

    }

    //获取堆中的所有元素
    public List<T> getAllElements() {

        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

    //交换两个数字
    public void swap(int index1, int index2) {

        //从堆中取出两个元素
        T t1 = heap.get(index1);
        T t2 = heap.get(index2);

        //交换数字的同时更新数字的新位置
        indexMap.put(t1, index2);
        indexMap.put(t2, index1);

        //重新设置堆中的位置
        heap.set(index1, t2);
        heap.set(index2, t1);
    }

}
