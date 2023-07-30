package id.haaweejee.moviedbandroid.core.base

import id.haaweejee.moviedbandroid.core.exception.ApiErrorException
import id.haaweejee.moviedbandroid.core.exception.NoInternetConnectionException
import id.haaweejee.moviedbandroid.core.exception.UnexpectedErrorException
import id.haaweejee.moviedbandroid.core.wrapper.DataResource
import io.ktor.client.plugins.ClientRequestException
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(apiCall.invoke())
        } catch (ioException: IOException) {
            DataResource.Error(NoInternetConnectionException())
        } catch (exception: ClientRequestException) {
            DataResource.Error(
                ApiErrorException(
                    exception.response.status.description,
                    exception.response.status.value,
                ),
            )
        } catch (exception: Exception) {
            DataResource.Error(UnexpectedErrorException())
        }
    }
}
