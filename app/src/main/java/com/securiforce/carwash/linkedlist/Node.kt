package com.securiforce.carwash.linkedlist

data class Node<T : Any>(
    var value: T,
    var next: Node<T>? = null
){

    override fun toString(): String {
        return if (next != null){
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }

    fun printInReverse(){
        next?.printInReverse()

        if (next != null){
            print(" -> ")
        }

        print(value.toString())
    }
}