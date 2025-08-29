package org.akwapos.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import org.akwapos.app.core.LocalDatabaseManager
import org.akwapos.app.screens.auth.AuthenticationScreen
import org.akwapos.app.screens.home.HomeScreen
import org.akwapos.app.screens.home.SplashScreen
import org.akwapos.app.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
internal fun App() = AppTheme {
    var isVerified by rememberSaveable { mutableStateOf<Boolean?>(null) }
    LaunchedEffect(null) {
        LocalDatabaseManager.initializeDatabase()
        val db = LocalDatabaseManager.getAuthStore()
        isVerified = (db != null && db.isVerified && db.json.trim().isNotEmpty())
    }
    when (isVerified) {
        true -> Navigator(HomeScreen)
        false -> Navigator(AuthenticationScreen)
        else -> Navigator(SplashScreen)
    }
//    HomeScreen(Modifier.fillMaxSize())
}
//@Preview
//@Composable
//internal fun App() = AppTheme {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .windowInsetsPadding(WindowInsets.safeDrawing)
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = stringResource(Res.string.cyclone),
//            fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
//            style = MaterialTheme.typography.displayLarge
//        )
//
//        var isRotating by remember { mutableStateOf(false) }
//
//        val rotate = remember { Animatable(0f) }
//        val target = 360f
//        if (isRotating) {
//            LaunchedEffect(Unit) {
//                while (isActive) {
//                    val remaining = (target - rotate.value) / target
//                    rotate.animateTo(target, animationSpec = tween((1_000 * remaining).toInt(), easing = LinearEasing))
//                    rotate.snapTo(0f)
//                }
//            }
//        }
//
//        Image(
//            modifier = Modifier
//                .size(250.dp)
//                .padding(16.dp)
//                .run { rotate(rotate.value) },
//            imageVector = vectorResource(Res.drawable.ic_cyclone),
//            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
//            contentDescription = null
//        )
//
//        ElevatedButton(
//            modifier = Modifier
//                .padding(horizontal = 8.dp, vertical = 4.dp)
//                .widthIn(min = 200.dp),
//            onClick = { isRotating = !isRotating },
//            content = {
//                Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text(
//                    stringResource(if (isRotating) Res.string.stop else Res.string.run)
//                )
//            }
//        )
//
//        var isDark by LocalThemeIsDark.current
//        val icon = remember(isDark) {
//            if (isDark) Res.drawable.ic_light_mode
//            else Res.drawable.ic_dark_mode
//        }
//
//        ElevatedButton(
//            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
//            onClick = { isDark = !isDark },
//            content = {
//                Icon(vectorResource(icon), contentDescription = null)
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text(stringResource(Res.string.theme))
//            }
//        )
//
//        val uriHandler = LocalUriHandler.current
//        TextButton(
//            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
//            onClick = { uriHandler.openUri("https://github.com/terrakok") },
//        ) {
//            Text(stringResource(Res.string.open_github))
//        }
//    }
//}
