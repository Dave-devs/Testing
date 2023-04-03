package com.dave_devs.testing.util

class Sample {

    fun isPalladium(input: String): Boolean {
        var i = 0
        var a = input.length -1 //hello-> 5
        var result = true
        while (i < a) {
            if (input[i] != input[a]) {
                result = false
                break
            }
            i++
            a--
        }
        return result
    }
}