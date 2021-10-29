

package com.example.pangestu

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.pangestu.databinding.ActivityMainBinding
import java.text.NumberFormat

/**
 * Activity that displays a tip calculator.
 */
class Fin195410015 : AppCompatActivity() {

    // Binding object instance with access to the views in the activity_main.xml layout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout XML file and return a binding object instance
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the content view of the Activity to be the root view of the layout
        setContentView(binding.root)

        // Setup a click listener on the calculate button to calculate the tip
        binding.calculateButton.setOnClickListener { calculateTip() }

        // Set up a key listener on the EditText field to listen for "enter" button presses
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    /**
     * Calculates the tip based on the user input.
     */
    private fun calculateTip() {
        // Get the decimal value from the cost of service EditText field
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        // If the cost is null or 0, then display 0 tip and exit this function early.
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        if (cost == null || cost == 0.0){
            displayTotal(0.0)
            return
        }
        if (cost == null || cost == 0.0){
            displayBiaya(0.0)
            return
        }

        // Get the tip percentage based on which radio button is selected
        val cicilan = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 12
            R.id.option_eighteen_percent -> 24
            else -> 36
        }

        // Calculate the tip
        val biayalayanan = cost * 0.05
        val bunga = cost * 0.0375
        val total = cost + (bunga * cicilan)
        val tip = total / cicilan




        // Display the formatted tip value onscreen
        displayTip(tip)
        displayTotal(total)
        displayBiaya(biayalayanan)

    }

    /**
     * Format the tip amount according to the local currency and display it onscreen.
     * Example would be "Tip Amount: $10.00".
     */
    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
    private fun displayTotal(total: Double){
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.totalResult.text = getString(R.string.total_amount, formattedTotal)
    }
    private fun displayBiaya(biayalayanan: Double){
        val formattedBiaya = NumberFormat.getCurrencyInstance().format(biayalayanan)
        binding.biayalayananResult.text = getString(R.string.biaya_amount, formattedBiaya)
    }

    /**
     * Key listener for hiding the keyboard when the "Enter" button is tapped.
     */
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}