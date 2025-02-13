import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kkkk.data.DateActivity
import com.example.kkkk.data.DateRepository
import java.util.Date

class DateViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DateRepository = DateRepository(application.applicationContext)

    private val _allDates = MutableLiveData<List<DateActivity>>()
    val allDates: LiveData<List<DateActivity>> = _allDates

    private val _selectedDate = MutableLiveData<DateActivity?>()
    val selectedDate: LiveData<DateActivity?> = _selectedDate

    init {
        refreshAllDates()
    }

    fun refreshAllDates() {
        _allDates.value = repository.getAllDates()
    }

    fun getDateById(id: Int) {
        try {
            val date = repository.getDateById(id)
            _selectedDate.value = date
        } catch (e: IllegalArgumentException) {
            _selectedDate.value = null
        }
    }

    fun updateDateDate(id: Int, dateDate: Date?) {
        val date = try {
            repository.getDateById(id)
        } catch (e: IllegalArgumentException) {
            null
        }

        date?.let {
            repository.saveDateDate(id, it.title, it.description, it.color, dateDate)
            refreshAllDates()

            // Rafraîchir la date sélectionnée si nécessaire
            if (_selectedDate.value?.id == id) {
                getDateById(id)
            }
        }
    }
}