package com.GTx_CS1332xI;


class Main
{
    public static void main ( String [] arguments )
    {
        ArrayQueue<Integer> q = new ArrayQueue<Integer>();
        q.enqueue(0);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.dequeue();
        q.dequeue();
        q.enqueue(9);
        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);
        q.dequeue();
        q.dequeue();
        q.dequeue();
//        q.enqueue(13);
//        q.enqueue(14);
    }
}
