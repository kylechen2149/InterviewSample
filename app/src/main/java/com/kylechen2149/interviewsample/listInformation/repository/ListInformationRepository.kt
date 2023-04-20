package com.kylechen2149.interviewsample.listInformation.repository

import android.content.Context
import com.kylechen2149.interviewsample.listInformation.model.ParkingAvailable
import com.kylechen2149.interviewsample.listInformation.model.ParkingInformation
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException

class ListInformationRepository {

    suspend fun getParkingJson(context: Context): List<ParkingInformation> {
        lateinit var jsonString: String
        val parkingList = mutableListOf<ParkingInformation>()
        val parkingAvailableList = mutableListOf<ParkingAvailable>()
        lateinit var combinedParkingList: List<ParkingInformation>
        withContext(IO) {
            try {
                //parsing TCMSV_alldesc.json
                context.assets.open("TCMSV_alldesc.json")
                    .bufferedReader()
                    .use { it.readText() }.also { jsonString = it }

                val obj = JSONObject(jsonString)
                val dataObj = obj.getJSONObject("data")
                val parkArray = dataObj.getJSONArray("park")
                for (i in 0 until parkArray.length()) {
                    val parkingDetail = parkArray.getJSONObject(i)
                    parkingList.add(
                        ParkingInformation(
                            parkingDetail.getString("id"),
                            parkingDetail.getString("name"),
                            parkingDetail.getString("address"),
                            parkingDetail.getInt("totalcar")
                        )
                    )
                }

                //parsing TCMSV_allavailable.json
                context.assets.open("TCMSV_allavailable.json")
                    .bufferedReader()
                    .use { it.readText() }.also { jsonString = it }
                val objAvailable = JSONObject(jsonString)
                val dataObjAvailable = objAvailable.getJSONObject("data")
                val parkArrayAvailable = dataObjAvailable.getJSONArray("park")

                var chargingNum = 0
                var standbyNum = 0
                for (i in 0 until parkArrayAvailable.length()) {
                    val parkingAvailableDetail = parkArrayAvailable.getJSONObject(i)
                    //get charging number
                    if (!parkingAvailableDetail.isNull("ChargeStation")) {
                        val objChargeStation = parkingAvailableDetail.getJSONObject("ChargeStation")
                        val objScoketStatusList = objChargeStation.getJSONArray("scoketStatusList")
                        for (j in 0 until objScoketStatusList.length()) {
                            val availableStatusDetail = objScoketStatusList.getJSONObject(j)
                            if (availableStatusDetail.getString("spot_status").equals("充電中"))
                                chargingNum++
                            if (availableStatusDetail.getString("spot_status").equals("待機中"))
                                standbyNum++
                        }
                    }

                    parkingAvailableList.add(
                        ParkingAvailable(
                            parkingAvailableDetail.getString("id"),
                            parkingAvailableDetail.getInt("availablecar"),
                            chargingNum,
                            standbyNum
                        )
                    )
                    chargingNum = 0
                    standbyNum = 0
                }

                //transform
                combinedParkingList = parkingList.flatMap { all ->
                    parkingAvailableList.filter { available -> all.id == available.id }
                        .map { available ->
                            ParkingInformation(
                                all.id,
                                all.name,
                                all.address,
                                all.totalCar,
                                available.availablecar,
                                available.charging,
                                available.standby
                            )
                        }
                }
            } catch (e: IOException) {
                Timber.e(e.message)
            }
        }
        return combinedParkingList
    }
}