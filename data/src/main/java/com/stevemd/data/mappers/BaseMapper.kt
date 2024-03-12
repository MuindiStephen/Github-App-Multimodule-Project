package com.stevemd.data.mappers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.stevemd.domain.utils.Result

interface BaseMapper<R,E>{
    fun mapFromApiResponse(type: R):E
}
fun<R,E> mapFromApiResponse(result: Flow<Result<R>>, mapper: BaseMapper<R, E>): Flow<Result<E>> {
    return result.map{
        when(it){
            is Result.Success-> Result.Success(mapper.mapFromApiResponse(it.data))
            is Result.Error->Result.Error(it.message,it.code)
            is Result.Loading -> Result.Loading(it.loading)
        }
    }
}