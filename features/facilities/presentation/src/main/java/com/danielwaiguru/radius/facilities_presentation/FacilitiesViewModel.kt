package com.danielwaiguru.radius.facilities_presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.radius.facilities_data.repository.FacilitiesRepository
import com.danielwaiguru.radius.facilities_domain.GetFacilitiesUseCase
import com.danielwaiguru.radius.facilities_presentation.models.FacilitiesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacilitiesViewModel @Inject constructor(
    private val facilitiesRepository: FacilitiesRepository,
    private val getFacilitiesUseCase: GetFacilitiesUseCase
) : ViewModel() {
    private val _facilitiesUIState: MutableStateFlow<FacilitiesUIState> =
        MutableStateFlow(FacilitiesUIState())
    val facilitiesUIState: StateFlow<FacilitiesUIState> get() = _facilitiesUIState.asStateFlow()

    init {
        getFacilities()
    }

    fun getFacilities() {
        viewModelScope.launch {
            getFacilitiesUseCase()
                .onStart {
                    _facilitiesUIState.update { currentState ->
                        currentState.copy(
                            isLoading = true
                        )
                    }
                }
                .catch { throwable ->
                    _facilitiesUIState.update { currentState ->
                        currentState.copy(
                            errorMessage = throwable.message,
                            isLoading = false
                        )
                    }
                }
                .collect { facilities ->
                    _facilitiesUIState.update { currentState ->
                        currentState.copy(
                            facilities = facilities,
                            errorMessage = null,
                            isLoading = false
                        )
                    }
                }
        }

    }

    fun selectOption(optionId: String, checked: Boolean) {
        viewModelScope.launch {
            val exclusions = facilitiesRepository.getFacilityExclusion()?.exclusions ?: emptyList()
            _facilitiesUIState.update { currentState ->
                currentState.copy(
                    facilities =
                    currentState.facilities.map { facility ->
                        facility.copy(
                            options = facility.options.map { option ->
                                if (option.id == optionId) {
                                    option.copy(
                                        isSelected = checked
                                    )
                                } else option
                            }
                        )
                    }
                )
            }
            _facilitiesUIState.update { currentState ->
                val disabledOptions = HashMap<String, String>()
                exclusions.map { exclusion ->
                    currentState.facilities.map { facility ->
                        if (
                            facility.facilityId == exclusion[0].facilityId &&
                            (facility.options.find {
                                it.id == exclusion[0].optionsId
                            }?.isSelected == true)
                        ) {
                            disabledOptions[exclusion[1].facilityId] = exclusion[1].optionsId
                        }
                    }
                }
                currentState.copy(
                    facilities =
                    currentState.facilities.map { facility ->
                        facility.copy(
                            options = facility.options.map { option ->
                                var updatedOption = option
                                disabledOptions.forEach {
                                    updatedOption = if (
                                        facility.facilityId == it.key && option.id == it.value
                                    ) {
                                        updatedOption.copy(isSelectable = false)
                                    } else {
                                        updatedOption.copy(isSelectable = true)
                                    }
                                }
                                updatedOption
                            }
                        )
                    }
                )
            }
        }

    }

}
