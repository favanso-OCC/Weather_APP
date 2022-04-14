package com.raiders.theweatherology

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SecondViewModel : ViewModel() {
    private val API: String = "574767b600e526d124c0cdaa69212b76"

    //private val APIclose:String = BuildConfig.KEY
    //Variables for Day Forecast
    private var temp: MutableLiveData<Double> = MutableLiveData()
    private var date: MutableLiveData<String> = MutableLiveData()
    private var wind: MutableLiveData<Double> = MutableLiveData()
    private var humidity: MutableLiveData<Int> = MutableLiveData()
    private var feelsLike: MutableLiveData<Double> = MutableLiveData()
    private var minTemp: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp: MutableLiveData<Double> = MutableLiveData()
    private var mainDescription: MutableLiveData<String> = MutableLiveData()
    private var iconWeather: MutableLiveData<String> = MutableLiveData()

    //Variables for 5-Day Forecast
    private var temp1: MutableLiveData<Double> = MutableLiveData()
    private var minTemp1: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp1: MutableLiveData<Double> = MutableLiveData()
    private var temp2: MutableLiveData<Double> = MutableLiveData()
    private var minTemp2: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp2: MutableLiveData<Double> = MutableLiveData()
    private var temp3: MutableLiveData<Double> = MutableLiveData()
    private var minTemp3: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp3: MutableLiveData<Double> = MutableLiveData()
    private var temp4: MutableLiveData<Double> = MutableLiveData()
    private var minTemp4: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp4: MutableLiveData<Double> = MutableLiveData()
    private var temp5: MutableLiveData<Double> = MutableLiveData()
    private var minTemp5: MutableLiveData<Double> = MutableLiveData()
    private var maxTemp5: MutableLiveData<Double> = MutableLiveData()
    private var date1: MutableLiveData<String> = MutableLiveData()
    private var date2: MutableLiveData<String> = MutableLiveData()
    private var date3: MutableLiveData<String> = MutableLiveData()
    private var date4: MutableLiveData<String> = MutableLiveData()
    private var date5: MutableLiveData<String> = MutableLiveData()

    //Getters
    fun getTemp(): MutableLiveData<Double> {
        return temp
    }

    fun getMinTemp1(): MutableLiveData<Double> {
        return minTemp1
    }

    fun getMinTemp2(): MutableLiveData<Double> {
        return minTemp2
    }

    fun getMinTemp3(): MutableLiveData<Double> {
        return minTemp3
    }

    fun getMinTemp4(): MutableLiveData<Double> {
        return minTemp4
    }

    fun getMinTemp5(): MutableLiveData<Double> {
        return minTemp5
    }

    fun getMaxTemp1(): MutableLiveData<Double> {
        return maxTemp1
    }

    fun getMaxTemp2(): MutableLiveData<Double> {
        return maxTemp2
    }

    fun getMaxTemp3(): MutableLiveData<Double> {
        return maxTemp3
    }

    fun getMaxTemp4(): MutableLiveData<Double> {
        return maxTemp4
    }

    fun getMaxTemp5(): MutableLiveData<Double> {
        return maxTemp5
    }

    fun getDate1(): MutableLiveData<String> {
        return date1
    }

    fun getDate2(): MutableLiveData<String> {
        return date2
    }

    fun getDate3(): MutableLiveData<String> {
        return date3
    }

    fun getDate4(): MutableLiveData<String> {
        return date4
    }

    fun getDate5(): MutableLiveData<String> {
        return date5
    }

    fun getDate(): MutableLiveData<String> {
        return date
    }

    fun getWind(): MutableLiveData<Double> {
        return wind
    }

    fun getHumidity(): MutableLiveData<Int> {
        return humidity
    }

    fun getFeelsLike(): MutableLiveData<Double> {
        return feelsLike
    }

    fun getMinTemp(): MutableLiveData<Double> {
        return minTemp
    }

    fun getMaxTemp(): MutableLiveData<Double> {
        return maxTemp
    }

    fun getMainDescription(): MutableLiveData<String> {
        return mainDescription
    }

    fun getIconWeather(): MutableLiveData<String> {
        return iconWeather
    }

    //
    fun oneDayForecastForLocation(unit: String, lon: String, lat: String, queue: RequestQueue) {
        val urlOneDayForecastForLocation =
            "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$API&units=$unit"

        val responseOneDayForecast =
            StringRequest(Request.Method.GET, urlOneDayForecastForLocation, { response ->
                val obj = JSONObject(response)
                val main = obj.getJSONObject("main") //Temperature
                val today = obj.getLong("dt") //date and hour
                val windSpeed = obj.getJSONObject("wind")
                val weather = obj.getJSONArray("weather")
                val description = weather.getJSONObject(0)

                date.value = SimpleDateFormat("EEE,MMMM dd hh:mm a", Locale.ENGLISH).format(
                    Date(today * 1000)
                )
                temp.value = main.getDouble("temp")
                wind.value = windSpeed.getDouble("speed")
                humidity.value = main.getInt("humidity")
                feelsLike.value = main.getDouble("feels_like")
                minTemp.value = main.getDouble("temp_min")
                maxTemp.value = main.getDouble("temp_max")
                mainDescription.value = description.getString("main")
                var icon = description.getString("icon")
                iconWeather.value = "https://api.openweathermap.org/img/w/$icon.png"
            },
                Response.ErrorListener { }
            )
        queue.add(responseOneDayForecast)
    }

    //TO INCLUDE ON PARAMETERS THE SWITCH WITH CHOSEN METRIC
    fun oneDayForecast(unit: String, city: String, queue: RequestQueue) {
        val urlOneDayForecast =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&units=$unit&appid=$API"
        val responseOneDayForecast =
            StringRequest(Request.Method.GET, urlOneDayForecast, { response ->
                val obj = JSONObject(response)
                val main = obj.getJSONObject("main") //Temperature
                val today = obj.getLong("dt") //date and hour
                val windSpeed = obj.getJSONObject("wind")
                val weather = obj.getJSONArray("weather")
                val description = weather.getJSONObject(0)

                date.value = SimpleDateFormat("EEE,MMMM dd hh:mm a", Locale.ENGLISH).format(
                    Date(today * 1000)
                )
                temp.value = main.getDouble("temp")
                wind.value = windSpeed.getDouble("speed")
                humidity.value = main.getInt("humidity")
                feelsLike.value = main.getDouble("feels_like")
                minTemp.value = main.getDouble("temp_min")
                maxTemp.value = main.getDouble("temp_max")
                mainDescription.value = description.getString("main")
                iconWeather.value = description.getString("icon")
                var icon = description.getString("icon")
                iconWeather.value = "https://api.openweathermap.org/img/w/$icon.png"
            },
                Response.ErrorListener { }
            )
        queue.add(responseOneDayForecast)
    }


    //fivedaywithLocation
    fun fiveDayForecastForLocation(unit: String, lon: String, lat: String, queue: RequestQueue) {
        val urlFiveDayForecast =
            "https://api.openweathermap.org/data/2.5/forecast?lat=$lat&lon=$lon&appid=$API&units=$unit"

        val responseFiveDayForecast =
            StringRequest(Request.Method.GET, urlFiveDayForecast, { response ->

                val obj = JSONObject(response)
                val dayOne = obj.getJSONArray("list").getJSONObject(0)
                val main0 = dayOne.getJSONObject("main")
                temp1.value = main0.getDouble("temp")
                minTemp1.value = main0.getDouble("temp_min")
                maxTemp1.value = main0.getDouble("temp_max")
                val toDate0 = dayOne.getLong("dt")
                date1.value = SimpleDateFormat(
                    "EEE",
                    Locale.ENGLISH
                ).format(Date(toDate0 * 1000))

                val dayTwo = obj.getJSONArray("list").getJSONObject(8)
                val main8 = dayTwo.getJSONObject("main")
                temp2.value = main8.getDouble("temp")
                minTemp2.value = main8.getDouble("temp_min")
                maxTemp2.value = main8.getDouble("temp_max")
                val toDate8 = dayTwo.getLong("dt")
                date2.value = SimpleDateFormat(
                    "EEE",
                    Locale.ENGLISH
                ).format(Date(toDate8 * 1000))

                val dayThree = obj.getJSONArray("list").getJSONObject(16)
                val main16 = dayThree.getJSONObject("main")
                temp3.value = main16.getDouble("temp")
                minTemp3.value = main16.getDouble("temp_min")
                maxTemp3.value = main16.getDouble("temp_max")
                val toDate16 = dayThree.getLong("dt")
                date3.value = SimpleDateFormat("EEE", Locale.ENGLISH).format(
                    Date(
                        toDate16 * 1000
                    )
                )

                val dayFour = obj.getJSONArray("list").getJSONObject(24)
                val main24 = dayFour.getJSONObject("main")
                temp4.value = main24.getDouble("temp")
                minTemp4.value = main24.getDouble("temp_min")
                maxTemp4.value = main24.getDouble("temp_max")
                val toDate24 = dayFour.getLong("dt")
                date4.value = SimpleDateFormat("EEE", Locale.ENGLISH).format(
                    Date(
                        toDate24 * 1000
                    )
                )

                val dayFive = obj.getJSONArray("list").getJSONObject(32)
                val main32 = dayFive.getJSONObject("main")
                temp5.value = main32.getDouble("temp")
                minTemp5.value = main32.getDouble("temp_min")
                maxTemp5.value = main32.getDouble("temp_max")
                val toDate32 = dayFive.getLong("dt")
                date5.setValue(
                    SimpleDateFormat("EEE", Locale.ENGLISH).format(
                        Date(
                            toDate32 * 1000
                        )
                    )
                )
            },
                Response.ErrorListener { }
            )
        queue.add(responseFiveDayForecast)
    }


    fun fiveDayForecast(unit: String, city: String, queue: RequestQueue) {
        val urlFiveDayForecast =
            "https://api.openweathermap.org/data/2.5/forecast?q=$city&units=$unit&appid=$API"

        val responseFiveDayForecast =
            StringRequest(Request.Method.GET, urlFiveDayForecast, { response ->

                val obj = JSONObject(response)
                val dayOne = obj.getJSONArray("list").getJSONObject(0)
                val main0 = dayOne.getJSONObject("main")
                temp1.value = main0.getDouble("temp")
                minTemp1.value = main0.getDouble("temp_min")
                maxTemp1.value = main0.getDouble("temp_max")
                val toDate0 = dayOne.getLong("dt")
                date1.value = SimpleDateFormat(
                    "EEE",
                    Locale.ENGLISH
                ).format(Date(toDate0 * 1000))


                val dayTwo = obj.getJSONArray("list").getJSONObject(8)
                val main8 = dayTwo.getJSONObject("main")
                temp2.value = main8.getDouble("temp")
                minTemp2.value = main8.getDouble("temp_min")
                maxTemp2.value = main8.getDouble("temp_max")
                val toDate8 = dayTwo.getLong("dt")
                date2.value = SimpleDateFormat(
                    "EEE",
                    Locale.ENGLISH
                ).format(Date(toDate8 * 1000))

                val dayThree = obj.getJSONArray("list").getJSONObject(16)
                val main16 = dayThree.getJSONObject("main")
                temp3.value = main16.getDouble("temp")
                minTemp3.value = main16.getDouble("temp_min")
                maxTemp3.value = main16.getDouble("temp_max")
                val toDate16 = dayThree.getLong("dt")
                date3.value = SimpleDateFormat("EEE", Locale.ENGLISH).format(
                    Date(
                        toDate16 * 1000
                    )
                )

                val dayFour = obj.getJSONArray("list").getJSONObject(24)
                val main24 = dayFour.getJSONObject("main")
                temp4.value = main24.getDouble("temp")
                minTemp4.value = main24.getDouble("temp_min")
                maxTemp4.value = main24.getDouble("temp_max")
                val toDate24 = dayFour.getLong("dt")
                date4.value = SimpleDateFormat("EEE", Locale.ENGLISH).format(
                    Date(
                        toDate24 * 1000
                    )
                )

                val dayFive = obj.getJSONArray("list").getJSONObject(32)
                val main32 = dayFive.getJSONObject("main")
                temp5.value = main32.getDouble("temp")
                minTemp5.value = main32.getDouble("temp_min")
                maxTemp5.value = main32.getDouble("temp_max")
                val toDate32 = dayFive.getLong("dt")
                date5.setValue(
                    SimpleDateFormat("EEE", Locale.ENGLISH).format(
                        Date(
                            toDate32 * 1000
                        )
                    )
                )
            },
                Response.ErrorListener { }
            )
        queue.add(responseFiveDayForecast)
    }

}