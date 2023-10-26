package com.example.neswsapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.neswsapp.R
import com.example.neswsapp.ui.fragment.SettingsFragment
import com.example.neswsapp.databinding.ActivityCategoryBinding
import com.example.neswsapp.ui.fragment.CategoryFragment
import com.example.neswsapp.ui.fragment.NewsFragment
import com.example.neswsapp.ui.listner.OnNavigateNewsFragmentListner

class CategoryActivity : AppCompatActivity(),OnNavigateNewsFragmentListner {
    lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showToggleButton()
        onItemselect()
        showFragment(CategoryFragment())
        binding.newssearch.visibility = View.GONE
    }

    private fun showToggleButton() {
        var toggle = ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,R.string.open,R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = getColor(R.color.white)
        toggle.syncState()

    }

    private fun onItemselect(){
        binding.navigationDrawer.setNavigationItemSelectedListener {
            var item = it.itemId
            when(item){
                R.id.category->{
                    showFragment(CategoryFragment())
                    binding.titleToolbar.text = getString(R.string.appbar_category_title)
                    binding.drawer.close()
                    binding.newssearch.visibility = View.GONE
                    true
                }
                R.id.settings->{
                    showFragment(SettingsFragment())
                    binding.titleToolbar.text = getString(R.string.appbar_settings_title)
                    binding.drawer.closeDrawers()
                    binding.newssearch.visibility = View.GONE
                    true
                }
                else->false
            }

        }
    }

    private fun showFragment(fragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .commit()
    }



    override fun onNavigate(category: String) {
        showFragment(NewsFragment.newInstance(category))
        binding.titleToolbar.text = getString(R.string.nesws)
        binding.newssearch.visibility = View.VISIBLE
    }
}