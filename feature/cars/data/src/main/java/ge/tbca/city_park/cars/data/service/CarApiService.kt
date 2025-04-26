package ge.tbca.city_park.cars.data.service

import ge.tbca.city_park.cars.data.model.AddCarRequest
import ge.tbca.city_park.cars.data.model.CarDTO
import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.data.util.UserId
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CarApiService {

    @UserId
    @GET(CARS)
    suspend fun getCars(): BaseResponse<List<CarDTO>>

    @UserId
    @POST(CARS)
    suspend fun addCar(@Body body: AddCarRequest): BaseResponse<CarDTO>

    @UserId
    @DELETE(CARS_DELETE)
    suspend fun deleteCar(@Path("id") id:Int): BaseResponse<Boolean>

    companion object{
        private const val CARS = "users/cars"
        private const val CARS_DELETE = "users/cars/{id}"
    }
}