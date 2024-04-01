package handwriting.heap;

public class CustomHeap {

    //大根堆
    public class MaxHeap {

        //堆中的数据
        public int[] data;

        //堆当前的数字个数
        public int size;

        //堆允许的最大长度
        public int length;

        public void MaxHeap(int length) {

            this.data = new int[length];
            this.size = 0;
            this.length = length;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == length;
        }

        public boolean push(int value) {
            //当堆满时，不能添加数据
            if (length == size) {
                throw new RuntimeException("heap is full!");
            }
            //讲新加数据放到堆的最后
            data[size] = value;
            //进行从下往上调整
            return heapInsert(data, size++);
        }

        //弹出数据
        public int pop() {

            //当集合大小为0时，说明集合内没有数字
            if (size == 0) {
                throw new RuntimeException("heap is empty!");
            }

            //交换第一个和最后一个位置的数字
            swap(data, 0, --size);

            //对堆进行从上往下调整
            heapify(data, 0, size);

            //弹出堆最后一个元素的下一个元素
            return data[size];
        }

        //从index位置开始调整，直到调整到0位置，或者自己的父级不大于自己位置
        //注意：最后0位置时   (0-1)/2=0 这时会相等跳出循环
        public boolean heapInsert(int[] data, int index) {

            //判断自己的父级和自己谁大，当自己大时和父级交换位置
            while (data[(index - 1) / 2] < data[index]) {
                swap(data, index, (index - 1) / 2);
            }
            return true;
        }

        public void heapify(int[] data, int index, int last) {

            //计算当前节点的左子节点位置
            int leftChild = 2 * index + 1;

            //当前节点存在子节点时，进入循环
            while (leftChild < last) {
                //计算当前节点的右子节点位置
                int rigthChild = leftChild + 1;

                //取出子节点中较大数字的位置
                int bigIndex = rigthChild < last && data[rigthChild] > data[leftChild] ? rigthChild : leftChild;

                //较大的子节点与当前节点比较
                if (data[index] > data[bigIndex]) {
                    return;
                }

                //需要调整，交换当前节点和较大子节点的位置
                swap(data, index, bigIndex);

                //将当前节点的位置下移到较大的子节点为止
                index = bigIndex;

                //重新计算子左子节点的位置
                leftChild = 2 * index + 1;
            }
        }

        public void swap(int[] data, int index1, int index2) {
            int temp = data[index1];
            data[index1] = data[index2];
            data[index2] = temp;
        }
    }


}
