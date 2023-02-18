package me.kofesst.vkservices.presentation.screens.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.kofesst.vkservices.domain.models.AppService
import me.kofesst.vkservices.domain.repositories.ServicesRepository
import me.kofesst.vkservices.domain.utils.RemoteResponse
import me.kofesst.vkservices.presentation.utils.RemoteState
import javax.inject.Inject

@HiltViewModel
class ServicesListViewModel @Inject constructor(
    private val servicesRepository: ServicesRepository,
) : ViewModel() {
    private val _listState = mutableStateOf<RemoteState<List<AppService>>>(
        value = RemoteState.Default()
    )
    val listState: State<RemoteState<List<AppService>>> = _listState

    init {
        fetchServices()
    }

    fun fetchServices() {
        viewModelScope.launch {
            _listState.value = RemoteState.Fetching()
            val response = servicesRepository.getAll()
            _listState.value = when (response) {
                is RemoteResponse.Error -> RemoteState.Failed(response.statusCode)
                is RemoteResponse.Ok -> {
                    if (!response.isSuccess || response.body == null) {
                        RemoteState.Failed(500)
                    } else {
                        RemoteState.Loaded(response)
                    }
                }
            }
        }
    }
}