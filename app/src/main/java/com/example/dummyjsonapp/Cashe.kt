import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    // دالة لحفظ نص في SharedPreferences
    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply() // يتم الحفظ بشكل غير متزامن
    }

    // دالة لاسترجاع نص من SharedPreferences
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // دالة لحفظ عدد صحيح
    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    // دالة لاسترجاع عدد صحيح
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    // دالة لحذف قيمة معينة
    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    // دالة لمسح كل البيانات المخزنة
    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
