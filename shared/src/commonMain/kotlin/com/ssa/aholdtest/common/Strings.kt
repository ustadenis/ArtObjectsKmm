package com.ssa.aholdtest.common

import dev.icerock.moko.resources.StringResource

expect class Strings {
    fun get(id: StringResource, args: List<Any>): String
}

expect fun getString(id: StringResource, args: List<Any> = emptyList()): String