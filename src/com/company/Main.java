package com.company;


class Main
{
    public static void main ( String [] arguments )
    {
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<Integer>();
        System.out.println(lst.getTail());
        lst.addToBack(12);
        System.out.println(lst.removeFromFront());
        System.out.println(lst.getTail());
        System.out.println(lst.removeFromFront());
    }
}
