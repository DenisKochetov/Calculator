//package com.example.calculator
package com.example.calculator.presentation.settings



import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.calculator.R
import com.example.calculator.presentation.common.BaseActivity
import kotlinx.android.synthetic.main.settings_activity.*





class SettingsActivity: BaseActivity() {

//    private val viewBinding by viewBinding(SettingsActivityBinding::bind)


    private val viewModel by viewModels<SettingsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
//        val data = intent.getIntExtra(SETTINGS_RESULT_KEY, -1)
        Toast.makeText(this, "Назад", Toast.LENGTH_SHORT).show()

        settingsBack.setOnClickListener{
            finish()
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
            .setMessage("Message")
            .setPositiveButton("Ok"){dialog, id ->}
            .setNegativeButton("Отмена"){dialog, id ->}
//            .setSingleChoiceItems(R.array.result_panel_types, 1){dialog, id ->
//                Toast.makeText(this, id.toString(), Toast.LENGTH_LONG)
//            }
            .show()
    }
}

enum class ResultPanelType{
    LEFT, RIGHT, HIDE
}
