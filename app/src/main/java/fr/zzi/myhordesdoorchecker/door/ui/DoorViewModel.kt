package fr.zzi.myhordesdoorchecker.door.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.zzi.myhordesdoorchecker.door.data.DoorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DoorViewModel : ViewModel() {

    companion object {
        const val CITY_LATITUDE = 47.2260781
        const val CITY_LONGITUDE = -1.6218336
        const val NB_FORECAST_DAY = 5
    }

    fun totot() {
        viewModelScope.launch(Dispatchers.IO) {
            val open = DoorRepository.isDoorOpen()
            Log.e("", "uiehfse open : $open")
        }

//    fun getForecast(): LiveData<List<DayItemData>> {
//        val result = MutableLiveData<List<DayItemData>>()
//
//        viewModelScope.launch(Dispatchers.IO) {
//            val forecastResult = WeatherRepository.getForecast(
//                CITY_LATITUDE,
//                CITY_LONGITUDE,
//                NB_FORECAST_DAY
//            )
//            val uiItemList = forecastResult.list.map {
//                DayItemData(
//                    formatDate(it.dt),
//                    buildImageURL(it.weather.first().icon),
//                    it.weather.first().main,
//                    it.weather.first().description
//                )
//            }
//            result.postValue(uiItemList)
//        }
//
//        return result
//    }
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    fun formatDate(timestamp: Long): String {
//        val date = Date(timestamp * 1000)
//        return SimpleDateFormat("dd/MM").format(date)
//    }
//
//    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
//    fun buildImageURL(iconName: String): String {
//        return "https://openweathermap.org/img/w/$iconName.png"
//    }

    }
}