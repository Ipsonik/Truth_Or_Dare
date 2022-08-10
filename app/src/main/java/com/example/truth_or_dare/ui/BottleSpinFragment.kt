package com.example.truth_or_dare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.truth_or_dare.R
import com.example.truth_or_dare.databinding.FragmentBottleSpinBinding
import java.util.*


class BottleSpinFragment : Fragment() {

    private lateinit var binding: FragmentBottleSpinBinding
    private lateinit var bottle: ImageView
    private var random = Random()
    private var lastDir = 0
    private var spinning: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bottle_spin,
            container,
            false
        )
        binding.bottleSpinFragment = this
        bottle = binding.bottleImage

        return binding.root
    }


    fun spin() {
        if (!spinning) {
            val newDir = random.nextInt(1800)
            val pivX = (bottle.width / 2).toFloat()
            val pivY = (bottle.height / 2).toFloat()

            val rotate = RotateAnimation(lastDir.toFloat(), newDir.toFloat(), pivX, pivY)

            rotate.duration = 2500
            rotate.fillAfter = true
            rotate.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    spinning = true
                }

                override fun onAnimationEnd(p0: Animation?) {
                    spinning = false
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

            })

            lastDir = newDir

            bottle.startAnimation(rotate)
        }
    }


}