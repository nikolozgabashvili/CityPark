package ge.tbca.city_park.fines.presentation.screen.fine_detail

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FineDetailViewModel @Inject constructor(

) : BaseViewModel<FineDetailState, FineDetailEffect, FineDetailEvent>(FineDetailState()) {

    override fun onEvent(event: FineDetailEvent) {

    }
}