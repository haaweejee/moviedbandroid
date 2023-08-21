package id.haaweejee.moviedbandroid.ui.component.molecules

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import id.haaweejee.moviedbandroid.ui.navigation.Navigation
import id.haaweejee.moviedbandroid.ui.navigation.model.NavigationItem
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                navigation = Navigation.HomeMovie,
            ),
            NavigationItem(
                title = "Account",
                icon = Icons.Default.AccountCircle,
                navigation = Navigation.Account,
            ),
        )
        BottomNavigation (
            backgroundColor = Blumine
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = FrostedMint,
                        )
                    },
                    label = {
                        Text(item.title, color = FrostedMint)
                    },
                    selected = currentRoute == item.navigation.route,
                    onClick = {
                        navController.navigate(item.navigation.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}
