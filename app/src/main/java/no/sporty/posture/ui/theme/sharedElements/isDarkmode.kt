package no.sporty.posture.ui.theme.sharedElements

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable

@Composable
fun isDarkmode() = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES