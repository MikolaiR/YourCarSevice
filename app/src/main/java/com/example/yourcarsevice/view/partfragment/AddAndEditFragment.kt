package com.example.yourcarsevice.view.partfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yourcarsevice.R
import com.example.yourcarsevice.databinding.FragmentAddAndEditBinding
import com.example.yourcarsevice.models.room.Part
import com.example.yourcarsevice.viewmodel.PartListFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class AddAndEditFragment : Fragment() {

    private val args: AddAndEditFragmentArgs by navArgs()
    private var part: Part = Part()
    private val partViewModel by viewModels<PartListFragmentViewModel>()
    private lateinit var fragmentAddEditBinding: FragmentAddAndEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddEditBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_and_edit, container, false)
        fragmentAddEditBinding.clickHandlers = AddAndEditFragmentClickHandlers()
        fragmentAddEditBinding.part = part
        return fragmentAddEditBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backendId = args.backendId
        Log.d("partUpdate", "UpdatePart: OK $backendId")
        if (backendId != "null") {
            GlobalScope.launch(Dispatchers.IO) {
                val partEdit = backendId.let { partViewModel.getPartBackendID(it) }
                part._id = partEdit._id
                part.backendId = partEdit.backendId
                part.partName = partEdit.partName
                part.partUpdateDate = partEdit.partUpdateDate
                part.carMillage = partEdit.carMillage
                part.price = partEdit.price
                part.isUpdate = true
                part.isSync = partEdit.isSync
            }
        }
    }

    inner class AddAndEditFragmentClickHandlers {
        fun onOkButtonClicked(view: View) {
            if (part.partName == null) {
                Toast.makeText(
                    context,
                    "Please input part name",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (!part.isUpdate) {
                    part.backendId = UUID.randomUUID().toString()
                    Log.d("partAdd", "onActivityResult: ${part.partName}")
                    partViewModel.addNewPart(part)
                } else {
                    Log.d("partUpdate", "isUpdate: ${part._id}-${part.backendId}-${part.partName}-${part.partUpdateDate}-${part.carMillage}-${part.price}-${part.isDelete}-${part.isUpdate}-${part.isSync} ")
                    partViewModel.updatePart(part)
                }
                findNavController().navigate(R.id.action_AddAndEditFragment_to_PartListFragment)
            }
        }
    }
}
