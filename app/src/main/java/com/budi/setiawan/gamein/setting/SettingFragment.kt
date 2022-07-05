package com.budi.setiawan.gamein.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.budi.setiawan.core.utils.NightMode
import com.budi.setiawan.gamein.R
import java.util.*

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_layout, rootKey)
        val listPreference = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        listPreference?.setOnPreferenceChangeListener { _, newValue ->
            updateTheme(NightMode.valueOf(newValue.toString().uppercase(Locale.getDefault())).value)
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean{
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}