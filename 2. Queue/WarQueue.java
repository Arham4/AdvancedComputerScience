public class WarQueue {
    /**
     * Created by 148439 on 9/4/2018.
     */

    public String[] ringBuffer;
    int front = 0;
    int size = 0;

    WarQueue(String[] a )
    {
        ringBuffer = new String[a.length];
    }// create an empty ring buffer, with given max capacity
    int size()
    {
        return size;
    }// return number of items currently in the buffer
    boolean isEmpty()
    {
        return size() == 0;
    }// is the buffer empty (size equals zero)?
    boolean isFull()
    {
        return size() == ringBuffer.length;
    }
    // buffer full (size equals capacity)?
    void enqueue(String b)
    {
        if (isFull()) {
            return;
        }
        ringBuffer[(front + size()) % ringBuffer.length] = String.valueOf(b);
        size++;
    }// add item x to the end
    String dequeue()
    {
        if (isEmpty())
            return null;
        String pos = ringBuffer[front];
        front = (front + 1) % ringBuffer.length;
        size--;
        return pos;


    }// delete and return item from the front

    String peek()
    {
        if (isEmpty())
        {
            return  null;
        }
        return ringBuffer[front];
    }

    void empty() {
        ringBuffer = new String[ringBuffer.length];
        front = 0;
        size = 0;
    }
}