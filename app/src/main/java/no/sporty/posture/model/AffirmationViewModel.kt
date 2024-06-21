package no.sporty.posture.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.sporty.posture.model.manager.AffirmationManager

class AffirmationViewModel : ViewModel() {

    fun getAffirmations(callback: (String?) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        AffirmationManager.getAffirmations {
            callback(it)
        }
    }
}