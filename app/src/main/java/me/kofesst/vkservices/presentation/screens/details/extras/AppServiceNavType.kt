package me.kofesst.vkservices.presentation.screens.details.extras

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class AppServiceNavType : NavType<AppServiceExtra>(isNullableAllowed = true) {
    @Suppress("DEPRECATION")
    override fun get(bundle: Bundle, key: String): AppServiceExtra? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, AppServiceExtra::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): AppServiceExtra {
        return Gson().fromJson(value, AppServiceExtra::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: AppServiceExtra) {
        bundle.putParcelable(key, value)
    }
}