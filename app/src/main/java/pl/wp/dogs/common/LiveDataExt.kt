package pl.wp.dogs.common

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.newBuilder(newValue: T.() -> T) = newValue(this.value!!)
