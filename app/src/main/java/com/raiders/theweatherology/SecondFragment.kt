package com.raiders.theweatherology

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SecondViewModel::class.java]

        //5day Forecast Observers
        val tempObserver =
            Observer<Double> { temp -> textTemp.text = ("%.0f" + "\u00B0").format(temp) }
        viewModel.getTemp().observe(viewLifecycleOwner, tempObserver)

        val tempObserver1MinTemp =
            Observer<Double> { temp1 -> tempMin1.text = ("%.0f" + "\u00B0").format(temp1) }
        viewModel.getMinTemp1().observe(viewLifecycleOwner, tempObserver1MinTemp)
        val tempObserver1MaxTemp =
            Observer<Double> { temp1Max -> tempMax1.text = ("%.0f" + "\u00B0").format(temp1Max) }
        viewModel.getMaxTemp1().observe(viewLifecycleOwner, tempObserver1MaxTemp)

        val tempObserver2MinTemp =
            Observer<Double> { temp2 -> tempMin2.text = ("%.0f" + "\u00B0").format(temp2) }
        viewModel.getMinTemp2().observe(viewLifecycleOwner, tempObserver2MinTemp)
        val tempObserver2MaxTemp =
            Observer<Double> { temp2Max -> tempMax2.text = ("%.0f" + "\u00B0").format(temp2Max) }
        viewModel.getMaxTemp2().observe(viewLifecycleOwner, tempObserver2MaxTemp)

        val tempObserver3MinTemp =
            Observer<Double> { temp3 -> tempMin3.text = ("%.0f" + "\u00B0").format(temp3) }
        viewModel.getMinTemp3().observe(viewLifecycleOwner, tempObserver3MinTemp)
        val tempObserver3MaxTemp =
            Observer<Double> { temp3Max -> tempMax3.text = ("%.0f" + "\u00B0").format(temp3Max) }
        viewModel.getMaxTemp3().observe(viewLifecycleOwner, tempObserver3MaxTemp)

        val tempObserver4MinTemp =
            Observer<Double> { temp4 -> tempMin4.text = ("%.0f" + "\u00B0").format(temp4) }
        viewModel.getMinTemp4().observe(viewLifecycleOwner, tempObserver4MinTemp)
        val tempObserver4MaxTemp =
            Observer<Double> { temp4Max -> tempMax4.text = ("%.0f" + "\u00B0").format(temp4Max) }
        viewModel.getMaxTemp4().observe(viewLifecycleOwner, tempObserver4MaxTemp)

        val tempObserver5MinTemp =
            Observer<Double> { temp5 -> tempMin5.text = ("%.0f" + "\u00B0").format(temp5) }
        viewModel.getMinTemp5().observe(viewLifecycleOwner, tempObserver5MinTemp)
        val tempObserver5MaxTemp =
            Observer<Double> { temp5Max -> tempMax5.text = ("%.0f" + "\u00B0").format(temp5Max) }
        viewModel.getMaxTemp5().observe(viewLifecycleOwner, tempObserver5MaxTemp)

        val dateObserver1 = Observer<String> { date1 -> dayOne.text = date1.toString() }
        viewModel.getDate1().observe(viewLifecycleOwner, dateObserver1)

        val dateObserver2 = Observer<String> { date2 -> dayTwo.text = date2.toString() }
        viewModel.getDate2().observe(viewLifecycleOwner, dateObserver2)

        val dateObserver3 = Observer<String> { date3 -> dayThree.text = date3.toString() }
        viewModel.getDate3().observe(viewLifecycleOwner, dateObserver3)

        val dateObserver4 = Observer<String> { date4 -> dayFour.text = date4.toString() }
        viewModel.getDate4().observe(viewLifecycleOwner, dateObserver4)

        val dateObserver5 = Observer<String> { date5 -> dayFive.text = date5.toString() }
        viewModel.getDate5().observe(viewLifecycleOwner, dateObserver5)

        //One Day Forecast Observers
        //Metric: meter/sec, Imperial: miles/hour.
        val windObserver =
            Observer<Double> { wind -> TextWind.text = ("%.2f" + " m/s").format(wind) }
        viewModel.getWind().observe(viewLifecycleOwner, windObserver)

        val humidityObserver = Observer<Int>
        { humidity -> textHumidity.text = "$humidity %" }
        viewModel.getHumidity().observe(viewLifecycleOwner, humidityObserver)

        val feelsLikeObserver = Observer<Double>
        { feelsLike -> textFeelsLike.text = ("%.0f" + "\u00B0").format(feelsLike) }
        viewModel.getFeelsLike().observe(viewLifecycleOwner, feelsLikeObserver)

        val minTempObserver = Observer<Double>
        { minTemp -> minTem.text = ("%.0f" + "\u00B0").format(minTemp) }
        viewModel.getMinTemp().observe(viewLifecycleOwner, minTempObserver)

        val maxTempObserver = Observer<Double>
        { maxTemp -> maxTem.text = ("%.0f" + "\u00B0").format(maxTemp) }
        viewModel.getMaxTemp().observe(viewLifecycleOwner, maxTempObserver)

        val iconObserver = Observer<String>{iconWeather->
            Picasso.with(context).load(iconWeather).resize(300, 300).into(iconView)
        }
        viewModel.getIconWeather().observe(viewLifecycleOwner,iconObserver)

        val mainDescriptionObserver = Observer<String>
        { mainDescription -> textMainDecription.text = mainDescription.toString() }
        viewModel.getMainDescription().observe(viewLifecycleOwner, mainDescriptionObserver)

        //Text City
        if(requireArguments().getString("cityName") == ""){
            textCity.text = "Your City"
        }else{
        textCity.text = arguments?.getString("cityName")}

        //Function getting info from first fragment and using Fun from Second Fragment
        val queue = Volley.newRequestQueue(context)
        arguments?.getString("cityName")?.let {
            viewModel.oneDayForecast("metric", it, queue)
        }

        val queue2 = Volley.newRequestQueue(context)
        arguments?.getString("cityName")?.let {
            viewModel.fiveDayForecast("metric", it, queue2)
        }

        //Lat and Long City
        val queue3 = Volley.newRequestQueue(context)
        val lat = arguments?.getString("lat")
        val long = arguments?.getString("long")
        let {
            if (long != null && lat!= null) {
                viewModel.oneDayForecastForLocation("metric", long, lat, queue3)
            }
        }

        val queue4 = Volley.newRequestQueue(context)
        let {
            if (long != null && lat!= null) {
                viewModel.oneDayForecastForLocation("metric", long, lat, queue4)
            }
        }

        //Unit Switch
        view?.findViewById<Switch>(R.id.unitMeasureSwitch)
            ?.setOnCheckedChangeListener { _, isChecked ->
                //ANOTHER IF TO CHECK IF IT HAS CITY OR LAT AND LONG
                if (isChecked) {
                    val queue = Volley.newRequestQueue(context)
                    arguments?.getString("cityName")?.let {
                        viewModel.oneDayForecast("imperial", it, queue)
                    }
                    val queue2 = Volley.newRequestQueue(context)
                    arguments?.getString("cityName")?.let {

                        viewModel.fiveDayForecast("imperial", it, queue2)

                    }
                } else {
                    val queue = Volley.newRequestQueue(context)
                    arguments?.getString("cityName")?.let {
                        viewModel.oneDayForecast("metric", it, queue)
                    }
                    val queue2 = Volley.newRequestQueue(context)
                    arguments?.getString("cityName")?.let {

                        viewModel.fiveDayForecast("metric", it, queue2)

                    }

                }
            }
    }
}











