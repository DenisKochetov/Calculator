//package com.example.calculator
package com.example.calculator.presentation.settings

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.R
import com.example.calculator.databinding.SettingsActivityBinding
import com.example.calculator.presentation.common.BaseActivity



class SettingsActivity: BaseActivity() {

//    private val viewBinding by viewBinding(SettingsActivityBinding::bind)


    private val viewModel by viewModels<SettingsViewModel>()

    private val viewBinding by viewBinding(SettingsActivityBinding::bind)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
//        val data = intent.getIntExtra(SETTINGS_RESULT_KEY, -1)
        Toast.makeText(this, "Назад", Toast.LENGTH_SHORT).show()

//        viewBinding.settingsBack.setOnClickListener{
          viewBinding.settingsBack.setOnClickListener{
            finish()
        }

        viewBinding.resultPanelContainer.setOnClickListener{
            showDialog()
        }
//        result_panel.setOnClickListener{
//            showDialog()
//        }
//        _resultPanelState.observe(this){state ->
//            result_panel.Description
//        }

    }

    override fun <T> viewModels(t: T): T {
        TODO("Not yet implemented")
    }

    private fun showDialog(){
        AlertDialog.Builder(this)
            .setTitle("Title")
//            .setMessage("Message")
            .setPositiveButton("Ок"){dialog, id ->}
            .setNegativeButton("Отмена"){dialog, id ->}
            .setSingleChoiceItems(R.array.result_panel_types, 1) {dialog, id ->
                Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
            }
            .show()
    }
}

enum class ResultPanelType{
    LEFT, RIGHT, HIDE
}
