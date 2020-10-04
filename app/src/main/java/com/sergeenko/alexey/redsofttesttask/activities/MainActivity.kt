package com.sergeenko.alexey.redsofttesttask.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sergeenko.alexey.redsofttesttask.R
import com.sergeenko.alexey.redsofttesttask.fragments.ProductListFragment


class MainActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)
        setProductListFragment()
    }

    fun setProductCardFragment(id: Int) {
        val fragment = ProductCardFragment.newInstance(id)
        supportFragmentManager.beginTransaction().apply {
            if(!fragment.isAdded) {
                add(R.id.fragment_placement, fragment)
                commit()
            }
        }
    }

    private fun setProductListFragment() {
        val fragment = ProductListFragment.newInstance()
        val trans = supportFragmentManager.beginTransaction()
        if(!fragment.isAdded) {
            trans.add(R.id.fragment_placement, fragment)
            trans.commit()
        }
    }
}
