package studio.seno.singlelife

import kotlin.Result

interface LongTaskCallback<T> {
    fun onResponse(result: Result<Boolean>)
}