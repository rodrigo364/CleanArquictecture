package br.com.network

import br.com.core.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response


suspend fun <T : Any>  handleApi(
    execute : suspend () -> Response<T>
): Resource<T> {
    return try {
        val response = execute()
        val body = response.body()
        if(response.isSuccessful && body != null){
            Resource.Success(body)
        }else {
            Resource.Failure(message = Exception(response.message()))
        }
    }catch (e : HttpException){
        Resource.Failure(message = e)
    } catch (e: Exception){
        Resource.Failure(message = e)
    }
}
//suspend fun <T : Any>  handleApi(
//    execute : suspend () -> Response<T>
//): Resource<T> {
//    return try {
//        val response = execute()
//        val body = response.body()
//        if(response.isSuccessful && body != null){
//            Resource.Success(body)
//        }else {
//            Resource.Failure(message = Exception(response.message()))
//        }
//    }catch (e : HttpException){
//        Resource.Failure(message = e)
//    } catch (e: Exception){
//        Resource.Failure(e)
//    }
//}
//suspend fun <T : Any>  handleApi(
//    execute : suspend () -> Response<T>
//) = flow {
//     try {
//        val response = execute()
//        val body = response.body()
//        if(response.isSuccessful && body != null){
//            emit(Resource.Success( data =  body))
//        }else {
//            emit(Resource.Failure(message = Exception(response.message())))
//        }
//    }catch (e : HttpException){
//       emit(Resource.Failure(message = e))
//    } catch (e: Exception){
//        emit(Resource.Failure(e))
//    }
//}