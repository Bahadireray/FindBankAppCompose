package com.bahadireray.findbankapp.navigation


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import com.bahadireray.findbankapp.presentation.screens.detail.BankDetailScreen
import com.bahadireray.findbankapp.presentation.screens.detail.BankDetailViewModel
import com.bahadireray.findbankapp.presentation.screens.list.BankListScreen
import com.bahadireray.findbankapp.presentation.screens.list.BankListViewModel
import com.bahadireray.findbankapp.util.handleUiEvent

@ExperimentalTextApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
  navController: NavHostController,
) {
  NavHost(
    navController = navController,
    startDestination = Route.BankScreen.route
  ) {
    composable(route = Route.BankScreen.route) {
      val viewModel = hiltViewModel<BankListViewModel>()
      val uiState by viewModel.uiState.collectAsState()
      BankListScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        uiEvent = viewModel.uiEvent,
        onUiEvent = {
          it.handleUiEvent(navController)
        }
      )
    }

    composable(route = Route.BankDetailScreen.route) {
      val viewModel = hiltViewModel<BankDetailViewModel>()
      val bankInfo = navController.currentBackStackEntry?.savedStateHandle?.get<BankInfoEntity>("bankInfo")
      BankDetailScreen(
        viewModel =viewModel,
        bankInfo = bankInfo,
        onEvent = viewModel::onEvent,
        uiEvent = viewModel.uiEvent,
        onUiEvent = {
          it.handleUiEvent(navController)
        }
      )
    }
  }
}
