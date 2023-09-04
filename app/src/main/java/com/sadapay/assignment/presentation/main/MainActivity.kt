package com.sadapay.assignment.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.accompanist.insets.ProvideWindowInsets
import com.sadapay.assignment.SadaPayAssignmentAppState
import com.sadapay.assignment.presentation.home.HomePageScreen
import com.sadapay.assignment.ui.theme.SadapayAssignmentTheme
import com.sadapay.assignment.utils.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SadapayAssignmentTheme {
                ProvideWindowInsets {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Scaffold { innerPadding ->
                            val appState = rememberAppState()
                            NavHost(
                                navController = appState.navController,
                                startDestination = Route.HOME,
                                modifier = Modifier.consumedWindowInsets(innerPadding)
                                    .padding(innerPadding).imePadding()
                            ) { zooziAppGraph(appState) }
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
) = remember(scaffoldState, navController) {
    SadaPayAssignmentAppState(scaffoldState, navController)
}

private fun NavGraphBuilder.zooziAppGraph(
    appState: SadaPayAssignmentAppState
) {
    composable(Route.HOME) {
        HomePageScreen(
            openAndPopUp = { route ->
                appState.clearAndNavigate(route)
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SadapayAssignmentTheme {

    }
}