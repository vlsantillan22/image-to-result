package com.vlsantillan.imagetoresult.util

/**
 * Utility for parsing expressions
 *
 * Created by Vincent Santillan on 25/06/2022.
 */
object ParserExt {

    fun String.removeInvalidChar(): String {
        return this.replace(Regex("[^0-9.+/x*-]"), "").replace("x", "*")
    }
}