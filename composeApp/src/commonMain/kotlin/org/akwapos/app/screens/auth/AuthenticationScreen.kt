package org.akwapos.app.screens.auth

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import compose.icons.TablerIcons
import compose.icons.tablericons.Eye
import compose.icons.tablericons.EyeOff
import org.akwapos.app.models.ComposeTextModel
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.screens.home.HomeScreen
import org.akwapos.app.theme.ClickableStyledText
import org.akwapos.app.theme.PixelDensity
import org.akwapos.app.theme.rememberPlatformOrientation
import org.akwapos.app.utils.toPercentage


object AuthenticationScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { AuthenticationScreenModel() }
        val platformOrientation = rememberPlatformOrientation()
        Scaffold {
            when (screenModel.authScreenState) {
                AuthenticationScreenModel.AuthScreenState.SIGN_IN -> {
                    SignInScreen(
                        modifier = Modifier.fillMaxSize().padding(PixelDensity.small),
                        orientation = platformOrientation
                    )
                }

                AuthenticationScreenModel.AuthScreenState.SIGN_UP -> {
                    SignUpScreen(
                        modifier = Modifier.fillMaxSize().padding(PixelDensity.small),
                        orientation = platformOrientation
                    )
                }
            }
        }
    }

    @Composable
    private fun SignUpScreen(modifier: Modifier = Modifier, orientation: PlatformOrientation) {
        val (width, _) = remember(orientation) {
            when (orientation) {
                is PlatformOrientation.LandScape -> orientation.width to orientation.height
                is PlatformOrientation.Portrait -> orientation.width to orientation.height
                is PlatformOrientation.Tablet -> orientation.width to orientation.height
            }
        }
        Box(modifier, contentAlignment = Alignment.Center) {
            when (orientation) {
                is PlatformOrientation.Portrait -> {
                    DisplaySignUp(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHigh,
                                RoundedCornerShape(5)
                            )
                            .padding(vertical = PixelDensity.large, horizontal = PixelDensity.large)
                    )
                }

                is PlatformOrientation.LandScape -> {
                    DisplaySignUp(
                        modifier = Modifier.width(PixelDensity.setValue(width.toPercentage(50)))
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHigh,
                                androidx.compose.foundation.shape.RoundedCornerShape(5)
                            )
                            .padding(PixelDensity.large)
                    )

                }

                is PlatformOrientation.Tablet -> {
                    DisplaySignUp(
                        modifier = Modifier.width(PixelDensity.setValue(width.toPercentage(70)))
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHigh,
                                androidx.compose.foundation.shape.RoundedCornerShape(5)
                            )
                            .padding(PixelDensity.large)
                    )
                }
            }
        }
    }

    @Composable
    private fun DisplaySignUp(modifier: Modifier) {
        val screenModel = rememberScreenModel { AuthenticationScreenModel() }
        val strength = remember(screenModel.password) { screenModel.getPasswordStrength(screenModel.password) }
        // Animate the bar width (percentage)
        val animatedStrength by animateFloatAsState(
            targetValue = strength.first,
            label = "PasswordStrengthAnimation"
        )

        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(PixelDensity.large)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = screenModel.name,
                onValueChange = { screenModel.name = it },
                label = { Text("Name", style = MaterialTheme.typography.bodySmall) },
                placeholder = { Text("John Doe", style = MaterialTheme.typography.bodySmall) },
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = screenModel.email,
                onValueChange = { screenModel.email = it },
                label = { Text("Email", style = MaterialTheme.typography.bodySmall) },
                placeholder = { Text("john@example.com", style = MaterialTheme.typography.bodySmall) },
            )

            Column(verticalArrangement = Arrangement.spacedBy(PixelDensity.small)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = screenModel.password,
                    onValueChange = { screenModel.password = it },
                    label = { Text("Password", style = MaterialTheme.typography.bodySmall) },
                    placeholder = { Text("XXXXXXXX", style = MaterialTheme.typography.bodySmall) },
                    visualTransformation = if (screenModel.isPasswordVisibile) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {screenModel.isPasswordVisibile = !screenModel.isPasswordVisibile}) {
                            Icon(
                                if (screenModel.isPasswordVisibile) TablerIcons.Eye else TablerIcons.EyeOff,
                                "password visibility"
                            )
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(PixelDensity.medium)
                        .clip(RoundedCornerShape(5))
                        .background(Color.LightGray)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedStrength)
                            .fillMaxHeight()
                            .background(strength.second)
                    )
                }
                Text("Password strength")
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = screenModel.confirmPassword,
                onValueChange = { screenModel.confirmPassword = it },
                label = { Text("Confirm Password", style = MaterialTheme.typography.bodySmall) },
                placeholder = { Text("XXXXXXXX", style = MaterialTheme.typography.bodySmall) },
                visualTransformation = if (screenModel.isPasswordVisibile) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {screenModel.isPasswordVisibile = !screenModel.isPasswordVisibile}) {
                        Icon(
                            if (screenModel.isPasswordVisibile) TablerIcons.Eye else TablerIcons.EyeOff,
                            "password visibility"
                        )
                    }
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5),
                onClick = {screenModel.authScreenState = AuthenticationScreenModel.AuthScreenState.SIGN_IN},
            ) {
                Text(
                    "Create Account",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            ClickableStyledText(
                texts = listOf(
                    ComposeTextModel(
                        text = "Already have an account? ",
                        textStyle = MaterialTheme.typography.bodyMedium
                    ),
                    ComposeTextModel(
                        text = "Sign in ",
                        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Green.copy(alpha = 0.7f)),
                        onClick = { screenModel.authScreenState = AuthenticationScreenModel.AuthScreenState.SIGN_IN }
                    ),
                )
            )
        }
    }

    @Composable
    private fun SignInScreen(modifier: Modifier = Modifier, orientation: PlatformOrientation) {
        val (width, _) = remember(orientation) {
            when (orientation) {
                is PlatformOrientation.LandScape -> orientation.width to orientation.height
                is PlatformOrientation.Portrait -> orientation.width to orientation.height
                is PlatformOrientation.Tablet -> orientation.width to orientation.height
            }
        }
        Box(modifier, contentAlignment = Alignment.Center) {
            when (orientation) {
                is PlatformOrientation.Portrait -> {
                    DisplaySignIn(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHigh,
                                RoundedCornerShape(5)
                            )
                            .padding(vertical = PixelDensity.large, horizontal = PixelDensity.large)
                    )
                }

                is PlatformOrientation.LandScape -> {
                    DisplaySignIn(
                        modifier = Modifier.width(PixelDensity.setValue(width.toPercentage(50)))
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHigh,
                                androidx.compose.foundation.shape.RoundedCornerShape(5)
                            )
                            .padding(PixelDensity.large)
                    )

                }

                is PlatformOrientation.Tablet -> {
                    DisplaySignIn(
                        modifier = Modifier.width(PixelDensity.setValue(width.toPercentage(70)))
                            .background(
                                MaterialTheme.colorScheme.surfaceContainerHigh,
                                androidx.compose.foundation.shape.RoundedCornerShape(5)
                            )
                            .padding(PixelDensity.large)
                    )
                }
            }
        }
    }

    @Composable
    private fun DisplaySignIn(modifier: Modifier) {
        val screenModel = rememberScreenModel { AuthenticationScreenModel() }
        val navigator = LocalNavigator.current
        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(PixelDensity.large)
        ) {
            Text("Sign in to your account", style = MaterialTheme.typography.titleMedium)
            Text("enter your email to begin", style = MaterialTheme.typography.bodySmall)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = screenModel.email,
                onValueChange = { screenModel.email = it },
                label = { Text("Email", style = MaterialTheme.typography.bodySmall) }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5),
                onClick = {navigator?.replace(HomeScreen)},
            ) {
                Text(
                    "Send Verification Code",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            ClickableStyledText(
                texts = listOf(
                    ComposeTextModel(
                        text = "No Account Yet? ",
                        textStyle = MaterialTheme.typography.bodyMedium
                    ),
                    ComposeTextModel(
                        text = "Register Here ",
                        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Green.copy(alpha = 0.7f)),
                        onClick = { screenModel.authScreenState = AuthenticationScreenModel.AuthScreenState.SIGN_UP }
                    ),
                )
            )
        }
    }
}