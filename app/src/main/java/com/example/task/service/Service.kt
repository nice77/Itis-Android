package com.example.task.service

class Service {

    companion object {
        private var arr : Array<String?> = Array(3) {null}
        private var size = 0;

        fun addValue(value : String) {
            if (size >= arr.size) {
                arr[0] = arr[1]
                arr[1] = arr[2]
                arr[2] = value
            }
            else {
                arr[size] = value
                size++
            }
        }

        fun getValue(index : Int): String? {
            return arr[index]
        }
    }
}