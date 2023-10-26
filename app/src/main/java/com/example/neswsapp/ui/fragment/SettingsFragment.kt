package com.example.neswsapp.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModelProvider
import com.example.neswsapp.databinding.FragmentSettingsBinding
import com.example.neswsapp.ui.viewmodels.SettingsViewModel
import java.util.Locale

class SettingsFragment : Fragment() {
 lateinit var binding: FragmentSettingsBinding
 lateinit var settingsViewModel:SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        binding.langSpinner.onItemSelectedListener  = object :OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    1->{
                        var locale = Resources.getSystem().configuration.locales[0].language
                        changeAppLanguage(locale)
                    }
                    2->{ changeAppLanguage("en") }
                    3->{ changeAppLanguage("ar") }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
    private fun changeAppLanguage(locale:String){
        if(settingsViewModel.getLanguage() != locale){
            settingsViewModel.saveLanguage(locale)
            val locals = LocaleListCompat.create(Locale(locale))
            AppCompatDelegate.setApplicationLocales(locals)
        }
    }
}