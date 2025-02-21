package pl.wp.dogs.common.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal inline fun <reified T> navigationType(): Pair<KType, NavType<T>> {
    return typeOf<T>() to navType<T>()
}

private inline fun <reified T> navType(): NavType<T> {
    return object : NavType<T>(isNullableAllowed = true) {
        override fun get(bundle: Bundle, key: String): T? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: T): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
