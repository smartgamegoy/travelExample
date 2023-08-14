package com.example.interviewapplication.ui.travel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.interviewapplication.data.Data
import com.example.interviewapplication.remote.TravelInterface
import com.example.interviewapplication.utils.Constants
import java.lang.Exception

class TravelPaging(val travelInterface: TravelInterface) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let{
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key?:1
        return try{
            val travelData = travelInterface.getAllTravels(Constants.language, page)
            LoadResult.Page(
                data = travelData.body()?.data!!,
                prevKey =
                if(page == 1)
                    null
                else
                    page - 1,
                nextKey =
                if(travelData.body()?.data?.isEmpty()!!)
                    null
                else
                    page + 1
            )
        }catch (error: Exception){
            LoadResult.Error(error)
        }
    }

}