package studio.seno.singlelife.util

import kotlin.Result

interface LongTaskCallback<T> {
    fun onResponse(result: Result<Boolean>)
}