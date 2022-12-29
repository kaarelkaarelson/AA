package coding_tasks;

import java.util.Arrays;

public class QueueArrayC {

    static private int front, rear, capacity;
    static private int queue[];

    QueueArrayC(int c) {
        front = rear = 0;
        capacity = c;
        queue = new int[capacity];
    }

    // function to insert an element
    // at the rear of the queue
    void queueEnqueue(int data) {
        // check queue is full or not
        if (capacity == rear) {
            System.out.printf("\nQueue is full\n");
            return;
        }

        // insert element at the rear
        else {
            queue[rear] = data;
            rear++;
        }
        System.out.println(Arrays.toString(queue));
        return;
    }

    // function to delete an element
    // from the front of the queue
    int queueDequeue() {
        // if queue is empty
        int first = -1;
        if (front == rear) {
            System.out.printf("\nQueue is empty\n");
            return -1;
        }
        // shift all the elements from index 2 till rear
        // to the right by one
        else {
            first = queue[0];

            for (int i = 0; i < rear - 1; i++) {
                queue[i] = queue[i + 1];
            }
            rear-=1;
            // store 0 at rear indicating there's no element
            if (rear < capacity)
                queue[rear] = 0;

            // decrement rear
//            rear--;
        }

        System.out.println(Arrays.toString(queue));
        return first;
    }

    boolean isQueueEmpty() {

        if (front == rear) {
            System.out.printf("\nQueue is empty\n");
            return true;
        }

        return false;
    }
}
