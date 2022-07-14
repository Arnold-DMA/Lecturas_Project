package com.danp.lecturas_project.pager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp.lecturas_project.database.LecturasDataBase
import com.danp.lecturas_project.database.LecturasEntity
import kotlinx.coroutines.launch

class MainViewModel /*constructor(dbl: LecturasDataBase)*/: ViewModel() {

    private val repository = Repository()//(dbl)

    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getItems(nextPage, 10)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<LecturasEntity> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)