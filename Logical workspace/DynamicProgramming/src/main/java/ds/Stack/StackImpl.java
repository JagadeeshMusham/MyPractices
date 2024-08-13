package ds.Stack;

public class StackImpl {

    int max_size;
    int stack[];
    int curPos = 0;
    public StackImpl(int arraySize)
    {
        max_size = arraySize;
        stack =  new int[max_size];
    }

    public int push(int value) {
        if (curPos < max_size - 1){
            stack[curPos++] = value;
            return value;
        }

        return -1;
    }

    public int pop() {
        if (curPos != 0) {
            return stack[--curPos];
        }

        return -1;
    }

    public void display()
    {
        for(int interator = 0; interator < curPos; interator++) {
            System.out.println(stack[interator]);
        }
    }
}
