package com.example.yourcarsevice.view.partfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yourcarsevice.R
import com.example.yourcarsevice.databinding.FragmentStatisticsBinding
import com.example.yourcarsevice.models.Statistic
import com.example.yourcarsevice.viewmodel.StatisticsPartViewModel

class StatisticsPartFragment : Fragment() {

    private lateinit var fragmentStatisticsBinding: FragmentStatisticsBinding
    private val statisticsPartViewModel by viewModels<StatisticsPartViewModel>()
    private var statistic = Statistic()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentStatisticsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false)
        fragmentStatisticsBinding.statics = statistic
        return fragmentStatisticsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statisticsPartViewModel.getParts().observe(viewLifecycleOwner, Observer {
            statistic.totalPrice = statisticsPartViewModel.getTotalAmount(it)
            statistic.costPerKilometer = statisticsPartViewModel.getCoastKilometerOfTravel(it)
            statistic.totalMillage = statisticsPartViewModel.getTotalMillage(it)
        })
    }
}